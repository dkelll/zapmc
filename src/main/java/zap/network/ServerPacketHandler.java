package zap.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import zap.sorting.ContainerDepositor;
import zap.sorting.ContainerExtractor;
import zap.sorting.ContainerRestocker;
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

                context.server().execute(() -> {
                    PlayerInventory inventory = player.getInventory();
                    InventorySorter.sortInventory(inventory);
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

                context.server().execute(() -> {
                    ScreenHandler handler = player.currentScreenHandler;

                    if (handler.syncId == syncId) {
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

        // Container deposit
        ServerPlayNetworking.registerGlobalReceiver(
            DepositContainerPacket.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();
                int syncId = payload.syncId();

                context.server().execute(() -> {
                    ScreenHandler handler = player.currentScreenHandler;

                    if (handler.syncId == syncId) {
                        Slot firstSlot = handler.getSlot(0);
                        if (firstSlot != null) {
                            Inventory containerInventory = firstSlot.inventory;
                            if (!(containerInventory instanceof PlayerInventory)) {
                                PlayerInventory playerInv = player.getInventory();
                                ContainerDepositor.depositIntoContainer(playerInv, containerInventory);
                                handler.sendContentUpdates();
                            }
                        }
                    }
                });
            }
        );

        // Container restock
        ServerPlayNetworking.registerGlobalReceiver(
            RestockContainerPacket.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();
                int syncId = payload.syncId();

                context.server().execute(() -> {
                    ScreenHandler handler = player.currentScreenHandler;

                    if (handler.syncId == syncId) {
                        Slot firstSlot = handler.getSlot(0);
                        if (firstSlot != null) {
                            Inventory containerInventory = firstSlot.inventory;
                            if (!(containerInventory instanceof PlayerInventory)) {
                                PlayerInventory playerInv = player.getInventory();
                                ContainerRestocker.restockContainer(playerInv, containerInventory);
                                handler.sendContentUpdates();
                            }
                        }
                    }
                });
            }
        );

        // Container extract
        ServerPlayNetworking.registerGlobalReceiver(
            ExtractContainerPacket.ID,
            (payload, context) -> {
                ServerPlayerEntity player = context.player();
                int syncId = payload.syncId();

                context.server().execute(() -> {
                    ScreenHandler handler = player.currentScreenHandler;

                    if (handler.syncId == syncId) {
                        Slot firstSlot = handler.getSlot(0);
                        if (firstSlot != null) {
                            Inventory containerInventory = firstSlot.inventory;
                            if (!(containerInventory instanceof PlayerInventory)) {
                                PlayerInventory playerInv = player.getInventory();
                                ContainerExtractor.extractFromContainer(playerInv, containerInventory);
                                handler.sendContentUpdates();
                            }
                        }
                    }
                });
            }
        );
    }
}
