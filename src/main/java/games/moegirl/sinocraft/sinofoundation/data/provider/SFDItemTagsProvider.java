package games.moegirl.sinocraft.sinofoundation.data.provider;

import games.moegirl.sinocraft.sinocore.api.data.BlockTagsProviderBase;
import games.moegirl.sinocraft.sinocore.api.data.ItemTagsProviderBase;
import games.moegirl.sinocraft.sinofoundation.data.SFDTags;
import games.moegirl.sinocraft.sinofoundation.item.SFDItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class SFDItemTagsProvider extends ItemTagsProviderBase {
    public SFDItemTagsProvider(DataGenerator pGenerator, BlockTagsProviderBase blockTags, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, blockTags, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(SFDTags.KNIVES).add(SFDItems.IRON_KNIFE.get(), SFDItems.GOLD_KNIFE.get(), SFDItems.DIAMOND_KNIFE.get());
    }
}
