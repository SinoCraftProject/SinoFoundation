package games.moegirl.sinocraft.sinofoundation.data.provider;

import games.moegirl.sinocraft.sinocore.api.data.BlockTagsProviderBase;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SFDBlockTagsProvider extends BlockTagsProviderBase {
    public SFDBlockTagsProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
