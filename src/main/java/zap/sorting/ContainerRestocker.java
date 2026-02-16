package zap.sorting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles restocking containers - only deposits items that already exist in the container.
 */
public class ContainerRestocker {

    // Player inventory slots to exclude (hotbar, armor, offhand)
    private static final int HOTBAR_START = 0;
    private static final int HOTBAR_END = 8;
    private static final int INVENTORY_START = 9;
    private static final int INVENTORY_END = 35;

    /**
     * Restocks the container with items from player inventory.
     * Only deposits items that already exist somewhere in the container.
     * Excludes hotbar, armor slots, and offhand.
     */
    public static void restockContainer(PlayerInventory playerInv, Inventory container) {
        // First, build a set of item types that exist in the container
        Set<ItemStack> containerItemTypes = new HashSet<>();
        for (int i = 0; i < container.size(); i++) {
            ItemStack stack = container.getStack(i);
            if (!stack.isEmpty()) {
                // Store a copy with count=1 for comparison purposes
                ItemStack template = stack.copy();
                template.setCount(1);
                containerItemTypes.add(template);
            }
        }

        // Now deposit only matching items from player inventory
        for (int playerSlot = INVENTORY_START; playerSlot <= INVENTORY_END; playerSlot++) {
            ItemStack playerStack = playerInv.getStack(playerSlot);

            if (playerStack.isEmpty()) {
                continue;
            }

            // Check if this item type exists in the container
            boolean itemExistsInContainer = containerItemTypes.stream()
                .anyMatch(template -> ItemStack.areItemsAndComponentsEqual(template, playerStack));

            if (!itemExistsInContainer) {
                continue; // Skip items not already in container
            }

            // Try to merge with existing stacks in container first
            for (int containerSlot = 0; containerSlot < container.size(); containerSlot++) {
                ItemStack containerStack = container.getStack(containerSlot);

                if (!containerStack.isEmpty() && ItemStack.areItemsAndComponentsEqual(playerStack, containerStack)) {
                    int maxStackSize = containerStack.getMaxCount();
                    int spaceAvailable = maxStackSize - containerStack.getCount();

                    if (spaceAvailable > 0) {
                        int amountToTransfer = Math.min(spaceAvailable, playerStack.getCount());
                        containerStack.increment(amountToTransfer);
                        playerStack.decrement(amountToTransfer);

                        if (playerStack.isEmpty()) {
                            playerInv.setStack(playerSlot, ItemStack.EMPTY);
                            break;
                        }
                    }
                }
            }

            // If there's still items left, put in empty slots
            if (!playerStack.isEmpty()) {
                for (int containerSlot = 0; containerSlot < container.size(); containerSlot++) {
                    ItemStack containerStack = container.getStack(containerSlot);

                    if (containerStack.isEmpty()) {
                        container.setStack(containerSlot, playerStack.copy());
                        playerInv.setStack(playerSlot, ItemStack.EMPTY);
                        break;
                    }
                }
            }
        }

        container.markDirty();
        playerInv.markDirty();
    }
}
