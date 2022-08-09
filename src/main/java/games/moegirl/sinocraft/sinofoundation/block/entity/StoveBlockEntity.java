package games.moegirl.sinocraft.sinofoundation.block.entity;

import games.moegirl.sinocraft.sinofoundation.block.StoveBlock;
import games.moegirl.sinocraft.sinofoundation.capability.SFDCapabilities;
import games.moegirl.sinocraft.sinofoundation.capability.block.HeatSourceProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoveBlockEntity extends BlockEntity implements BlockEntityTicker<StoveBlockEntity> {
    public StoveBlockEntity(BlockPos pos, BlockState state) {
        super(SFDBlockEntities.STOVE.get(), pos, state);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap != SFDCapabilities.HEAT_SOURCE) {
            return LazyOptional.empty();
        }
        return new HeatSourceProvider().getCapability(cap, side);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        return getCapability(cap, null);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, StoveBlockEntity te) {
        var capOptional = te.getCapability(SFDCapabilities.HEAT_SOURCE);
        var cap = capOptional.orElse(new HeatSourceProvider()
                .getCapability(SFDCapabilities.HEAT_SOURCE).orElse(null));

        state.setValue(StoveBlock.BURNING, cap.isBurning());
        if (cap.isBurning() && !state.getValue(StoveBlock.BURNING)) {

        }
    }
}
