package net.mrqx.slashblade.exenchantmenteffect.event;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.sbr_core.events.ExEnchantmentRegistryEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExEnchantmentRegistryHandler {
    public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();

    @SubscribeEvent
    public static void onExEnchantmentRegistryEvent(ExEnchantmentRegistryEvent event) {
        event.getNewExEnchantments().addAll(ENCHANTMENTS);
    }

    static {
        // 水下呼吸
        ENCHANTMENTS.add(Enchantments.RESPIRATION);
        // 多重射击
        ENCHANTMENTS.add(Enchantments.MULTISHOT);
        // 冰霜行者
        ENCHANTMENTS.add(Enchantments.FROST_WALKER);
        // 迅捷潜行
        ENCHANTMENTS.add(Enchantments.SWIFT_SNEAK);
        // 水下速掘
        ENCHANTMENTS.add(Enchantments.AQUA_AFFINITY);
        // 保护
        ENCHANTMENTS.add(Enchantments.ALL_DAMAGE_PROTECTION);
        // 爆炸保护
        ENCHANTMENTS.add(Enchantments.BLAST_PROTECTION);
        // 弹射物保护
        ENCHANTMENTS.add(Enchantments.PROJECTILE_PROTECTION);
        // 精准采集
        ENCHANTMENTS.add(Enchantments.SILK_TOUCH);
        // 穿透
        ENCHANTMENTS.add(Enchantments.PIERCING);
        // 穿刺
        ENCHANTMENTS.add(Enchantments.IMPALING);
        // 激流
        ENCHANTMENTS.add(Enchantments.RIPTIDE);
        // 深海探索者
        ENCHANTMENTS.add(Enchantments.DEPTH_STRIDER);
        // 无限
        ENCHANTMENTS.add(Enchantments.INFINITY_ARROWS);
        // 快速装填
        ENCHANTMENTS.add(Enchantments.QUICK_CHARGE);
        // 引雷
        ENCHANTMENTS.add(Enchantments.CHANNELING);
        // 火矢
        ENCHANTMENTS.add(Enchantments.FLAMING_ARROWS);
        // 饵钓
        ENCHANTMENTS.add(Enchantments.FISHING_SPEED);
        // 海之眷顾
        ENCHANTMENTS.add(Enchantments.FISHING_LUCK);
        // 冲击
        ENCHANTMENTS.add(Enchantments.PUNCH_ARROWS);
        // 忠诚
        ENCHANTMENTS.add(Enchantments.LOYALTY);
        // 效率
        ENCHANTMENTS.add(Enchantments.BLOCK_EFFICIENCY);
    }
}
