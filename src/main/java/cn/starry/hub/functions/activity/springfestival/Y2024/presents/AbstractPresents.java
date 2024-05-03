package cn.starry.hub.functions.activity.springfestival.Y2024.presents;

import cn.starry.hub.api.enums.PresentsType;
import org.bukkit.Location;

/**
 * @Author: Starry_Killer
 * @Date: 2024/1/30
 */
public abstract class AbstractPresents {

    public abstract String getInternalName();

    public abstract PresentsType getType();

    public abstract Location getLocation();

}
