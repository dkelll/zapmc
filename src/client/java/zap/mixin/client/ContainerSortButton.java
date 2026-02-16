package zap.mixin.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import zap.network.SortContainerPacket;
import zap.network.DepositContainerPacket;

@Mixin(GenericContainerScreen.class)
public abstract class ContainerSortButton extends HandledScreen<GenericContainerScreenHandler> {

    private ContainerSortButton() {
        super(null, null, null);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void ensureButtons(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Only add buttons once
        if (this.children().stream().noneMatch(child ->
            child instanceof ButtonWidget btn && btn.getMessage().getString().equals("S"))) {

            // Sort button - rightmost
            int sortX = this.x + this.backgroundWidth - 20;
            int sortY = this.y + 5;

            ButtonWidget sortButton = ButtonWidget.builder(Text.literal("S"), button -> {
                onSortClicked();
            })
            .dimensions(sortX, sortY, 16, 16)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Sort container")))
            .build();

            // Deposit button - left of sort
            int depositX = sortX - 18;
            int depositY = sortY;

            ButtonWidget depositButton = ButtonWidget.builder(Text.literal("D"), button -> {
                onDepositClicked();
            })
            .dimensions(depositX, depositY, 16, 16)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Deposit all items")))
            .build();

            this.addDrawableChild(sortButton);
            this.addDrawableChild(depositButton);
        }
    }

    private void onSortClicked() {
        int syncId = this.handler.syncId;
        ClientPlayNetworking.send(new SortContainerPacket(syncId));
    }

    private void onDepositClicked() {
        int syncId = this.handler.syncId;
        ClientPlayNetworking.send(new DepositContainerPacket(syncId));
    }
}
