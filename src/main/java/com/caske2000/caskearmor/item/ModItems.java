package com.caske2000.caskearmor.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

    // Materials
    public static ItemArmor.ArmorMaterial TestMaterial = EnumHelper.addArmorMaterial("testMaterial", 33, new int[]{3, 8, 6, 3}, 50);

    // Items
    public static Item testChest;

    public static void init() {
        GameRegistry.registerItem(testChest = new ItemTestArmor("test_chest", TestMaterial, "testArmor", 1), "test_chest");
    }
}
