package cn.starry.hub.functions.npc.type.prototype;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.functions.npc.AbstractNPC;
import cn.starry.hub.utils.ConnecterUtil;
import com.bnstra.npclib.api.skin.Skin;
import me.clip.placeholderapi.PlaceholderAPI;
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
public class MurderMysteryNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "murder_mystery";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        UUID uuid = player.getUniqueId();
        lines.add("&6❖ &b密室杀手");
        //lines.add(PlaceholderAPI.setPlaceholders((Player)player, (String)("&7当前有 &b" + StarryLobby.getInstance().getConfig().getString("total.RPG") + " &7名玩家正在游玩")));
        lines.add(PlaceholderAPI.setPlaceholders((Player)player, "&c即将到来"));
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), -29.5, 74.0, -21.5, 90.0f, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTYyMzQwODgwNzc1MSwKICAicHJvZmlsZUlkIiA6ICIzZjM4YmViZGYwMWQ0MjNkYWI4MjczZjUwNGFiNGEyNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJjazM0Nzk0MjM1NzUzNzMxIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E4ODg3NmFkYWRhNWVlODRlNzQ5YzA1MzY2OGIyOTc3YzkyMzFkYmVlNmVjNGRkMTUyOTFmNzA1ZTQ4OGE1NDAiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
        String signature = "X27T+and768rvkA4qx5tYu7ik3cmQCS0xlr2xjTGZUeSmgUFxFrwFtA6HQ0+SuNq255fTunkf70O+dd2/QBKp2NZI/bC2K/GK5jlwC978kny88V5WSOBx08+GEYO1II0a+t/oDiTLr83nDmntEGh74LgbM/icb4cVRwwfH6qQ19Vkzx9hSowZi00gpIsTxgqTYE4Sc5UsyBHHuS7r+uqh9lPpO8hdsEvoIu5ePZpoS/CJseNB0DyjtHr4SYinoLBTRNx6eYe7ofAtPdTIQvNcHN/Bk+5IpHG7+skqa53DLmUr+7QKZI9Q9YrSJd+7ioBVzflbZYzLHN4H3oc4pcfb/wp5JdSzwyANN6fEMnOIQwLuSzNgzYjizQYJlcko0BubVTGgZICZ9pUX4L/L4z+E0btDOg0UkS0Q7eWr/8d2HlVCuxGq+kMdik3BoJFRX6uV9ThdPR4w7VmBRtLsFqO/+pfmBFHcKPIM2cSHGNom8L8kgcKWtco/OnVJRCN8BzMBXL4FEiHgUqR8M+AWqPRYQ6fbIMFE+2y+PYaGkyEPcsYbSG2kwg+4SUHhpxZ9A4F4Qhg20af9UConcMdI9rbeqs05gwzfQLgrcAWbwIDAemFmny62jvJRNkYRCamh38oNzWTnyU2tnha+Ufa/cnDD68T3ek9NF24J5ghI7YbcgA=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        ConnecterUtil.connect(player,"MurderMystery");
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.BOW).build();
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return null;
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return false;
    }
}
