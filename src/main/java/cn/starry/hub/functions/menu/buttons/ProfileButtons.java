package cn.starry.hub.functions.menu.buttons;

import cn.starry.hub.utils.*;
import cn.starry.hub.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileButtons {

    ItemMeta meta;
    ItemStack item;

    public ItemStack PlayerButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7会员等级: " + RankUtil.getDisplayRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"),player.getUniqueId())));
        lores.add(" ");
        lores.add(PlaceholderAPI.setPlaceholders(player, Main.getInstance().rewards ? "&7等级: &6%alonsolevels_level%" : "&7等级: &8已禁用"));
        lores.add(PlaceholderAPI.setPlaceholders(player, Main.getInstance().rewards ? "&7距离下级所需经验: &6%alonsolevels_experience_to_level_up%" : "&7距离下级所需经验: &8已禁用"));
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7成就点数: &e%starrylobby_points%"));
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, "&7神秘之尘: &b%gadgetsmenu_mystery_dust%"));
            lores.add(PlaceholderAPI.setPlaceholders(player, "&7人品值: &d0"));
            lores.add(PlaceholderAPI.setPlaceholders(player, Main.getInstance().economy ? "&7YumeGames Diamond: &b%playerpoints_points%" : "&7YumeGames Diamond: &8已禁用"));
        }

        item = new ItemBuilder(Material.SKULL_ITEM).durability(3).name(ColorUtil.color("&a角色信息")).setNewSkullOwner(player).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SuffixChangeButton(Player player) {
        List<String> lores = new ArrayList<>();
        String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix% ");
        lores.add(" ");
        if (suffix.equals("") || suffix == null) {
            lores.add(" &7后缀 暂无 ");
        } else {
            lores.add(" &7后缀" + suffix + " ");
        }
        lores.add(" ");
        lores.add(" &7在这切换 ");
        lores.add(" &7你所拥有的后缀 ");
        lores.add(" ");

        item = new ItemBuilder(Material.SNOW_BALL).name(ColorUtil.color(" &f后缀切换")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack SettingButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7允许你编辑和配置各种各样的个人设置");
        lores.add(" ");
        lores.add("&e点击以编辑你的设定！");

        item = new ItemBuilder(Material.REDSTONE_COMPARATOR).name(ColorUtil.color("&a设置及可见性")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack QuestsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7完成各种任务与挑战");
        lores.add("&7你将获得&6硬币&7,&3YumeGames");
        lores.add("&3经验&7等奖励！ ");
        lores.add(" ");
        lores.add("&e点击查看任务与挑战！");

        item = new ItemBuilder(Material.ENCHANTED_BOOK).name(ColorUtil.color("&a任务与挑战")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AchievementsButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7查看你成就解锁的进度");
        lores.add("&7以及成就点数");
        lores.add(" ");
        lores.add("&e点击查看你的成就！");

        item = new ItemBuilder(Material.DIAMOND).name(ColorUtil.color("&a成就")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack LanguagesButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7更改你的语言");
        lores.add(" ");
        lores.add("&7已翻译:");
        lores.add(" &7• &f简体中文");
        lores.add(" ");
        lores.add(" &7更多语言即将推出！");
        lores.add(" ");
        lores.add("&e点击更换你的语言！");

        item = SkullUtil.makeTextureSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=");
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color("&a选择语言"));
        meta.setLore(ColorUtil.color(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AppearanceButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add("&7为你的人物自定义外观！");
        lores.add("&f• MVP+ 会员等级颜色");
        lores.add("&f• 发光");
        lores.add(" ");
        lores.add("&e点击查看！");

        item = new ItemBuilder(Material.LEATHER_CHESTPLATE).name(ColorUtil.color("&a自定义外观")).lore(lores).setLetherColor(Color.BLUE).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack FriendButton() {
        List<String> lores = new ArrayList<>();
        lores.add("&7浏览你YumeGames好友的资料,并与你的在线好友进行互动！");

        item = SkullUtil.makeTextureSkull("eyJ0aW1lc3RhbXAiOjE0OTY0MzA4NjM5NjYsInByb2ZpbGVJZCI6IjkzYzdmMmUxMTg2MzQ5NzU4OGE2ZWI0YzUwYjRhZGZiIiwicHJvZmlsZU5hbWUiOiJUYWN0ZnVsIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lMDYzZWVkYjIxODQzNTRiZDQzYTE5ZGVmZmJhNTFiNTNkZDZiNzIyMmY4Mzg4Y2FhMjM5Y2FiY2RjZTg0In19fQ==");
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color("&a好友"));
        meta.setLore(ColorUtil.color(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack PartyButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7创建队伍与其他玩家一起游戏！");

        item = SkullUtil.makeTextureSkull("eyJ0aW1lc3RhbXAiOjE0OTY0MzA5NTY4MjMsInByb2ZpbGVJZCI6IjkzYzdmMmUxMTg2MzQ5NzU4OGE2ZWI0YzUwYjRhZGZiIiwicHJvZmlsZU5hbWUiOiJUYWN0ZnVsIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82Njc5NjNjYTFmZmRjMjRhMTBiMzk3ZmY4MTYxZDBkYTgyZDZhM2Y0Nzg4ZDVmNjdmMWE5ZjliZmJjMWViMSJ9fX0=");
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color("&a组队"));
        meta.setLore(ColorUtil.color(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack GuildButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7与其他YumeGames玩家");
        lores.add("&7一起创立公会,征服各类游戏模式");

        item = SkullUtil.makeTextureSkull("eyJ0aW1lc3RhbXAiOjE0OTY0MzA5MjU0NDMsInByb2ZpbGVJZCI6IjkzYzdmMmUxMTg2MzQ5NzU4OGE2ZWI0YzUwYjRhZGZiIiwicHJvZmlsZU5hbWUiOiJUYWN0ZnVsIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mZThiNTlmOGNjZTUxMDgwOTQyN2MzODQzY2Y1NzVmYWU4ZmU2YThiN2QxNTYwZGQ0Njk1OGQxNDg1NjM4MTUifX19");
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color("&a公会"));
        meta.setLore(ColorUtil.color(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack RecentPlayersButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7查看最近和你一起游戏的玩家");

        item = SkullUtil.makeTextureSkull("eyJ0aW1lc3RhbXAiOjE0OTY0MzA5ODc1NTMsInByb2ZpbGVJZCI6IjkzYzdmMmUxMTg2MzQ5NzU4OGE2ZWI0YzUwYjRhZGZiIiwicHJvZmlsZU5hbWUiOiJUYWN0ZnVsIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS85OTkzYTM1NjgwOTUzMmQ2OTY4NDFhMzdhMDU0OWI4MWIxNTliNzlhN2IyOTE5Y2ZmNGU1YWJkZmVhODNkNjYifX19");
        meta = item.getItemMeta();
        meta.setDisplayName(ColorUtil.color("&a近期活跃玩家"));
        meta.setLore(ColorUtil.color(lores));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack AccountStatusButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add("&7查看你的处罚记录和当前账号状态");
        lores.add(" ");
        lores.add("&e点击查看！");

        item = new ItemBuilder(Material.ANVIL).name(ColorUtil.color("&a账号状态")).lore(lores).build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack StoreButton(Player player) {
        List<String> lores = new ArrayList<>();
        lores.add(PlaceholderAPI.setPlaceholders(player, "&7从这里查看YumeGames商店！"));
        lores.add(" ");
        if (!NickUtil.isNicked(player.getUniqueId())) {
            lores.add(PlaceholderAPI.setPlaceholders(player, Main.getInstance().economy ? "&7YumeGames Diamond: &b%playerpoints_points%" : "&7YumeGames Diamond: &8已禁用"));
            lores.add(" ");
        }
        lores.add("&e点击查看！");

        item = new ItemBuilder(Material.GOLD_INGOT).name(ColorUtil.color("&aYumeGames Store")).lore(lores).shiny().build();
        meta = item.getItemMeta();
        item.setItemMeta(meta);
        return item;
    }

}
