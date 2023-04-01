package io.ticticboom.mods.mconf.loader.path;

import io.ticticboom.mods.mconf.loader.IDocumentLoader;
import io.ticticboom.mods.mconf.parser.IParseableDocument;
import io.ticticboom.mods.mconf.parser.IParseableDocumentSpec;
import io.ticticboom.mods.mconf.setup.MConfRegistries;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathDocumentLoader implements IDocumentLoader {

    private List<IParseableDocument> docs;
    private final Path rootDirectory;

    public PathDocumentLoader(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    @Override
    public void loadDocuments() {
        docs = new ArrayList<>();
        loadDocuments(rootDirectory.toFile());
        for (IParseableDocument doc : docs) {
            doc.runConsumers();
        }
    }

    @SneakyThrows
    private void loadDocuments(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (final File file : files) {
            if (file.isDirectory()) {
                loadDocuments(file);
            }
            if (file.isFile()) {
                var factory = MConfRegistries.PARSERS.entrySet().stream().filter(entry -> entry.getValue().matchFormat(file.getName())).findFirst();
                if (factory.isPresent()) {
                    var parser = factory.get().getValue();
                    var doc = parser.createParser(Files.readString(file.toPath()));
                    docs.add(doc);
                }
            }
        }
    }
}
