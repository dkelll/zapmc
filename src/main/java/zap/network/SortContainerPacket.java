package zap.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Packet sent from client to server requesting container sort.
 * Includes the syncId to identify which container to sort.
 */
public record SortContainerPacket(int syncId) implements CustomPayload {

    public static final CustomPayload.Id<SortContainerPacket> ID =
        new CustomPayload.Id<>(Identifier.of("zap", "sort_container"));

    public static final PacketCodec<RegistryByteBuf, SortContainerPacket> CODEC =
        PacketCodec.tuple(
            PacketCodecs.VAR_INT, SortContainerPacket::syncId,
            SortContainerPacket::new
        );

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
