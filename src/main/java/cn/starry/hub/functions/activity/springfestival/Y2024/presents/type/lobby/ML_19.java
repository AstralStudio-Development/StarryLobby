package cn.starry.hub.functions.activity.springfestival.Y2024.presents.type.lobby;

import cn.starry.hub.api.enums.PresentsType;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.AbstractPresents;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ML_19 extends AbstractPresents {

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
        return new Location(Bukkit.getWorld("world"),-150,43,50);
    }

}
