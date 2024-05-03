package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Studio implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&b[STUDIO&b]&b");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&bSTUDIO&b");
    }

    @Override
    public String getStringId() {
        return "NOP_STUDIO";
    }

}
