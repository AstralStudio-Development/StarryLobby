package cn.starry.hub.menus.login;

import cn.starry.hub.menus.login.button.AgreeButton;
import cn.starry.hub.menus.login.button.AgreementInfoButton;
import cn.starry.hub.menus.login.button.DisagreeButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AgreementMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "玩家协议";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(13, new AgreementInfoButton());
        buttons.put(29, new AgreeButton());
        buttons.put(33, new DisagreeButton());

        return buttons;
    }

    @Override
    public int getSize() {
        return 45;
    }
}

