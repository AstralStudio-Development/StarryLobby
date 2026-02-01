package cn.starry.hub.listener.handler;

import cn.starry.core.utils.ItemBuilder;
import dev.jnic.annotations.Include;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Include
public class ItemHandler {
    public static final ItemStack SELECTOR = new ItemBuilder(Material.PAPER).name("&a游戏菜单 &7(右键打开)").lore("&7右键打开游戏菜单").build();
    public static final ItemStack STORE = new ItemBuilder(Material.NAME_TAG).name("&f货摊 &7(右键打开)").lore("&7右键打开货摊菜单", "&7可用这个购买服务器物品").build();
    public static final ItemStack GADGETSMENU = new ItemBuilder(Material.CHEST).name("&a收藏品 &7(右键点击)").lore("&7右键打开收藏品菜单").build();
    public static final ItemStack SETTINGS = new ItemBuilder(Material.FEATHER).name("&a设置 &7(右键打开)").lore("&7右键打开设置菜单", "&7可用这个自定义你的视觉").build();
    public static final ItemStack COSMETICS = new ItemBuilder(Material.EMERALD).name("&a商店与特效 &7(右键打开)").lore("&7右键打开该游戏的商店菜单").build();
    public static final ItemStack PROTOTYPE = new ItemBuilder(Material.GLOWSTONE_DUST).name("&a人气星尘 &7(右键打开)").lore("&7右键打开投票菜单","&7选择你希望正式上线的游戏").build();
    public static final ItemStack LOBBYS = new ItemBuilder(Material.NETHER_STAR).name("&a选择大厅 &7(右键打开)").lore("&7右键打开以在不同大厅间切换", "&7可用这个与你的朋友待在一起").build();
    public static ItemStack PROFILE; // Kept for compatibility if needed, but not final as it depends on player? No, PROFILE shouldn't be static field if it depends on player.

    public static ItemStack getItem(Player player,int id) {
        return switch (id) {
            case 0 -> SELECTOR.clone();
            case 1 -> STORE.clone();
            case 2 -> new ItemBuilder(Material.PLAYER_HEAD).name("&a个人档案 &7(右键打开)").lore("&7右键更改你已有的后缀,设置","&7以及更多内容").durability(3).setModernSkullOwner(player).build();
            case 3 -> GADGETSMENU.clone();
            case 4 -> SETTINGS.clone();
            case 5 -> COSMETICS.clone();
            case 6 -> PROTOTYPE.clone();
            case 7 -> LOBBYS.clone();
            default -> null;
        };
    }
}
