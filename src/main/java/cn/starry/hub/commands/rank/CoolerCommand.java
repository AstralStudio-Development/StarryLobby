package cn.starry.hub.commands.rank;

import cn.starry.hub.Main;
import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AutoRegister
public class CoolerCommand extends Command implements Listener {

    private final Map<Block, BlockData> changed = new HashMap<>();

    public CoolerCommand() {
        super("cooler");
        setAliases(Arrays.asList("冰霜行者"));
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            UUID uuid = player.getUniqueId();
            if (!commandSender.hasPermission("lobby.cooler")) {
                commandSender.sendMessage(ColorUtil.color("&c你没有权限来使用这个命令"));
                return true;
            }
            if (Main.getInstance().getData().getPlayerData(uuid,"cooler").equalsIgnoreCase("false")) {
                Main.getInstance().getData().updatePlayerData(uuid, "cooler", "true");
                player.sendMessage(ColorUtil.color("&b&l冰霜行者! &7你化身为了冰的代言人!"));
            } else {
                Main.getInstance().getData().updatePlayerData(uuid, "cooler", "false");
                player.sendMessage(ColorUtil.color("&b&l冰霜行者! &7你不再是冰的代言人了!"));
            }
        } else {
            commandSender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation().add(0,-1,0);
        Block block = location.getBlock();
        if (Main.getInstance().getData().getPlayerData(player.getUniqueId(),"cooler").equalsIgnoreCase("true") && player.hasPermission("lobby.cooler")) {
            if (!(block.getType() == Material.AIR)) {
                if (!changed.containsKey(block)) {
                    BlockData blockData = new BlockData(block.getType(), block.getData());
                    changed.put(block, blockData);

                    block.setType(Material.ICE);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (changed.containsKey(block)) {
                                // 恢复方块的原始类型
                                BlockData originalData = changed.get(block);
                                block.setType(originalData.getType());
                                block.setData(originalData.getData());
                                changed.remove(block);
                            }
                        }
                    }.runTaskLater(Main.getInstance(), 60L);
                }
            }
        }
    }

    private static class BlockData {
        private final Material type;
        private final byte data;

        public BlockData(Material type, byte data) {
            this.type = type;
            this.data = data;
        }

        public Material getType() {
            return type;
        }

        public byte getData() {
            return data;
        }
    }

}


