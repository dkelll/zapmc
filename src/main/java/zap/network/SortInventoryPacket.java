package zap.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

/**
 * Packet sent from client to server requesting inventory sort.
 */
public record SortInventoryPacket() implements CustomPayload {

    public static final CustomPayload.Id<SortInventoryPacket> ID =
        new CustomPayload.Id<>(Identifier.of("zap", "sort_inventory"));

    public static final PacketCodec<RegistryByteBuf, SortInventoryPacket> CODEC =
        PacketCodec.unit(new SortInventoryPacket());

    public static void register() {
        PayloadTypeRegistry.playC2S().register(ID, CODEC);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
