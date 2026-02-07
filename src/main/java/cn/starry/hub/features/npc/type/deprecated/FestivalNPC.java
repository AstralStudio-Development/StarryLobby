package cn.starry.hub.features.npc.type.deprecated;

import cn.starry.hub.menus.activity.ActivityGuideMenu;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.utils.toolkit.citizens.Skin;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Starry_Killer
 * @Created_In: 2024/1/1
 */
@Include
public class FestivalNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "festival";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        UUID uuid = player.getUniqueId();
        lines.add("&c新春活动指南");
        lines.add("&e&l右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-5.5,48.0,-11.5,45,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTcwNDAwNjQ1NDg0NCwKICAicHJvZmlsZUlkIiA6ICJjMzgyYjZmZjRhOTQ0MjYxOWQ1ZWU2Y2I5YjE3ZDQ5MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJZdXJvU2FibGVuayIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MmE1MThjNGVhN2Q2Yzc5NDk2M2IyYTBlZDdkOTgwMTZjMzkzNjhjZWRjYzhhZDdiM2EwMzljZDcyODE2OTcxIgogICAgfQogIH0KfQ==";
        String signature = "MhV1vt71M7iV5EEIssHojf2nFzNCHkFlVeQ/iL1VFepSfY9QRX9lU2bDxZxiwpj7JP5cq/7IT7lgQ9zBTVV4lFIxEysCE3FxVmfMUGp7i/yPu3afAJO7uHzuC1YbB4Rqj8QdLf0nqx+02DlXUVDCSSev26nboFd3f1j6t6t/CrAoqDfkP0AZEkDE4gen026ltJiSB4GF6pjpPtZaxzAAbhSnicrD45QpSAqRtUuVUd7gnO7/DteKJvWwM2ZsUFkR3amXuNgXVlTp17GqCh74fDlt1luln62qhhpQWpP4Geq2mFg49diJ0ONJbyXQhcyi84rm5QxjAo8ge9U66nyy0HcIL4078pSeDVZbIZW3jvW89TT/gQADZZk6M7QmrFa5LT3Hu7s6s0xiDYReauoKZb1cflTDuEPNx03xWxA57F8mSoDEu5o5UUXtJygG1zq9jAAXH063bytY0KInpUYBCfl3nfCKtM53BL8FajyCxEHGIt+IsWtK1ihJQFJjxqSGF3+7+2+RPSIgwIiRjx39slzSyIE168FRAjRZdxJiVUuyY1XLMK8I4YOYF2RfZXkE/wt81Q1Fqx9vlEJO8R/r29z1QWtcUIsYFz1fHJSIemlePzFpW3Lpq5GQb2uepgG4N+R6VO9d/WA8wuIIPVcJMnUIcJPVeK0/4XymJKE76N0=";
        return new Skin(value,signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        new ActivityGuideMenu().openMenu(player);
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

