package net.mrqx.slashblade.exenchantmenteffect;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MoreSlashBladeExEnchantmentEffects.MODID)
public class MoreSlashBladeExEnchantmentEffects {
    public static final String MODID = "more_slashblade_ex_enchantment_effects";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation prefix(String s) {
        return ResourceLocation.fromNamespaceAndPath(MODID, s);
    }

    public MoreSlashBladeExEnchantmentEffects() {
    }
}
