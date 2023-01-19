package org.infernalstudios.infernalexp.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.init.IEEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class InfectionHeartOverlay {
    protected int heartOffset;
    private static final ResourceLocation INFECTION_HEART_TEXTURE = new ResourceLocation(InfernalExpansion.MOD_ID, "textures/gui/infection_gui.png");

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPreRender(RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL || event.isCanceled()) {
            return;
        }

        heartOffset = ((ForgeIngameGui) Minecraft.getInstance().gui).left_height;
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPostRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL || event.isCanceled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        int left = mc.getWindow().getGuiScaledWidth() / 2 - 91;
        int top = mc.getWindow().getGuiScaledHeight() - heartOffset;

        if (player.getEffect(IEEffects.INFECTION.get()) != null && ((ForgeIngameGui) mc.gui).shouldDrawSurvivalElements() && !mc.options.hideGui) {
            drawInfectionOverlay(mc, event.getMatrixStack(), left, top);
        }
    }

    public static void drawInfectionOverlay(Minecraft mc, PoseStack matrixStack, int left, int top) {
        matrixStack.pushPose();
        matrixStack.translate(0, 0, 0.01);

        Player player = mc.player;
        float currentHealth = Mth.ceil(player.getHealth());
        int ticks = mc.gui.getGuiTicks();
        Random rand = new Random();
        rand.setSeed(ticks * 312871L);

        float absorb = Mth.ceil(player.getAbsorptionAmount());
        boolean highlight = mc.gui.healthBlinkTime > (long) ticks && (mc.gui.healthBlinkTime - (long) ticks) / 3L % 2L == 1L;


        int healthRows = Mth.ceil((player.getMaxHealth() + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION)) {
            regen = ticks % 25;
        }

        final int TOP = 9 * (mc.level.getLevelData().isHardcore() ? 1 : 0);
        final int BACKGROUND = (highlight ? 9 : 0);

        float absorbRemaining = absorb;


        RenderSystem.setShaderTexture(0, INFECTION_HEART_TEXTURE);
        RenderSystem.enableBlend();


        for (int i = Mth.ceil((player.getMaxHealth() + absorb) / 2.0F) - 1; i >= 0; --i) {
            int row = Mth.ceil((float) (i + 1) / 10.0F) - 1;
            int x = left + i % 10 * 8;
            int y = top - row * rowHeight;

            if (currentHealth <= 4) y += rand.nextInt(2);
            if (i == regen) y -= 2;

            mc.gui.blit(matrixStack, x, y, BACKGROUND, TOP, 9, 9);

            if (highlight) {
                if (i * 2 + 1 < mc.gui.displayHealth) {
                    mc.gui.blit(matrixStack, x, y, 54, TOP, 9, 9);
                } else if (i * 2 + 1 == mc.gui.displayHealth) {
                    mc.gui.blit(matrixStack, x, y, 63, TOP, 9, 9);
                }
            }

            if (absorbRemaining > 0.0F) {
                if (absorbRemaining == absorb && absorb % 2.0F == 1.0F) {
                    mc.gui.blit(matrixStack, x, y, 63, TOP, 9, 9);
                    absorbRemaining -= 1.0F;
                } else {
                    mc.gui.blit(matrixStack, x, y, 54, TOP, 9, 9);
                    absorbRemaining -= 2.0F;
                }
            } else {
                if (i * 2 + 1 < currentHealth) {
                    mc.gui.blit(matrixStack, x, y, 36, TOP, 9, 9);
                } else if (i * 2 + 1 == currentHealth) {
                    mc.gui.blit(matrixStack, x, y, 45, TOP, 9, 9);
                }
            }
        }

        RenderSystem.disableBlend();
        matrixStack.popPose();
        RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
    }
}
