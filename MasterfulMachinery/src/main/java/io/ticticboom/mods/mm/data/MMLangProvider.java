package io.ticticboom.mods.mm.data;

import com.google.gson.JsonObject;
import io.ticticboom.mods.mconf.builtin.TextDocumentType;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mm.Ref;
import io.ticticboom.mods.mm.port.PortTypeConfigs;
import io.ticticboom.mods.mm.setup.MMControllers;
import io.ticticboom.mods.mm.setup.holder.RegisteredController;
import io.ticticboom.mods.mm.setup.holder.RegisteredPort;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.data.LanguageProvider;

public class MMLangProvider extends LanguageProvider {

    public MMLangProvider(PackOutput output, String locale) {
        super(output, Ref.ID, locale);
    }

    @Override
    protected void addTranslations() {
        for (RegisteredController value : MMControllers.REG_CONTROLLERS.values()) {
            ConfigDocument<TextDocumentType.Data> name = value.doc().data.name().cast();
            var result = MConfRegistries.DOCUMENT_TYPES.get(name.type).createResult(name.cast(), new JsonObject());
            var comp = (Component)result.resultData().getFirst().get();
            add(value.block().GetValue().get(), comp.getString());
        }

        for (RegisteredPort value : MMControllers.REG_PORTS.values()) {
            var name = PortTypeConfigs.HANDLERS.get(value.doc().id).createName(value.doc().cast(), value);
            add(value.block().GetValue().get(), name);
        }
    }
}
