package zap;

import net.fabricmc.api.ClientModInitializer;

/**
 * Client-side mod initializer.
 */
public class ZapClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("Zap mod initialized on client!");
    }
}
