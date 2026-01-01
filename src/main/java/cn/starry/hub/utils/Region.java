package cn.starry.hub.utils;

import lombok.Data;
import org.bukkit.Location;

/**
 * @Author huanmeng_qwq
 * 2020/9/23
 */
@Data
public class Region {
    private Location min,max,middle;

    public Region(Location min, Location max, Location middle) {
        this.min = min;
        this.max = max;
        this.middle = middle;
    }
}
