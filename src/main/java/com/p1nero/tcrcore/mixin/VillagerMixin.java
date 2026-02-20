package com.p1nero.tcrcore.mixin;

import com.p1nero.dialog_lib.DialogueLib;
import com.p1nero.tcrcore.capability.TCRCapabilityProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {

    @Shadow public abstract VillagerData getVillagerData();

    public VillagerMixin(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void tcr$mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (player instanceof ServerPlayer serverPlayer && hand == InteractionHand.MAIN_HAND) {
            this.playAmbientSound();
            TCRCapabilityProvider.getTCRPlayer(serverPlayer).setCurrentTalkingEntity(this);
            ItemStack mainHand = player.getMainHandItem();
            if (mainHand.is(Items.EMERALD)) {
                //不拦截，用绿宝石才能交易
                return;
            } else {
                DialogueLib.sendDialog(this, serverPlayer);
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
        cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
    }
}
