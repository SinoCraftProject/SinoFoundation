package games.moegirl.sinocraft.sinofoundation.data;

import games.moegirl.sinocraft.sinofoundation.SinoFoundation;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.data.provider.*;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.SFDLanguageProviderENUS;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.SFDLanguageProviderZHCN;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = SinoFoundation.MODID)
public class SFDData {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var exHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new SFDBlockStateProvider(generator, SinoFoundation.MODID, exHelper, SFDBlocks.BLOCKS));
            generator.addProvider(new SFDItemModelProvider(generator, SinoFoundation.MODID, exHelper, SFDBlockItems.ITEMS));
            generator.addProvider(new SFDItemModelProvider(generator, SinoFoundation.MODID, exHelper, SFDItems.ITEMS));
        }

        if (event.includeServer()) {
            generator.addProvider(new SFDLanguageProviderZHCN(generator, SinoFoundation.MODID, "zh_cn"));
            generator.addProvider(new SFDLanguageProviderENUS(generator, SinoFoundation.MODID, "en_us"));

            generator.addProvider(new SFDRecipeProvider(generator));

            var blockTagsProvider = new SFDBlockTagsProvider(generator, SinoFoundation.MODID, exHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new SFDItemTagsProvider(generator, blockTagsProvider, SinoFoundation.MODID, exHelper));
        }
    }
}
