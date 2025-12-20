package net.mrqx.slashblade.exenchantmenteffect.data.category;

import com.reimnop.pgen.builder.PGenLangBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.slashblade.patchouli.SlashbladePatchouli;
import net.mrqx.slashblade.patchouli.data.category.CategorySlashbladeBaseKnowledge;

public class CategoryMoreSlashbladeExEnchantmentEffects {
    public static final String ID = "more_slashblade_ex_enchantment_effects";

    public static void enUsEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(CategorySlashbladeBaseKnowledge.ID + "/" + ID + "/" + "more_slashblade_ex_enchantment_effects_intro", "More Enchantment Enhancement Effects", ForgeRegistries.ITEMS.getKey(Items.ENCHANTING_TABLE),
                SlashbladePatchouli.prefix(CategorySlashbladeBaseKnowledge.ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("Welcome to $(thing)More SlashBlade Ex Enchantment Effects$()!$()$(br)" +
                                "This mod adds a large number of additional enchantment enhancement effects to Slash Blades—allowing them to use almost every enchantment that exists in vanilla Minecraft!$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)Armor-type Enchantments$()$(br2)" +
                                "Includes: Protection, Blast Protection, Projectile Protection, Feather Falling, Respiration, Frost Walker, Swift Sneak, Aqua Affinity, Depth Strider.$()$(br)" +
                                "These enchantments on Slash Blades have effects equivalent to those on armor, and their effects can stack with enchantments on armor.$()$(br2)" +
                                "Note: These are passive effects that are always active when the blade is held.$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)Summoned Sword Quantity Effects$()$(br2)" +
                                "Enchantments that affect the number of Summoned Swords fired, without consuming additional Proud Soul Value:$()" +
                                "$(li)$(thing)Multishot$(): +1 $(thing)basic Summoned Sword$(), +2 for other Sword Arrays.$()" +
                                "$(li)$(thing)Piercing$(): Summoned Swords can penetrate targets; each level increases max penetration count by +1.$()" +
                                "$(li)$(thing)Infinity$(): Using $(thing)basic Summoned Swords$() does not consume Proud Soul Value.$()$(br2)" +
                                "These effects apply when performing $(l:abilities_and_mechanics/summoned_swords_series)Summoned Sword shooting arts$().$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)Summoned Sword Enhancement Effects$()$(br2)" +
                                "Enchantments that enhance the effects of Summoned Sword shooting arts:$()" +
                                "$(li)$(thing)Quick Charge$(): Reduces charging time for Sword Arrays by 3 ticks per level (default: 10 ticks).$()" +
                                "$(li)$(thing)Punch$(): Summoned Swords launch targets upward upon hit; each level increases launch force.$()" +
                                "$(li)$(thing)Flame$(): 30% chance for Summoned Swords to become fire swords (ignite targets). Chance increases in hot dimensions, when on fire, or in hot fluids.$()" +
                                "$(li)$(thing)Channeling$(): 5% chance for Summoned Swords to call lightning (20% during thunderstorms).$()$(br2)" +
                                "These effects apply when performing $(l:abilities_and_mechanics/summoned_swords_series)Summoned Sword shooting arts$().$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)Environmental Effects$()$(br2)" +
                                "Enchantments with effects related to water, rain, or specific environmental conditions:$()" +
                                "$(li)$(thing)Impaling$(): Deals extra damage to targets in water/rain (+20% per level).$()" +
                                "$(li)$(thing)Riptide$(): When swinging blade in water/rain, propels you forward like a trident and increases damage (+20% and speed per level).$()" +
                                "$(li)$(thing)Lure$(): Pulls targets closer when hitting with blade swing; each level increases pull strength.$()" +
                                "$(li)$(thing)Luck of the Sea$(): When killing creatures in water/rain, adds Luck of the Sea level to Looting level.$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)Other Utility Effects$()$(br2)" +
                                "Miscellaneous enchantments with unique utility functions:$()" +
                                "$(li)$(thing)Efficiency$(): When hitting targets with blade swing, 10% chance per level to spawn an extra Summoned Sword, and 5% chance per level to break shields.$()" +
                                "$(li)$(thing)Loyalty$(): When performing $(l:slashblade_base_knowledge/blade_stand_intro)Proud Soul Enchanting$(), $(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_ingot)copying SA$(), or $(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_crystal)copying SE$(), has (10 × enchantment level)% chance to not consume materials.$()" +
                                "$(li)$(thing)Silk Touch$(): Doubles success rate for $(l:slashblade_base_knowledge/blade_stand_intro)Proud Soul Enchanting$(). When attempting to $(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_crystal)copy an SE$(), if the SE is copyable and removable, preserves the SE on the blade and removes the Silk Touch enchantment.$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)General Tips$()$(br2)" +
                                "$(li)Multiple enhancement effects can coexist on the same blade, but some may have priority interactions.$()" +
                                "$(li)Enhancement effects only activate when the corresponding enchantment is present on the Slash Blade.$()" +
                                "$(li)Some effects (like Infinity and Quick Charge) are particularly useful for Summoned Sword-focused playstyles.$()" +
                                "$(li)Environmental effects like Impaling and Riptide are powerful in aquatic combat.$()$(br2)" +
                                "$(o)Experiment with different enchantment combinations to find the perfect setup for your playstyle!$()", pageBuilder -> {
                        })
        );
    }

    public static void zhCnEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(CategorySlashbladeBaseKnowledge.ID + "/" + ID + "/" + "more_slashblade_ex_enchantment_effects_intro", "更多附魔增效", ForgeRegistries.ITEMS.getKey(Items.ENCHANTING_TABLE),
                SlashbladePatchouli.prefix(CategorySlashbladeBaseKnowledge.ID), entryBuilder -> entryBuilder.withSortnum(0)
                        .addTextPage("欢迎使用$(thing)超多附魔增效$()！$()$(br)" +
                                "本模组为拔刀剑增加了大量额外的附魔增效——几乎允许拔刀剑使用 Minecraft 原版中存在的每一种附魔！$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)盔甲类附魔$()$(br2)" +
                                "包括：保护；爆炸保护；弹射物保护；摔落缓冲；水下呼吸；冰霜行者；迅捷潜行；水下速掘；深海探索者$()$(br)" +
                                "这些附魔在拔刀剑上的效果与它们在盔甲上的效果等同，其效果也可以与盔甲上的附魔叠加。$()$(br2)" +
                                "注意：这些都是被动效果，持刀时始终生效。$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)射技数量类效果$()$(br2)" +
                                "影响幻影剑数量的附魔，不会消耗额外的耀魂值：$()" +
                                "$(li)$(thing)多重射击$()：$(thing)基础幻影剑$()+1，其他剑阵+2。$()" +
                                "$(li)$(thing)穿透$()：射出的幻影剑能够穿透目标；每级增加最大穿透数量+1。$()" +
                                "$(li)$(thing)无限$()：使用$(thing)基础幻影剑$()不会消耗耀魂值。$()$(br2)" +
                                "这些效果在执行$(l:abilities_and_mechanics/summoned_swords_series)幻影剑射技$()时生效。$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)射技增强类效果$()$(br2)" +
                                "增强幻影剑射技效果的附魔：$()" +
                                "$(li)$(thing)快速装填$()：每级减少剑阵蓄力时间3刻（原为10刻）。$()" +
                                "$(li)$(thing)冲击$()：幻影剑击中目标会将其向上抛起；每级增加抛起力度。$()" +
                                "$(li)$(thing)火矢$()：幻影剑有30%概率变为火焰幻影剑（点燃目标）。在炎热维度、自身着火或在高温流体中时概率提升。$()" +
                                "$(li)$(thing)引雷$()：幻影剑有5%概率附加雷电效果（雷暴中为20%）。$()$(br2)" +
                                "这些效果在执行$(l:abilities_and_mechanics/summoned_swords_series)幻影剑射技$()时生效。$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)环境类效果$()$(br2)" +
                                "与水、雨或特定环境条件相关的附魔：$()" +
                                "$(li)$(thing)穿刺$()：对水/雨中的生物造成额外伤害（每级+20%）。$()" +
                                "$(li)$(thing)激流$()：在水/雨中挥刀，会像三叉戟一样使你向前冲锋，并提升伤害（每级+20%伤害和速度）。$()" +
                                "$(li)$(thing)饵钓$()：挥刀击中目标会将其拉近；每级增加拉动力度。$()" +
                                "$(li)$(thing)海之眷顾$()：在水/雨中击杀生物时，将海之眷顾等级叠加至抢夺等级。$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)其他实用效果$()$(br2)" +
                                "具有独特实用功能的杂项附魔：$()" +
                                "$(li)$(thing)效率$()：挥刀击中目标时，每级10%概率额外生成一枚幻影剑，每级5%概率破盾。$()" +
                                "$(li)$(thing)忠诚$()：进行$(l:slashblade_base_knowledge/blade_stand_intro)耀魂附魔$()、$(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_ingot)复制SA$()或$(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_crystal)复制SE$()时，有(10 × 附魔等级)%概率不消耗材料。$()" +
                                "$(li)$(thing)精准采集$()：$(l:slashblade_base_knowledge/blade_stand_intro)耀魂附魔$()成功率翻倍。尝试$(l:slashblade_base_knowledge/proudsoul_intro#proudsoul_crystal)复制SE$()时，若该SE可复制且可移除，则保留刀上的SE并移除精准采集附魔。$()", pageBuilder -> {
                        })
                        .addTextPage("$(thing)通用技巧$()$(br2)" +
                                "$(li)多个增效效果可在同一把刀上共存，但某些效果可能有优先级交互。$()" +
                                "$(li)增效效果仅在拔刀剑持有对应附魔时生效。$()" +
                                "$(li)某些效果（如无限、快速装填）对于专注幻影剑的玩法特别有用。$()" +
                                "$(li)环境类效果（如穿刺、激流）在水下战斗中十分强大。$()$(br2)" +
                                "$(o)尝试不同的附魔组合，找到最适合你战斗风格的搭配！$()", pageBuilder -> {
                        })
        );
    }
}