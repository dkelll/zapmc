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

@Mixin(ShulkerBoxScreen.class)
public abstract class ShulkerBoxSortButton extends HandledScreen<ShulkerBoxScreenHandler> {

    private ShulkerBoxSortButton() {
        super(null, null, null);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void ensureSortButton(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Only add button once
        if (this.children().stream().noneMatch(child ->
            child instanceof ButtonWidget btn && btn.getMessage().getString().equals("S"))) {

            int x = this.x + this.backgroundWidth - 30;
            int y = this.y + 10;

            ButtonWidget sortButton = ButtonWidget.builder(Text.literal("S"), button -> {
                onSortClicked();
            })
            .dimensions(x, y, 20, 20)
            .build();

            this.addDrawableChild(sortButton);
        }
    }

    private void onSortClicked() {
        // Send packet to server to request container sorting
        int syncId = this.handler.syncId;
        ClientPlayNetworking.send(new SortContainerPacket(syncId));
    }
}
