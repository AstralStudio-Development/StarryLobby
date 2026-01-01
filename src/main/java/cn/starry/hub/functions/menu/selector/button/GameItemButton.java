package cn.starry.hub.functions.menu.selector.button;

import cn.starry.core.api.enums.GameOwned;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.ConnecterUtil;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Stalyer
 * @Date: 2025/8/20
 */

public class GameItemButton extends Button {

    private GameOwned game;

    public GameItemButton(GameOwned game) {
        this.game = game;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lore = new ArrayList<>();
        List<String> description = game.getDescription();
        lore.add(game.getType().getFormattedDisplayName());
        lore.add(" ");
        for (String line : description) {
            lore.add(line);
        }
        if (game.equals(GameOwned.THEPIT) || game.equals(GameOwned.MEGAWALLS) || game.equals(GameOwned.UHC)) {
            lore.add(" ");
            lore.add("  &c前方高能！  ");
            lore.add("  &c你已进入极限区域，  ");
            lore.add("  &c不推荐新手游玩  ");
            lore.add(" ");
        }
        lore.add(" ");
        lore.add("  &b✧ 点击连接");
        lore.add(" ");

        return new ItemBuilder(game.getItemStack()).name(CC.translate(game.getDisplayName())).lore(lore).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
        switch (game) {
            case BEDWARS -> {
                ConnecterUtil.connect(player,"BedWarsLobby");
            }
            case MURDERMYSTERY -> {
                ConnecterUtil.connect(player,"MurderMysteryLobby");
            }
            case DUEL -> {
                ConnecterUtil.connect(player,"DuelLobby");
            }
            case SKYWARS -> {
                ConnecterUtil.connect(player,"SkyWarsLobby");
            }
            case THEPIT -> {
                ConnecterUtil.connect(player,"ThePit");
            }
            case RPG -> {
                ConnecterUtil.connect(player,"TourProject");
            }
            case ARCADE -> {
                ConnecterUtil.connect(player,"ArcadeLobby");
            }
            case PROTOTYPE -> {
                ConnecterUtil.connect(player,"PrototypeLobby");
            }
        }
    }
}
