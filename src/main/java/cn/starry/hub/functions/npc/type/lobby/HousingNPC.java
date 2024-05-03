package cn.starry.hub.functions.npc.type.lobby;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.functions.npc.AbstractNPC;
import cn.starry.hub.utils.ItemBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
public class HousingNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "housing";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
                lines.add("&6&l版本更新！");
                lines.add("&b家园世界");
                lines.add(PlaceholderAPI.setPlaceholders(player, "&e当前有" + Main.getInstance().getConfig().getString("total.ThePit") + "名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-27.5,93.0,14.5,130,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTY0NjA3NjY0MTE0NSwKICAicHJvZmlsZUlkIiA6ICIxYWZhZjc2NWI1ZGY0NjA3YmY3ZjY1ZGYzYWIwODhhOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJMb3lfQmxvb2RBbmdlbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84NDBhMGJiYjhhOTQ0NzNjNjE0NmE5YWU4ODQ3ZTQwNjQxMDBmZGQ0NmVmYzcwODAzNDVmZjE1MjgwYWQ4NzIxIgogICAgfQogIH0KfQ==";
        String signature = "nx76X1zk44QtpHoUbZfRNLYUEnsdTejLA5e7BGq/plNlXLmnBCGm053wqJvSgGc2oatQSSaOkRjp1ezR/lesg8LhTtnyH728VGoEW4Du2fImdMBEHK/1YevAxzb4F4bH+6RIoHiTZZVHZe/HwTI6NUbP3krMS/SwKF9R4rIvskiffR8ucjbkfQK3bV6v071uO8FDudiMo89DvWIYN1gxWYW546G4dk16eC3DTsjOX96SYRYhchc7tDDwu5koAAwjSXnYCItGDNz8/RUOslbTX9nAAQxLcWLHKvGD2ffk5O1w4O9J4OnW2lFczWb5yebmD4JrXWDevssoozO8AxNEpIt/Tle5iyZEId8qoE70cjNIhLzhLwxaO2exFig+V/4orEnKdxdbSnR72y452bqd3WTFNSYHqaQBENdgGkCwJpmjSKzSPrDC28z1KnH1dgdXYjTd+WLTsVI/PSDQHbQ+vfyIKnvkKiMtpfjqUZc3rBgkxUxVGAzW7AQfgHZWWu+Xs10RYZRo3ZyEve8uhxe/Wvuv0c8/1fL3l3BPPiDjONNxksIs6yaGrXWTzPKJuZOAdvJOVQuEaT4H3/vbpM9eXBK7NDeI5BFfO+ZxYc/wRcB2Bt+pdMGVH9jjL947S7rkoqUhYOv40hbzE+jJA90tbVWMmm1w2AcoAl5bTbpSZIE=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_ThePit#1");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return null;
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return new ItemBuilder(Material.GOLD_HELMET).build();
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return false;
    }
}
