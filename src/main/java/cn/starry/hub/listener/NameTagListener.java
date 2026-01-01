package cn.starry.hub.listener;

import cn.starry.core.Core;
import cn.starry.core.utils.RankUtil;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.nametag.BufferedNametag;
import cn.starry.hub.utils.nametag.CautusAdapter;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date:   2024/1/27
 */
@Include
public class NameTagListener implements CautusAdapter {

    @Override
    public List<BufferedNametag> getPlate(Player viewer) {
        List<BufferedNametag> tags = new ArrayList<>();
        int sortOrder = 200;

        for (Player target : Bukkit.getOnlinePlayers()) {
            // 1. 每位目标玩家自己的 rankKey
            String rankKey = Core.getInstance()
                    .getMongoDB()
                    .getPlayerData(target.getUniqueId(), "rank");

            // 2. 构造排序用 groupName
            StringBuilder g = new StringBuilder();
            switch (rankKey) {
                case "NOP_OWNER":       g.append("00"); break;
                case "NOP_ADMIN":       g.append("01"); break;
                case "NOP_SLOTH":       g.append("02"); break;
                case "NOP_STUDIO":      g.append("03"); break;
                case "NOP_GM":          g.append("04"); break;
                case "NOP_MEDIA":       g.append("05"); break;
                case "P_MVP_PLUS_PLUS": g.append("06"); break;
                case "P_MVP_PLUS":      g.append("07"); break;
                case "P_MVP":           g.append("08"); break;
                case "P_VIP_PLUS":      g.append("09"); break;
                case "P_VIP":           g.append("10"); break;
                default:                g.append("11"); break;
            }
            g.append(sortOrder);

            // 3. 构造并着色前缀文本（只一次）
            String formattedRank = RankUtil.getFormatRankById(rankKey, target.getUniqueId());
            String rawText = rankKey.equalsIgnoreCase("NOP_DEFAULT")
                    ? formattedRank
                    : (formattedRank + " ");
            String prefix = CC.translate(rawText);

            // 4. 添加 BufferedNametag
            tags.add(new BufferedNametag(
                    g.toString(),  // groupName
                    prefix,        // prefix（带颜色和末尾空格）
                    null,          // suffix
                    false,         // friendlyInvis
                    target         // 目标玩家
            ));

            sortOrder--;
        }

        return tags;
    }

    @Override
    public boolean showHealthBelowName(Player player) {
        return false;
    }
}