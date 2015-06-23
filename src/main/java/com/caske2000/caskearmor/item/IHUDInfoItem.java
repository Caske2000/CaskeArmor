package com.caske2000.caskearmor.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IHUDInfoItem {
    @SideOnly(Side.CLIENT)
    void addInfoToHUD(List<String> list, ItemStack stack);
}
