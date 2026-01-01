package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.SkullUtil;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.StainedGlassBlock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DevelopersButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack HeadButton(Player player, String name, boolean isEnable, String a, String b, String base) {
        this.item = isEnable ? SkullUtil.makeModernSkull(base) : SkullUtil.makeModernSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTNkNzU3NWE2ZDhmZWZmYjIyNzgzZTFlMmI2ZGZiZTI5OTZmYWRiYzk3NzlmODhlNzQyYTAxMmM2MmFhIn19fQ==");
        ArrayList<String> lores = new ArrayList<String>();
        lores.add(" ");
        lores.add(" &7职务&f " + a);
        if (!b.equals("")) {
            lores.add(" ");
            lores.add(" &7\"" + b + "\" ");
            lores.add(" ");
        } else {
            lores.add(" ");
            lores.add(" &7\"" + "无" + "\" ");
            lores.add(" ");
        }
        return new ItemBuilder(this.item).name("&e" + name).lore(lores).build();
    }

    public ItemStack TypeButton(String name, String str, String type) {
        List<String> lores = new ArrayList<>();
        lores.add("&7");
        lores.add(str);
        lores.add(" ");

        item = new ItemBuilder(Material.valueOf(type + "_STAINED_GLASS")).name(CC.translate(name)).lore(lores).build();
        return item;
    }

    public ItemStack More() {
        List<String> lores = new ArrayList<>();
        lores.add("&7已离职/曾经合作的名单列表/第三方社区支持");

        item = new ItemBuilder(Material.RED_WOOL).name(CC.translate("&b特别感谢名单")).lore(lores).build();
        return item;
    }

    public ItemStack BackToProfile() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至在职工作人员列表");
        item = new ItemBuilder(Material.ARROW).name(CC.translate("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack Close() {
        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&c关闭")).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SpecialThanksButton(String name, String msg, String base) {
        this.item = SkullUtil.makeModernSkull(base);
        ArrayList<String> lores = new ArrayList<String>();
        lores.add("");
        lores.add("&7时间节点&6 " + msg);
        return new ItemBuilder(this.item).name("&e" + name).lore(lores).build();
    }

}
