package cn.starry.hub.features.parkour;

import cn.starry.hub.StarryLobby;
import cn.starry.hub.database.MongoDBManager;
import cn.starry.hub.utils.hologram.PacketHologram;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bson.Document;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.block.Block;

public class ParkourManager {

    @Getter
    private static ParkourManager instance;
    private final StarryLobby plugin;

    private final Map<UUID, Long> parkourStartTime = new ConcurrentHashMap<>();
    private final Map<UUID, Integer> parkourCheckpoint = new ConcurrentHashMap<>();
    private final Map<UUID, Long> bestTimes = new ConcurrentHashMap<>();
    private final Map<UUID, Map<Integer, Long>> bestSegmentTimes = new ConcurrentHashMap<>();
    private final Map<UUID, Long> startPlateCooldown = new ConcurrentHashMap<>();
    private final Map<UUID, Long> checkpointCooldown = new ConcurrentHashMap<>();
    private final Map<UUID, Long> lastCheckpointTime = new ConcurrentHashMap<>();
    private final Map<UUID, PacketHologram> playerStartHolograms = new ConcurrentHashMap<>();

    private Location startLocation;
    private final Map<Integer, Location> checkpoints = new HashMap<>();
    private final List<PacketHologram> holograms = new ArrayList<>();

    public ParkourManager(StarryLobby plugin) {
        instance = this;
        this.plugin = plugin;
        loadLocations();
        loadHolograms();
        registerPacketListener();
    }
    
    private void registerPacketListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Client.USE_ENTITY) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                int entityId = event.getPacket().getIntegers().read(0);
                
                // Check if entity ID belongs to any hologram
                boolean isHologram = false;
                
                // Check shared holograms
                for (PacketHologram holo : holograms) {
                    if (holo.getEntityIds().contains(entityId)) {
                        isHologram = true;
                        break;
                    }
                }
                
                // Check per-player start hologram
                if (!isHologram) {
                    PacketHologram startHolo = playerStartHolograms.get(player.getUniqueId());
                    if (startHolo != null && startHolo.getEntityIds().contains(entityId)) {
                        isHologram = true;
                    }
                }
                
                if (isHologram) {
                    // Handle interaction on main thread
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        player.sendMessage(CC.translate("&a是的！这就是跑酷挑战！"));
                    });
                }
            }
        });
    }

    public void loadLocations() {
        checkpoints.clear();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("parkour.checkpoints");
        if (section != null) {
            for (String key : section.getKeys(false)) {
                try {
                    int index = Integer.parseInt(key);
                    Location loc = (Location) section.get(key);
                    checkpoints.put(index, loc);
                } catch (NumberFormatException ignored) {}
            }
        }
        this.startLocation = (Location) plugin.getConfig().get("parkour.start");
    }

    public void saveLocations() {
        plugin.getConfig().set("parkour.start", startLocation);
        plugin.getConfig().set("parkour.checkpoints", null); // Clear old
        for (Map.Entry<Integer, Location> entry : checkpoints.entrySet()) {
            plugin.getConfig().set("parkour.checkpoints." + entry.getKey(), entry.getValue());
        }
        plugin.saveConfig();
        loadHolograms(); // Reload holograms when locations change
    }

    private void loadHolograms() {
        // Clear existing
        for (Player p : Bukkit.getOnlinePlayers()) {
            holograms.forEach(h -> h.destroy(p));
            if (playerStartHolograms.containsKey(p.getUniqueId())) {
                playerStartHolograms.get(p.getUniqueId()).destroy(p);
                playerStartHolograms.remove(p.getUniqueId());
            }
        }
        holograms.clear();
        playerStartHolograms.clear();

        // Start Location Hologram is now handled per-player in showHolograms
        // So we don't add it to the shared 'holograms' list.

        for (Map.Entry<Integer, Location> entry : checkpoints.entrySet()) {
            Location loc = entry.getValue().clone().add(0, 2, 0);
            List<String> lines = new ArrayList<>();
            if (entry.getKey() == checkpoints.size()) {
                lines.add(CC.translate("&e&l跑酷挑战"));
                lines.add(CC.translate("&c&l结束"));
            } else {
                lines.add(CC.translate("&e&l存档点"));
                lines.add(CC.translate("&b&l#" + entry.getKey()));
            }
            PacketHologram holo = new PacketHologram(loc, lines);
            holograms.add(holo);
        }
        
        for (Player p : Bukkit.getOnlinePlayers()) {
            showHolograms(p);
        }
    }
    
    public void showHolograms(Player player) {
        // h.destroy(player) was causing duplicate packets if not tracked, but shared holograms are safe to redraw if we clear them first? 
        // No, PacketHologram logic is simple.
        // Actually, if we call showHolograms multiple times, we are sending spawn packets again.
        // ProtocolLib might handle entity ID conflicts if we reuse IDs? No, we generate new IDs in PacketHologram constructor.
        // So shared holograms are recreated in loadHolograms(), so their IDs change on reload.
        // But if we just call showHolograms(player), we are re-sending the same IDs. 
        // If client already has them, it might be fine or glitchy.
        // Better to destroy first.
        holograms.forEach(h -> h.destroy(player)); 
        
        // Show shared holograms (Checkpoints)
        holograms.forEach(h -> h.display(player));
        
        // Show Start Hologram with Best Time
        if (startLocation != null) {
            // Destroy old start hologram for this player if exists
            if (playerStartHolograms.containsKey(player.getUniqueId())) {
                playerStartHolograms.get(player.getUniqueId()).destroy(player);
                playerStartHolograms.remove(player.getUniqueId());
            }

            Location holoLoc = startLocation.clone().add(0, 2, 0);
            List<String> lines = new ArrayList<>();
            lines.add(CC.translate("&e&l跑酷挑战"));
            
            if (bestTimes.containsKey(player.getUniqueId())) {
                String timeStr = formatTime(bestTimes.get(player.getUniqueId()));
                lines.add(CC.translate("&6&l你的最佳时间： &e&l" + timeStr));
            }
            
            lines.add(CC.translate("&a&l开始"));
            
            PacketHologram startHolo = new PacketHologram(holoLoc, lines);
            startHolo.display(player);
            playerStartHolograms.put(player.getUniqueId(), startHolo);
        }
    }
    
    public void startParkour(Player player) {
        startParkour(player, true);
    }

    public void startParkour(Player player, boolean teleport) {
        if (startLocation == null) {
            player.sendMessage(CC.translate("&c跑酷起点未设置！"));
            return;
        }
        
        // Reset player state
        if (teleport) {
            player.teleport(startLocation);
        }
        resetPlayerState(player);
        
        long now = System.currentTimeMillis();
        parkourStartTime.put(player.getUniqueId(), now);
        lastCheckpointTime.put(player.getUniqueId(), now);
        // Start action counts as reset CD
        startPlateCooldown.put(player.getUniqueId(), now);
        parkourCheckpoint.put(player.getUniqueId(), 0);
        
        // Items
        player.getInventory().setItem(4, new ItemBuilder(Material.HEAVY_WEIGHTED_PRESSURE_PLATE).name("&a回到记录点 &7(右键点击)").lore("&7回到你最后到达的记录点").build());
        player.getInventory().setItem(5, new ItemBuilder(Material.OAK_DOOR).name("&a重置时间 &7(右键点击)").lore("&7回到起点并重置时间").build());
        player.getInventory().setItem(6, new ItemBuilder(Material.RED_BED).name("&c取消跑酷 &7(右键点击)").lore("&7退出跑酷模式").build());
        player.getInventory().setItem(7, null); // Clear slot 8 (index 7)
        
        player.sendMessage(CC.translate("&a跑酷挑战已开始！"));
        player.sendMessage(CC.translate("&e使用 /parkour checkpoint 传送至最后你到达的记录点或使用 /parkour cancel 以取消跑酷！"));
    }

    public void resetParkour(Player player) {
        resetParkour(player, true, true);
    }
    
    public void resetParkour(Player player, boolean teleport, boolean sendMessage) {
        if (!isPlaying(player)) {
            player.sendMessage(CC.translate("&c你现在不在跑酷中。请使用 /parkour start"));
            return;
        }
        
        // Cooldown check for item/command usage too?
        // User asked for "Reset time and Checkpoint" to have 0.5s cooldown.
        // This method handles "Reset".
        long last = checkpointCooldown.getOrDefault(player.getUniqueId(), 0L);
        if (System.currentTimeMillis() - last < 500) {
            return; // Silent fail on cooldown for item spam
        }
        checkpointCooldown.put(player.getUniqueId(), System.currentTimeMillis());
        
        if (teleport) {
            Location resetLoc = startLocation.clone();
            // Add random offset for reset to avoid stacking (range -1.5 to 1.5 for X and Z)
            double offsetX = (Math.random() * 3) - 1.5;
            double offsetZ = (Math.random() * 3) - 1.5;
            resetLoc.add(offsetX, 0, offsetZ);
            
            player.teleport(resetLoc);
        }
        
        long now = System.currentTimeMillis();
        parkourStartTime.put(player.getUniqueId(), now);
        lastCheckpointTime.put(player.getUniqueId(), now);
        parkourCheckpoint.put(player.getUniqueId(), 0);
        
        if (sendMessage) {
            player.sendMessage(CC.translate("&a已将你的时间重置为 00:00！快点赶到终点吧！"));
        }
    }

    public void checkpoint(Player player) {
        if (!isPlaying(player)) {
            player.sendMessage(CC.translate("&c你现在不在跑酷中。请使用 /parkour start"));
            return;
        }
        
        long last = checkpointCooldown.getOrDefault(player.getUniqueId(), 0L);
        if (System.currentTimeMillis() - last < 500) {
            return;
        }
        checkpointCooldown.put(player.getUniqueId(), System.currentTimeMillis());
        
        int currentCp = parkourCheckpoint.getOrDefault(player.getUniqueId(), 0);
        if (currentCp == 0) {
            player.teleport(startLocation);
        } else {
            Location loc = checkpoints.get(currentCp);
            if (loc != null) {
                player.teleport(loc);
            } else {
                player.teleport(startLocation); // Fallback
            }
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
    }

    public void cancelParkour(Player player) {
        cancelParkour(player, false);
    }

    public void cancelParkour(Player player, boolean silent) {
        if (!isPlaying(player)) {
            if (!silent) player.sendMessage(CC.translate("&c你现在不在跑酷中。请使用 /parkour start"));
            return;
        }
        
        parkourStartTime.remove(player.getUniqueId());
        parkourCheckpoint.remove(player.getUniqueId());
        startPlateCooldown.remove(player.getUniqueId());
        checkpointCooldown.remove(player.getUniqueId());
        lastCheckpointTime.remove(player.getUniqueId());
        
        // Remove start hologram
        if (playerStartHolograms.containsKey(player.getUniqueId())) {
            playerStartHolograms.get(player.getUniqueId()).destroy(player);
            playerStartHolograms.remove(player.getUniqueId());
        }
        
        // Restore inventory (Assuming LobbyHandler handles this on join/reset)
        // We can just call LobbyHandler.loadItem(player) or similar logic
        // But for now let's just clear the parkour items
        player.getInventory().clear();
        new cn.starry.hub.listeners.handler.LobbyHandler().loadItem(player); // Reload lobby items
        
        if (!silent) {
            player.sendMessage(CC.translate("&c已取消跑酷挑战。"));
        }
        
        // Show start hologram again since they are back at spawn/lobby and might want to see it
        // Or if they are near start. Since showHolograms checks if startLocation is set, it's safe.
        // However, showHolograms creates a new PacketHologram. 
        // We should delay it slightly or just show it.
        // But wait, the previous code destroyed it. 
        // The start hologram is "Start Parkour" text. It should be visible when NOT playing too?
        // Actually, the prompt says "Start Hologram disappears after cancel".
        // It implies it SHOULD be visible.
        // The issue is I removed it in cancelParkour logic above: 
        // if (playerStartHolograms.containsKey...) remove...
        // This was likely done because the "Best Time" might update or just cleanup.
        // But we should re-show it immediately so it's visible for the player to start again.
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.isOnline()) {
                    showHolograms(player);
                }
            }
        }.runTaskLater(plugin, 5L);
    }

    public void reachCheckpoint(Player player, int cpIndex) {
        if (!isPlaying(player)) return;

        int currentCp = parkourCheckpoint.getOrDefault(player.getUniqueId(), 0);
        
        // Skip Checkpoint Check
        if (cpIndex > currentCp + 1) {
            failParkour(player, "skipped");
            return;
        }
        
        // Must reach checkpoints in order
        if (cpIndex == currentCp + 1) {
            parkourCheckpoint.put(player.getUniqueId(), cpIndex);
            
            long now = System.currentTimeMillis();
            long segmentTime = now - lastCheckpointTime.getOrDefault(player.getUniqueId(), now);
            long totalTime = now - parkourStartTime.getOrDefault(player.getUniqueId(), now);
            lastCheckpointTime.put(player.getUniqueId(), now);
            
            String totalTimeStr = formatTime(totalTime);
            String segmentTimeStr = formatTime(segmentTime);
            
            // Save Segment Best
            Thread.ofVirtual().start(() -> {
                String collectionName = "parkour_" + plugin.getType();
                MongoDBManager db = plugin.getMongoDBManager();
                if (db != null && db.getDatabase() != null) {
                    Map<Integer, Long> playerSegments = bestSegmentTimes.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>());
                    long oldSegmentBest = playerSegments.getOrDefault(cpIndex, -1L);
                    
                    if (oldSegmentBest == -1 || segmentTime < oldSegmentBest) {
                        playerSegments.put(cpIndex, segmentTime);
                        
                        // Update DB
                        Document update = new Document("$set", new Document("bestSegments." + cpIndex, segmentTime));
                        db.getCollection(collectionName).updateOne(
                                new Document("uuid", player.getUniqueId().toString()),
                                update,
                                new com.mongodb.client.model.UpdateOptions().upsert(true)
                        );
                    }
                }
            });

            Map<Integer, Long> currentSegments = bestSegmentTimes.getOrDefault(player.getUniqueId(), new HashMap<>());
            long personalBestSegment = currentSegments.getOrDefault(cpIndex, -1L);
            String pbStr = personalBestSegment == -1 ? "N/A" : formatTime(personalBestSegment);
            // If just updated, use current time
            if (personalBestSegment == -1 || segmentTime <= personalBestSegment) {
                pbStr = segmentTimeStr;
            }

            if (cpIndex == checkpoints.size()) {
                finishParkour(player, segmentTimeStr);
            } else {
                player.sendMessage(CC.translate("&a你到达了记录点 &e#" + cpIndex + "&a，用时 &e" + totalTimeStr + "&a。"));
                try {
                    net.md_5.bungee.api.chat.BaseComponent[] components = net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
                            CC.translate("&a你完成了这个部分的跑酷，用时：" + segmentTimeStr + " (个人最佳：" + pbStr + ")")
                    );
                    player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, components);
                } catch (Exception e) {
                    // Fallback or ignore if version incompatible
                }
                
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            }
        }
    }
    
    private void failParkour(Player player, String reason) {
        if ("skipped".equals(reason)) {
            player.sendTitle(CC.translate("&c跑酷挑战失败！"), CC.translate("&c你跳过了一个存档点！！"), 10, 70, 20);
            player.sendMessage(CC.translate("&c跑酷挑战失败！你跳过了一个存档点！！"));
        }
        cancelParkour(player, true); // Silent cancel
    }
    
    private void finishParkour(Player player, String finalSegmentTimeStr) {
        long time = System.currentTimeMillis() - parkourStartTime.get(player.getUniqueId());
        String timeStr = formatTime(time);
        
        // Save best time
        Thread.ofVirtual().start(() -> {
            String collectionName = "parkour_" + plugin.getType();
            MongoDBManager db = plugin.getMongoDBManager();
            if (db == null || db.getDatabase() == null) return;
            
            Document doc = db.getCollection(collectionName).find(new Document("uuid", player.getUniqueId().toString())).first();
            
            boolean newRecord = false;
            long oldBest = -1;
            
            if (doc == null) {
                newRecord = true;
                Document newDoc = new Document("uuid", player.getUniqueId().toString())
                        .append("bestTime", time);
                db.getCollection(collectionName).insertOne(newDoc);
            } else {
                Long val = doc.getLong("bestTime");
                oldBest = val != null ? val : -1;
                
                if (oldBest == -1 || time < oldBest) {
                    newRecord = true;
                    db.getCollection(collectionName).updateOne(
                            new Document("uuid", player.getUniqueId().toString()),
                            new Document("$set", new Document("bestTime", time))
                    );
                }
            }
            
            if (newRecord) {
                // New Record Messages
                player.sendMessage(CC.translate("&a你到达了记录点 &e#" + checkpoints.size() + "&a，用时 &e" + timeStr + "&a。"));
                if (oldBest != -1) {
                     player.sendMessage(CC.translate("&a你完成了这个部分的跑酷，用时：&e" + finalSegmentTimeStr + "&a，成功超越了你先前的最佳用时 &e" + formatTime(oldBest) + "&a！"));
                } else {
                     player.sendMessage(CC.translate("&a你完成了这个部分的跑酷，用时：&e" + finalSegmentTimeStr + "&a！"));
                }
                
                player.sendMessage(CC.translate("&a这是一个全新的纪录 &e" + timeStr + "&a！再来一次，尝试缔造新的纪录吧！"));
                // Title/Actionbar for new record could be added here
                
                bestTimes.put(player.getUniqueId(), time);
            } else {
                // No Record Messages
                player.sendMessage(CC.translate("&a你的时间是 &e" + timeStr + "&a，并没有打破你的最快纪录，&e" + formatTime(oldBest) + "&a！"));
                player.sendMessage(CC.translate("&a再次挑战一下，争取打破纪录吧！"));
            }
            
            // Common Action Bar
            try {
                net.md_5.bungee.api.chat.BaseComponent[] components = net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
                        CC.translate("&a你完成了这个部分的跑酷，用时：" + finalSegmentTimeStr)
                );
                player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, components);
            } catch (Exception ignored) {}
        });

        cancelParkour(player, true); // Reset state (Silent)
    }

    public boolean isPlaying(Player player) {
        return parkourStartTime.containsKey(player.getUniqueId());
    }

    private void resetPlayerState(Player player) {
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        // player.getInventory().clear(); // Keep other items (e.g. Lobby items)
    }
    
    public void setStartLocation(Location loc) {
        this.startLocation = loc;
        saveLocations();
    }
    
    public void setCheckpoint(int index, Location loc) {
        checkpoints.put(index, loc);
        saveLocations();
    }
    
    public void deleteCheckpoint(int index) {
        checkpoints.remove(index);
        saveLocations();
    }

    private String formatTime(long millis) {
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        long ms = millis % 1000;
        return String.format("%02d:%02d.%03d", minutes, seconds, ms);
    }
    
    public void loadBestTime(Player player) {
        Thread.ofVirtual().start(() -> {
            String collectionName = "parkour_" + plugin.getType();
            MongoDBManager db = plugin.getMongoDBManager();
            if (db == null || db.getDatabase() == null) return;

            Document doc = db.getCollection(collectionName).find(new Document("uuid", player.getUniqueId().toString())).first();
            if (doc != null) {
                if (doc.containsKey("bestTime")) {
                    bestTimes.put(player.getUniqueId(), doc.getLong("bestTime"));
                }
                if (doc.containsKey("bestSegments")) {
                    Document segmentsDoc = (Document) doc.get("bestSegments");
                    Map<Integer, Long> segments = new HashMap<>();
                    for (String key : segmentsDoc.keySet()) {
                        try {
                            segments.put(Integer.parseInt(key), segmentsDoc.getLong(key));
                        } catch (NumberFormatException ignored) {}
                    }
                    bestSegmentTimes.put(player.getUniqueId(), segments);
                }
            }
            
            // Refresh Holograms for player after loading best time
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.isOnline()) {
                        showHolograms(player);
                    }
                }
            }.runTask(plugin);
        });
    }
    
    public void checkPhysicalInteract(Player player, Block block) {
        if (block.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE) {
            // Check for Start
            if (isLocationMatch(block.getLocation(), startLocation)) {
                 if (isPlaying(player)) {
                     long last = startPlateCooldown.getOrDefault(player.getUniqueId(), 0L);
                     if (System.currentTimeMillis() - last < 3000) {
                         //player.sendMessage(CC.translate("&c重置时间冷却中..."));
                         return;
                     }
                     
                     // 0.5s item/interact cooldown for reset/checkpoint
                     long lastInteract = checkpointCooldown.getOrDefault(player.getUniqueId(), 0L);
                     if (System.currentTimeMillis() - lastInteract < 500) {
                         return;
                     }
                     checkpointCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                     
                     startPlateCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                     // Reset by physical interaction: no teleport, but send message
                     resetParkour(player, false, true);
                 } else {
                     startParkour(player, false);
                 }
                 return;
            }
            
            // Check for Finish (Last Checkpoint is Gold Plate)
            int lastIdx = checkpoints.size();
            Location lastLoc = checkpoints.get(lastIdx);
            if (lastLoc != null && isLocationMatch(block.getLocation(), lastLoc)) {
                if (isPlaying(player)) {
                    reachCheckpoint(player, lastIdx);
                } else {
                    // Not playing, but stepped on finish plate
                    long last = startPlateCooldown.getOrDefault(player.getUniqueId(), 0L);
                    if (System.currentTimeMillis() - last < 3000) {
                        // Cooldown shared with start/reset
                        return;
                    }
                    startPlateCooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    
                    try {
                        net.md_5.bungee.api.chat.BaseComponent[] components = net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
                                CC.translate("&a这里是跑酷的终点！回到起点再重新证明你自己！")
                        );
                        player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, components);
                    } catch (Exception ignored) {}
                }
            }
        } else if (block.getType() == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            if (isPlaying(player)) {
                for (Map.Entry<Integer, Location> entry : checkpoints.entrySet()) {
                    // Skip if it's the last checkpoint (Finish), as it should be Gold Plate
                    if (entry.getKey() == checkpoints.size()) continue;
                    
                    if (isLocationMatch(block.getLocation(), entry.getValue())) {
                        reachCheckpoint(player, entry.getKey());
                        return;
                    }
                }
            }
        }
    }
    
    private boolean isLocationMatch(Location bLoc, Location target) {
        if (target == null) return false;
        return bLoc.getWorld().equals(target.getWorld()) &&
               bLoc.getBlockX() == target.getBlockX() &&
               bLoc.getBlockY() == target.getBlockY() &&
               bLoc.getBlockZ() == target.getBlockZ();
    }
}

