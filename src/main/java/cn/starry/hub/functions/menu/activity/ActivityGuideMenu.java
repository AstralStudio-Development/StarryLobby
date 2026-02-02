package cn.starry.hub.functions.menu.activity;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.activity.button.LinkItemButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ActivityGuideMenu extends Menu {

    private final Menu parent;

    public ActivityGuideMenu(Menu parent) {
        this.parent = parent;
    }

    public ActivityGuideMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("                &0活动指南");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(13, new LinkItemButton());
        buttons.put(31, new BackButton(parent));

        return buttons;
    }

    @Override
    public int getSize() {
        return 36;
    }
}
