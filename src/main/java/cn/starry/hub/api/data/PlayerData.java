package cn.starry.hub.api.data;

import cn.starry.hub.api.enums.PlayerState;
import cn.starry.hub.api.enums.ProfileState;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerData {
    public static HashMap<Player, PlayerState> EDIT;
    public static HashMap<Player, Integer> TUTORIAL;
    public static HashMap<Player, ProfileState> PROFILE;

    static {
        PlayerData.EDIT = new HashMap<>();
        PlayerData.TUTORIAL = new HashMap<>();
        PlayerData.PROFILE = new HashMap<>();
    }
}
