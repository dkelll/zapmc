package zap.sorting;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Handles extracting items from containers - only pulls items that match player's inventory.
 */
public class ContainerExtractor {

    // Player inventory slots to fill (main inventory, not hotbar)
    private static final int HOTBAR_START = 0;
    private static final int HOTBAR_END = 8;
    private static final int INVENTORY_START = 9;
    private static final int INVENTORY_END = 35;

    /**
     * Extracts items from container that match types in player's inventory.
     * Only pulls items that the player already has at least one of.
     */
    public static void extractFromContainer(PlayerInventory playerInv, Inventory container) {
        // First, build a set of item types that exist in player inventory
        Set<ItemStack> playerItemTypes = new HashSet<>();
        for (int i = 0; i < playerInv.size(); i++) {
            ItemStack stack = playerInv.getStack(i);
            if (!stack.isEmpty()) {
                // Store a copy with count=1 for comparison purposes
                ItemStack template = stack.copy();
                template.setCount(1);
                playerItemTypes.add(template);
            }
        }

        // Now extract matching items from container
        for (int containerSlot = 0; containerSlot < container.size(); containerSlot++) {
            ItemStack containerStack = container.getStack(containerSlot);

            if (containerStack.isEmpty()) {
                continue;
            }

            // Check if this item type exists in player inventory
            boolean itemExistsInPlayer = playerItemTypes.stream()
                .anyMatch(template -> ItemStack.areItemsAndComponentsEqual(template, containerStack));

            if (!itemExistsInPlayer) {
                continue; // Skip items player doesn't have
            }

            // Try to merge with existing stacks in player inventory first
            for (int playerSlot = INVENTORY_START; playerSlot <= INVENTORY_END; playerSlot++) {
                ItemStack playerStack = playerInv.getStack(playerSlot);

                if (!playerStack.isEmpty() && ItemStack.areItemsAndComponentsEqual(containerStack, playerStack)) {
                    int maxStackSize = playerStack.getMaxCount();
                    int spaceAvailable = maxStackSize - playerStack.getCount();

                    if (spaceAvailable > 0) {
                        int amountToTransfer = Math.min(spaceAvailable, containerStack.getCount());
                        playerStack.increment(amountToTransfer);
                        containerStack.decrement(amountToTransfer);

                        if (containerStack.isEmpty()) {
                            container.setStack(containerSlot, ItemStack.EMPTY);
                            break;
                        }
                    }
                }
            }

            // If there's still items left in container, put in empty player slots
            if (!containerStack.isEmpty()) {
                for (int playerSlot = INVENTORY_START; playerSlot <= INVENTORY_END; playerSlot++) {
                    ItemStack playerStack = playerInv.getStack(playerSlot);

                    if (playerStack.isEmpty()) {
                        playerInv.setStack(playerSlot, containerStack.copy());
                        container.setStack(containerSlot, ItemStack.EMPTY);
                        break;
                    }
                }
            }
        }

        container.markDirty();
        playerInv.markDirty();
    }
}
