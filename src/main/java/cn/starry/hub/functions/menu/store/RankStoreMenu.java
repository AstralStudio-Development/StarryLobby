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

public class RankStoreMenu extends Menu {

    private final Menu parent;

    public RankStoreMenu(Menu parent) {
        this.parent = parent;
    }

    public RankStoreMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("                  &0会员");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(0, new StoreCategoryButton(StoreCategoryButton.Category.RANK, parent));
        buttons.put(1, new StoreCategoryButton(StoreCategoryButton.Category.BOOSTER, parent));
        buttons.put(2, new StoreCategoryButton(StoreCategoryButton.Category.DLC, parent));

        for (int i = 9; i < 18; i++) {
            buttons.put(i, new StoreGlassButton(false));
        }

        // Highlight the active category (Rank is index 0, so slot 9 should be active?)
        // In original code: this.inv.setItem(9,new StoreButtons().GlassButton(true));
        buttons.put(9, new StoreGlassButton(true));

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
