package cn.starry.hub.functions.menu.activity.button;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.ArrayList;
import java.util.List;

public class LinkItemButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(" &7点击打开书本页面");
        lores.add(" &7以了解本次活动内容");
        lores.add(" ");
        lores.add(" &e点击打开！");
        lores.add(" ");

        return new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).name(CC.translate(" &c活动链接")).lore(lores).shiny().build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton, ItemStack currentItem) {
        BookUtil.openPlayer(player,
                BookUtil.writtenBook()
                        .title("")
                        .author("")
                        .pages(
                                new BookUtil.PageBuilder()
                                        .add(
                                                CC.translate("&8不知道活动具体流程?")
                                        )
                                        .newLine()
                                        .add(
                                                CC.translate("&8点击以下链接查看详情！")
                                        )
                                        .newLine()
                                        .newLine()
                                        .add(
                                                BookUtil.TextBuilder
                                                        .of(CC.translate("     &b&l&n点我前往！"))
                                                        .onHover(BookUtil.HoverAction.showText(CC.translate("&b看看他的")))
                                                        .onClick(BookUtil.ClickAction.openUrl("https://space.bilibili.com/447409448?spm_id_from=333.337.0.0"))
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }
}
