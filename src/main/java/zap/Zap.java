package zap;

import net.fabricmc.api.ModInitializer;
import zap.network.ServerPacketHandler;
import zap.network.SortInventoryPacket;
import zap.network.SortContainerPacket;
import zap.network.DepositContainerPacket;
import zap.network.RestockContainerPacket;

/**
 * Main mod initializer for server-side logic.
 */
public class Zap implements ModInitializer {

    @Override
    public void onInitialize() {
        // Register packets
        SortInventoryPacket.register();
        SortContainerPacket.register();
        DepositContainerPacket.register();
        RestockContainerPacket.register();

        // Register packet handlers
        ServerPacketHandler.register();

        System.out.println("Zap mod initialized on server!");
    }
}
