package cn.starry.hub.api.enums;

import cn.starry.hub.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum AchievementType {

    GENERAL(0, "综合", new ItemBuilder(Material.BOOK).build()),
    BEDWARS(1, "起床战争", new ItemBuilder(Material.BED).build()),
    PIT(2, "天坑乱斗", new ItemBuilder(Material.DIRT).build());

    private final int id;
    private final String displayName;
    private final ItemStack itemStack;

    private AchievementType(int id, String displayName, ItemStack itemStack) {
        this.id = id;
        this.displayName = displayName;
        this.itemStack = itemStack;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public ItemStack getIcon() {
        return this.itemStack;
    }

    public static AchievementType getById(int id) {
        for (AchievementType achievementType : AchievementType.values()) {
            if (achievementType.getID() == id) {
                return achievementType;
            }
        }
        return null;
    }

}
