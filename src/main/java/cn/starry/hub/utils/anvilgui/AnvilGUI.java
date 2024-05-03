package cn.starry.hub.utils.anvilgui;

import cn.starry.hub.utils.anvilgui.version.VersionMatcher;
import cn.starry.hub.utils.anvilgui.version.VersionWrapper;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class AnvilGUI {
    private static final VersionWrapper WRAPPER = new VersionMatcher().match();
    private static final ItemStack AIR = new ItemStack(Material.AIR);
    private final Plugin plugin;
    private final Player player;
    private final String inventoryTitle;
    private final ItemStack[] initialContents;
    private final boolean preventClose;
    private final Set<Integer> interactableSlots;
    private final Consumer<Player> closeListener;
    private final Function<Completion, List<ResponseAction>> completeFunction;
    private final Consumer<Player> inputLeftClickListener;
    private final Consumer<Player> inputRightClickListener;
    private int containerId;
    private Inventory inventory;
    private final ListenUp listener = new ListenUp();
    private boolean open;

    private AnvilGUI(Plugin plugin, Player player, String inventoryTitle, ItemStack[] initialContents, boolean preventClose, Set<Integer> interactableSlots, Consumer<Player> closeListener, Consumer<Player> inputLeftClickListener, Consumer<Player> inputRightClickListener, Function<Completion, List<ResponseAction>> completeFunction) {
        this.plugin = plugin;
        this.player = player;
        this.inventoryTitle = inventoryTitle;
        this.initialContents = initialContents;
        this.preventClose = preventClose;
        this.interactableSlots = Collections.unmodifiableSet(interactableSlots);
        this.closeListener = closeListener;
        this.inputLeftClickListener = inputLeftClickListener;
        this.inputRightClickListener = inputRightClickListener;
        this.completeFunction = completeFunction;
        this.openInventory();
    }

    private void openInventory() {
        WRAPPER.handleInventoryCloseEvent(this.player);
        WRAPPER.setActiveContainerDefault(this.player);
        Bukkit.getPluginManager().registerEvents((Listener)this.listener, this.plugin);
        Object container = WRAPPER.newContainerAnvil(this.player, this.inventoryTitle);
        this.inventory = WRAPPER.toBukkitInventory(container);
        for (int i = 0; i < this.initialContents.length; ++i) {
            this.inventory.setItem(i, this.initialContents[i]);
        }
        this.containerId = WRAPPER.getNextContainerId(this.player, container);
        WRAPPER.sendPacketOpenWindow(this.player, this.containerId, this.inventoryTitle);
        WRAPPER.setActiveContainer(this.player, container);
        WRAPPER.setActiveContainerId(container, this.containerId);
        WRAPPER.addActiveContainerSlotListener(container, this.player);
        this.open = true;
    }

    public void closeInventory() {
        this.closeInventory(true);
    }

    private void closeInventory(boolean sendClosePacket) {
        if (!this.open) {
            return;
        }
        this.open = false;
        HandlerList.unregisterAll((Listener)this.listener);
        if (sendClosePacket) {
            WRAPPER.handleInventoryCloseEvent(this.player);
            WRAPPER.setActiveContainerDefault(this.player);
            WRAPPER.sendPacketCloseWindow(this.player, this.containerId);
        }
        if (this.closeListener != null) {
            this.closeListener.accept(this.player);
        }
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    private ItemStack notNull(ItemStack stack) {
        return stack == null ? AIR : stack;
    }

    public static final class Completion {
        private final ItemStack leftItem;
        private final ItemStack rightItem;
        private final ItemStack outputItem;
        private final Player player;
        private final String text;

        public Completion(ItemStack leftItem, ItemStack rightItem, ItemStack outputItem, Player player, String text) {
            this.leftItem = leftItem;
            this.rightItem = rightItem;
            this.outputItem = outputItem;
            this.player = player;
            this.text = text;
        }

        public ItemStack getLeftItem() {
            return this.leftItem;
        }

        public ItemStack getRightItem() {
            return this.rightItem;
        }

        public ItemStack getOutputItem() {
            return this.outputItem;
        }

        public Player getPlayer() {
            return this.player;
        }

        public String getText() {
            return this.text;
        }
    }

    public static class Slot {
        private static final int[] values = new int[]{0, 1, 2};
        public static final int INPUT_LEFT = 0;
        public static final int INPUT_RIGHT = 1;
        public static final int OUTPUT = 2;

        public static int[] values() {
            return values;
        }
    }

    @Deprecated
    public static class Response {
        public static List<ResponseAction> close() {
            return Arrays.asList(ResponseAction.close());
        }

        public static List<ResponseAction> text(String text) {
            return Arrays.asList(ResponseAction.replaceInputText(text));
        }

        public static List<ResponseAction> openInventory(Inventory inventory) {
            return Arrays.asList(ResponseAction.openInventory(inventory));
        }
    }

    public static interface ResponseAction
    extends BiConsumer<AnvilGUI, Player> {
        public static ResponseAction replaceInputText(String text) {
            return (anvilgui, player) -> {
                ItemStack outputSlotItem = anvilgui.getInventory().getItem(2).clone();
                ItemMeta meta = outputSlotItem.getItemMeta();
                meta.setDisplayName(text);
                outputSlotItem.setItemMeta(meta);
                anvilgui.getInventory().setItem(0, outputSlotItem);
            };
        }

        public static ResponseAction openInventory(Inventory otherInventory) {
            return (anvigui, player) -> player.openInventory(otherInventory);
        }

        public static ResponseAction close() {
            return (anvilgui, player) -> anvilgui.closeInventory();
        }

        public static ResponseAction run(Runnable runnable) {
            return (anvilgui, player) -> runnable.run();
        }
    }

    public static class Builder {
        private Consumer<Player> closeListener;
        private boolean preventClose = false;
        private Set<Integer> interactableSlots = Collections.emptySet();
        private Consumer<Player> inputLeftClickListener;
        private Consumer<Player> inputRightClickListener;
        private Function<Completion, List<ResponseAction>> completeFunction;
        private Plugin plugin;
        private String title = "Repair & Name";
        private String itemText;
        private ItemStack itemLeft;
        private ItemStack itemRight;
        private ItemStack itemOutput;

        public Builder preventClose() {
            this.preventClose = true;
            return this;
        }

        public Builder interactableSlots(int ... slots) {
            HashSet<Integer> newValue = new HashSet<Integer>();
            for (int slot : slots) {
                newValue.add(slot);
            }
            this.interactableSlots = newValue;
            return this;
        }

        public Builder onClose(Consumer<Player> closeListener) {
            Validate.notNull(closeListener, (String)"closeListener cannot be null");
            this.closeListener = closeListener;
            return this;
        }

        public Builder onLeftInputClick(Consumer<Player> inputLeftClickListener) {
            this.inputLeftClickListener = inputLeftClickListener;
            return this;
        }

        public Builder onRightInputClick(Consumer<Player> inputRightClickListener) {
            this.inputRightClickListener = inputRightClickListener;
            return this;
        }

        @Deprecated
        public Builder onComplete(BiFunction<Player, String, List<ResponseAction>> completeFunction) {
            Validate.notNull(completeFunction, (String)"Complete function cannot be null");
            this.completeFunction = completion -> (List)completeFunction.apply(((Completion)completion).player, ((Completion)completion).text);
            return this;
        }

        public Builder onComplete(Function<Completion, List<ResponseAction>> completeFunction) {
            Validate.notNull(completeFunction, (String)"Complete function cannot be null");
            this.completeFunction = completeFunction;
            return this;
        }

        public Builder plugin(Plugin plugin) {
            Validate.notNull(plugin, (String)"Plugin cannot be null");
            this.plugin = plugin;
            return this;
        }

        public Builder text(String text) {
            Validate.notNull(text, (String)"Text cannot be null");
            this.itemText = text;
            return this;
        }

        public Builder title(String title) {
            Validate.notNull(title, (String)"title cannot be null");
            this.title = title;
            return this;
        }

        @Deprecated
        public Builder item(ItemStack item) {
            return this.itemLeft(item);
        }

        public Builder itemLeft(ItemStack item) {
            Validate.notNull(item, (String)"item cannot be null");
            this.itemLeft = item;
            return this;
        }

        public Builder itemRight(ItemStack item) {
            this.itemRight = item;
            return this;
        }

        public Builder itemOutput(ItemStack item) {
            this.itemOutput = item;
            return this;
        }

        public AnvilGUI open(Player player) {
            Validate.notNull(this.plugin, (String)"Plugin cannot be null");
            Validate.notNull(this.completeFunction, (String)"Complete function cannot be null");
            Validate.notNull(player, (String)"Player cannot be null");
            if (this.itemText != null) {
                if (this.itemLeft == null) {
                    this.itemLeft = new ItemStack(Material.PAPER);
                }
                ItemMeta paperMeta = this.itemLeft.getItemMeta();
                paperMeta.setDisplayName(this.itemText);
                this.itemLeft.setItemMeta(paperMeta);
            }
            return new AnvilGUI(this.plugin, player, this.title, new ItemStack[]{this.itemLeft, this.itemRight, this.itemOutput}, this.preventClose, this.interactableSlots, this.closeListener, this.inputLeftClickListener, this.inputRightClickListener, this.completeFunction);
        }
    }

    private class ListenUp
    implements Listener {
        private ListenUp() {
        }

        @EventHandler
        public void onInventoryClick(final InventoryClickEvent event) {
            if (!event.getInventory().equals(AnvilGUI.this.inventory)) {
                return;
            }
            final Player clicker = (Player)event.getWhoClicked();
            final Inventory clickedInventory = event.getClickedInventory();
            if (clickedInventory != null && clickedInventory.equals(clicker.getInventory()) && event.getClick().equals((Object)ClickType.DOUBLE_CLICK)) {
                event.setCancelled(true);
                return;
            }
            if (event.getRawSlot() < 3 || event.getAction().equals((Object)InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                final int slot = event.getRawSlot();
                event.setCancelled(!AnvilGUI.this.interactableSlots.contains(slot));
                if (event.getRawSlot() == 2) {
                    final ItemStack clicked = AnvilGUI.this.inventory.getItem(2);
                    if (clicked == null || clicked.getType() == Material.AIR) {
                        return;
                    }
                    final List<ResponseAction> actions = AnvilGUI.this.completeFunction.apply(new Completion(AnvilGUI.this.notNull(AnvilGUI.this.inventory.getItem(0)), AnvilGUI.this.notNull(AnvilGUI.this.inventory.getItem(1)), AnvilGUI.this.notNull(AnvilGUI.this.inventory.getItem(2)), AnvilGUI.this.player, clicked.hasItemMeta() ? clicked.getItemMeta().getDisplayName() : ""));
                    for (final ResponseAction action : actions) {
                        action.accept(AnvilGUI.this, clicker);
                    }
                }
                else if (event.getRawSlot() == 0) {
                    if (AnvilGUI.this.inputLeftClickListener != null) {
                        AnvilGUI.this.inputLeftClickListener.accept(AnvilGUI.this.player);
                    }
                }
                else if (event.getRawSlot() == 1 && AnvilGUI.this.inputRightClickListener != null) {
                    AnvilGUI.this.inputRightClickListener.accept(AnvilGUI.this.player);
                }
            }
        }

        @EventHandler
        public void onInventoryDrag(InventoryDragEvent event) {
            if (event.getInventory().equals(AnvilGUI.this.inventory)) {
                for (int slot : Slot.values()) {
                    if (!event.getRawSlots().contains(slot)) continue;
                    event.setCancelled(!AnvilGUI.this.interactableSlots.contains(slot));
                    break;
                }
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent event) {
            if (AnvilGUI.this.open && event.getInventory().equals(AnvilGUI.this.inventory)) {
                AnvilGUI.this.closeInventory(false);
                if (AnvilGUI.this.preventClose) {
                    Bukkit.getScheduler().runTask(AnvilGUI.this.plugin, () -> AnvilGUI.this.openInventory());
                }
            }
        }
    }
}

