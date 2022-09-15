package games.moegirl.sinocraft.sinofoundation.data.provider.language;

import games.moegirl.sinocraft.sinocore.api.data.I18nProviderBase;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import games.moegirl.sinocraft.sinofoundation.item.SinoSeriesTab;
import net.minecraft.data.DataGenerator;

public class LanguageProviderZHCN extends I18nProviderBase {
    public LanguageProviderZHCN(DataGenerator genIn, String modIdIn, String localeIn) {
        super(genIn, modIdIn, modIdIn, localeIn);
    }

    @Override
    protected void addTranslations() {
        add(SFDBlocks.STOVE.get(), "灶台");

        addItem(SFDItems.IRON_KNIFE, "铁制小刀");
        addItem(SFDItems.GOLD_KNIFE, "金质小刀");
        addItem(SFDItems.DIAMOND_KNIFE, "钻石小刀");

        addCreativeTab(SinoSeriesTab.BUILDING_BLOCKS, "华夏系列 | 建筑方块");
        addCreativeTab(SinoSeriesTab.FUNCTIONAL_BLOCKS, "华夏系列 | 功能性方块");
        addCreativeTab(SinoSeriesTab.AGRICULTURE, "华夏系列 | 农业");
        addCreativeTab(SinoSeriesTab.TOOLS, "华夏系列 | 工具");
        addCreativeTab(SinoSeriesTab.WEAPONS, "华夏系列 | 武器");
        addCreativeTab(SinoSeriesTab.MISC, "华夏系列 | 杂项");
    }

    protected void addCreativeTab(SinoSeriesTab tab, String value) {
        add("itemGroup." + tab.getLabel(), value);
    }
}
