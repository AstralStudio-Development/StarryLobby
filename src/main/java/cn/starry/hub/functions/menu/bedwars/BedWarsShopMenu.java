package cn.starry.hub.functions.menu.bedwars;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.bedwars.button.BedWarsSettingsButton;
import cn.starry.hub.functions.menu.bedwars.button.CosmeticsButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BedWarsShopMenu extends Menu {

    private final Menu parent;

    public BedWarsShopMenu(Menu parent) {
        this.parent = parent;
    }

    public BedWarsShopMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("自定义外观");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(11, new CosmeticsButton());
        buttons.put(15, new BedWarsSettingsButton());
        buttons.put(31, new BackButton(parent));

        return buttons;
    }

    @Override
    public int getSize() {
        return 36;
    }
}
