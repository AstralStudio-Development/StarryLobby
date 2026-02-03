package cn.starry.hub.features.npc.type.lobby.fun;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.features.npc.AbstractNPC;
import com.bnstra.npclib.api.skin.Skin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2025/5/4
 */
public class GuardNPCL extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "guardl";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&b守卫");
        lines.add("&e&l右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-146.5,39.0,-10.5,-90,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcyNjc4NTY4NTUzNiwKICAicHJvZmlsZUlkIiA6ICJjY2MxNGM2ZDUwMDE0MjBmYmMxYjkyMTM2Y2JmOWU4MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJab25lX1gwODE1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ0OTY1YzQyNDlhZTQ0ZDY1OTM0MzAzNWM3OGMxY2ZjNGZiNmViOGY4NDM1N2IxOTFlYWJkYzBkOGIyZDA5MDUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==";
        String signature = "Bq88IAYKtAnVPxHMmOQpURIgNVEQKzP6oYyQXiXSqo407goSgLQaOM6iqqfEfo4ET+8ixBzGSlwAxjHsyt1tKuYgFvwypbdnATg6+bUE8EmfI5u+mK4YY1Jtc4IndsOcj+Xhvb76kRTzmBQI5ZXbPcGW4tRDO42yT8bGfL7YOx/Dv+A4ltTeyNrI/mpv7bXKW/QXYzN3NWz/9JbRDTIw+kYWL9e4DsdDnoCE85QUXBEhdapGuZUhsSmYrwUP+nkc7+rkmUZWGPF9BfbQfszkQKLypEFVdYObDN1Ha+Mw9x42HjP1rFkVliiGPXma4MpX2YFfbcfiIl5PFk8pvrSUe7afDpCfg4u5Z1HsTRBE6ztZNGbm1D+9WKduKrGMJSgyVM2nAWXxmFhXUjulNFrvw3LKxxXGnaVLXvNwVHC4YATdaspbI1DFifqp4xr+irQ8y63mck4LLVx/lKPc++TW/12wKQWuQjs0WanIvbuE7lDpUY5vbkcC3tdrLpKh1DTuQmDS+eMVqYF1tokz+rdJ9RAUbj+xGozIbaDD9cc09FD3LYQCySyDsHqqOqeNBCWw7W16Jt/uz50pW/B2kOb/uy6WyNCkPmiIYO4VNwjBZE2jPKDhK9AJbMg4Cu5vfrGtGdc1oB6b40xgsDLENLz3f8TTW1GFb2dsaQYnqQhbdTw=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        sendMessage(player);
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.IRON_SWORD).build();
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return new ItemBuilder(Material.GOLDEN_HELMET).build();
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return false;
    }

    private void sendMessage(Player player) {
        player.sendMessage(CC.translate("&e[NPC] 守卫&f: 你好."));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);
    }
}

