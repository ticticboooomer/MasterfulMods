package io.ticticboom.mods.mui;

import io.ticticboom.mods.mconf.setup.MConfRegistries;
import io.ticticboom.mods.mui.screen.ScreenConfig;
import io.ticticboom.mods.mui.screen.element.ImageElement;
import io.ticticboom.mods.mui.screen.element.TextElement;
import net.minecraftforge.fml.common.Mod;

@Mod(Ref.ID)
public class ModRoot {

    public ModRoot() {
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.SCREENS, new ScreenConfig());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.IMAGE_ELEMENT, new ImageElement());
        MConfRegistries.DOC_TYPES.put(Ref.DocTypes.TEXT_ELEMENT, new TextElement());
    }

}
