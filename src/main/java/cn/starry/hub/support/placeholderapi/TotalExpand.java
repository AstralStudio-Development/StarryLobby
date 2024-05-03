package cn.starry.hub.support.placeholderapi;

import cn.starry.hub.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class TotalExpand extends PlaceholderExpansion {

    @NotNull
    public String getIdentifier() {
        return "hub";
    }

    @NotNull
    public String getAuthor() {
        return "Starry_Killer";
    }

    @NotNull
    public String getVersion() {
        return Main.getPlugin(Main.class).getDescription().getVersion();
    }

    public boolean canRegister() {
        return true;
    }

    public String onRequest(OfflinePlayer player, String params) {
        FileConfiguration yml = YamlConfiguration.loadConfiguration(new File(Main.getPlugin(Main.class).getDataFolder(),"servers.yml"));
        int TotalPlayer = 0;
        for (String str : yml.getStringList(params)) {
            int ren = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%bungee_" + str + "%"));
            TotalPlayer += ren;
        }
        return String.valueOf(TotalPlayer);
    }

}

