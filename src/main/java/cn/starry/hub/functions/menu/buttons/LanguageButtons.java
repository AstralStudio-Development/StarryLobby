package cn.starry.hub.functions.menu.buttons;

import cn.starry.core.utils.chat.CC;
import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.SkullUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LanguageButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack Close() {
        item = new ItemBuilder(Material.BARRIER).name(CC.translate("&c关闭")).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack BackToProfile() {
        List<String> lores = new ArrayList<>();
        lores.add("&7返回至个人档案");
        item = new ItemBuilder(Material.ARROW).name(CC.translate("&a返回")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack ChineseButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7将你的语言更改为简体中文 ");
        lores.add(" ");
        lores.add("&7已翻译:");
        lores.add("   &7• &fAll Lobbies");
        lores.add("   &7• &fArcade Games");
        lores.add("   &7• &fBedWars");
        lores.add("   &7• &fBuild Battle");
        lores.add("   &7• &fDuels");
        lores.add("   &7• &fHousing");
        lores.add("   &7• &fMega Walls");
        lores.add("   &7• &fMurder Mystery");
        lores.add("   &7• &fPit");
        lores.add("   &7• &fSkyBlock");
        lores.add("   &7• &fSkyWars");
        lores.add("   &7• &fTourAtlus II");
        lores.add("   &7• &fUHC Champions");
        lores.add(" ");
        lores.add("&e点击更换你的语言！");

        item = SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5YmMwMzVjZGM4MGYxYWI1ZTExOThmMjlmM2FkM2ZkZDJiNDJkOWE2OWFlYjY0ZGU5OTA2ODE4MDBiOThkYyJ9fX0=");
        meta = item.getItemMeta();
        meta.setDisplayName(CC.translate("&a简体中文"));
        meta.setLore(CC.translate(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack EnglishButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7Change your language to English ");
        lores.add(" ");
        lores.add(" &7Currently available ");
        lores.add("     &7• &fAll Lobbies");
        lores.add(" ");
        lores.add(" &eClick to change your language! ");

        item = SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNhYzk3NzRkYTEyMTcyNDg1MzJjZTE0N2Y3ODMxZjY3YTEyZmRjY2ExY2YwY2I0YjM4NDhkZTZiYzk0YjQifX19");
        meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(" &fEnglish"));
        meta.setLore(CC.translate(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack JapaneseButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" &7言語を日本語に変更します ");
        lores.add(" ");
        lores.add(" &7現在利用可能 ");
        lores.add("     &7• &fAll Lobbies");
        lores.add(" ");
        lores.add(" &eクリックして言語を変更！ ");

        item = SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDZjMmNhNzIzODY2NmFlMWI5ZGQ5ZGFhM2Q0ZmM4MjlkYjIyNjA5ZmI1NjkzMTJkZWMxZmIwYzhkNmRkNmMxZCJ9fX0=");
        meta = item.getItemMeta();
        meta.setDisplayName(CC.translate(" &f日本語"));
        meta.setLore(CC.translate(lores));
        item.setItemMeta(meta);
        return item;
    }

}
