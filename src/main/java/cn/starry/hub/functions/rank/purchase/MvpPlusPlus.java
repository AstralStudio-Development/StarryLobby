package cn.starry.hub.functions.rank.purchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.rank.IRank;
import cn.starry.hub.functions.rank.RankColors;

import java.util.UUID;

public class MvpPlusPlus implements IRank {

    private String plusColor;
    private String prefixColor;
    private UUID uuid;

    public MvpPlusPlus(UUID uuid) {
        this.uuid = uuid;
        this.plusColor = Main.getInstance().getData().getPlayerData(uuid,"rankColor");
        this.prefixColor = Main.getInstance().getData().getPlayerData(uuid,"prefixColor");
    }
    @Override
    public String getFormatName() {
        return ColorUtil.color("&" + RankColors.getByEnglish(this.prefixColor).getColorChar() + "[MVP&" + RankColors.getByEnglish(this.plusColor).getColorChar() + "++&" + RankColors.getByEnglish(this.prefixColor).getColorChar() + "]&" + RankColors.getByEnglish(this.prefixColor).getColorChar());
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&" + RankColors.getByEnglish(this.prefixColor).getColorChar() + "MVP&" + RankColors.getByEnglish(this.plusColor).getColorChar() + "++&" + RankColors.getByEnglish(this.prefixColor).getColorChar());
    }

    @Override
    public String getStringId() {
        return "P_MVP_PLUS_PLUS";
    }

}
