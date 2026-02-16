package zap.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import zap.sorting.ContainerSorter;
import zap.sorting.InventorySorter;

/**
 * Handles network packets on the server side.
 */
public class ServerPacketHandler {

    public static void register() {
        // Player inventory sort
        ServerPlayNetworking.registerGlobalReceiver(
            SortInventoryPacket.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();

                // Execute on server thread
                context.server().execute(() -> {
                    PlayerInventory inventory = player.getInventory();
                    InventorySorter.sortInventory(inventory);

                    // Mark inventory as changed so it syncs to client
                    player.currentScreenHandler.sendContentUpdates();
                });
            }
        );

        // Container sort
        ServerPlayNetworking.registerGlobalReceiver(
            SortContainerPacket.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();
                int syncId = payload.syncId();

                // Execute on server thread
                context.server().execute(() -> {
                    ScreenHandler handler = player.currentScreenHandler;

                    // Verify this is the correct screen handler
                    if (handler.syncId == syncId) {
                        // Get the container inventory (not player inventory)
                        // Slot 0 is typically the first container slot
                        Slot firstSlot = handler.getSlot(0);
                        if (firstSlot != null) {
                            Inventory containerInventory = firstSlot.inventory;
                            if (!(containerInventory instanceof PlayerInventory)) {
                                ContainerSorter.sortContainer(containerInventory);
                                handler.sendContentUpdates();
                            }
                        }
                    }
                });
            }
        );
    }
}
