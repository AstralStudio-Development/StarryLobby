package cn.starry.hub.functions.menu.store.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.store.BoosterStoreMenu;
import cn.starry.hub.functions.menu.store.DLCStoreMenu;
import cn.starry.hub.functions.menu.store.RankStoreMenu;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class StoreCategoryButton extends Button {

    public enum Category {
        RANK, BOOSTER, DLC
    }

    private final Category category;
    private final Menu parent; // Parent to pass when switching menus

    public StoreCategoryButton(Category category, Menu parent) {
        this.category = category;
        this.parent = parent;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        Material mat;
        String name;
        ItemBuilder builder;

        switch (category) {
            case RANK:
                name = " ";
                lores.add("   &a会员   ");
                lores.add(" ");
                lores.add("   &7购买会员可拥有更好的游戏体验   ");
                lores.add("   &7但不会提供任何游戏内优势   ");
                lores.add("   &7每个等级都包含先前等级的所有特权   ");
                lores.add(" ");
                lores.add("   &f您当前等级的价值将会   ");
                lores.add("   &f自动从未来任何的等级升级费用中扣除   ");
                lores.add(" ");
                mat = Material.EMERALD;
                builder = new ItemBuilder(mat);
                break;
            case BOOSTER:
                name = " ";
                lores.add("   &a硬币增倍器   ");
                lores.add(" ");
                lores.add("   &7使用硬币增倍器，可在特定的小游戏中   ");
                lores.add("   &7给予&f所有人&7额外的硬币加成   ");
                lores.add("   &7增幅最高可达3%   ");
                lores.add(" ");
                lores.add("   &c如果此时已经存在一个启用的增倍器   ");
                lores.add("   &c那么您须等待当前的增倍器结束   ");
                lores.add(" ");
                mat = Material.POTION;
                builder = new ItemBuilder(mat).flags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
                break;
            case DLC:
                name = " ";
                lores.add("   &a拓展内容   ");
                lores.add(" ");
                lores.add("   &7购买拓展内容   ");
                lores.add("   &7将会解锁部分在原定游戏中锁定的   ");
                lores.add("   &7包括但不限于   ");
                lores.add("   &f职业，地图区域，任务，技能，武器等   ");
                lores.add(" ");
                lores.add("   &c针对角色扮演游戏的拓展内容   ");
                lores.add("   &c将于此世界剧情结束后免费开放   ");
                lores.add(" ");
                mat = Material.PRISMARINE_CRYSTALS;
                builder = new ItemBuilder(mat);
                break;
            default:
                mat = Material.STONE;
                name = "Unknown";
                builder = new ItemBuilder(mat);
        }

        return builder.name(CC.translate(name)).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, org.bukkit.event.inventory.ClickType clickType, int hotbarButton, ItemStack currentItem) {
        switch (category) {
            case RANK:
                new RankStoreMenu(parent).openMenu(player);
                break;
            case BOOSTER:
                new BoosterStoreMenu(parent).openMenu(player);
                break;
            case DLC:
                new DLCStoreMenu(parent).openMenu(player);
                break;
        }
    }
}
