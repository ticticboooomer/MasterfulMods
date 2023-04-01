package io.ticticboom.mods.mconf.document;

import net.minecraft.resources.ResourceLocation;

public class DocumentValidationError {
    public String message;
    public ResourceLocation documentId;
    public ResourceLocation documentType;

    @Override
    public String toString() {
        return "Document has failed required validation checks:\n" + "Document ID: " + documentId + "\nDocument Type: " + documentType + "\nMessage: " + message;
    }
}
