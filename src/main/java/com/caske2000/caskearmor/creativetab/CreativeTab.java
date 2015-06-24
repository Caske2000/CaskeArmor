package com.caske2000.caskearmor.creativetab;

import com.caske2000.caskearmor.item.ModItems;
import com.caske2000.caskearmor.lib.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTab
{
    public static final CreativeTabs CASKE_TAB = new CreativeTabs(Reference.MODID)
    {
        @Override
        public Item getTabIconItem()
        {
            return ModItems.leadstoneChest;
        }
    };
}
