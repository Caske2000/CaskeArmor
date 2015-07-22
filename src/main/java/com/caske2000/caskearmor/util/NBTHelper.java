package com.caske2000.caskearmor.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/*
    Code By Pahimar (https://goo.gl/9xDh8X)
 */
public abstract class NBTHelper
{

    /**
     * Initializes the NBT Tag Compound for the given ItemStack if it is null
     *
     * @param itemStack The ItemStack for which its NBT Tag Compound is being checked for initialization
     */
    private static void initNBTTagCompound(ItemStack itemStack)
    {
        if (itemStack.stackTagCompound == null)
            itemStack.setTagCompound(new NBTTagCompound());
    }

    public static NBTTagCompound getNBT(ItemStack itemStack)
    {
        initNBTTagCompound(itemStack);

        return itemStack.getTagCompound();
    }

    public static void setInteger(ItemStack itemStack, String keyName, int keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setInteger(keyName, keyValue);
    }

    public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue)
    {
        initNBTTagCompound(itemStack);

        itemStack.stackTagCompound.setBoolean(keyName, keyValue);
    }

    public static ItemStack setDefaultEnergyTag(ItemStack stack, int energy)
    {
        if (stack.stackTagCompound == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.stackTagCompound.setInteger("ENERGY", energy);
        return stack;
    }
}