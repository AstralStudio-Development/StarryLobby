package cn.starry.hub.utils.hologram;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

@SuppressWarnings({"deprecation", "removal"})
public class PacketHologram {

    private final Location location;
    private final List<String> lines;
    private final List<Integer> entityIds = new ArrayList<>();
    private final double LINE_SPACING = 0.3;

    public PacketHologram(Location location, String... lines) {
        this.location = location;
        this.lines = Arrays.asList(lines);
        generateEntityIds();
    }
    
    public PacketHologram(Location location, List<String> lines) {
        this.location = location;
        this.lines = new ArrayList<>(lines);
        generateEntityIds();
    }

    private void generateEntityIds() {
        for (int i = 0; i < lines.size(); i++) {
            // Generate a random entity ID or use a counter. 
            // ProtocolLib doesn't generate IDs, we usually use a very large number or NMS Entity count.
            // Using a random large integer is risky but often works, or better: use Bukkit to get a new Entity ID?
            // Safest way without NMS is to let the client handle it? No, server dictates ID.
            // We can use a static counter starting from a high number to avoid conflict with real entities.
            entityIds.add(EntityIdProvider.getNextId());
        }
    }

    public void display(Player player) {
        Location currentLoc = location.clone().add(0, (lines.size() * LINE_SPACING), 0);

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            int entityId = entityIds.get(i);
            currentLoc.subtract(0, LINE_SPACING, 0);

            spawnArmorStand(player, entityId, currentLoc, line);
        }
    }
    
    public void destroy(Player player) {
        PacketContainer destroyPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        destroyPacket.getIntLists().write(0, new ArrayList<>(entityIds));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, destroyPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void spawnArmorStand(Player player, int entityId, Location loc, String text) {
        try {
            // 1. Spawn Entity Packet
            PacketContainer spawnPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.SPAWN_ENTITY);
            spawnPacket.getIntegers().write(0, entityId);
            spawnPacket.getUUIDs().write(0, UUID.randomUUID());
            spawnPacket.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
            spawnPacket.getDoubles()
                    .write(0, loc.getX())
                    .write(1, loc.getY())
                    .write(2, loc.getZ());
            
            // 2. Metadata Packet
            PacketContainer metaPacket = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
            metaPacket.getIntegers().write(0, entityId);
            
            List<WrappedDataValue> metadata = new ArrayList<>();
            
            // Index 0: Status (0x20 = Invisible)
            metadata.add(new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), (byte) 0x20));
            
            // Index 2: Custom Name (ChatComponent)
            metadata.add(new WrappedDataValue(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true), 
                    Optional.of(WrappedChatComponent.fromLegacyText(text).getHandle())));
            
            // Index 3: Custom Name Visible (Boolean)
            metadata.add(new WrappedDataValue(3, WrappedDataWatcher.Registry.get(Boolean.class), true));
            
            // Index 15: Armor Stand Flags (Marker = 0x10) - Small, No BasePlate, Marker
            // 0x01 (Small) | 0x08 (No BasePlate) | 0x10 (Marker) = 0x19? Or just Marker?
            // Marker removes hitbox which is good for holograms.
            metadata.add(new WrappedDataValue(15, WrappedDataWatcher.Registry.get(Byte.class), (byte) (0x01 | 0x08 | 0x10)));

            metaPacket.getDataValueCollectionModifier().write(0, metadata);

            ProtocolLibrary.getProtocolManager().sendServerPacket(player, spawnPacket);
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, metaPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Integer> getEntityIds() {
        return entityIds;
    }
        private static class EntityIdProvider {
        private static int currentId = 2000000000;
        public static synchronized int getNextId() {
            return currentId++;
        }
    }
}
