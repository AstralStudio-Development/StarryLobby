package cn.starry.hub.functions.npc.type.megawalls;

import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.Main;
import cn.starry.hub.functions.npc.AbstractNPC;
import net.jitse.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.yuzegod.megawallslobby.inventory.InventoryManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/28
 */
public class QuestMasterNPC extends AbstractNPC {

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
                lines.add("&b任务大师");
                lines.add("&f右键点击");
                break;
        }
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-117.5,88.0,105.5,45,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwNjE1NDYwNTMwMSwKICAicHJvZmlsZUlkIiA6ICI0NmE5OWFkMDY0ZmI0MDViODcxOTQzZGIxZDFhMDA5ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQcm9IYXdrayIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NjJjY2YyNDFlODkyMGFiZjZiOGFlMjViODM2NGQ3ZGVjNTRiYmRjYzU1MDc1MTRhMTE4NjlhYjAzZmUwOTcwIgogICAgfQogIH0KfQ==";
        String signature = "WqaaJIfTPpvewygRR0Vdp5zkrbBtxmIGUxQuuZ/+skVlEiymak5Hq9bmO4Wtg7DLZ3ZeG9PBAYZK2zyjwT/Dne5ZsFCWbfAH8DdTRoyfmEriWPa8ZBEooWaKns2xXG4xwCOmsELxX/YR+gG4pEEICoN8znr9ajVelRyqf5gMUBlmo70rLY420X1p4HgotTKpREt88VxzZlKT6IIax1S4D0ZAuSWenzgl54Ni3VMbsGZ57sOrhjplroygiA/CAoYDw8TAyLkF2HmNRHM7FCV2x+s97xQcqKagFI7KqDzbMwNneTlu42wNGrcUiCmny2tIbprsTuutAYBOi3yD1rGpQCyDgStLo2IKCHHBMatwRNPjEP6Wbt9RVkcyANwg0zPqQsyBKFT747jdkaXRTUNJNh+g5PAkLvcUgIrSfrnuVga6rhxMZAAQ2Gasmg+URHUT0KTUQozC/BFbBM8n6uHJM6hnxyKkX1JosuSs5NFo61reReFnK4K/AiQi6wicDTcXv49jwMluqhjhEogmve1L1IzGkIqVLnieJ6q0Um/bcBi4WqPjw2h/u0xqiRu52yyvNFdh+m4iDY7nb1TQ8+G9A9f9n4+Vtre8O8lqvNaZZSjHwIlqIlOUFzHgVhPTImovdzjCCYPKg/jxDmyYEE0CtpxAaZU4FEiIdPZ4fb2mFD8=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        InventoryManager.TASKMENU.open(player);
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

}
