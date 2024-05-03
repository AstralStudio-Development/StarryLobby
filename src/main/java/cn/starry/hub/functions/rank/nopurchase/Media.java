package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Media implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&c[&fMEDIA&c]&c");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&fMEDIA&c");
    }

    @Override
    public String getStringId() {
        return "NOP_MEDIA";
    }

}
