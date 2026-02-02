package cn.starry.hub.functions.menu.slot;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.slot.button.ResetSlotButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SlotMenu extends Menu {

    private final Menu parent;

    public SlotMenu(Menu parent) {
        this.parent = parent;
    }

    public SlotMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("              &0选择菜单图标");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(40, new BackButton(parent));
        buttons.put(41, new ResetSlotButton());

        return buttons;
    }

    @Override
    public int getSize() {
        return 45;
    }
}
