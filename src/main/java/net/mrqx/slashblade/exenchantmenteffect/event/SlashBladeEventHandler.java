package net.mrqx.slashblade.exenchantmenteffect.event;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.SlashBladeConfig;
import mods.flammpfeil.slashblade.ability.SummonedSwordArts;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.event.handler.InputCommandEvent;
import mods.flammpfeil.slashblade.event.handler.KnockBackHandler;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrqx.sbr_core.utils.MrqxSummonedSwordArts;
import net.mrqx.slashblade.exenchantmenteffect.util.impl.EntityAbstractSummonedSwordImpl;

import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

@Mod.EventBusSubscriber
public class SlashBladeEventHandler {
    @SubscribeEvent
    public static void onSummonedSwordOnHitEntityEvent(SlashBladeEvent.SummonedSwordOnHitEntityEvent event) {
        Entity target = event.getTarget();
        Level level = target.level();
        EntityAbstractSummonedSword sword = event.getSummonedSword();
        if (sword instanceof EntityAbstractSummonedSwordImpl sword2) {
            if (sword2.moreSlashBladeEnchantmentEffect$isLighting()) {
                BlockPos blockpos = target.blockPosition();
                if (level.canSeeSky(blockpos)) {
                    LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
                    if (lightningbolt != null) {
                        lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                        lightningbolt.setCause(sword.getOwner() instanceof ServerPlayer serverPlayer ? serverPlayer : null);
                        lightningbolt.setDamage((float) sword.getDamage());
                        lightningbolt.setVisualOnly(true);
                        level.addFreshEntity(lightningbolt);
                        sword.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
                        if (lightningbolt.level() instanceof ServerLevel serverLevel) {
                            AttackManager.doManagedAttack(entity1 -> entity1.thunderHit(serverLevel, lightningbolt),
                                    target, true, true);
                        }
                    }
                }
            }
            if (sword2.moreSlashBladeEnchantmentEffect$isFlaming()) {
                target.setSecondsOnFire(5);
                AttackManager.doManagedAttack(entity1 -> entity1.hurt(entity1.damageSources().onFire(),
                        (float) sword.getDamage()), target, true, true);
            }
            int punchLevel = sword2.moreSlashBladeEnchantmentEffect$getPunchLevel();
            if (punchLevel > 0 && target instanceof LivingEntity livingEntity) {
                KnockBackHandler.setVertical(livingEntity, 0.25f * punchLevel);
            }
        }
    }

    @SubscribeEvent
    public static void onSlashBladeDoSlashEvent(SlashBladeEvent.DoSlashEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        LivingEntity entity = event.getUser();
        if (enchantments.containsKey(Enchantments.RIPTIDE) && entity.isInWaterRainOrBubble()) {
            int level = enchantments.getOrDefault(Enchantments.RIPTIDE, 0);
            event.setDamage(event.getDamage() * (level * 0.2F + 1));
            double f7 = entity.getYRot();
            double f = entity.getXRot();
            double f1 = -Math.sin(f7 * Math.PI / 180) * Math.cos(f * Math.PI / 180);
            double f2 = -Math.sin(f * Math.PI / 180);
            double f3 = Math.cos(f7 * Math.PI / 180) * Math.cos(f * Math.PI / 180);
            double f4 = Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            double f5 = 3.0F * ((1.0F + level) / 4.0F);
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            entity.push(f1, f2, f3);
            if (entity instanceof Player player) {
                player.startAutoSpinAttack(20);
            }
            if (entity.onGround()) {
                entity.move(MoverType.SELF, new Vec3(0, 2, 0));
            }
            event.getBlade().hurtAndBreak(1, entity, livingEntity -> livingEntity.broadcastBreakEvent(entity.getUsedItemHand()));
            entity.hurtMarked = true;
        }
    }

    @SubscribeEvent
    public static void onSlashBladeHitEvent(SlashBladeEvent.HitEvent event) {
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(event.getBlade());
        LivingEntity entity = event.getUser();
        LivingEntity target = event.getTarget();
        if (enchantments.containsKey(Enchantments.FISHING_SPEED)) {
            Vec3 vec3 = new Vec3(entity.getX() - target.getX(), entity.getY() - target.getY(), entity.getZ() - target.getZ())
                    .scale(enchantments.getOrDefault(Enchantments.FISHING_SPEED, 0) * 0.05);
            target.setDeltaMovement(target.getDeltaMovement().add(vec3));
        }
        if (enchantments.containsKey(Enchantments.BLOCK_EFFICIENCY) && enchantments.containsKey(Enchantments.POWER_ARROWS)) {
            double powerLevel = enchantments.getOrDefault(Enchantments.POWER_ARROWS, 0);
            int level = enchantments.getOrDefault(Enchantments.BLOCK_EFFICIENCY, 0);
            if (entity.level().random.nextDouble() < level * 0.1) {
                MrqxSummonedSwordArts.BASE_SUMMONED_SWORD.accept(entity, target, powerLevel);
            }
        }
    }

    @SubscribeEvent
    public static void onInputCommandEvent(InputCommandEvent event) {
        ServerPlayer sender = event.getEntity();
        ItemStack blade = sender.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.MULTISHOT)) {
            ISlashBladeState bladeState = blade.getCapability(ItemSlashBlade.BLADESTATE).orElse(new SlashBladeState(blade));
            if (!bladeState.isBroken() && !bladeState.isSealed() && SwordType.from(blade).contains(SwordType.BEWITCHED)) {
                int powerLevel = blade.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                if (powerLevel > 0) {
                    EnumSet<InputCommand> old = event.getOld();
                    EnumSet<InputCommand> current = event.getCurrent();
                    InputCommand inputCommand = InputCommand.M_DOWN;
                    boolean onDown = !old.contains(inputCommand) && current.contains(inputCommand);
                    if (onDown) {
                        for (int i = 0; i < enchantments.get(Enchantments.MULTISHOT); i++) {
                            blade.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(state -> {
                                if (state.getProudSoulCount() >= SlashBladeConfig.SUMMON_SWORD_COST.get()) {
                                    AdvancementHelper.grantCriterion(sender, SummonedSwordArts.ADVANCEMENT_SUMMONEDSWORDS);
                                    Optional<Entity> foundTarget = SummonedSwordArts.getInstance().findTarget(sender, state.getTargetEntity(sender.level()));
                                    Level worldIn = sender.level();
                                    Vec3 targetPos = foundTarget.map((e) -> new Vec3(e.getX(), e.getY() + (double) e.getEyeHeight() * (double) 0.5F, e.getZ())).orElseGet(() -> {
                                        Vec3 start = sender.getEyePosition(1.0F);
                                        Vec3 end = start.add(sender.getLookAngle().scale(40.0F));
                                        HitResult result = worldIn.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, sender));
                                        return result.getLocation();
                                    });
                                    int counter = StatHelper.increase(sender, SlashBlade.RegistryEvents.SWORD_SUMMONED, 1);
                                    boolean sided = counter % 2 == 0;
                                    EntityAbstractSummonedSword ss = new EntityAbstractSummonedSword(SlashBlade.RegistryEvents.SummonedSword, worldIn);
                                    Vec3 pos = sender.getEyePosition(1.0F).add(VectorHelper.getVectorForRotation(0.0F, sender.getViewYRot(0.0F) + 90.0F).scale(sided ? (double) 1.0F : (double) -1.0F));
                                    ss.setPos(pos.x, pos.y, pos.z);
                                    ss.setDamage(powerLevel);
                                    Vec3 dir = targetPos.subtract(pos).normalize();
                                    ss.shoot(dir.x, dir.y, dir.z, 3.0F, 0.0F);
                                    ss.setOwner(sender);
                                    ss.setColor(state.getColorCode());
                                    ss.setRoll(sender.getRandom().nextFloat() * 360.0F);
                                    worldIn.addFreshEntity(ss);
                                    sender.playNotifySound(SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 0.2F, 1.45F);
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
