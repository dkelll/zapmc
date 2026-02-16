package zap.sorting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

/**
 * Handles depositing items from player inventory into containers.
 */
public class ContainerDepositor {

    // Player inventory slots to exclude (hotbar, armor, offhand)
    private static final int HOTBAR_START = 0;
    private static final int HOTBAR_END = 8;
    private static final int INVENTORY_START = 9;
    private static final int INVENTORY_END = 35;

    /**
     * Deposits all items from player's main inventory into the container.
     * Excludes hotbar, armor slots, and offhand.
     * Tries to merge with existing stacks first, then uses empty slots.
     */
    public static void depositIntoContainer(PlayerInventory playerInv, Inventory container) {
        // Process each slot in player's main inventory (not hotbar)
        for (int playerSlot = INVENTORY_START; playerSlot <= INVENTORY_END; playerSlot++) {
            ItemStack playerStack = playerInv.getStack(playerSlot);

            if (playerStack.isEmpty()) {
                continue;
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
