package cn.starry.hub.manager;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import cn.starry.hub.commands.admin.*;
import cn.starry.hub.commands.player.*;
import cn.starry.hub.commands.player.menu.*;
import cn.starry.hub.commands.player.ParkourCommand;
import cn.starry.hub.commands.player.time.DayCommand;
import cn.starry.hub.commands.player.time.NightCommand;
import cn.starry.hub.commands.player.time.SunsetCommand;
import cn.starry.hub.commands.rank.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommandRegistry {

    private final StarryLobby plugin;

    public CommandRegistry(StarryLobby plugin) {
        this.plugin = plugin;
    }

    public void registerAll() {
        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f正在尝试注册命令..."));

        register(new EditCommand());
        register(new MusicCommand());
        register(new KaboomCommand());

        register(new StuckCommand());
        register(new ShopCommand());
        register(new RankColorCommand());
        register(new SuperCommand());
        register(new DayCommand());
        register(new SunsetCommand());
        register(new NightCommand());
        register(new MenuCommand());
        register(new ProfileCommand());
        register(new SettingsCommand());

        //Ranked Player Commands
        register(new FlyCommand());
        register(new RideCommand());
        register(new SpeedCommand());
        register(new FireworkCommand());
        register(new EmojiCommand());
        register(new ParkourCommand());

        Bukkit.getConsoleSender().sendMessage(CC.translate(plugin.getPrefix() + "&f所有命令已成功注册!"));
    }

    private void register(Command cmd) {
        try {
            SimplePluginManager manager = (SimplePluginManager) Bukkit.getServer().getPluginManager();
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(manager);
            Method registerMethod = CommandMap.class.getDeclaredMethod("register", String.class, Command.class);
            registerMethod.setAccessible(true);
            registerMethod.invoke(commandMap, cmd.getName(), cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
