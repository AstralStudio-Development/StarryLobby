package cn.starry.hub.functions.achievement.type.bedwars;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;

import java.util.ArrayList;
import java.util.List;

@AutoRegister
public class WinningTheFirstGame extends AbstractAchievement {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "不经一番寒彻骨,怎得梅花扑鼻香";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7赢得首场起床战争");
        return description;
    }

    @Override
    public AchievementType getType() {
        return AchievementType.BEDWARS;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public int getPoints() {
        return 5;
    }

}
