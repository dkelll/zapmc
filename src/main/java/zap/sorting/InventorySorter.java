package zap.sorting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Handles sorting of player inventory based on item categories.
 * Excludes hotbar (slots 0-8), armor slots, and offhand.
 */
public class InventorySorter {

    // Player inventory layout:
    // 0-8: Hotbar (don't sort)
    // 9-35: Main inventory (sort this)
    // 36-39: Armor slots (don't sort)
    // 40: Offhand (don't sort)

    private static final int INVENTORY_START = 9;
    private static final int INVENTORY_END = 35;

    /**
     * Sorts the player's main inventory (excluding hotbar, armor, offhand).
     */
    public static void sortInventory(PlayerInventory inventory) {
        // Collect all items from sortable slots
        List<SlotItem> items = new ArrayList<>();

        for (int i = INVENTORY_START; i <= INVENTORY_END; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                items.add(new SlotItem(i, stack.copy()));
                inventory.setStack(i, ItemStack.EMPTY);
            }
        }

        // Sort by category priority, then by item ID
        items.sort(Comparator
            .comparingInt((SlotItem item) -> ItemCategorizer.categorize(item.stack).getPriority())
            .thenComparing(item -> getItemId(item.stack))
        );

        // Put items back in sorted order
        int slot = INVENTORY_START;
        for (SlotItem item : items) {
            inventory.setStack(slot++, item.stack);
        }
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString();
    }

    /**
     * Helper class to track which slot an item came from.
     */
    private static class SlotItem {
        final int slot;
        final ItemStack stack;

        SlotItem(int slot, ItemStack stack) {
            this.slot = slot;
            this.stack = stack;
        }
    }
}
