package com.caske2000.caskearmor.item;

import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

    // Items
    public static Item testChest;

    public static void preInit() {
        testChest = new ItemTestArmor();

        registerItem(testChest, Reference.Names.TEST_CHEST);
    }

    private static void registerItem(Item item, String name) {
        if (item != null) {
            GameRegistry.registerItem(item, name);
        }
    }
}
