package cn.starry.hub.features.presents.type.lobby;

import cn.starry.core.api.enums.PresentsType;
import cn.starry.hub.features.presents.AbstractPresents;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ML_14 extends AbstractPresents {

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
        return new Location(Bukkit.getWorld("world"),-183,47,45);
    }

}

