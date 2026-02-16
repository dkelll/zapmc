package zap.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Packet sent from client to server requesting container deposit.
 * Deposits all items from player inventory into the container.
 */
public record DepositContainerPacket(int syncId) implements CustomPayload {

    public static final CustomPayload.Id<DepositContainerPacket> ID =
        new CustomPayload.Id<>(Identifier.of("zap", "deposit_container"));

    public static final PacketCodec<RegistryByteBuf, DepositContainerPacket> CODEC =
        PacketCodec.tuple(
            PacketCodecs.VAR_INT, DepositContainerPacket::syncId,
            DepositContainerPacket::new
        );

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
