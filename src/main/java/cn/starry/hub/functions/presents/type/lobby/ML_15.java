package cn.starry.hub.functions.presents.type.lobby;

import cn.starry.core.api.enums.PresentsType;
import cn.starry.hub.functions.presents.AbstractPresents;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ML_15 extends AbstractPresents {

    @Override
    public String getInternalName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public PresentsType getType() {
        return PresentsType.MAINLOBBY;
    }

    @Override
    public Location getLocation() {
        return new Location(Bukkit.getWorld("world"),-220,48,37);
    }

}
