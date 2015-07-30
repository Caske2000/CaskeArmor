package com.caske2000.caskearmor.util;

import net.minecraft.util.StatCollector;

public final class CStringHelper
{

    public static String shiftForInfo()
    {
        return "§b§o" + localize("info.caske.hold") + " §r§e" + localize("info.caske.shift") + " §r" + "§b§o" + localize("info.caske.moreInfo");
    }

    public static String getHUDEnergy(int energy, int maxEnergy, int armorType)
    {
        String HUDText = "";
        switch (armorType)
        {
            case 0:
                HUDText += "Helmet";
                break;
            case 1:
                HUDText += "Chestplate";
                break;
            case 2:
                HUDText += "Leggings";
                break;
            case 3:
                HUDText += "Boots";
                break;
        }
        HUDText += " : " + energy + " / " + maxEnergy + " RF";
        return HUDText;
    }

    public static String getEnergyLow(int armorType)
    {
        String HUDText = "§4" + localize("info.caske.your");
        switch (armorType)
        {
            case 0:
                HUDText += " helmet ";
                break;
            case 1:
                HUDText += " chestplate ";
                break;
            case 2:
                HUDText += " leggings ";
                break;
            case 3:
                HUDText += " boots ";
                break;
        }
        LogHelper.info(armorType + " :Type");
        HUDText += localize("info.caske.low");
        LogHelper.info("Text: " + HUDText);

        return HUDText;
    }

    public static String localize(String var0)
    {
        return StatCollector.translateToLocal(var0);
    }
}
