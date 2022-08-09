package games.moegirl.sinocraft.sinofoundation.capability.block;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Heat output default implement
 *
 * @author qyl27
 */
public class HeatSource implements IHeatSource {
    public static final String TAG_NAME_HEAT = "heat";
    public static final String TAG_NAME_BURN_TIME = "burnTime";
    public static final String TAG_NAME_FUEL = "fuel";
    public static final String TAG_NAME_ASHES = "ashes";

    private int heatValue = 0;
    private int burnTime = 0;
    private ItemStack fuelStack = ItemStack.EMPTY;
    private NonNullList<ItemStack> ashes = NonNullList.create();

    @Override
    public int getHeat() {
        return heatValue;
    }

    @Override
    public void setHeat(int heat) {
        heatValue = heat;
    }

    @Override
    public void resetHeat() {
        heatValue = 0;
    }

    @Override
    public boolean isBurning() {
        return burnTime > 0;
    }

    @Override
    public int getBurnTime() {
        return burnTime;
    }

    @Override
    public void setBurnTime(int time) {
        burnTime = time;
    }

    @Override
    public void clearBurnTime() {
        burnTime = 0;
    }

    @Override
    public void addBurnTime(int time) {
        burnTime += time;
    }

    @Override
    public void subBurnTime(int time) {
        burnTime -= time;
    }

    @Override
    public void setFuel(ItemStack fuel) {
        fuelStack = fuel;
    }

    @Override
    public boolean hasFuel() {
        return fuelStack.isEmpty();
    }

    @Override
    public ItemStack getFuel() {
        return fuelStack;
    }

    @Override
    public int getFuelCount() {
        return fuelStack.getCount();
    }

    @Override
    public void addAshes(ItemStack ash) {
        ashes.add(ash);
    }

    @Override
    public void removeAshes(Item ash) {
        var stackOptional = ashes.stream().filter(e -> e.is(ash)).findAny();

        if (stackOptional.isEmpty()) {
            return;
        }

        ashes.remove(stackOptional.get());
    }

    @Override
    public void clearAshes() {
        ashes.clear();
    }

    @Override
    public int getAshesTypeCount() {
        return ashes.size();
    }

    @Override
    public ItemStack takeAshes(Item ash, int count) {
        return ashes.stream()
                .filter(e -> e.is(ash))
                .peek(stack -> stack.shrink(count))
                .map(ItemStack::copy)
                .findFirst()
                .orElse(ItemStack.EMPTY);
    }

    @Override
    public NonNullList<ItemStack> getAllAshes() {
        return ashes;
    }

    @Override
    public CompoundTag serializeNBT() {
        var tag = new CompoundTag();

        tag.putInt(TAG_NAME_HEAT, heatValue);
        tag.putInt(TAG_NAME_BURN_TIME, burnTime);
        tag.put(TAG_NAME_FUEL, fuelStack.serializeNBT());

        var ashesList = new ListTag();
        for (var ash : ashes) {
            ashesList.add(ash.serializeNBT());
        }
        tag.put(TAG_NAME_ASHES, ashesList);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        if (tag.contains(TAG_NAME_HEAT)) {
            heatValue = tag.getInt(TAG_NAME_HEAT);
        }

        if (tag.contains(TAG_NAME_BURN_TIME)) {
            burnTime = tag.getInt(TAG_NAME_BURN_TIME);
        }

        if (tag.contains(TAG_NAME_FUEL)) {
            fuelStack.deserializeNBT(tag.getCompound(TAG_NAME_FUEL));
        }

        if (tag.contains(TAG_NAME_ASHES)) {
            for (var compound : tag.getList(TAG_NAME_ASHES, Tag.TAG_COMPOUND)) {
                ashes.add(ItemStack.of((CompoundTag) compound));
            }
        }
    }
}
