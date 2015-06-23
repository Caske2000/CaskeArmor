package com.caske2000.caskearmor.util;

import cofh.lib.util.helpers.StringHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

public final class CStringHelper {

    public static String shiftForInfo() {
        return "§b§o" + localize("info.caske.hold") + " §r§e" + localize("info.caske.hold") + " §r" + "§b§o" + localize("info.caske.moreInfo");
    }

    public static String displayPower() {
        return null;
    }

    public static String getHUDEnergy(int energy, int maxEnergy) {
        String HUDtext = "";
        HUDtext += StringHelper.localize("info.caske.energy") + ": " + energy + " / " + maxEnergy + " RF";
        return HUDtext;
    }

    public static String localize(String var0) {
        return StatCollector.translateToLocal(var0);
    }

    // Energy Stuff

    public static ItemStack setDefaultEnergyTag(ItemStack stack, int energy) {
        if(stack.stackTagCompound == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.stackTagCompound.setInteger("ENERGY", energy);
        return stack;
    }
}
