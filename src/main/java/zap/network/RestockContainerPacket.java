package zap.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Packet sent from client to server requesting container restock.
 * Only deposits items that already exist in the container.
 */
public record RestockContainerPacket(int syncId) implements CustomPayload {

    public static final CustomPayload.Id<RestockContainerPacket> ID =
        new CustomPayload.Id<>(Identifier.of("zap", "restock_container"));

    public static final PacketCodec<RegistryByteBuf, RestockContainerPacket> CODEC =
        PacketCodec.tuple(
            PacketCodecs.VAR_INT, RestockContainerPacket::syncId,
            RestockContainerPacket::new
        );

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
