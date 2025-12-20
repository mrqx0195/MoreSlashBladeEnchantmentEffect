package net.mrqx.slashblade.exenchantmenteffect.data;

import com.reimnop.pgen.PGenBookProvider;
import com.reimnop.pgen.builder.PGenBookBuilder;
import com.reimnop.pgen.builder.PGenLangBuilder;
import com.reimnop.pgen.data.PGenBook;
import com.reimnop.pgen.data.PGenCategory;
import com.reimnop.pgen.data.PGenEntry;
import com.reimnop.pgen.data.PGenLang;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.mrqx.slashblade.exenchantmenteffect.MoreSlashBladeExEnchantmentEffects;
import net.mrqx.slashblade.exenchantmenteffect.data.category.CategoryMoreSlashbladeExEnchantmentEffects;
import net.mrqx.slashblade.patchouli.SlashbladePatchouli;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PatchouliProvider extends PGenBookProvider {
    private final PackOutput.PathProvider dataPathProvider;
    private final PackOutput.PathProvider assetsPathProvider;

    private final Map<ResourceLocation, PGenBook> books = new HashMap<>();

    public PatchouliProvider(CompletableFuture<HolderLookup.Provider> lookupProvider, PackOutput packOutput) {
        super(MoreSlashBladeExEnchantmentEffects.MODID, lookupProvider, packOutput);
        this.dataPathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "patchouli_books");
        this.assetsPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "patchouli_books");
    }

    @Override
    protected void addBook(PGenBook book) {
        this.books.put(book.id, book);
    }

    @Override
    protected void addBook(String id, String name, String landingText, Boolean useResourcePack, Consumer<PGenBookBuilder> consumer) {
        var builder = new PGenBookBuilder(
                ResourceLocation.fromNamespaceAndPath(SlashbladePatchouli.MODID, id),
                name,
                landingText,
                useResourcePack);
        consumer.accept(builder);
        this.addBook(builder.build());
    }

    @Override
    protected void generate(HolderLookup.Provider provider) {
        this.addBook("slashblade_book", "", "", true,
                builder -> builder
                        .addLanguage("en_us", PatchouliProvider::enUsLangBuilder)
                        .addLanguage("zh_cn", PatchouliProvider::zhCnLangBuilder));
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return this.createContentsProvider().thenCompose((provider) ->
                CompletableFuture.allOf(this.books.values().stream().map(book -> {
                    List<CompletableFuture<?>> futures = new ArrayList<>();

                    ResourceLocation bookPath = ResourceLocation.fromNamespaceAndPath(
                            book.id.getNamespace(),
                            book.id.getPath() + "/book");

                    if (!"slashblade_book".equals(book.id.getPath())) {
                        Path bookFilePath = this.dataPathProvider.json(bookPath);
                        futures.add(DataProvider.saveStable(pOutput, book.serialize(), bookFilePath));
                    }

                    PackOutput.PathProvider contentProvider = (book.useResourcePack != null ? book.useResourcePack : false)
                            ? this.assetsPathProvider
                            : this.dataPathProvider;

                    for (PGenLang lang : book.languages) {
                        ResourceLocation langBasePath = ResourceLocation.fromNamespaceAndPath(
                                book.id.getNamespace(),
                                book.id.getPath() + "/" + lang.name);

                        ResourceLocation categoriesPath = ResourceLocation.fromNamespaceAndPath(
                                langBasePath.getNamespace(),
                                langBasePath.getPath() + "/categories");

                        for (PGenCategory category : lang.categories) {
                            ResourceLocation categoryPath = ResourceLocation.fromNamespaceAndPath(
                                    categoriesPath.getNamespace(),
                                    categoriesPath.getPath() + "/" + category.id);
                            Path categoryFilePath = contentProvider.json(categoryPath);
                            futures.add(DataProvider.saveStable(pOutput, category.serialize(), categoryFilePath));
                        }

                        ResourceLocation entriesPath = ResourceLocation.fromNamespaceAndPath(
                                langBasePath.getNamespace(),
                                langBasePath.getPath() + "/entries");

                        for (PGenEntry entry : lang.entries) {
                            ResourceLocation entryPath = ResourceLocation.fromNamespaceAndPath(
                                    entriesPath.getNamespace(),
                                    entriesPath.getPath() + "/" + entry.id);
                            Path entryFilePath = contentProvider.json(entryPath);
                            futures.add(DataProvider.saveStable(pOutput, entry.serialize(), entryFilePath));
                        }
                    }

                    return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
                }).toArray(CompletableFuture[]::new)));
    }

    public static void enUsLangBuilder(PGenLangBuilder builder) {
        CategoryMoreSlashbladeExEnchantmentEffects.enUsEntryBuilder(builder);
    }

    public static void zhCnLangBuilder(PGenLangBuilder builder) {
        CategoryMoreSlashbladeExEnchantmentEffects.zhCnEntryBuilder(builder);
    }
}
