package cn.starry.hub.functions.rank.purchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Mvp implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&b[MVP]&b");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&bMVP&b");
    }

    @Override
    public String getStringId() {
        return "P_MVP";
    }

}
