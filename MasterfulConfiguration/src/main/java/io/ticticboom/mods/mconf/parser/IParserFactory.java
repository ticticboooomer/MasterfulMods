package io.ticticboom.mods.mconf.parser;

public interface IParserFactory {
    IParseableDocument createParser(String contents);
    boolean matchFormat(String filename);
}
