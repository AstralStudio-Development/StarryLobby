package cn.starry.hub.functions.achievement.type.pit;

import cn.starry.hub.api.enums.AchievementType;
import cn.starry.hub.functions.achievement.AbstractAchievement;

import java.util.ArrayList;
import java.util.List;

public class CakeGluttonyKing extends AbstractAchievement {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getDisplayName() {
        return "蛋糕暴食王";
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("&7在单次事件\"蛋糕争夺战\"中,获得5000+硬币");
        return description;
    }

    @Override
    public AchievementType getType() {
        return AchievementType.PIT;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isPremium() {
        return true;
    }

    @Override
    public int getPoints() {
        return 15;
    }

}
