package cn.starry.hub.utils.nametag;

import org.bukkit.entity.Player;

public record BufferedNametag(String groupName, String prefix, String suffix, boolean friendlyInvis, Player player) {
}
