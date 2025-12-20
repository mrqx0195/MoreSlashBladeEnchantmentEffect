package net.mrqx.slashblade.exenchantmenteffect.event;

import mods.flammpfeil.slashblade.event.bladestand.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber
public class BlandStandEventHandler {
    @SubscribeEvent
    public static void onBladeChangeSpecialAttackEvent(BladeChangeSpecialAttackEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        if (event.getOriginalEvent() != null && enchantments.containsKey(Enchantments.LOYALTY)
                && event.getOriginalEvent().getBladeStand().level().random.nextDouble() < 0.1 * enchantments.get(Enchantments.LOYALTY)) {
            event.setShrinkCount(0);
        }
    }

    @SubscribeEvent
    public static void onBladeChangeSpecialEffectEvent(BladeChangeSpecialEffectEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        if (event.getOriginalEvent() != null && enchantments.containsKey(Enchantments.LOYALTY)
                && event.getOriginalEvent().getBladeStand().level().random.nextDouble() < 0.1 * enchantments.get(Enchantments.LOYALTY)) {
            event.setShrinkCount(0);
        }
    }

    @SubscribeEvent
    public static void onPreCopySpecialAttackFromBladeEvent(PreCopySpecialAttackFromBladeEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        if (event.getOriginalEvent() != null && enchantments.containsKey(Enchantments.LOYALTY)
                && event.getOriginalEvent().getBladeStand().level().random.nextDouble() < 0.1 * enchantments.get(Enchantments.LOYALTY)) {
            event.setShrinkCount(0);
        }
    }

    @SubscribeEvent
    public static void onPreCopySpecialEffectFromBladeEvent(PreCopySpecialEffectFromBladeEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        if (event.getOriginalEvent() != null) {
            if (enchantments.containsKey(Enchantments.LOYALTY)
                    && event.getOriginalEvent().getBladeStand().level().random.nextDouble() < 0.1 * enchantments.get(Enchantments.LOYALTY)) {
                event.setShrinkCount(0);
            }
            if (enchantments.containsKey(Enchantments.SILK_TOUCH) && event.isCopiable() && event.isRemovable()) {
                event.setRemovable(false);
                enchantments.remove(Enchantments.SILK_TOUCH);
                EnchantmentHelper.setEnchantments(enchantments, event.getBlade());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onProudSoulEnchantmentEvent(ProudSoulEnchantmentEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        if (event.getOriginalEvent() != null) {
            if (enchantments.containsKey(Enchantments.LOYALTY)
                    && event.getOriginalEvent().getBladeStand().level().random.nextDouble() < 0.1 * enchantments.get(Enchantments.LOYALTY)) {
                event.setTotalShrinkCount(0);
            }
            if (enchantments.containsKey(Enchantments.SILK_TOUCH)) {
                event.setProbability(event.getProbability() * 2);
            }
        }
    }
}
