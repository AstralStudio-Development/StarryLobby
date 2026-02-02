package cn.starry.hub.functions.menu.profile;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.profile.button.*;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.CloseButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerProfileMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.translate("               &0个人档案     ");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(13, new ProfileButton());
        buttons.put(20, new AppearanceButton());
        buttons.put(21, new AchievementButton());
        buttons.put(22, new AccountStatusButton());
        buttons.put(23, new SettingsButton());
        buttons.put(24, new StoreButton());

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 6 * 9;
    }
}

