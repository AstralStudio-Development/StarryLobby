package cn.starry.hub.functions.menu.selector;

import cn.starry.core.api.enums.GameOwned;
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
        button.put(11, new GameItemButton(GameOwned.BEDWARS));
        button.put(12, new GameItemButton(GameOwned.THEPIT));
        button.put(13, new GameItemButton(GameOwned.RPG));
        button.put(14, new GameItemButton(GameOwned.SKYWARS));
        button.put(15, new GameItemButton(GameOwned.DUEL));

        button.put(21, new GameItemButton(GameOwned.ARCADE));
        button.put(23, new GameItemButton(GameOwned.MURDERMYSTERY));

        return button;
    }

    @Override
    public int getSize() {
        return 4 * 9;
    }

}
