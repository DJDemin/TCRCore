package com.p1nero.tcrcore.item.custom;

import com.yesman.epicskills.network.NetworkManager;
import com.yesman.epicskills.network.client.ClientBoundReloadSkillTree;
import com.yesman.epicskills.registry.entry.EpicSkillsSounds;
import com.yesman.epicskills.world.capability.SkillTreeProgression;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ResetSkillStoneItem extends SimpleDescriptionItem{

    public ResetSkillStoneItem(Properties properties) {
        super(properties, true);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(player instanceof ServerPlayer serverPlayer) {
            player.getCapability(SkillTreeProgression.SKILL_TREE_PROGRESSION).ifPresent((skillTreeProgression) -> {
                skillTreeProgression.reload(false, true);
                NetworkManager.sendToPlayer(new ClientBoundReloadSkillTree(false), serverPlayer);
            });
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        player.playSound(EpicSkillsSounds.GAIN_ABILITY_POINTS.get(), 1.0F, 1.0F);
        return InteractionResultHolder.consume(itemstack);
    }
}
