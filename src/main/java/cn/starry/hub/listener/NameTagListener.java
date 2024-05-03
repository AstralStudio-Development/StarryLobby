package cn.starry.hub.listener;

import cn.starry.hub.utils.nametag.BufferedNametag;
import cn.starry.hub.utils.nametag.NametagAdapter;
import cn.starry.hub.Main;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.RankUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2024/1/27
 */
public class NameTagListener implements NametagAdapter {
    @Override
    public List<BufferedNametag> getPlate(Player player) {
        List<BufferedNametag> tags = new ArrayList<>();
        int a = 200;
        StringBuilder g;
        for (Player p : Bukkit.getOnlinePlayers()) {
            String groupName = Main.getInstance().getData().getPlayerData(p.getUniqueId(),"rank");
            switch (groupName) {
                case "NOP_OWNER":
                    g = new StringBuilder("00");
                    break;
                case "NOP_ADMIN":
                    g = new StringBuilder("01");
                    break;
                case "NOP_SLOTH":
                    g = new StringBuilder("02");
                    break;
                case "NOP_STUDIO":
                    g = new StringBuilder("03");
                    break;
                case "NOP_GM":
                    g = new StringBuilder("04");
                    break;
                case "NOP_MEDIA":
                    g = new StringBuilder("05");
                    break;
                case "P_MVP_PLUS_PLUS":
                    g = new StringBuilder("06");
                    break;
                case "P_MVP_PLUS":
                    g = new StringBuilder("07");
                    break;
                case "P_MVP":
                    g = new StringBuilder("08");
                    break;
                case "P_VIP_PLUS":
                    g = new StringBuilder("09");
                    break;
                case "P_VIP":
                    g = new StringBuilder("10");
                    break;
                default:
                    g = new StringBuilder("11");
                    break;
            }
            g.append(a);
            String prefix = ColorUtil.color(Main.getInstance().getData().getPlayerData(p.getUniqueId(),"rank").equalsIgnoreCase("NOP_DEFAULT") ? RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(p.getUniqueId(),"rank"), p.getUniqueId()) : RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(p.getUniqueId(),"rank"), p.getUniqueId()) + " ");
            tags.add(new BufferedNametag(g.toString(), prefix, null, false, p));
            a--;
        }
        return tags;
    }

    @Override
    public boolean showHealthBelowName(Player player) {
        return false;
    }

}
