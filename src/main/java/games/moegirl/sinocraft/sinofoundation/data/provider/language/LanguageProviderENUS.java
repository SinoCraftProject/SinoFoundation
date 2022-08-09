package games.moegirl.sinocraft.sinofoundation.data.provider.language;

import games.moegirl.sinocraft.sinocore.api.data.I18nProviderBase;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import net.minecraft.data.DataGenerator;

public class LanguageProviderENUS extends I18nProviderBase {
    public LanguageProviderENUS(DataGenerator genIn, String modIdIn, String localeIn) {
        super(genIn, modIdIn, modIdIn, localeIn);
    }

    @Override
    protected void addTranslations() {
        add(SFDBlocks.STOVE.get(), "Stove");
    }
}
