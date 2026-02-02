package cn.starry.hub.functions.menu.selector;

import cn.starry.hub.functions.menu.selector.button.GameItemButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Stalyer
 * @Date: 2025/8/20
 */
public class SelectorMenu extends Menu {
    @Override
    public String getTitle(Player player) {
        return "               &0游戏菜单     ";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> button = new HashMap<>();
        button.put(20, new GameItemButton(GameOwned.BEDWARS));
        button.put(21, new GameItemButton(GameOwned.THEPIT));
        button.put(22, new GameItemButton(GameOwned.RPG));
        button.put(23, new GameItemButton(GameOwned.SKYWARS));
        button.put(24, new GameItemButton(GameOwned.DUEL));

        button.put(30, new GameItemButton(GameOwned.ARCADE));
        button.put(32, new GameItemButton(GameOwned.MURDERMYSTERY));

        return button;
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

}
