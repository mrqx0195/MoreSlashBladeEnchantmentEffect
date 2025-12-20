package net.mrqx.slashblade.exenchantmenteffect.mixin;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.mrqx.slashblade.exenchantmenteffect.event.ExEnchantmentRegistryHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
    @Inject(method = "getEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/entity/LivingEntity;)I", at = @At("RETURN"), cancellable = true)
    private static void injectGetEnchantmentLevel(Enchantment enchantment, LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if (ExEnchantmentRegistryHandler.ENCHANTMENTS.contains(enchantment)) {
            ItemStack blade = entity.getMainHandItem();
            if (!enchantment.getSlotItems(entity).containsValue(blade)) {
                blade.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
                    if (enchantments.containsKey(enchantment)) {
                        cir.setReturnValue(cir.getReturnValue() + enchantments.get(enchantment));
                    }
                });
            }
        }
    }
}
