package cn.starry.hub.functions.npc.type.lobby;

import cn.starry.core.Core;
import cn.starry.core.functions.achievement.AchievementManager;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.npc.AbstractNPC;
import com.bnstra.npclib.api.skin.Skin;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/10
 */
public class AchievementNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "achievement";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("&b成就指南");
        lines.add("&f右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), -36.5, 28.0, -11.5, -65.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYzMTI4MDA2MzQ5MiwKICAicHJvZmlsZUlkIiA6ICI0ZGU4MjQxNjJkZTU0MzU5YWFlMDBmMzQ1ZmMyZTY0MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOYXRoYW5fS2luZyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84NTk1NzA5YzIzM2VlMmI5YmVkNzY0NDU4YzA5Mjg2NzQyMDFjY2JlMmE5NDUzMDgwMTI4NzE1MmE3MWM5NTY0IgogICAgfQogIH0KfQ==";
        String signature = "RhMYwAKj3tBwoifCaqGYgND1Z+iLGW96GBg/hRQmBmsV0uVnvkXDZuVRTebjA4l0yMPq/yzfw9X1WskfQ31Hw6Hl28w5tREzY88WLdACaiVh+KwGSCuoOXsyRDtkDHYORGnESiZhxNJAPOayfXd1OZiXgGJzmLASlT33nAy0CUle7TsRP/y/MuCXK3DXKguf3D75LC9OndQflnegy4v3bfC2Hf6eKthUKcz1SGMWy2CY8H6xZx9FUU3jUGXfjf8tO/h9fNTjaVo2uW22ctz01LPi4duGivSnUSU5WPaaBdcb8hPew9DWi7OOqJa3zJU0AtI53bujSo6Qq2QfdyTbjfV3bHZbfazO63mmUNHVRAmJ0CUcpAdHmxtlB6X5XGavCcpamz3wg87urPRSS68av+q4/Fwf+JsdZko+ZiDPDZNdCWdaigQiHd3gTHv/4rrCV+S6AcSyaCUmyhQDgJjS9scHPEjX5u6WUcwD5ziwiwSaW4xDzeM7bNtJgFNhTGwQ4nTpGKPfIovnMklFJTBA36+H/rjMuaPTWieNTED5sqDPjBKSJG39EFsFL52SLlSLt6Pg1kLVbhybyEhZwS1vNZr6DSWv9krBjOdMDt9TjIjvOv6l285bqJLl9xR4OsEUvy5YrMg4+gPPRNnRC+MvJWRPk4aJMVMCMbkIIF1dNQw=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BookUtil.openPlayer(player, BookUtil.writtenBook().title("").author("").pages(new BaseComponent[][]{new BookUtil.PageBuilder().add(CC.translate("&6&l成就")).newLine().add(CC.translate("&7 在幻梦茶会上")).newLine().newLine().add(CC.translate("&01. 完成任务:")).newLine().add(BookUtil.TextBuilder.of(CC.translate("     &2&l&n查看任务")).onHover(BookUtil.HoverAction.showText(CC.translate("&e点击查看成就列表！"))).onClick(BookUtil.ClickAction.runCommand("/super 8JLo4ZPpbi3IXMyVeyx0Tw7wUX1gjh5X")).build()).newLine().newLine().newLine().add(CC.translate("&02. 赚取成就点数:")).newLine().add(BookUtil.TextBuilder.of(CC.translate("     &6&l成就点数: " + Core.getInstance().getMongoDB().getAchievementPoints(player.getUniqueId(),"points"))).onHover(BookUtil.HoverAction.showText(CC.translate("&e这是你的成就点数！"))).build()).newLine().newLine().newLine().add(CC.translate("&03. 获得奖励:")).newLine().add(BookUtil.TextBuilder.of(CC.translate("     &2&l&n查看奖励")).onHover(BookUtil.HoverAction.showText(CC.translate("&c此功能于未来上线！"))).build()).build()}).build());
        AchievementManager.unlockAchievement(player, AchievementManager.getAchievement("Achievement"));
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return null;
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return null;
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return true;
    }
}
