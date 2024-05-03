package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Admin implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&c[ADMIN]&c");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&cADMIN&c");
    }

    @Override
    public String getStringId() {
        return "NOP_ADMIN";
    }

}
