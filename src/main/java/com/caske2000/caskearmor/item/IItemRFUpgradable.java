package com.caske2000.caskearmor.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

public interface IItemRFUpgradable
{
    @SideOnly(Side.CLIENT)
    String getHUDString(ItemStack stack);
}
