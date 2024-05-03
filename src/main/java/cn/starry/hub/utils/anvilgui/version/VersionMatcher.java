package cn.starry.hub.utils.anvilgui.version;

import org.bukkit.Bukkit;

public class VersionMatcher {
    public VersionWrapper match() {
        String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
        try {
            return (VersionWrapper)Class.forName(this.getClass().getPackage().getName() + ".Wrapper" + serverVersion).newInstance();
        }
        catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalStateException("Failed to instantiate version wrapper for version " + serverVersion, exception);
        }
        catch (ClassNotFoundException exception) {
            throw new IllegalStateException("AnvilGUI does not support server version \"" + serverVersion + "\"", exception);
        }
    }
}

