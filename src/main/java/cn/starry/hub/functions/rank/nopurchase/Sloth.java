package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Sloth implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&c[SLOTH]&c");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&cSLOTH&c");
    }

    @Override
    public String getStringId() {
        return "NOP_SLOTH";
    }

}
