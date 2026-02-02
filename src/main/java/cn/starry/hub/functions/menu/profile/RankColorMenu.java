package cn.starry.hub.functions.menu.profile;

import cn.starry.core.api.enums.Permission;
import cn.starry.core.functions.rank.RankColors;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.profile.button.RankColorSelectionButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RankColorMenu extends Menu {

    private final Menu parent;

    public RankColorMenu(Menu parent) {
        this.parent = parent;
    }

    public RankColorMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("               &0自定义颜色     ");
    }

    @Override
    public void openMenu(Player player) {
        if (player.hasPermission(Permission.PREMIUM.getNode())) {
            super.openMenu(player);
        } else {
            player.closeInventory();
        }
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(10, new RankColorSelectionButton(RankColors.RED, Material.RED_DYE));
        buttons.put(11, new RankColorSelectionButton(RankColors.GOLD, Material.ORANGE_DYE));
        buttons.put(12, new RankColorSelectionButton(RankColors.GREEN, Material.LIME_DYE));
        buttons.put(13, new RankColorSelectionButton(RankColors.YELLOW, Material.YELLOW_DYE));
        buttons.put(14, new RankColorSelectionButton(RankColors.PINK, Material.PINK_DYE));
        buttons.put(15, new RankColorSelectionButton(RankColors.WHITE, Material.WHITE_DYE));
        buttons.put(16, new RankColorSelectionButton(RankColors.BLUE, Material.LIGHT_BLUE_DYE));
        buttons.put(19, new RankColorSelectionButton(RankColors.DARK_GREEN, Material.GREEN_DYE));
        buttons.put(20, new RankColorSelectionButton(RankColors.DARK_RED, Material.REDSTONE));
        buttons.put(21, new RankColorSelectionButton(RankColors.CYAN, Material.CYAN_DYE));
        buttons.put(22, new RankColorSelectionButton(RankColors.PURPURE, Material.PURPLE_DYE));
        buttons.put(23, new RankColorSelectionButton(RankColors.GRAY, Material.GRAY_DYE));
        buttons.put(24, new RankColorSelectionButton(RankColors.BLACK, Material.INK_SAC));
        buttons.put(25, new RankColorSelectionButton(RankColors.DARK_BLUE, Material.LAPIS_LAZULI));

        buttons.put(40, new BackButton(parent));

        return buttons;
    }

    @Override
    public int getSize() {
        return 54;
    }
}
