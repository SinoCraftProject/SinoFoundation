package games.moegirl.sinocraft.sinofoundation;

import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.block.entity.SFDBlockEntities;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SinoFoundation.MODID)
public class SinoFoundation {
    public static final String MODID = "sinofoundation";
    public static final String NAME = "SinoFoundation";
    public static final String VERSION = "@version@";

    private static final Logger LOGGER = LogManager.getLogger(NAME);

    private static SinoFoundation INSTANCE = null;

    public SinoFoundation() {
        INSTANCE = this;

        LOGGER.info("Loading SinoFoundation. Ver: " + VERSION);

        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        SFDBlocks.register(bus);
        SFDBlockItems.register(bus);
        SFDItems.register(bus);
        SFDBlockEntities.register(bus);

        LOGGER.info("SinoFoundation loaded.");
    }

    public static SinoFoundation getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return LOGGER;
    }
}
