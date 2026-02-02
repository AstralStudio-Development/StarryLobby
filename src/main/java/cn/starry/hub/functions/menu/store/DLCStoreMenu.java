package cn.starry.hub.functions.menu.store;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.store.button.PointInfoButton;
import cn.starry.hub.functions.menu.store.button.StoreCategoryButton;
import cn.starry.hub.functions.menu.store.button.StoreGlassButton;
import cn.starry.hub.functions.menu.store.button.UnavailableButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DLCStoreMenu extends Menu {

    private final Menu parent;

    public DLCStoreMenu(Menu parent) {
        this.parent = parent;
    }

    public DLCStoreMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("                &0拓展内容");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(3, new StoreCategoryButton(StoreCategoryButton.Category.RANK, parent));
        buttons.put(4, new StoreCategoryButton(StoreCategoryButton.Category.BOOSTER, parent));
        buttons.put(5, new StoreCategoryButton(StoreCategoryButton.Category.DLC, parent));

        for (int i = 9; i < 18; i++) {
            buttons.put(i, new StoreGlassButton(false));
        }

        // In original code: this.inv.setItem(11,new StoreButtons().GlassButton(true));
        buttons.put(14, new StoreGlassButton(true));

        buttons.put(31, new UnavailableButton());

        buttons.put(48, new BackButton(parent));
        buttons.put(49, new PointInfoButton());

        return buttons;
    }

    @Override
    public int getSize() {
        return 54;
    }
}
