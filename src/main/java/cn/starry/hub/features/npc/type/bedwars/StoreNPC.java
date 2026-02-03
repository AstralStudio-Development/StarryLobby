package cn.starry.hub.features.npc.type.bedwars;

import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.Skin;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Created_In: 2023/11/20
 */
@Include
public class StoreNPC extends AbstractNPC {
    @Override
    public String getNpcInternalName() {
        return "store";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        List<String> lines = new ArrayList<>();
        lines.add("&b货摊");
        lines.add("&f右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"),-21.5,15.0,21.5,-135,0);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTc0NjY4NDU4MzUwMSwKICAicHJvZmlsZUlkIiA6ICI1MjhlYzVmMmEzZmM0MDA0YjYwY2IwOTA5Y2JiMjdjYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQdWxpenppIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzVmYjM4NGEwNGM0Y2Y1N2QyOTU0Yjc3ZDA2MGQ4OTA3YjljMzY2YmM1ZDIxMzUzZmJmNDU0OWUzMmNmMDBjZmIiCiAgICB9CiAgfQp9";
        String signature = "o4bUWYDw2DF9Xbuh++vHDHKDBA1i7ZQEPY+AKEDSQLYlLkt2Ab9yeA3GKQN7utlgso7XtOyxpCijotkZOCISKrGIn8ke+F1UEfjm/MXZsm0Pi/Z30N+hvU0Ld5x8YhkhZW49TeCycLS9vFlKr3QGs8UbSZXsmUlwguIRl3VD3zLu5eg+tabw8PU/MyJU/n24bUDeQ/BsXCzmNkDsg+QSks6oMKUrJ52//cFU8VGDw+3j8jDVaZ2YKZHgucf3EPFVlGCWekLBW7THtLIMQV3eBZ4Ddejotr+OqV36dn2Ap2+bKsCJP4C68NPig2AQ+Nra2kWz3z9VA33HuxBTlnsdsiO/r6cer9+yG3FnevsEKEDZzfdS9M+wBbwpo9sXPIDb2uwXEQASMUBwjbPPOWn5hwR+a1rU1U8AIByFtHlmWz4O8HCkzKxBgEfxrt5zwozavY9MM2cVjeyWv/gA/JqlglJLO14nwzboFxNgwl0yK+FTfrxQ9zjR4KnW5iEnlIvOQXRA8/Oc6dl9i2Gmxpdl79s7KC0LSkEqTzlV7smucTjl9detCqNhjwqcadqRmJiyokPDzPepA4qqfww+B3hhtuHOBd9+WhOgf1d7AxOYRE20DED5EXxOo5dTgeV66dH6WCXY7oJN0mGtY7Ei+IJMWO/wkul2Qb3z6hROj/AlYfc=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        //BungeeUtil.sendServer(player,"G_TourP");
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


