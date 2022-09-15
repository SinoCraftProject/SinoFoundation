package games.moegirl.sinocraft.sinofoundation.data.provider.language;

import games.moegirl.sinocraft.sinocore.api.data.I18nProviderBase;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import games.moegirl.sinocraft.sinofoundation.item.SinoSeriesTab;
import net.minecraft.data.DataGenerator;

public class LanguageProviderENUS extends I18nProviderBase {
    public LanguageProviderENUS(DataGenerator genIn, String modIdIn, String localeIn) {
        super(genIn, modIdIn, modIdIn, localeIn);
    }

    @Override
    protected void addTranslations() {
        add(SFDBlocks.STOVE.get(), "Stove");

        addItem(SFDItems.IRON_KNIFE, "Iron knife");
        addItem(SFDItems.GOLD_KNIFE, "Gold knife");
        addItem(SFDItems.DIAMOND_KNIFE, "Diamond knife");

//        addCreativeTab(SinoSeriesTab.BUILDING_BLOCKS, "SinoSeries | Building Blocks");
//        addCreativeTab(SinoSeriesTab.FUNCTIONAL_BLOCKS, "SinoSeries | Functional Blocks");
//        addCreativeTab(SinoSeriesTab.AGRICULTURE, "SinoSeries | Agriculture");
//        addCreativeTab(SinoSeriesTab.TOOLS, "SinoSeries | Tools");
//        addCreativeTab(SinoSeriesTab.WEAPONS, "SinoSeries | Weapons");
//        addCreativeTab(SinoSeriesTab.MISC, "SinoSeries | Misc");
    }
}
