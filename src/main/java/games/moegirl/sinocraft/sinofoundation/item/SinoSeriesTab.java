package games.moegirl.sinocraft.sinofoundation.item;

import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public class SinoSeriesTab extends CreativeModeTab {
    public static final SinoSeriesTab BUILDING_BLOCKS = new SinoSeriesTab("building_blocks", null);
    public static final SinoSeriesTab FUNCTIONAL_BLOCKS = new SinoSeriesTab("functional_blocks", SFDBlockItems.STOVE);
    public static final SinoSeriesTab AGRICULTURE = new SinoSeriesTab("agriculture", null);
    public static final SinoSeriesTab TOOLS = new SinoSeriesTab("tools", null);
    public static final SinoSeriesTab WEAPONS = new SinoSeriesTab("weapons", null);
    public static final SinoSeriesTab MISC = new SinoSeriesTab("misc", null);

    protected String name;
    protected Supplier<? extends ItemLike> icon;

    public SinoSeriesTab(String label, Supplier<? extends ItemLike> iconItem) {
        super("sinoseries_" + label);
        name = "sinoseries_" + label;
        icon = iconItem;
    }

    public String getLabel() {
        return name;
    }

    @Override
    public ItemStack makeIcon() {
        if (icon == null) {
            return ItemStack.EMPTY;
        }

        return new ItemStack(icon.get());
    }
}
