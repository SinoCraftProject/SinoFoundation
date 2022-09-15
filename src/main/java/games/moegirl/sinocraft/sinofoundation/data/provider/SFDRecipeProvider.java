package games.moegirl.sinocraft.sinofoundation.data.provider;

import games.moegirl.sinocraft.sinofoundation.block.SFDBlockItems;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class SFDRecipeProvider extends RecipeProvider {
    public SFDRecipeProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        shapedRecipes(consumer);
    }

    private void shapedRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(SFDItems.IRON_KNIFE.get())
                .pattern(" I")
                .pattern("S ")
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("got_iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .group("knives")
                .save(consumer);

        ShapedRecipeBuilder.shaped(SFDItems.GOLD_KNIFE.get())
                .pattern(" G")
                .pattern("S ")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STICK)
                .unlockedBy("got_gold", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .group("knives")
                .save(consumer);

        ShapedRecipeBuilder.shaped(SFDItems.DIAMOND_KNIFE.get())
                .pattern(" D")
                .pattern("S ")
                .define('D', Items.DIAMOND)
                .define('S', Items.STICK)
                .unlockedBy("got_diamond", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .group("tools/knives")
                .save(consumer);

        ShapedRecipeBuilder.shaped(SFDBlockItems.STOVE.get())
                .pattern("BBB")
                .pattern("BFB")
                .pattern("BWB")
                .define('B', Items.BRICK)
                .define('F', Blocks.FURNACE)
                .define('W', ItemTags.PLANKS)
                .unlockedBy("got_brick", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BRICK))
                .group("functional_blocks")
                .save(consumer);
    }
}
