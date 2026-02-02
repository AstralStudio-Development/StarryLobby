package cn.starry.hub.functions.menu.settings;

import cn.starry.hub.functions.menu.settings.button.SettingTypeButton;
import cn.starry.hub.functions.menu.settings.button.chat.MessageFilterateButton;
import cn.starry.hub.functions.menu.settings.button.chat.MessageJoinButton;
import cn.starry.hub.functions.menu.settings.button.chat.MessageReceiveButton;
import cn.starry.hub.functions.menu.settings.button.chat.MessageReminderButton;
import cn.starry.hub.functions.menu.settings.button.lobby.LobbyLauncherButton;
import cn.starry.hub.functions.menu.settings.button.lobby.LobbyTimeButton;
import cn.starry.hub.functions.menu.settings.button.lobby.LobbyVisibilityButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import cn.starry.hub.utils.menu.buttons.CloseButton;
import cn.starry.hub.utils.menu.buttons.GlassButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Stalyer
 * @Date: 2025/8/24
 */
public class SettingsMenu extends Menu {

    public Menu parent;
    public int type;

    public SettingsMenu(Menu parent, int type) {
        this.parent = parent;
        this.type = type;
    }

    @Override
    public String getTitle(Player player) {
        switch (type) {
            case 0:
                return "               &0大厅设置     ";
            case 1:
                return "               &0聊天设置     ";
            case 2:
                return "               &0社交设置     ";
            default:
                return "Unknown";
        }
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> button = new HashMap<>();

        for (int i = 9; i < 18; i++) {
            button.put(i,new GlassButton("&8⇧ &7类别","&8⇩ &7设置",false));
        }

        switch (type) {
            case 0:
                button.put(3, new SettingTypeButton(parent,true,0));
                button.put(4, new SettingTypeButton(parent,false,1));
                button.put(5, new SettingTypeButton(parent,false,2));

                button.put(12,new GlassButton("&8⇧ &7类别","&8⇩ &7设置",true));

                button.put(20, new LobbyVisibilityButton(true,parent,type));
                button.put(29, new LobbyVisibilityButton(false,parent,type));

                button.put(22, new LobbyTimeButton(true,parent,type));
                button.put(31, new LobbyTimeButton(false,parent,type));

                button.put(24, new LobbyLauncherButton(true,parent,type));
                button.put(33, new LobbyLauncherButton(false,parent,type));
                break;
            case 1:
                button.put(3, new SettingTypeButton(parent,false,0));
                button.put(4, new SettingTypeButton(parent,true,1));
                button.put(5, new SettingTypeButton(parent,false,2));

                button.put(13,new GlassButton("&8⇧ &7类别","&8⇩ &7设置",true));

                button.put(19, new MessageReceiveButton(true,parent,type));
                button.put(28, new MessageReceiveButton(false,parent,type));

                button.put(20, new MessageJoinButton(true,parent,type));
                button.put(29, new MessageJoinButton(false,parent,type));

                button.put(22, new MessageFilterateButton(true,parent,type));
                button.put(31, new MessageFilterateButton(false,parent,type));

                button.put(24, new MessageReminderButton(true,parent,type));
                button.put(33, new MessageReminderButton(false,parent,type));
                break;
            case 2:
                button.put(3, new SettingTypeButton(parent,false,0));
                button.put(4, new SettingTypeButton(parent,false,1));
                button.put(5, new SettingTypeButton(parent,true,2));

                button.put(14,new GlassButton("&8⇧ &7类别","&8⇩ &7设置",true));
                break;
            default:
                break;
        }

            button.put(49, new BackButton(parent));

        return button;
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

}
