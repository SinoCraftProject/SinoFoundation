package games.moegirl.sinocraft.sinofoundation.capability;

import games.moegirl.sinocraft.sinofoundation.capability.block.IHeatSource;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class SFDCapabilities {
    public static final Capability<IHeatSource> HEAT_SOURCE = CapabilityManager.get(new CapabilityToken<>() { });
}
