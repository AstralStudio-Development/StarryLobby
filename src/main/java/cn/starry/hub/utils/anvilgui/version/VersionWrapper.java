package cn.starry.hub.utils.anvilgui.version;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface VersionWrapper {
    public int getNextContainerId(Player var1, Object var2);

    public void handleInventoryCloseEvent(Player var1);

    public void sendPacketOpenWindow(Player var1, int var2, String var3);

    public void sendPacketCloseWindow(Player var1, int var2);

    public void setActiveContainerDefault(Player var1);

    public void setActiveContainer(Player var1, Object var2);

    public void setActiveContainerId(Object var1, int var2);

    public void addActiveContainerSlotListener(Object var1, Player var2);

    public Inventory toBukkitInventory(Object var1);

    public Object newContainerAnvil(Player var1, String var2);
}

