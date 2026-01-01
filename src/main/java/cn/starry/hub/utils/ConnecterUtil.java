package cn.starry.hub.utils;

import cn.starry.hub.StarryLobby;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

@Include
public class ConnecterUtil {

    public static void connect(Player player, String s) {
        if (player.getInventory() != null) {
            player.closeInventory();
        }
        final ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        final Server server = Bukkit.getPlayer(player.getName()).getServer();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(player.getUniqueId().toString());
        dataOutput.writeUTF(s);
        server.sendPluginMessage(StarryLobby.getInstance(), "mastercontrol:", dataOutput.toByteArray());
    }

}
