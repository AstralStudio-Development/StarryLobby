package cn.starry.hub.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TabListUtil
{
    public static Class<?> getNmsClass(final String nmsClassName) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + "." + nmsClassName);
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    public static void sendTablist(final Player p, final String msg, final String msg2) {
        try {
            if (!getServerVersion().equalsIgnoreCase("v1_9_R1") && !getServerVersion().equalsIgnoreCase("v1_9_R2")) {
                if (!getServerVersion().equalsIgnoreCase("v1_8_R2") && !getServerVersion().equalsIgnoreCase("v1_8_R3")) {
                    final Object header = getNmsClass("ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + msg + "'}");
                    final Object footer = getNmsClass("ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + msg2 + "'}");
                    final Object ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
                    final Field f = ppoplhf.getClass().getDeclaredField("b");
                    f.setAccessible(true);
                    f.set(ppoplhf, footer);
                    final Object nmsp = p.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(p, new Object[0]);
                    final Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
                    pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
                }
                else {
                    final Object header = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + msg + "'}");
                    final Object footer = getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{'text': '" + msg2 + "'}");
                    final Object ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
                    final Field f = ppoplhf.getClass().getDeclaredField("b");
                    f.setAccessible(true);
                    f.set(ppoplhf, footer);
                    final Object nmsp = p.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(p, new Object[0]);
                    final Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
                    pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
                }
            }
            else {
                final Object header = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg));
                final Object footer = getNmsClass("ChatComponentText").getConstructor(String.class).newInstance(ChatColor.translateAlternateColorCodes('&', msg2));
                final Object ppoplhf = getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNmsClass("IChatBaseComponent")).newInstance(header);
                final Field f = ppoplhf.getClass().getDeclaredField("b");
                f.setAccessible(true);
                f.set(ppoplhf, footer);
                final Object nmsp = p.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(p, new Object[0]);
                final Object pcon = nmsp.getClass().getField("playerConnection").get(nmsp);
                pcon.getClass().getMethod("sendPacket", getNmsClass("Packet")).invoke(pcon, ppoplhf);
            }
        }
        catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | NoSuchFieldException | IllegalAccessException ex2) {
            final Exception ex = new Exception();
            final Exception var9 = ex;
            var9.printStackTrace();
        }
    }
}
