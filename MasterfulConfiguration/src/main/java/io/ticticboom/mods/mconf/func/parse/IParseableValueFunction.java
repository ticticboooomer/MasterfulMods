package io.ticticboom.mods.mconf.func.parse;

import java.util.List;

public interface IParseableValueFunction {
    String getString();
    Integer getInteger();
    Boolean getBoolean();
    Float getFloat();
    Double getDouble();
    Long getLong();
    boolean has(String key);
    IParseableValueFunction get(String key);
    List<? extends IParseableValueFunction> getArray();
}
