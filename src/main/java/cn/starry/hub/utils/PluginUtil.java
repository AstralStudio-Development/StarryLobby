package cn.starry.hub.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Starry_Killer
 * @since 2023/8/25 13:20
 */
public class PluginUtil {

    public static int getPluginCount() {
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        return plugins.length;
    }

    public static List<String> getPluginNames() {
        List<String> pluginNames = new ArrayList<>();
        Plugin[] plugins = Bukkit.getPluginManager().getPlugins();
        for (Plugin plugin : plugins) {
            pluginNames.add(plugin.getName());
        }
        return pluginNames;
    }

}
