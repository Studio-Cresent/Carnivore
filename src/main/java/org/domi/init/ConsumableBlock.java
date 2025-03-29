package org.domi.init;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import org.domi.init.block.PMFBlock;

import java.util.function.Supplier;

public class ConsumableBlock extends PMFBlock {
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 4);
    private final Supplier<Item> clickItem;
    private final Supplier<Item> returnItem;
    private final boolean dropBlockWhenFull;

    // 블록 모델링에 맞는 히트박스 정의
    // VoxelShape.box(minX, minY, minZ, maxX, maxY, maxZ) - 좌표는 0~16 범위
    protected static final VoxelShape SHAPE_FULL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SHAPE_BITE_1 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
    protected static final VoxelShape SHAPE_BITE_2 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape SHAPE_BITE_3 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape SHAPE_BITE_4 = Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D); // 거의 다 먹은 상태

    public ConsumableBlock(Properties properties, Supplier<Item> returnItem, Supplier<Item> clickItem, boolean dropBlockWhenFull) {
        super(properties);
        this.returnItem = returnItem;
        this.clickItem = clickItem;
        this.dropBlockWhenFull = dropBlockWhenFull;

        this.registerDefaultState(this.getStateDefinition().any().setValue(BITES, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int bites = state.getValue(BITES);

        // 바이트 상태에 따라 다른 히트박스 반환
        switch(bites) {
            case 0:
                return SHAPE_FULL;
            case 1:
                return SHAPE_BITE_1;
            case 2:
                return SHAPE_BITE_2;
            case 3:
                return SHAPE_BITE_3;
            case 4:
                return SHAPE_BITE_4;
            default:
                return SHAPE_FULL;
        }
    }

    public static RegistryObject<Block> registerConsumableBlock(String name, Properties properties, Supplier<Item> returnItem, Supplier<Item> clickItem, boolean dropBlockWhenFull) {
        return PMFBlock.BLOCKS.register(name, () -> new ConsumableBlock(properties, returnItem, clickItem, dropBlockWhenFull));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BITES);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.getItem() == clickItem.get()) {
            int bites = state.getValue(BITES);
            if (bites < 4) {
                level.setBlock(pos, state.setValue(BITES, bites + 1), 3);
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if (!level.isClientSide()) {
                    ItemStack returnStack = new ItemStack(returnItem.get());
                    if (!player.getInventory().add(returnStack)) {
                        player.drop(returnStack, false);
                    }
                    level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        int bites = state.getValue(BITES);

        if (bites == 0 && dropBlockWhenFull) {
            if (!level.isClientSide && !player.getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(this);
                popResource(level, pos, itemstack);
            }
        }

        super.playerWillDestroy(level, pos, state, player);
    }
}