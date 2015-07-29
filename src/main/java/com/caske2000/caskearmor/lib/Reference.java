package com.caske2000.caskearmor.lib;

import cofh.CoFHCore;

public final class Reference
{
    public static final String MODID = "caskearmor";
    public static final String NAME = "Caske Armor";
    public static final String ID = "1.7.10-0.1.2";
    public static final String DEPENDENCIES = "required-after:CoFHCore@[" + CoFHCore.version + ",);after:ThermalExpansion";

    public static final String COMMON_PROXY = "com.caske2000.caskearmor.proxy.CommonProxy";
    public static final String CLIENT_PROXY = "com.caske2000.caskearmor.proxy.ClientProxy";
    public static final String GUI_FACTORY = "com.caske2000.caskearmor.client.gui.GuiFactory";

    public static final class Names
    {
        public static final String LEADSTONE_CHEST = "leadstoneChestplate";
        public static final String LEADSTONE_HELMET = "leadstoneHelmet";
        public static final String LEADSTONE_LEGS = "leadstoneLegs";
        public static final String LEADSTONE_BOOTS = "leadstoneBoots";

        public static final String HARDENED_CHEST = "hardenedChestplate";
        public static final String HARDENED_HELMET = "hardenedHelmet";
        public static final String HARDENED_LEGS = "hardenedLegs";
        public static final String HARDENED_BOOTS = "hardenedBoots";

        public static final String REDSTONE_CHEST = "redstoneChestplate";
        public static final String REDSTONE_HELMET = "redstoneHelmet";
        public static final String REDSTONE_LEGS = "redstoneLegs";
        public static final String REDSTONE_BOOTS = "redstoneBoots";

        public static final String RESONANT_CHEST = "resonantChestplate";
        public static final String RESONANT_HELMET = "resonantHelmet";
        public static final String RESONANT_LEGS = "resonantLegs";
        public static final String RESONANT_BOOTS = "resonantBoots";

        public static final String ENERGY_CORE = "energyCore";
        public static final String ARMOR_UPGRADE = "armorUpgrade";
    }

    public static final class Numbers
    {
        public static final int LEADSTONE_MAX_ENERGY = 80000;
        public static final int LEADSTONE_MAX_TRANSFER = 200;

        public static final int HARDENED_MAX_ENERGY = 400000;
        public static final int HARDENED_MAX_TRANSFER = 800;

        public static final int REDSTONE_MAX_ENERGY = 4000000;
        public static final int REDSTONE_MAX_TRANSFER = 8000;

        public static final int RESONANT_MAX_ENERGY = 20000000;
        public static final int RESONANT_MAX_TRANSFER = 32000;
    }

    public static final class Keys
    {
        public static final String CATEGORY = "keys.caskearmor.category";
        public static final String OPTIONS_MENU = "keys.caskearmor.optionsMenu";
    }
}
