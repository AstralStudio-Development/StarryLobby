package cn.starry.hub.functions.rank.purchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class Vip implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&a[VIP]&a");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&aVIP&a");
    }

    @Override
    public String getStringId() {
        return "P_VIP";
    }

}
