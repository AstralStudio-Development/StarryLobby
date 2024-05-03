package cn.starry.hub.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Yurinan, Misoryan, Starry_Killer
 * @since 2021/1/5 14:55
 */
public class LuckPermsUtil {

    public static String getPlayerRank(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        return user.getCachedData().getMetaData().getPrefix();
    }

    public static String getPlayerRank(UUID uuid) {
        User user = LuckPermsProvider.get().getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData().getPrefix();
    }

    public static String getPlayerRankByCache(UUID uuid) {
        User user = null;
        try {
            user = LuckPermsProvider.get().getUserManager().loadUser(uuid).get();
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return user.getCachedData().getMetaData().getPrefix();
    }

    public static String getPlayerSuffix(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        return user.getCachedData().getMetaData().getSuffix();
    }

    public static String getPlayerSuffix(UUID uuid) {
        User user = LuckPermsProvider.get().getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData().getSuffix();
    }

    public static String getPlayerRankColor(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        return user.getCachedData().getMetaData().getPrefix().substring(0, 2);
    }

    public static String getPlayerRankColor(UUID uuid) {
        User user = LuckPermsProvider.get().getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData().getPrefix().substring(0, 2);
    }

    public static String getPlayerName(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        return name;
    }

    /**
     * 通过 LuckPerms 权限组获取用户 Prefix 并写入返回值
     * 此处可以直接获取 Nick
     *
     * @param name 玩家昵称
     * @return [NickRank] NickName(Colored)
     */
    public static String getPlayerColoredName(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        String prefix = user.getCachedData().getMetaData().getPrefix();
        if (prefix != null) {
            if ("&7".equalsIgnoreCase(prefix)) {
                return prefix + name;
            }
        }
        return prefix + " " + name;
    }

    /**
     * 通过 LuckPerms 权限组获取用户 Prefix 并写入返回值
     * 此处可以直接获取 Nick
     *
     * @param uuid 玩家 UUID
     * @return [NickRank] NickName(Colored)
     */
    public static String getPlayerColoredName(UUID uuid) {
        User user = LuckPermsProvider.get().getUserManager().getUser(uuid);
        String prefix = user.getCachedData().getMetaData().getPrefix();
        if (prefix != null) {
            if ("&7".equalsIgnoreCase(prefix)) {
                return prefix + Bukkit.getOfflinePlayer(uuid).getName();
            }
        }
        return prefix + " " + Bukkit.getOfflinePlayer(uuid).getName();
    }

    /**
     * 通过 LuckPerms 权限组获取用户 Prefix 并写入返回值
     *
     * @param name 玩家昵称
     * @return [Rank] DisplayName(Colored)
     */
    public static String getPlayerRealColoredName(String name) {
        User user = LuckPermsProvider.get().getUserManager().getUser(Bukkit.getPlayer(name).getUniqueId());
        return user.getCachedData().getMetaData().getPrefix() + " " + name;
    }

    /**
     * 通过 LuckPerms 权限组获取用户 Prefix
     * 并转换为对应权限组昵称颜色后写入返回值
     *
     * @param uuid 玩家 UUID
     * @return [Rank] DisplayName(Colored)
     */
    public static String getPlayerRealColoredName(UUID uuid) {
        User user = LuckPermsProvider.get().getUserManager().getUser(uuid);
        return user.getCachedData().getMetaData().getPrefix() + " " + Bukkit.getOfflinePlayer(uuid).getName();
    }

    public static boolean isPlayerNicked(UUID uuid) {
        return getPlayerRealColoredName(uuid).equalsIgnoreCase(getPlayerColoredName(uuid));
    }

    public static boolean isPlayerNicked(String name) {
        return getPlayerRealColoredName(name).equalsIgnoreCase(getPlayerColoredName(name));
    }

    public static boolean isPlayerNicked(Player player) {
        return isPlayerNicked(player.getUniqueId());
    }

}
