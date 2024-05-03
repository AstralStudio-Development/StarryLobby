package cn.starry.hub.utils;

import cn.starry.hub.utils.skin.NickSkin;
import cn.starry.hub.utils.skin.SkinManager;
import cn.starry.hub.Main;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import xyz.upperlevel.spigot.book.BookUtil;

import java.lang.reflect.Field;
import java.util.*;

public class NickUtil {

    public static Set<UUID> nickedUuids = new HashSet<>();
    public static Map<UUID, GameProfile> nickprofiles = new HashMap<>();

    public static void setNick(Player player, String name) {
        GameProfile befortgp = ((CraftPlayer) player).getProfile();
        GameProfile aftergp = new GameProfile(befortgp.getId(), name);
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer ep = (cp).getHandle();
        try {
            Field field = ep.getClass().getSuperclass().getDeclaredField("bH");
            field.setAccessible(true);
            field.set(ep, aftergp);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        player.setDisplayName(name);
        nickedUuids.add(player.getUniqueId());
        if (!nickprofiles.containsKey(befortgp.getId())) {
            nickprofiles.put(befortgp.getId(), befortgp);
        }
        setPlayerCustomSkin(player,name);
        PacketPlayOutEntityDestroy pds = new PacketPlayOutEntityDestroy(cp.getEntityId());
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(pds);
        }
        PacketPlayOutPlayerInfo tab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tab);
        }

        PacketPlayOutPlayerInfo tabadd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{cp.getHandle()});
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tabadd);
        }
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(cp.getName())) {
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(spawn);
            }
        }
        Main.getInstance().getData().updatePlayerData(player.getUniqueId(),"isNicked","true");
        Main.getInstance().getData().updatePlayerData(player.getUniqueId(),"nickName",name);
        VanishUtil.sendVanishActionbar(player);
    }

    public static void setUnNick(Player player) {
        GameProfile befortgp = ((CraftPlayer) player).getProfile();
        GameProfile aftergp = nickprofiles.get(befortgp.getId());
        CraftPlayer cp = (CraftPlayer) player;
        EntityPlayer ep = ((CraftPlayer) player).getHandle();
        try {
            Field field = ep.getClass().getSuperclass().getDeclaredField("bH");
            field.setAccessible(true);
            field.set(ep, aftergp);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        player.setDisplayName(aftergp.getName());
        nickedUuids.remove(player.getUniqueId());
        setPlayerCustomSkin(player,aftergp.getName());
        PacketPlayOutEntityDestroy pds = new PacketPlayOutEntityDestroy(cp.getEntityId());
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(pds);
        }
        PacketPlayOutPlayerInfo tab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tab);
        }

        PacketPlayOutPlayerInfo tabadd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{cp.getHandle()});
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(tabadd);
        }
        PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(cp.getName())) {
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(spawn);
            }
        }
        Main.getInstance().getData().updatePlayerData(player.getUniqueId(),"isNicked","false");
        Main.getInstance().getData().updatePlayerData(player.getUniqueId(),"nickName","Steve");
    }

    public static boolean isNicked(UUID uuid) {
        return Main.getInstance().getData().getPlayerData(uuid,"isNicked").equalsIgnoreCase("true");
    }

    public static String getNickName(UUID uuid) {
        return Main.getInstance().getData().getPlayerData(uuid,"nickName");
    }

    public static void setPlayerCustomSkin(Player p, String skinName) {
        NickSkin[] nickSkinArray;
        int n;
        int n2;
        NickSkin nickSkin = null;
        HashSet<NickSkin> getNickSkins = new HashSet<NickSkin>();
        String[] infos = SkinManager.getSkinFormApi(skinName);
        if (infos[0] != null && infos[1] != null) {
            getNickSkins.add(new NickSkin(infos[0], infos[1]));
        }
        if ((n2 = 0) < (n = (nickSkinArray = getNickSkins.toArray(new NickSkin[0])).length)) {
            NickSkin getNickSkin;
            nickSkin = getNickSkin = nickSkinArray[n2];
        }
        if (nickSkin == null) {
            return;
        }
        String value = nickSkin.getValue();
        String signature = nickSkin.getSignature();
        for (Player pl : Bukkit.getOnlinePlayers()) {
            GameProfile gp;
            if (pl == p) {
                ((CraftPlayer) pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{((CraftPlayer)((Object)p)).getHandle()}));
                gp = ((CraftPlayer) p).getProfile();
                gp.getProperties().removeAll("textures");
                gp.getProperties().put("textures", new Property("textures", value, signature));
                ((CraftPlayer) pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[]{((CraftPlayer)((Object)p)).getHandle()}));
                continue;
            }
            ((CraftPlayer) pl).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[]{((CraftPlayer)((Object)p)).getHandle()}));
            gp = ((CraftPlayer) p).getProfile();
            gp.getProperties().removeAll("textures");
            gp.getProperties().put("textures", new Property("textures", value, signature));
        }
    }

    public static void BookHelpPage(Player player) {
        BookUtil.openPlayer(player,
                BookUtil.writtenBook()
                        .title(ColorUtil.color(""))
                        .author(UUID.randomUUID().toString())
                        .pages(
                                new BookUtil.PageBuilder()
                                        .add(
                                                ColorUtil.color("&0匿名功能将允许你使用不同")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0的用户名，使得其他玩家无法")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0识别出你的真实身份。")
                                        ).newLine()
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0所有规则仍然适用，你仍可以")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0被举报并且你的匿名记录将")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0被保存。")
                                        )
                                        .newLine()
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .onClick(BookUtil.ClickAction.runCommand("/super hTtjyOKQ91Bmj0u3NvZJS8a9UazUjl1M"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n我明白了，开始设定我的匿名"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .onClick(BookUtil.ClickAction.runCommand("/super hTtjyOKQ91Bmj0u3NvZJS8a9UazUjl1M"))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n吧"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .onClick(BookUtil.ClickAction.runCommand("/super hTtjyOKQ91Bmj0u3NvZJS8a9UazUjl1M"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }

    public static void BookRankPage(Player player) {
        BookUtil.openPlayer(player,
                BookUtil.writtenBook()
                        .title(ColorUtil.color(""))
                        .author(UUID.randomUUID().toString())
                        .pages(
                                new BookUtil.PageBuilder()
                                        .add(
                                                ColorUtil.color("&0帮助你设置匿名！")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0首先，你需要选择你想要你想要显示")
                                        )
                                        .newLine()
                                        .add(
                                                ColorUtil.color("&0的&0&l会员等级。")
                                        ).newLine()
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤ &7DEFAULT"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤ &aVIP"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤ &aVIP&c+"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤ &bMVP"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .build()
                                        )
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(ColorUtil.color("&0&n➤ &bMVP&c+"))
                                                        .onHover(BookUtil.HoverAction.showText(ColorUtil.color("&f点击确定")))
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }

}
