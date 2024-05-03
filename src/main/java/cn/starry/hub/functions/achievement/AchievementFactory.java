package cn.starry.hub.functions.achievement;

import cn.starry.hub.utils.ClassUtil;
import cn.starry.hub.Main;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2023/12/19
 * @IdeaProvider: duduskz
 */
@Data
public class AchievementFactory {

    @Getter
    private final List<AbstractAchievement> achievements;

    public AchievementFactory() {
        this.achievements = new ArrayList<>();
    }

    @SneakyThrows
    public void init() {
        Collection<Class<?>> classes = ClassUtil.getClassesInPackage(Main.getInstance(), "cn.starry.hub.functions.achievement.type");
        for (Class<?> clazz : classes) {
            if (AbstractAchievement.class.isAssignableFrom(clazz)) {
                Object instance = clazz.newInstance();
                achievements.add((AbstractAchievement) instance);
            }
        }
        Bukkit.getConsoleSender().sendMessage("加载了" + achievements.size() + "个成就");
    }

}
