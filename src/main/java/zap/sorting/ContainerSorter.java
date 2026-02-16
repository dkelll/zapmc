package zap.sorting;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Handles sorting of container inventories (chests, barrels, etc).
 */
public class ContainerSorter {

    /**
     * Sorts the entire container inventory.
     */
    public static void sortContainer(Inventory inventory) {
        int size = inventory.size();

        // Collect all items
        List<ItemStack> items = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                items.add(stack.copy());
                inventory.setStack(i, ItemStack.EMPTY);
            }
        }

        // Sort by category priority, then by item ID
        items.sort(Comparator
            .comparingInt((ItemStack item) -> ItemCategorizer.categorize(item).getPriority())
            .thenComparing(item -> getItemId(item))
        );

        // Put items back in sorted order
        int slot = 0;
        for (ItemStack item : items) {
            inventory.setStack(slot++, item);
        }

        inventory.markDirty();
    }

    private static String getItemId(ItemStack stack) {
        return Registries.ITEM.getId(stack.getItem()).toString();
    }
}
