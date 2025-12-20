package net.mrqx.slashblade.exenchantmenteffect.mixin;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(PowderSnowBlock.class)
public abstract class MixinPowderSnowBlock {
    @Inject(method = "canEntityWalkOnPowderSnow(Lnet/minecraft/world/entity/Entity;)Z", at = @At("HEAD"), cancellable = true)
    private static void injectCanEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack blade = livingEntity.getMainHandItem();
            blade.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
                if (enchantments.containsKey(Enchantments.FROST_WALKER)) {
                    cir.setReturnValue(true);
                }
            });
        }
    }
}
