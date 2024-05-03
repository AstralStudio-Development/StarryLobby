package cn.starry.hub.api.data;

import cn.starry.hub.api.enums.AchievementType;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CacheData {
    public static HashMap<Player, String> CUSTOMSLOT;
    public static HashMap<Player, AchievementType> ACHIEVEMENT;
    public static HashMap<Player, Boolean> QUEUE;

    static {
        CacheData.CUSTOMSLOT = new HashMap<>();
        CacheData.ACHIEVEMENT = new HashMap<>();
        CacheData.QUEUE = new HashMap<>();
    }
}
