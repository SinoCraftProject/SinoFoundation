package games.moegirl.sinocraft.sinofoundation.capability;

import games.moegirl.sinocraft.sinofoundation.SinoFoundation;
import games.moegirl.sinocraft.sinofoundation.block.entity.StoveBlockEntity;
import games.moegirl.sinocraft.sinofoundation.capability.block.HeatSourceProvider;
import games.moegirl.sinocraft.sinofoundation.capability.block.IHeatSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoFoundation.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SFDCapabilityRegister {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IHeatSource.class);
    }

    @SubscribeEvent
    public static void onAttachBlockEntity(AttachCapabilitiesEvent<BlockEntity> event) {
        if (event.getObject() instanceof StoveBlockEntity) {
            var provider = new HeatSourceProvider();
            event.addCapability(new ResourceLocation(SinoFoundation.MODID, "heat_source"), provider);
            event.addListener(provider::invalidate);
        }
    }
}
