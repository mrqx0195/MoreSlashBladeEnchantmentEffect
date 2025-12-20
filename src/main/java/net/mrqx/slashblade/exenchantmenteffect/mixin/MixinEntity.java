package net.mrqx.slashblade.exenchantmenteffect.mixin;

import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.mrqx.slashblade.exenchantmenteffect.util.MathUtils;
import net.mrqx.slashblade.exenchantmenteffect.util.impl.EntityAbstractSummonedSwordImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "onAddedToWorld()V", at = @At("HEAD"), remap = false)
    private void injectOnAddedToWorld(CallbackInfo ci) {
        if (((Object) this) instanceof EntityAbstractSummonedSword sword) {
            if (sword.getOwner() instanceof LivingEntity livingEntity) {
                ItemStack blade = livingEntity.getMainHandItem();
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
                if (enchantments.containsKey(Enchantments.PIERCING)) {
                    sword.setPierce((byte) Math.min(sword.getPierce() + enchantments.getOrDefault(Enchantments.PIERCING, 0), Byte.MAX_VALUE));
                }
                if (sword instanceof EntityAbstractSummonedSwordImpl sword2) {
                    if (enchantments.containsKey(Enchantments.CHANNELING)) {
                        if (livingEntity.isInWaterRainOrBubble() || livingEntity.level().isRaining() || livingEntity.level().isThundering()) {
                            if (sword.level().random.nextDouble() < (livingEntity.level().isThundering() ? 0.2 : 0.05) * enchantments.getOrDefault(Enchantments.CHANNELING, 0)) {
                                sword2.moreSlashBladeEnchantmentEffect$setLighting(true);
                                sword.setColor(sword.getColor() == 0x3333FF || sword.getColor() == 0xFF3333FF
                                        ? 0xFFD700 : MathUtils.blendColorsSimple(sword.getColor(), 0xFFD700));
                            }
                        }
                    }
                    if (enchantments.containsKey(Enchantments.FLAMING_ARROWS)) {
                        double chance = 0.3;
                        if (livingEntity.level().dimensionType().ultraWarm()) {
                            chance += 0.5;
                        }
                        if (livingEntity.isOnFire() || livingEntity.getRemainingFireTicks() > 0) {
                            chance += 0.2;
                        }
                        AtomicInteger temperature = new AtomicInteger(0);
                        if (livingEntity.isInLava()
                                || livingEntity.isInFluidType(((fluidType, aDouble) -> {
                                    if (fluidType.getTemperature() >= 1000) {
                                        temperature.set(fluidType.getTemperature());
                                        return true;
                                    }
                                    return false;
                                })
                        )) {
                            chance += temperature.get() == 0 ? 0.3 : (temperature.get() - 1000) / 1000D;
                        }
                        if (sword.level().random.nextDouble() < chance) {
                            sword2.moreSlashBladeEnchantmentEffect$setFlaming(true);
                            sword.setColor(sword.getColor() == 0x3333FF || sword.getColor() == 0xFF3333FF
                                    ? 0xE25822 : MathUtils.blendColorsSimple(sword.getColor(), 0xE25822));
                        }
                    }
                    if (enchantments.containsKey(Enchantments.PUNCH_ARROWS)) {
                        sword2.moreSlashBladeEnchantmentEffect$setPunchLevel(enchantments.getOrDefault(Enchantments.PUNCH_ARROWS, 0));
                    }
                }
            }
        }
    }
}
