package cn.starry.hub.functions.npc.type.megawalls;

import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.utils.BungeeUtil;
import cn.starry.hub.Main;
import cn.starry.hub.functions.npc.AbstractNPC;
import me.clip.placeholderapi.PlaceholderAPI;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.yuzegod.megawallslobby.MegaWallsLobby;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/28
 */
public class StandardNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "quests";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        switch (languageType) {
            default:
            if (MegaWallsLobby.isActiveMode()) {
                lines.add("&6&l神话游戏&6持续&a&l" + getFormattedTime(MegaWallsLobby.getActiveMode() - System.currentTimeMillis()));
            }
            lines.add("&7❖ &b标准");
            lines.add(PlaceholderAPI.setPlaceholders(player, "&7当前有 &b" + Main.getInstance().getConfig().getString("total.MegaWalls") + " &7名玩家正在游玩"));
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-105.5,90.0,111.5,90,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwNjQ0NDIwMzk3MywKICAicHJvZmlsZUlkIiA6ICI0NmNkY2U3ZDc0NjA0YTExYTMwMjNhODVjZmI3MzkzMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJtdXJpbG96cTciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVjNDI1N2ExYTk3MTZmM2M3YzlhYmM0MmMyYWUyZWYxNzNkODEzN2U3MWJmOGQ5NzlkODZjZmZjNWIwOWIxNCIKICAgIH0KICB9Cn0=";
        String signature = "FXTsVHMbvgt8k4QLb0tINsOGL4xGO7ZVylBi+xPPNlNom02EC/gLNWU8OmZFIQitYggvzkJo7iUHsZ+YJBOs4OQCgKwjR62NF+1HiGJ30e3S35HY+EXyNf+QwD+/6YPy/NHAlvSbW4B+He3OwbZAJ8+P53fajbdTT5Zmm3S782I45+g0DvUVX8CcwjDaRBTJouGN6WHGcWnkcQFPfPNSFYWtl1BKPWufBSWis1I4oeDE8qDCCszpHvnKUlCtCj7FfIKu/helS9eJBvoLoYsjs62nprK875tU0oZohzU30aVxKb+53ShOvWruBKqYDYoJad8QuFZda/8yIT9poYtjPD1/pMPkCysxjNYMQMri6ceCvljhfVMy4Fr0XRXE/gtZdDxcACsQw92KrCyRXRsb6XNPz/yxgUNbpKc63dBe7FWl8//AMi+4DQ5sZBy1fdhngnKJ+N0FXivq1NmpKP7qlCNMW3hFWRUfhFcA3Q6RGViiJJKupLnPwsN3mo/gT4FxyRPNhOX8Mt5cKhqn3bf52T5qqaLLdrvruKXJeHXp6m4VySIdipm7tB6TssiMBGTeoSJCf7GMpP6MElvoTTv5WYkZ8GTQo6TeVatbibO3L/pSULT6RF6betVd4uMgJ17Gzn/8Wpnh9gDfI2qr1tr5TsEnUg+f/Wyb3dvM9MMCxF0=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        BungeeUtil.sendServer(player,"G_MegaWalls#1");
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
        return false;
    }

    private String getFormattedTime(long l) {
        int time = (int)l / 1000;
        int min = (int)Math.floor(time / 60);
        int sec = time % 60;
        String minStr = min < 10 ? "0" + min : String.valueOf(min);
        String secStr = sec < 10 ? "0" + sec : String.valueOf(sec);
        return minStr + ":" + secStr;
    }

}
