package zap.mixin.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import zap.network.SortContainerPacket;
import zap.network.DepositContainerPacket;
import zap.network.RestockContainerPacket;
import zap.network.ExtractContainerPacket;

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxSortButton extends HandledScreen<ShulkerBoxScreenHandler> {

    private ShulkerBoxSortButton() {
        super(null, null, null);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void ensureButtons(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Only add buttons once
        if (this.children().stream().noneMatch(child ->
            child instanceof ButtonWidget btn && btn.getMessage().getString().equals("S"))) {

            // Sort button - rightmost
            int sortX = this.x + this.backgroundWidth - 16;
            int sortY = this.y + 5;

            ButtonWidget sortButton = ButtonWidget.builder(Text.literal("S"), button -> {
                onSortClicked();
            })
            .dimensions(sortX, sortY, 12, 12)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Sort container")))
            .build();

            // Deposit button - left of sort
            int depositX = sortX - 14;
            int depositY = sortY;

            ButtonWidget depositButton = ButtonWidget.builder(Text.literal("D"), button -> {
                onDepositClicked();
            })
            .dimensions(depositX, depositY, 12, 12)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Deposit all items")))
            .build();

            // Restock button - left of deposit
            int restockX = depositX - 14;
            int restockY = sortY;

            ButtonWidget restockButton = ButtonWidget.builder(Text.literal("R"), button -> {
                onRestockClicked();
            })
            .dimensions(restockX, restockY, 12, 12)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Restock matching items")))
            .build();

            // Extract button - left of restock
            int extractX = restockX - 14;
            int extractY = sortY;

            ButtonWidget extractButton = ButtonWidget.builder(Text.literal("E"), button -> {
                onExtractClicked();
            })
            .dimensions(extractX, extractY, 12, 12)
            .tooltip(net.minecraft.client.gui.tooltip.Tooltip.of(Text.literal("Extract matching items")))
            .build();

            this.addDrawableChild(sortButton);
            this.addDrawableChild(depositButton);
            this.addDrawableChild(restockButton);
            this.addDrawableChild(extractButton);
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

    private void onRestockClicked() {
        int syncId = this.handler.syncId;
        ClientPlayNetworking.send(new RestockContainerPacket(syncId));
    }

    private void onExtractClicked() {
        int syncId = this.handler.syncId;
        ClientPlayNetworking.send(new ExtractContainerPacket(syncId));
    }
}
