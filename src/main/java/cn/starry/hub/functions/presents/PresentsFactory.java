package cn.starry.hub.functions.presents;

import cn.starry.core.utils.ClassUtil;
import cn.starry.hub.StarryLobby;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Starry_Killer
 * @Date: 2023/12/19
 * @IdeaProvider: duduskz
 */
@Data
public class PresentsFactory {

    @Getter
    private final List<AbstractPresents> presents;

    public PresentsFactory() {
        this.presents = new ArrayList<>();
    }

    @SneakyThrows
    public void init() {
        Collection<Class<?>> classes = null;

        if (StarryLobby.getInstance().getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            classes = ClassUtil.getClassesInPackage(StarryLobby.getInstance(), "cn.starry.hub.functions.presents.type.lobby");
        }

        if (classes == null) {
            return;
        }
        for (Class<?> clazz : classes) {
            if (AbstractPresents.class.isAssignableFrom(clazz)) {
                Object instance = clazz.newInstance();
                presents.add((AbstractPresents) instance);
            }
        }
        Bukkit.getPluginManager().registerEvents(new PresentsListener(), StarryLobby.getInstance());
        Bukkit.getConsoleSender().sendMessage("加载了" + presents.size() + "个礼物");
    }

}
