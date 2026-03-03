package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.block.blockentity.spawner.BTAbstractSpawnerBlockEntity;
import com.brass_amber.ba_bt.client.renderer.BTSpawnerBlockEntityRenderer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTSpawnerBlockEntityRenderer.class)
public class BTSpawnerBlockEntityRendererMixin {

    @Inject(method = "render(Lcom/brass_amber/ba_bt/block/blockentity/spawner/BTAbstractSpawnerBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At("HEAD"), cancellable = true, remap = false)
    private void tcr$render(BTAbstractSpawnerBlockEntity spawnerBlockEntity, float rotation, PoseStack poseStack, MultiBufferSource bufferSource, int lightCoords, int p_112568_, CallbackInfo ci) {
        if (Minecraft.getInstance().player == null || Minecraft.getInstance().level == null) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.0D, 0.5F);
//        BlockPos pos = spawnerBlockEntity.getBlockPos();
//        Vec3 playerPos = Minecraft.getInstance().player.position();

        RenderSystem.disableDepthTest();
        OutlineBufferSource outlineBufferSource = Minecraft.getInstance().renderBuffers().outlineBufferSource();
        outlineBufferSource.setColor(255, 0, 0, 255);
        VertexConsumer vertexConsumer = outlineBufferSource.getBuffer(RenderType.lines());

        Matrix4f matrix = poseStack.last().pose();

        vertexConsumer.vertex(matrix, -0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();

        vertexConsumer.vertex(matrix, -0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();

        vertexConsumer.vertex(matrix, -0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f, -0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix,  0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 0f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();
        vertexConsumer.vertex(matrix, -0.5f, 1f,  0.5f).color(255, 0, 0, 255).normal(0f, 1f, 0f).endVertex();

        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }

}
