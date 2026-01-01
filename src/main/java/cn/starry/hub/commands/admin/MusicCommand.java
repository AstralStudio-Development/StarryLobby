package cn.starry.hub.commands.admin;

import cn.starry.core.api.enums.Permission;
import cn.starry.core.utils.chat.CC;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import dev.jnic.annotations.Include;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Include
public class MusicCommand extends Command {

    private final Map<UUID, RadioSongPlayer> playerMap = new HashMap<>();

    public MusicCommand() {
        super("music");
    }

    public boolean execute(final CommandSender commandSender, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;
            if (!commandSender.hasPermission(Permission.ADMIN.getNode())) {
                commandSender.sendMessage(CC.translate("&c你没有权限来使用这个命令"));
                return true;
            }
            if (strings.length < 2) {
                player.sendMessage(CC.translate("&cUsage: /music <nbsFile> <boolean>"));
                return true;
            }
            Song song = NBSDecoder.parse(new File("./plugins/StarryLobby/songs/" + strings[0]));
            RadioSongPlayer rsp = new RadioSongPlayer(song);
            if (Boolean.parseBoolean(strings[1])) {
                rsp.addPlayer(player);
                rsp.setPlaying(true);
                playerMap.put(player.getUniqueId(), rsp);
            } else {
                RadioSongPlayer mRsp = playerMap.get(player.getUniqueId());
                mRsp.setPlaying(false);
                mRsp.removePlayer(player);
            }
        } else {
            commandSender.sendMessage(CC.translate("&c你必须是一名玩家才能执行这个指令!"));
        }
        return true;

    }

}
