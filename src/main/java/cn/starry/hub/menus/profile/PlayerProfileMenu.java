package cn.starry.hub.menus.profile;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.menus.profile.button.*;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerProfileMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return CC.translate("                &0个人档案     ");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(13, new ProfileButton());
        buttons.put(29, new AppearanceButton(this));
        buttons.put(30, new AchievementButton(this));
        buttons.put(31, new AccountStatusButton());
        buttons.put(32, new SettingsButton());
        buttons.put(33, new StoreButton(this));

        return buttons;
    }
    
    @Override
    public int getSize() {
        return 6 * 9;
    }
}


