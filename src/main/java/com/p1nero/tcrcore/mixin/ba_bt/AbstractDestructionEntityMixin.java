package com.p1nero.tcrcore.mixin.ba_bt;

import com.brass_amber.ba_bt.entity.AbstractDestructionEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 防止倒塌
 */
@Mixin(AbstractDestructionEntity.class)
public abstract class AbstractDestructionEntityMixin extends Entity {

    public AbstractDestructionEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tcr$tick(CallbackInfo ci) {
        this.discard();
    }

}
