package games.moegirl.sinocraft.sinofoundation.data;

import games.moegirl.sinocraft.sinofoundation.SinoFoundation;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.data.provider.BlockStateProvider;
import games.moegirl.sinocraft.sinofoundation.data.provider.ItemModelProvider;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.LanguageProviderENUS;
import games.moegirl.sinocraft.sinofoundation.data.provider.language.LanguageProviderZHCN;
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
        }

        if (event.includeServer()) {
            generator.addProvider(new LanguageProviderZHCN(generator, SinoFoundation.MODID, "zh_cn"));
            generator.addProvider(new LanguageProviderENUS(generator, SinoFoundation.MODID, "en_us"));
        }
    }
}
