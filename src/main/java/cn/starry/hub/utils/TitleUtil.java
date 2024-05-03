package cn.starry.hub.utils;

import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * @Author: Starry_Killer, EmptyIrony
 * @Date: 2021/1/1 13:26
 * @Remaster: 2023/7/20
 */
public class TitleUtil {

    public static void sendTitle(Player player, String title, String sub, int fadeIn, int fadeOut, int duration) {
        if (title != null) {
            PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, new ChatComponentText(ColorUtil.color(title)), fadeIn, fadeOut, duration);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
        if (sub != null) {
            PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, new ChatComponentText(ColorUtil.color(sub)), fadeIn, fadeOut, duration);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }
    }

}
