package games.moegirl.sinocraft.sinofoundation.block;

import games.moegirl.sinocraft.sinocore.api.utility.shape.VoxelShapeHelper;
import games.moegirl.sinocraft.sinofoundation.block.entity.StoveBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
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
        var topHoleFull = Block.box(2, 16 - 1, 2, 16 - 2, 16, 16 - 2);
        var topHoleOccludePart1 = Block.box(2, 16 - 1, 2, 4, 16, 4);
        var topHoleOccludePart2 = Block.box(2, 16 - 1, 4, 3, 16, 5);
        var topHoleOccludePart3 = Block.box(4, 16 - 1, 2, 5, 16, 3);
        var topHolePart = Shapes.or(topHoleOccludePart1, topHoleOccludePart2, topHoleOccludePart3);

        var topHole = Shapes.join(topHoleFull, topHolePart, BooleanOp.ONLY_FIRST);
        for (var i = 0; i < 3; i++) {
            topHolePart = VoxelShapeHelper.rotateClockwise(topHolePart);
            topHole = Shapes.join(topHole, topHolePart, BooleanOp.ONLY_FIRST);
        }

        var frontHoleFull = Block.box(4, 2, 0, 4 + 8, 2 + 8, 3);
        var frontHoleOccludePartLeft1 = Block.box(4, 5 + 4, 0, 4 + 3, 2 + 8, 3);
        var frontHoleOccludePartLeft2 = Block.box(4, 5 + 3, 0, 4 + 2, 2 + 7, 3);
        var frontHoleOccludePartLeft3 = Block.box(4, 5 + 2, 0, 4 + 1, 2 + 6, 3);
        var frontHoleOccludePartRight1 = Block.box(4 + 5, 5 + 4, 0, 4 + 5 + 3, 2 + 8, 3);
        var frontHoleOccludePartRight2 = Block.box(4 + 6, 5 + 3, 0, 4 + 6 + 2, 2 + 7, 3);
        var frontHoleOccludePartRight3 = Block.box(4 + 7, 5 + 2, 0, 4 + 7 + 1, 2 + 6, 3);
        var frontHolePartLeft = Shapes.or(frontHoleOccludePartLeft1, frontHoleOccludePartLeft2, frontHoleOccludePartLeft3);
        var frontHolePartRight = Shapes.or(frontHoleOccludePartRight1, frontHoleOccludePartRight2, frontHoleOccludePartRight3);
        var frontHolePartFull = Shapes.or(frontHolePartLeft, frontHolePartRight);
        var frontHole = Shapes.join(frontHoleFull, frontHolePartFull, BooleanOp.ONLY_FIRST);

        var fullBlock = Block.box(0, 0, 0, 16, 16, 16);
        var lastShape = Shapes.join(fullBlock, topHole, BooleanOp.ONLY_FIRST);
        SHAPE = Shapes.join(lastShape, frontHole, BooleanOp.ONLY_FIRST);
    }

    public static final VoxelShape SHAPE;

    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public StoveBlock() {
        super(Properties
                .of(Material.STONE, MaterialColor.COLOR_GRAY)
                .requiresCorrectToolForDrops()
                .strength(3.5F));
    }

    // qyl27: for debugging.
//    @Override
//    public RenderShape getRenderShape(BlockState state) {
//        return RenderShape.INVISIBLE;
//    }

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
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (!entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entity)) {
            entity.hurt(DamageSource.HOT_FLOOR, 0.5f);
        }
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
