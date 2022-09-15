package games.moegirl.sinocraft.sinofoundation.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.ArrayList;
import java.util.List;

public class KnifeItem extends SwordItem {
//    public final static List<KnifeItem> KNIVES = new ArrayList<>();

    public KnifeItem(Tier tier) {
        super(tier, 1, -3, new Item.Properties().tab(SinoSeriesTab.TOOLS));
//        KNIVES.add(this);
    }

    // Todo: Usage of knives.
}
