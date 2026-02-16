package zap.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Packet sent from client to server requesting container extract.
 * Extracts items from container that match player's inventory.
 */
public record ExtractContainerPacket(int syncId) implements CustomPayload {

    public static final CustomPayload.Id<ExtractContainerPacket> ID =
        new CustomPayload.Id<>(Identifier.of("zap", "extract_container"));

    public static final PacketCodec<RegistryByteBuf, ExtractContainerPacket> CODEC =
        PacketCodec.tuple(
            PacketCodecs.VAR_INT, ExtractContainerPacket::syncId,
            ExtractContainerPacket::new
        );

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
