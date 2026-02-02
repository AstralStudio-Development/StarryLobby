package cn.starry.hub.functions.menu.profile;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.profile.button.RankColorMenuButton;
import cn.starry.hub.functions.menu.profile.button.ShineButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CustomViewMenu extends Menu {

    private final Menu parent;

    public CustomViewMenu(Menu parent) {
        this.parent = parent;
    }

    public CustomViewMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("               &0自定义外观");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(20, new RankColorMenuButton(this));
        buttons.put(24, new ShineButton());

        buttons.put(40, new BackButton(parent));

        return buttons;
    }

    @Override
    public int getSize() {
        return 54;
    }
}
