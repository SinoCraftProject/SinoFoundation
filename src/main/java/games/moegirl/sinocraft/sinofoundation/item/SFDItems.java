package games.moegirl.sinocraft.sinofoundation.item;

import games.moegirl.sinocraft.sinofoundation.SinoFoundation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SFDItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SinoFoundation.MODID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
