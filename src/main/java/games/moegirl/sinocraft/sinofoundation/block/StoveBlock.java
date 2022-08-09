package games.moegirl.sinocraft.sinofoundation.block;

import games.moegirl.sinocraft.sinocore.api.utility.shape.VoxelShapeHelper;
import games.moegirl.sinocraft.sinofoundation.block.entity.StoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.Nullable;

public class StoveBlock extends HorizontalDirectionalBlock implements EntityBlock {
    static {
        var topHoleFull = Block.box(2, 16 - 1, 2, 16 - 2, 16 - 1, 16 - 2);
        var topHoleOccludePart1 = Block.box(2, 16 - 1, 2, 4, 16, 4);
        var topHoleOccludePart2 = Block.box(2, 16 - 1, 4, 3, 16, 5);
        var topHoleOccludePart3 = Block.box(4, 16 - 1, 2, 5, 16, 3);

        var topHolePart = Shapes.or(topHoleOccludePart1, topHoleOccludePart2, topHoleOccludePart3);
        var topHole = Shapes.join(topHoleFull, topHolePart, BooleanOp.ONLY_FIRST);

        for (var i = 0; i < 3; i++) {
            topHolePart = VoxelShapeHelper.rotateClockwise(topHolePart);
            topHole = Shapes.join(topHole, topHolePart, BooleanOp.ONLY_FIRST);
        }

//        var frontHole = Block.box(2, 16 - 1, 2, 16 - 2, 16 - 1, 16 - 2);
        var fullBlock = Block.box(0, 0, 0, 16, 16, 16);

        SHAPE = Shapes.join(fullBlock, topHole, BooleanOp.ONLY_FIRST);
//        var lastShape = Shapes.join(fullBlock, topHole, BooleanOp.ONLY_FIRST);
//        SHAPE = Shapes.join(lastShape, frontHole, BooleanOp.ONLY_FIRST);
    }

    public static final VoxelShape SHAPE;

    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public StoveBlock() {
        super(Properties
                .of(Material.STONE, MaterialColor.COLOR_GRAY)
                .requiresCorrectToolForDrops()
                .strength(3.5F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(BURNING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(BURNING, false);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.getValue(BURNING)) {
            return 14;
        }

        return super.getLightEmission(state, level, pos);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new StoveBlockEntity(pos, state);
    }
}
