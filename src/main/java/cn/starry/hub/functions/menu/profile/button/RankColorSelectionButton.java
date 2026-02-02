package cn.starry.hub.functions.menu.profile.button;

import cn.starry.core.Core;
import cn.starry.core.functions.rank.RankColors;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RankColorSelectionButton extends Button {

    private final RankColors rankColors;
    private final Material material;

    public RankColorSelectionButton(RankColors rankColors, Material material) {
        this.rankColors = rankColors;
        this.material = material;
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(CC.translate("   &a" + rankColors.getColorChinese() + "色会员颜色   "));
        lores.add("");
        lores.add("   &7在&bStar&c+&7处改变“+”的颜色   ");
        lores.add("   &7为" + rankColors.getColorChinese() + "色，将它变为&bStar&" + rankColors.getColorChar() + "+   ");
        lores.add("");
        lores.add("   &7在TAB列表，聊天中   ");
        lores.add("   &7与进入大厅时展示   ");
        lores.add("");
        if (Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(), "rankColor").equalsIgnoreCase(rankColors.toString())) {
            lores.add("   &a当前已选择   ");
            lores.add(" ");
        } else {
            lores.add("   &a+ &f点击选择   ");
            lores.add(" ");
        }
        return new ItemBuilder(material).name(CC.translate(" ")).lore(lores).build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        player.closeInventory();
        if (!Core.getInstance().getMongoDB().getPlayerData(player.getUniqueId(), "rankColor").equalsIgnoreCase(rankColors.toString())) {
            Core.getInstance().getMongoDB().updatePlayerData(player.getUniqueId(), "rankColor", rankColors.toString());
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
            player.sendTitle(CC.translate("&a更换成功"), (CC.translate("&f已成功更新颜色")), 10, 10, 10);
        } else {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.sendTitle(CC.translate("&c更换失败"), (CC.translate("&f当前已经为此颜色")), 10, 10, 10);
        }
    }
}
