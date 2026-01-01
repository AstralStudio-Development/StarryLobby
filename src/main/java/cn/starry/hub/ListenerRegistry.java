package cn.starry.hub;

import cn.starry.core.utils.ClassUtil;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

@Include
public class ListenerRegistry {

    private final Plugin plugin;

    public ListenerRegistry(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerListenersInPackage(String packageName) {
        try {
            // 1. 获取包内所有类
            Collection<Class<?>> classes = ClassUtil.getClassesInPackage(plugin, packageName);

            // 2. 遍历每个类
            for (Class<?> clazz : classes) {
                // 3. 检查是否实现 Listener 接口
                if (isBukkitListener(clazz)) {
                    // 4. 创建实例并注册
                    registerListener(clazz);
                }
            }
        } catch (Exception e) {
            plugin.getLogger().severe("注册监听器时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isBukkitListener(Class<?> clazz) {
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            if (interfaceClass.equals(Listener.class)) {
                return true;
            }
        }
        return false;
    }

    private void registerListener(Class<?> clazz) {
        try {
            Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
            Bukkit.getPluginManager().registerEvents(listener, plugin);
            plugin.getLogger().info("成功注册监听器: " + clazz.getSimpleName());
        } catch (NoSuchMethodException e) {
            plugin.getLogger().warning(clazz.getName() + " 缺少无参构造器，无法实例化");
        } catch (Exception e) {
            plugin.getLogger().warning("注册 " + clazz.getName() + " 时出错: " + e.getMessage());
        }
    }
}
