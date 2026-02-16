package zap.mixin.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import zap.network.SortInventoryPacket;

@Mixin(InventoryScreen.class)
public abstract class InventorySortButton extends HandledScreen<PlayerScreenHandler> {

    private InventorySortButton() {
        super(null, null, null);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addSortButton(CallbackInfo ci) {
        int x = this.x + this.backgroundWidth - 30;
        int y = this.y + 10;

        ButtonWidget sortButton = ButtonWidget.builder(Text.literal("S"), button -> {
            onSortClicked();
        })
        .dimensions(x, y, 20, 20)
        .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Sort inventory")))
        .build();

        this.addDrawableChild(sortButton);
    }

    private void onSortClicked() {
        // Send packet to server to request sorting
        ClientPlayNetworking.send(new SortInventoryPacket());
    }
}

