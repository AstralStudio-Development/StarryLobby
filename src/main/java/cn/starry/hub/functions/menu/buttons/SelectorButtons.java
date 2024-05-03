package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.api.enums.GameOwned;
import cn.starry.hub.api.enums.GameType;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.utils.ItemBuilder;
import cn.starry.hub.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SelectorButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Button(Player player, GameOwned game) {
        String online = PlaceholderAPI.setPlaceholders(player,game.getOnlinePapi());
        List<String> lores = new ArrayList<>();
        List<String> description = game.getDescription();
        lores.add(game.getType().getFormattedDisplayName());
        lores.add(" ");
        for (String line : description) {
            lores.add(line);
        }
        double multiple = Main.getInstance().getData().getBoosterMultiple(game.toString(),"booster") != null ? Main.getInstance().getData().getBoosterMultiple(game.toString(),"booster") : 1.0;
        if (multiple > 1.0) {
            String booster = Main.getInstance().getData().getBoosterData(game.toString(),"name");
            lores.add(" ");
            lores.add("&7硬币倍数: &6+" + multiple + "倍");
            lores.add("&b" + booster + "&8开启的硬币增倍器");
        }
        lores.add(" ");
        lores.add("&a▸ 点击连接");
        lores.add("&7" + online + " &7名玩家正在游戏中！");

        item = new ItemBuilder(game.getItemStack()).amount(Math.max(Integer.parseInt(online), 1)).name(ColorUtil.color(game.getDisplayName())).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SMPButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(GameType.RPG.getFormattedDisplayName());
        lores.add(" ");
        lores.add("&7创建属于你自己的SMP服务器，");
        lores.add("&7和朋友们一起游玩吧");
        lores.add(" ");
        lores.add("&a▸ 点击连接");

        item = new ItemBuilder(Material.GRASS).amount(1).name(ColorUtil.color("&aYumeGames SMP")).lore(lores).build();
        meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

}
