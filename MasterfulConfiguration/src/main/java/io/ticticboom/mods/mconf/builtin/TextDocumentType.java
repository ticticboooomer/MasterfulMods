package io.ticticboom.mods.mconf.builtin;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.ticticboom.mods.mconf.document.ConfigDocument;
import io.ticticboom.mods.mconf.document.ConfigDocumentType;
import io.ticticboom.mods.mconf.document.IConfigDocumentData;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

public class TextDocumentType extends ConfigDocumentType<TextDocumentType.Data, Component> {

    public static final TextDocumentType LITERAL = new TextDocumentType(Component::literal);
    public static final TextDocumentType TRANSLATE = new TextDocumentType(Component::translatable);
    private final Function<String, Component> component;

    public TextDocumentType(Function<String, Component> component) {
        super();
        this.component = component;
    }

    @Override
    protected Codec<Data> createCodec() {
        return RecordCodecBuilder.create(b -> b.group(Codec.STRING.fieldOf("value").forGetter(Data::value)).apply(b, Data::new));
    }

    @Override
    public ConfigDocumentResult<Data, Component> createResult(ConfigDocument<Data> doc, JsonObject state) {
        return new ConfigDocumentResult<>(new Pair<>(() -> component.apply(doc.data.value), doc));
    }
    public record Data(String value) implements IConfigDocumentData {}
}
