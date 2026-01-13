package net.mrqx.slashblade.exenchantmenteffect.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import mods.flammpfeil.slashblade.ability.SummonedSwordArts;
import mods.flammpfeil.slashblade.event.Scheduler;
import mods.flammpfeil.slashblade.event.handler.InputCommandEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Map;

@Mixin(value = SummonedSwordArts.class, remap = false)
public abstract class MixinSummonedSwordArts {
    @Unique
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    @Nullable
    private ServerPlayer moreSlashBladeEnchantmentEffect$sender;

    @Inject(method = "onInputChange(Lmods/flammpfeil/slashblade/event/handler/InputCommandEvent;)V", at = @At("HEAD"))
    private void injectOnInputChange(InputCommandEvent event, CallbackInfo ci) {
        moreSlashBladeEnchantmentEffect$sender = event.getEntity();
    }

    @WrapOperation(method = "lambda$onInputChange$4", at = @At(value = "INVOKE", target = "Lmods/flammpfeil/slashblade/event/Scheduler;schedule(Ljava/lang/String;JLnet/minecraft/world/level/timers/TimerCallback;)V"))
    private void wrapOperationScheduler(Scheduler instance, String key, long time, TimerCallback<LivingEntity> callback, Operation<Void> original) {
        ItemStack blade;
        if (moreSlashBladeEnchantmentEffect$sender != null) {
            blade = moreSlashBladeEnchantmentEffect$sender.getMainHandItem();
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
            if (enchantments.containsKey(Enchantments.QUICK_CHARGE)) {
                instance.schedule(key, time - Math.min(9L, 3L * enchantments.getOrDefault(Enchantments.QUICK_CHARGE, 0)), callback);
                return;
            }
        }
        original.call(instance, key, time, callback);
    }

    @WrapOperation(method = "lambda$onInputChange$7", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/ForgeConfigSpec$IntValue;get()Ljava/lang/Object;"))
    private Object wrapOperationGet(ForgeConfigSpec.IntValue instance, Operation<Integer> original, @Local(argsOnly = true, name = "arg1") ServerPlayer player) {
        ItemStack blade = player.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.INFINITY_ARROWS)) {
            return 0;
        }
        return original.call(instance);
    }

    @ModifyVariable(method = "lambda$performSpiralSwords$17", at = @At("STORE"), name = "count")
    private static int modifyVariablePerformSpiralSwords(int count, @Local(argsOnly = true, name = "arg0") ServerPlayer player) {
        ItemStack blade = player.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.MULTISHOT)) {
            count += 2;
        }
        return count;
    }

    @ModifyVariable(method = "lambda$performStormSwords$20", at = @At("STORE"), name = "count")
    private static int modifyVariablePerformStormSwords(int count, @Local(argsOnly = true, name = "arg0") ServerPlayer player) {
        ItemStack blade = player.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.MULTISHOT)) {
            count += 2;
        }
        return count;
    }

    @ModifyVariable(method = "lambda$performBlisteringSwords$23", at = @At("STORE"), name = "count")
    private static int modifyVariablePerformBlisteringSwords(int count, @Local(argsOnly = true, name = "arg0") ServerPlayer player) {
        ItemStack blade = player.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.MULTISHOT)) {
            count += 2;
        }
        return count;
    }

    @ModifyVariable(method = "lambda$performHeavyRains$26", at = @At("STORE"), name = "count")
    private int modifyVariablePerformHeavyRains(int count, @Local(argsOnly = true, name = "arg1") ServerPlayer player) {
        ItemStack blade = player.getMainHandItem();
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(blade);
        if (enchantments.containsKey(Enchantments.MULTISHOT)) {
            count += 2;
        }
        return count;
    }
}
