package zap;

import net.fabricmc.api.ModInitializer;
import zap.network.ServerPacketHandler;
import zap.network.SortInventoryPacket;

/**
 * Main mod initializer for server-side logic.
 */
public class Zap implements ModInitializer {

    @Override
    public void onInitialize() {
        // Register packets
        SortInventoryPacket.register();

        // Register packet handlers
        ServerPacketHandler.register();

        System.out.println("Zap mod initialized on server!");
    }
}
