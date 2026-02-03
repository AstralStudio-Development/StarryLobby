package cn.starry.hub.features.presents;

import cn.starry.core.Core;
import cn.starry.core.api.enums.PresentsType;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;

import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Date: 2023/12/19
 */
public class PresentsManager {

    public static void unlockPresents(UUID uuid, AbstractPresents presents) {
        if (presents == null) {
            Bukkit.getPlayer(uuid).sendMessage(CC.translate("&c不存在此礼物,请联系管理员修复！"));
            return;
        }
        if (!Core.getInstance().getMongoDB().getPresentsData(uuid,"presents").contains(presents.getInternalName())) {
            TextComponent tc = new TextComponent("§b§l§o点击这里");
            TextComponent tc1 = new TextComponent("§f获取更多寻宝活动信息！");
            tc1.setColor(ChatColor.AQUA);
            StringBuilder lore = new StringBuilder("§c打开统计页面");
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(lore.toString()).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/presents"));
            tc.addExtra(tc1);
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 24.0f);
            Core.getInstance().getMongoDB().updatePresentsData(uuid,"presents",presents.getInternalName());
            Core.getInstance().getMongoDB().updatePresentsPoints(uuid,"points",1);
            int typePresents = 0;
            int unlockedPresents = 0;
            for (AbstractPresents present : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
                if (present.getType().equals(presents.getType())) {
                    typePresents++;
                }
                if (Core.getInstance().getMongoDB().getPresentsData(uuid, "presents").contains(present.getInternalName())) {
                    if (present.getType().equals(presents.getType())) {
                        unlockedPresents++;
                    }
                }
            }
            //PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_NORMAL, true, (float) (presents.getLocation().getX() + 0.5), (float) presents.getLocation().getY(), (float) (presents.getLocation().getZ() + 0.5), 0, 0, 0, 0, 18, 5);
            //((CraftPlayer) Bukkit.getPlayer(uuid)).getHandle().playerConnection.sendPacket(packet);
            Bukkit.getPlayer(uuid).sendMessage(CC.translate("&f你&b找到了&f一个大厅礼物！ &7(&b" + unlockedPresents + "&8/&b" + typePresents + "&7)"));
            Bukkit.getPlayer(uuid).spigot().sendMessage(tc);
        } else {
            //PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.VILLAGER_ANGRY, true, (float) (presents.getLocation().getX() + 0.5), (float) presents.getLocation().getY(), (float) (presents.getLocation().getZ() + 0.5), 0, 0, 0, 0, 18, 5);
            //((CraftPlayer) Bukkit.getPlayer(uuid)).getHandle().playerConnection.sendPacket(packet);
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            Bukkit.getPlayer(uuid).sendMessage(CC.translate("&c你已经领取了该礼物了！"));
        }
    }

    public static AbstractPresents getAchievement(String name) {
        for (AbstractPresents achievement : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
            if (achievement.getInternalName().equalsIgnoreCase(name)) {
                return achievement;
            }
        }
        return null;
    }

    public static int getPlayerPresents(UUID uuid) {
        int unlockedPresents = 0;
        for (AbstractPresents present : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
            if (Core.getInstance().getMongoDB().getPresentsData(uuid, "presents").contains(present.getInternalName())) {
                unlockedPresents++;
            }
        }
        return unlockedPresents;
    }

    public static int getPlayerPresentsByType(UUID uuid, PresentsType type) {
        int unlockedPresents = 0;
        for (AbstractPresents present : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
            if (Core.getInstance().getMongoDB().getPresentsData(uuid, "presents").contains(present.getInternalName())) {
                if (present.getType().equals(type)) {
                    unlockedPresents++;
                }
            }
        }
        return unlockedPresents;
    }

    public static int getTotalPresentsByType(PresentsType type) {
        int totalPresents = 0;
        for (AbstractPresents present : StarryLobby.getInstance().getPresentsFactory().getPresents()) {
            if (present.getType().equals(type)) {
                totalPresents++;
            }
        }
        return totalPresents;
    }

}

