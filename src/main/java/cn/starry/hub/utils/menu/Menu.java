package cn.starry.hub.utils.menu;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.StarryLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Menu {

    public static final Map<String, Menu> currentlyOpenedMenus = new ConcurrentHashMap<>();
    private Map<Integer, Button> buttons = new HashMap<>();
    private boolean autoUpdate = false;
    private boolean updateAfterClick = true;
    private boolean closedByMenu = false;
    private boolean placeholder = false;
    private Button placeholderButton = Button.placeholder(Material.BLACK_STAINED_GLASS_PANE, (byte) 15, " ");

    private ItemStack createItemStack(Player player, Button button) {
        ItemStack item = button.getButtonItem(player);
        if (item == null) {
            return new ItemStack(Material.AIR);
        }
        return item;
    }

    public void openMenu(final Player player) {
        if (!Bukkit.isPrimaryThread()) {
            Bukkit.getScheduler().runTask(StarryLobby.getInstance(), () -> openMenu(player));
            return;
        }

        try {
            // 重新生成这次要渲染的按钮映射
            this.buttons = this.getButtons(player);

            Menu previousMenu = Menu.currentlyOpenedMenus.get(player.getName());
            Inventory targetInv = null;

            int size = this.getSize() == -1 ? this.size(this.buttons) : this.getSize();
            String title = CC.translate(this.getTitle(player));
            if (title.length() > 32) title = title.substring(0, 32);

            boolean canReuse = false;

            // 判断是否可复用当前打开的顶层容器
            if (previousMenu != null && player.getOpenInventory() != null) {
                Inventory top = player.getOpenInventory().getTopInventory();
                int previousSize = top.getSize();
                String previousTitle = player.getOpenInventory().getTitle(); // 保持你原逻辑

                if (previousSize == size && previousTitle.equals(title)) {
                    canReuse = true;
                    targetInv = top;
                }
            }

            // —— 情况A：可复用（无开关界面动作，避免闪烁），只做差异更新 —— //
            if (canReuse && targetInv != null) {
                currentlyOpenedMenus.put(player.getName(), this);

                // 1) 先更新/设置这次的所有按钮槽位（仅当不同才写入）
                for (Map.Entry<Integer, Button> entry : this.buttons.entrySet()) {
                    int slot = entry.getKey();
                    ItemStack newItem = createItemStack(player, entry.getValue());
                    ItemStack curItem = targetInv.getItem(slot);

                    if (!sameVisual(curItem, newItem)) {
                        if (isEmpty(newItem)) {
                            // 需要清空
                            if (!isEmpty(curItem)) targetInv.clear(slot);
                        } else {
                            targetInv.setItem(slot, newItem);
                        }
                    }
                }

                // 2) 占位符填充：只对未被按钮占用的格子，且与当前不同才写入
                if (this.isPlaceholder()) {
                    ItemStack ph = this.placeholderButton.getButtonItem(player);
                    for (int i = 0; i < size; i++) {
                        if (!this.buttons.containsKey(i)) {
                            ItemStack cur = targetInv.getItem(i);
                            if (!sameVisual(cur, ph)) {
                                targetInv.setItem(i, ph);
                            }
                        }
                    }
                } else {
                    // 无占位符：清理这次未使用但当前仍有物品的格子（防残留）
                    for (int i = 0; i < size; i++) {
                        if (!this.buttons.containsKey(i)) {
                            ItemStack cur = targetInv.getItem(i);
                            if (!isEmpty(cur)) targetInv.clear(i);
                        }
                    }
                }

                // 不调用 updateInventory()，避免整界面刷新导致的闪烁
                this.onOpen(player);
                this.setClosedByMenu(false);
                return;
            }

            // —— 情况B：需要新建 Inventory（标题或大小不同）—— //
            // 关键点：先把所有内容填到数组里，再 setContents，一次性打开（先填后开，避免空白闪烁）
            Inventory newInv = Bukkit.createInventory(player, size, title);

            // 先构建完整内容数组
            ItemStack[] contents = new ItemStack[size]; // 默认全 null
            // 按钮
            for (Map.Entry<Integer, Button> entry : this.buttons.entrySet()) {
                int slot = entry.getKey();
                if (slot < 0 || slot >= size) continue;
                ItemStack it = createItemStack(player, entry.getValue());
                contents[slot] = isEmpty(it) ? null : it;
            }
            // 占位符
            if (this.isPlaceholder()) {
                ItemStack ph = this.placeholderButton.getButtonItem(player);
                for (int i = 0; i < size; i++) {
                    if (contents[i] == null) {
                        contents[i] = isEmpty(ph) ? null : ph;
                    }
                }
            }

            // 一次性写入
            newInv.setContents(contents);

            // 再打开（避免先开空界面导致的闪一下）
            player.openInventory(newInv);
            currentlyOpenedMenus.put(player.getName(), this);

            this.onOpen(player);
            this.setClosedByMenu(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;
        for (int buttonValue : buttons.keySet()) {
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }
        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSlot(int x, int y) {
        return ((9 * y) + x);
    }

    public int getSize() {
        return -1;
    }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);

    public void onOpen(Player player) {}
    public void onClose(Player player) {}
    public void onClickEvent(InventoryClickEvent event) {}

    public boolean isAutoUpdate() {
        return this.autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public boolean isUpdateAfterClick() {
        return this.updateAfterClick;
    }

    public void setUpdateAfterClick(boolean updateAfterClick) {
        this.updateAfterClick = updateAfterClick;
    }

    public boolean isClosedByMenu() {
        return this.closedByMenu;
    }

    public void setClosedByMenu(boolean closedByMenu) {
        this.closedByMenu = closedByMenu;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public Button getPlaceholderButton() {
        return this.placeholderButton;
    }

    public void setPlaceholderButton(Button placeholderButton) {
        this.placeholderButton = placeholderButton;
    }

    public Map<Integer, Button> getButtons() {
        return this.buttons;
    }

    public void setButtons(Map<Integer, Button> buttons) {
        this.buttons = buttons;
    }

    // ===== 工具方法：把 null 与 AIR 视为同义的“空”，并做稳定的可视比较 =====
    private boolean isEmpty(ItemStack it) {
        return it == null || it.getType() == Material.AIR;
    }

    /**
     * 比较两个 ItemStack 在客户端视觉上是否“相同”：
     * - 两者都为空（null/AIR）视为相同
     * - 都非空：比较 isSimilar + 数量
     */
    private boolean sameVisual(ItemStack a, ItemStack b) {
        if (isEmpty(a) && isEmpty(b)) return true;
        if (isEmpty(a) != isEmpty(b)) return false;
        return a.isSimilar(b) && a.getAmount() == b.getAmount();
    }
}
