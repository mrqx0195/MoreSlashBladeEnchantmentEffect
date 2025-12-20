package net.mrqx.slashblade.exenchantmenteffect.event;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber
public class OtherEventHandler {
    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getSource() != null) {
            Entity entity = event.getSource().getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(livingEntity.getMainHandItem());
                    LivingEntity target = event.getEntity();
                    if (enchantments.containsKey(Enchantments.IMPALING)) {
                        if (livingEntity.isInWaterRainOrBubble() || target.isInWaterRainOrBubble()) {
                            event.setAmount(event.getAmount() * (1 + enchantments.getOrDefault(Enchantments.IMPALING, 0) * 0.2F));
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onLootingLevelEvent(LootingLevelEvent event) {
        if (event.getDamageSource() != null) {
            Entity entity = event.getDamageSource().getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(livingEntity.getMainHandItem());
                    LivingEntity target = event.getEntity();
                    if (enchantments.containsKey(Enchantments.FISHING_LUCK)) {
                        if (livingEntity.isInWaterRainOrBubble() || target.isInWaterRainOrBubble()) {
                            event.setLootingLevel(event.getLootingLevel() + enchantments.getOrDefault(Enchantments.FISHING_LUCK, 0));
                        }
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onShieldBlockEvent(ShieldBlockEvent event) {
        if (event.getDamageSource() != null) {
            Entity entity = event.getDamageSource().getEntity();
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(livingEntity.getMainHandItem());
                    LivingEntity target = event.getEntity();
                    if (enchantments.containsKey(Enchantments.BLOCK_EFFICIENCY)) {
                        float f = EnchantmentHelper.getBlockEfficiency(livingEntity) * 0.05F;
                        if (target.level().random.nextDouble() < f) {
                            event.setShieldTakesDamage(false);
                            target.stopUsingItem();
                            target.level().broadcastEntityEvent(target, (byte) 30);
                            if (target instanceof Player player) {
                                player.getCooldowns().addCooldown(player.getUseItem().getItem(), 100);
                            }
                        }
                    }
                });
            }
        }
    }
}
