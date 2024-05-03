package cn.starry.hub.functions.rank.purchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.rank.IRank;
import cn.starry.hub.functions.rank.RankColors;

import java.util.UUID;

public class MvpPlus implements IRank {

    private String plusColor;
    private UUID uuid;

    public MvpPlus(UUID uuid) {
        this.uuid = uuid;
        this.plusColor = Main.getInstance().getData().getPlayerData(uuid,"rankColor");
    }
    @Override
    public String getFormatName() {
        return ColorUtil.color("&b[MVP&" + RankColors.getByEnglish(this.plusColor).getColorChar() + "+&b]&b");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&bMVP&" + RankColors.getByEnglish(this.plusColor).getColorChar() + "+&b");
    }

    @Override
    public String getStringId() {
        return "P_MVP_PLUS";
    }

}
