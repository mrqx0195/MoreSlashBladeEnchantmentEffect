package net.mrqx.slashblade.exenchantmenteffect.mixin;

import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.IShootable;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.NBTHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.mrqx.slashblade.exenchantmenteffect.util.impl.EntityAbstractSummonedSwordImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityAbstractSummonedSword.class)
@SuppressWarnings({"AlibabaConstantFieldShouldBeUpperCase", "WrongEntityDataParameterClass", "AlibabaLowerCamelCaseVariableNaming"})
public abstract class MixinEntityAbstractSummonedSword extends Projectile implements IShootable, EntityAbstractSummonedSwordImpl {
    @Unique
    private static final EntityDataAccessor<Boolean> moreSlashBladeEnchantmentEffect$LIGHTING = SynchedEntityData
            .defineId(EntityAbstractSummonedSword.class, EntityDataSerializers.BOOLEAN);
    @Unique
    private static final EntityDataAccessor<Boolean> moreSlashBladeEnchantmentEffect$FLAMING = SynchedEntityData
            .defineId(EntityAbstractSummonedSword.class, EntityDataSerializers.BOOLEAN);
    @Unique
    private static final EntityDataAccessor<Integer> moreSlashBladeEnchantmentEffect$PUNCH_LEVEL = SynchedEntityData
            .defineId(EntityAbstractSummonedSword.class, EntityDataSerializers.INT);

    protected MixinEntityAbstractSummonedSword(EntityType<? extends net.minecraft.world.entity.projectile.Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData()V", at = @At("TAIL"))
    private void injectDefineSynchedData(CallbackInfo ci) {
        this.entityData.define(moreSlashBladeEnchantmentEffect$LIGHTING, false);
        this.entityData.define(moreSlashBladeEnchantmentEffect$FLAMING, false);
        this.entityData.define(moreSlashBladeEnchantmentEffect$PUNCH_LEVEL, 0);
    }

    @Inject(method = "addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void injectAddAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        NBTHelper.getNBTCoupler(compound)
                .put("moreSlashBladeEnchantmentEffectLighting", this.moreSlashBladeEnchantmentEffect$isLighting())
                .put("moreSlashBladeEnchantmentEffectFlaming", this.moreSlashBladeEnchantmentEffect$isFlaming())
                .put("moreSlashBladeEnchantmentEffectPunchLevel", this.moreSlashBladeEnchantmentEffect$getPunchLevel());
    }

    @Inject(method = "readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void injectReadAdditionalSaveData(CompoundTag compound, CallbackInfo ci) {
        NBTHelper.getNBTCoupler(compound)
                .get("moreSlashBladeEnchantmentEffectLighting", this::moreSlashBladeEnchantmentEffect$setLighting)
                .get("moreSlashBladeEnchantmentEffectFlaming", this::moreSlashBladeEnchantmentEffect$setFlaming)
                .get("moreSlashBladeEnchantmentEffectPunchLevel", this::moreSlashBladeEnchantmentEffect$setPunchLevel);
    }

    @Override
    public boolean moreSlashBladeEnchantmentEffect$isLighting() {
        return this.entityData.get(moreSlashBladeEnchantmentEffect$LIGHTING);
    }

    @Override
    public void moreSlashBladeEnchantmentEffect$setLighting(boolean lighting) {
        this.entityData.set(moreSlashBladeEnchantmentEffect$LIGHTING, lighting);
    }

    @Override
    public boolean moreSlashBladeEnchantmentEffect$isFlaming() {
        return this.entityData.get(moreSlashBladeEnchantmentEffect$FLAMING);
    }

    @Override
    public void moreSlashBladeEnchantmentEffect$setFlaming(boolean flaming) {
        this.entityData.set(moreSlashBladeEnchantmentEffect$FLAMING, flaming);
    }

    @Override
    public int moreSlashBladeEnchantmentEffect$getPunchLevel() {
        return this.entityData.get(moreSlashBladeEnchantmentEffect$PUNCH_LEVEL);
    }

    @Override
    public void moreSlashBladeEnchantmentEffect$setPunchLevel(int punchLevel) {
        this.entityData.set(moreSlashBladeEnchantmentEffect$PUNCH_LEVEL, punchLevel);
    }
}
