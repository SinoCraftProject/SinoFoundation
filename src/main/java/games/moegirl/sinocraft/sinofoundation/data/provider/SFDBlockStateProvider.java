package games.moegirl.sinocraft.sinofoundation.data.provider;

import games.moegirl.sinocraft.sinofoundation.block.SFDBlocks;
import games.moegirl.sinocraft.sinofoundation.block.StoveBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;

public class SFDBlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public SFDBlockStateProvider(DataGenerator generator, String modId, ExistingFileHelper exHelper,
                                 DeferredRegister<? extends Block> deferredRegister) {
        super(generator, modId, exHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        addStove();
    }

    private void addStove() {
        VariantBlockStateBuilder stoveBuilder = getVariantBuilder(SFDBlocks.STOVE.get());
        Direction stoveDirection = Direction.NORTH;
        for (int i = 0; i < 4; i++) {
            stoveBuilder.partialState()
                    .with(StoveBlock.FACING, stoveDirection)
                    .with(StoveBlock.BURNING, false)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_off")))
                    .rotationY(90 * i)
                    .addModel();
            stoveBuilder.partialState()
                    .with(StoveBlock.FACING, stoveDirection)
                    .with(StoveBlock.BURNING, true)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_on")))
                    .rotationY(90 * i)
                    .addModel();
            stoveDirection = stoveDirection.getClockWise();
        }
    }
}
