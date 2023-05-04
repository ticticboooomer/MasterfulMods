package io.ticticboom.mods.mconf.setup;

import io.ticticboom.mods.mconf.Ref;
import io.ticticboom.mods.mconf.builtin.TextDocumentType;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.registry.MasterfulRegistry;
import io.ticticboom.mods.mconf.value.DocumentValueFunction;
import io.ticticboom.mods.mconf.value.builtin.LiteralValueFunction;
import io.ticticboom.mods.mconf.value.builtin.ReferenceValueFunction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.advancements.packs.VanillaAdvancementProvider;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MConfRegistries {
    public static final MasterfulRegistry<ConfigDocumentType<?, ?>> DOCUMENT_TYPES = MasterfulRegistry.create();
    public static final MasterfulRegistry<DocumentValueFunction<?>> FUNCTION_CODECS = MasterfulRegistry.create();
    public static final MasterfulRegistry<BiFunction<DataGenerator, ExistingFileHelper, DataProvider>> DATA_GEN_PROVIDERS = MasterfulRegistry.create();
    public static void registerDefaults() {
        DOCUMENT_TYPES.put(Ref.DocTypes.TEXT_COMPONENT, TextDocumentType.LITERAL);
        DOCUMENT_TYPES.put(Ref.DocTypes.TRANSLATED_COMPONENT, TextDocumentType.TRANSLATE);
        FUNCTION_CODECS.put(Ref.FunctionTypes.LITERAL, new LiteralValueFunction());
        FUNCTION_CODECS.put(Ref.FunctionTypes.REFERENCE, new ReferenceValueFunction());
    }
}