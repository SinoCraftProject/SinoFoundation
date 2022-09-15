package games.moegirl.sinocraft.sinofoundation.data;

import games.moegirl.sinocraft.sinofoundation.SinoFoundation;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.data.provider.*;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.LanguageProviderENUS;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.LanguageProviderZHCN;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = SinoFoundation.MODID)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var exHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new BlockStateProvider(generator, SinoFoundation.MODID, exHelper, SFDBlocks.BLOCKS));
            generator.addProvider(new ItemModelProvider(generator, SinoFoundation.MODID, exHelper, SFDBlockItems.ITEMS));
            generator.addProvider(new ItemModelProvider(generator, SinoFoundation.MODID, exHelper, SFDItems.ITEMS));
        }

        if (event.includeServer()) {
            generator.addProvider(new LanguageProviderZHCN(generator, SinoFoundation.MODID, "zh_cn"));
            generator.addProvider(new LanguageProviderENUS(generator, SinoFoundation.MODID, "en_us"));

            generator.addProvider(new RecipesProvider(generator));

            var blockTagsProvider = new BlockTagsProvider(generator, SinoFoundation.MODID, exHelper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ItemTagsProvider(generator, blockTagsProvider, SinoFoundation.MODID, exHelper));
        }
    }
}
