package cn.starry.hub.functions.rank.purchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class VipPlus implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&a[VIP&c+&a]&a");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&aVIP&c+&a");
    }

    @Override
    public String getStringId() {
        return "P_VIP_PLUS";
    }

}
