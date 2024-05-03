package cn.starry.hub.commands.player;

import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class FireworkCommand extends Command {

    public FireworkCommand() {
        super("firework");
        setAliases(Arrays.asList("fw","烟花"));
    }

    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)sender;
        if (!Objects.equals(Main.getPlugin(Main.class).getConfig().getString("type"), "Login")) {
            if (isAllowedYear() || player.hasPermission("lobby.fw")) {
                shootRandomFireWork(player);
                player.sendMessage(ColorUtil.color("&c&l咻! &7你发射了一个烟花"));
            } else {
                player.sendMessage(ColorUtil.color("&fUnknown command. Type \"/help\" for help."));
            }
        } else {
            player.sendMessage(ColorUtil.color("&c此服务器不允许执行该命令"));
        }
        return true;
    }

    private void shootRandomFireWork(Player player) {
        Location playerLocation = player.getLocation();
        Firework firework = (Firework) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.FIREWORK);

        Random random = new Random();
        int c1i = random.nextInt(16) + 1;
        int c2i = random.nextInt(16) + 1;
        int type = random.nextInt(FireworkEffect.Type.values().length);
        int power = random.nextInt(3) + 1;

        Color c1 = this.getColor(c1i);
        Color c2 = this.getColor(c2i);

        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
                .withColor(c1).withFade(c2)
                .with(FireworkEffect.Type.values()[type])
                .build());
        fireworkMeta.setPower(power);
        firework.setFireworkMeta(fireworkMeta);
    }

    public Color getColor(int c) {
        switch (c) {
            default: {
                return Color.AQUA;
            }
            case 2: {
                return Color.BLACK;
            }
            case 3: {
                return Color.BLUE;
            }
            case 4: {
                return Color.FUCHSIA;
            }
            case 5: {
                return Color.GRAY;
            }
            case 6: {
                return Color.GREEN;
            }
            case 7: {
                return Color.LIME;
            }
            case 8: {
                return Color.MAROON;
            }
            case 9: {
                return Color.NAVY;
            }
            case 10: {
                return Color.OLIVE;
            }
            case 11: {
                return Color.ORANGE;
            }
            case 12: {
                return Color.PURPLE;
            }
            case 13: {
                return Color.RED;
            }
            case 14: {
                return Color.SILVER;
            }
            case 15: {
                return Color.TEAL;
            }
            case 16: {
                return Color.WHITE;
            }
            case 17:
        }
        return Color.YELLOW;
    }

    private boolean isAllowedYear() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear() >= 2024;
    }

}
