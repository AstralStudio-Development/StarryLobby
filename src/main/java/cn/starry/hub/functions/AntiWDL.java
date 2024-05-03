package cn.starry.hub.functions;

import cn.starry.hub.Main;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class AntiWDL implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if ("WDL|INIT".equals(channel)) {
            //Bukkit.getConsoleSender().sendMessage("§c检测到§6" + player.getName() + "§c使用的客户端安装了WorldDownloader!");
            ByteArrayDataOutput output = ByteStreams.newDataOutput();
            output.writeInt(1);
            output.writeBoolean(false);
            output.writeInt(0);
            output.writeBoolean(false);
            output.writeBoolean(false);
            output.writeBoolean(false);
            output.writeBoolean(false);
            player.sendPluginMessage(Main.getInstance(), "WDL|CONTROL", output.toByteArray());
            this.sendDenyPacket(player);
            player.sendMessage("§c警告: 你无法在此服务器上使用WorldDownloader功能");
        }
    }

    public void sendDenyPacket(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeInt(3);
        output.writeBoolean(false);
        output.writeUTF("§4请不要使用WorldDownloader!");
        player.sendPluginMessage(Main.getInstance(), "WDL|CONTROL", output.toByteArray());
    }
}
