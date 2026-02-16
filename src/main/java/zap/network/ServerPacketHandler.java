package zap.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.network.ServerPlayerEntity;
import zap.sorting.InventorySorter;

/**
 * Handles network packets on the server side.
 */
public class ServerPacketHandler {

    public static void register() {
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
    }
}
