package games.moegirl.sinocraft.sinofoundation.capability.block;

import games.moegirl.sinocraft.sinofoundation.capability.SFDCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HeatSourceProvider implements ICapabilitySerializable<CompoundTag> {
    private final IHeatSource heatSource = new HeatSource();

    private final LazyOptional<IHeatSource> heatSourceOptional = LazyOptional.of(() -> heatSource);

    public void invalidate() {
        heatSourceOptional.invalidate();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction arg) {
        if (capability == SFDCapabilities.HEAT_SOURCE) {
            return heatSourceOptional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        var heat = heatSourceOptional.orElse(new HeatSource());
        return heat.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        var heat = heatSourceOptional.orElse(new HeatSource());
        heat.deserializeNBT(tag);
    }
}
