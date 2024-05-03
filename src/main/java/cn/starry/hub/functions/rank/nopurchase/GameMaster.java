package cn.starry.hub.functions.rank.nopurchase;

import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.functions.rank.IRank;

public class GameMaster implements IRank {
    @Override
    public String getFormatName() {
        return ColorUtil.color("&2[GM]&2");
    }

    @Override
    public String getDisplayName() {
        return ColorUtil.color("&2GM&2");
    }

    @Override
    public String getStringId() {
        return "NOP_GM";
    }

}
