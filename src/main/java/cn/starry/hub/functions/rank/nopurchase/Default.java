package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Default implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&7");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&7Default");
    }

    @Override
    public String getStringId() {
        return "NOP_DEFAULT";
    }

}
