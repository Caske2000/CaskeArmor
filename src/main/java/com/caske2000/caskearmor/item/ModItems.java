package com.caske2000.caskearmor.item;

import cofh.thermalexpansion.item.TEItems;
import com.caske2000.caskearmor.crafting.ArmorUpgrade;
import com.caske2000.caskearmor.creativetab.CreativeTab;
import com.caske2000.caskearmor.lib.Reference;
import com.caske2000.caskearmor.util.NBTHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModItems
{

    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_LEADSTONE = EnumHelper.addArmorMaterial("LEADSTONE", 100, new int[]{2, 4, 3, 2}, 10);
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_HARDENED = EnumHelper.addArmorMaterial("HARDENED", 100, new int[]{2, 6, 4, 2}, 20);
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_REDSTONE = EnumHelper.addArmorMaterial("REDSTONE", 100, new int[]{3, 6, 5, 2}, 30);
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_RESONANT = EnumHelper.addArmorMaterial("RESONANT", 100, new int[]{3, 8, 6, 3}, 40);
    // Leadstone Armor
    public static Item leadstoneHelmet;
    public static Item leadstoneChest;
    public static Item leadstoneLegs;
    public static Item leadstoneBoots;
    // Hardened Armor
    public static Item hardenedHelmet;
    public static Item hardenedChest;
    public static Item hardenedLegs;
    public static Item hardenedBoots;
    // Redstone Armor
    public static Item redstoneHelmet;
    public static Item redstoneChest;
    public static Item redstoneLegs;
    public static Item redstoneBoots;
    // Resonant Armor
    public static Item resonantHelmet;
    public static Item resonantChest;
    public static Item resonantLegs;
    public static Item resonantBoots;

    public static Item energyCore;
    public static Item armorUpgrade;

    public static void preInit()
    {

        // TODO: Create a better way to register the items & stuffs!

        //region Leadstone
        leadstoneHelmet = new ItemRFArmor(ARMOR_MATERIAL_LEADSTONE, 0, Reference.Numbers.LEADSTONE_MAX_ENERGY, Reference.Numbers.LEADSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.LEADSTONE, 3).setUnlocalizedName(Reference.Names.LEADSTONE_HELMET).setTextureName(Reference.MODID + ":" + Reference.Names.LEADSTONE_HELMET);
        leadstoneChest = new ItemRFArmor(ARMOR_MATERIAL_LEADSTONE, 1, Reference.Numbers.LEADSTONE_MAX_ENERGY, Reference.Numbers.LEADSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.LEADSTONE, 3).setUnlocalizedName(Reference.Names.LEADSTONE_CHEST).setTextureName(Reference.MODID + ":" + Reference.Names.LEADSTONE_CHEST);
        leadstoneLegs = new ItemRFArmor(ARMOR_MATERIAL_LEADSTONE, 2, Reference.Numbers.LEADSTONE_MAX_ENERGY, Reference.Numbers.LEADSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.LEADSTONE, 3).setUnlocalizedName(Reference.Names.LEADSTONE_LEGS).setTextureName(Reference.MODID + ":" + Reference.Names.LEADSTONE_LEGS);
        leadstoneBoots = new ItemRFArmor(ARMOR_MATERIAL_LEADSTONE, 3, Reference.Numbers.LEADSTONE_MAX_ENERGY, Reference.Numbers.LEADSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.LEADSTONE, 3).setUnlocalizedName(Reference.Names.LEADSTONE_BOOTS).setTextureName(Reference.MODID + ":" + Reference.Names.LEADSTONE_BOOTS);

        registerItem(leadstoneHelmet, Reference.Names.LEADSTONE_HELMET);
        registerItem(leadstoneChest, Reference.Names.LEADSTONE_CHEST);
        registerItem(leadstoneLegs, Reference.Names.LEADSTONE_LEGS);
        registerItem(leadstoneBoots, Reference.Names.LEADSTONE_BOOTS);
        //endregion

        //region Hardened
        hardenedHelmet = new ItemRFArmor(ARMOR_MATERIAL_HARDENED, 0, Reference.Numbers.HARDENED_MAX_ENERGY, Reference.Numbers.HARDENED_MAX_TRANSFER, ItemRFArmor.ArmorMetal.HARDENED, 5).setUnlocalizedName(Reference.Names.HARDENED_HELMET).setTextureName(Reference.MODID + ":" + Reference.Names.HARDENED_HELMET);
        hardenedChest = new ItemRFArmor(ARMOR_MATERIAL_HARDENED, 1, Reference.Numbers.HARDENED_MAX_ENERGY, Reference.Numbers.HARDENED_MAX_TRANSFER, ItemRFArmor.ArmorMetal.HARDENED, 5).setUnlocalizedName(Reference.Names.HARDENED_CHEST).setTextureName(Reference.MODID + ":" + Reference.Names.HARDENED_CHEST);
        hardenedLegs = new ItemRFArmor(ARMOR_MATERIAL_HARDENED, 2, Reference.Numbers.HARDENED_MAX_ENERGY, Reference.Numbers.HARDENED_MAX_TRANSFER, ItemRFArmor.ArmorMetal.HARDENED, 5).setUnlocalizedName(Reference.Names.HARDENED_LEGS).setTextureName(Reference.MODID + ":" + Reference.Names.HARDENED_LEGS);
        hardenedBoots = new ItemRFArmor(ARMOR_MATERIAL_HARDENED, 3, Reference.Numbers.HARDENED_MAX_ENERGY, Reference.Numbers.HARDENED_MAX_TRANSFER, ItemRFArmor.ArmorMetal.HARDENED, 5).setUnlocalizedName(Reference.Names.HARDENED_BOOTS).setTextureName(Reference.MODID + ":" + Reference.Names.HARDENED_BOOTS);

        registerItem(hardenedChest, Reference.Names.HARDENED_HELMET);
        registerItem(hardenedHelmet, Reference.Names.HARDENED_CHEST);
        registerItem(hardenedLegs, Reference.Names.HARDENED_LEGS);
        registerItem(hardenedBoots, Reference.Names.HARDENED_BOOTS);
        //endregion

        //region Redstone
        redstoneHelmet = new ItemRFArmor(ARMOR_MATERIAL_REDSTONE, 0, Reference.Numbers.REDSTONE_MAX_ENERGY, Reference.Numbers.REDSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.REDSTONE, 10).setUnlocalizedName(Reference.Names.REDSTONE_HELMET).setTextureName(Reference.MODID + ":" + Reference.Names.REDSTONE_HELMET);
        redstoneChest = new ItemRFArmor(ARMOR_MATERIAL_REDSTONE, 1, Reference.Numbers.REDSTONE_MAX_ENERGY, Reference.Numbers.REDSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.REDSTONE, 10).setUnlocalizedName(Reference.Names.REDSTONE_CHEST).setTextureName(Reference.MODID + ":" + Reference.Names.REDSTONE_CHEST);
        redstoneLegs = new ItemRFArmor(ARMOR_MATERIAL_REDSTONE, 2, Reference.Numbers.REDSTONE_MAX_ENERGY, Reference.Numbers.REDSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.REDSTONE, 10).setUnlocalizedName(Reference.Names.REDSTONE_LEGS).setTextureName(Reference.MODID + ":" + Reference.Names.REDSTONE_LEGS);
        redstoneBoots = new ItemRFArmor(ARMOR_MATERIAL_REDSTONE, 3, Reference.Numbers.REDSTONE_MAX_ENERGY, Reference.Numbers.REDSTONE_MAX_TRANSFER, ItemRFArmor.ArmorMetal.REDSTONE, 10).setUnlocalizedName(Reference.Names.REDSTONE_BOOTS).setTextureName(Reference.MODID + ":" + Reference.Names.REDSTONE_BOOTS);

        registerItem(redstoneHelmet, Reference.Names.REDSTONE_HELMET);
        registerItem(redstoneChest, Reference.Names.REDSTONE_CHEST);
        registerItem(redstoneLegs, Reference.Names.REDSTONE_LEGS);
        registerItem(redstoneBoots, Reference.Names.REDSTONE_BOOTS);
        //endregion

        //region Resonant
        resonantHelmet = new ItemRFArmor(ARMOR_MATERIAL_RESONANT, 0, Reference.Numbers.RESONANT_MAX_ENERGY, Reference.Numbers.RESONANT_MAX_TRANSFER, ItemRFArmor.ArmorMetal.RESONANT, 15).setUnlocalizedName(Reference.Names.RESONANT_HELMET).setTextureName(Reference.MODID + ":" + Reference.Names.RESONANT_HELMET);
        resonantChest = new ItemRFArmor(ARMOR_MATERIAL_RESONANT, 1, Reference.Numbers.RESONANT_MAX_ENERGY, Reference.Numbers.RESONANT_MAX_TRANSFER, ItemRFArmor.ArmorMetal.RESONANT, 15).setUnlocalizedName(Reference.Names.RESONANT_CHEST).setTextureName(Reference.MODID + ":" + Reference.Names.RESONANT_CHEST);
        resonantLegs = new ItemRFArmor(ARMOR_MATERIAL_RESONANT, 2, Reference.Numbers.RESONANT_MAX_ENERGY, Reference.Numbers.RESONANT_MAX_TRANSFER, ItemRFArmor.ArmorMetal.RESONANT, 15).setUnlocalizedName(Reference.Names.RESONANT_LEGS).setTextureName(Reference.MODID + ":" + Reference.Names.RESONANT_LEGS);
        resonantBoots = new ItemRFArmor(ARMOR_MATERIAL_RESONANT, 3, Reference.Numbers.RESONANT_MAX_ENERGY, Reference.Numbers.RESONANT_MAX_TRANSFER, ItemRFArmor.ArmorMetal.RESONANT, 15).setUnlocalizedName(Reference.Names.RESONANT_BOOTS).setTextureName(Reference.MODID + ":" + Reference.Names.RESONANT_BOOTS);

        registerItem(resonantHelmet, Reference.Names.RESONANT_HELMET);
        registerItem(resonantChest, Reference.Names.RESONANT_CHEST);
        registerItem(resonantLegs, Reference.Names.RESONANT_LEGS);
        registerItem(resonantBoots, Reference.Names.RESONANT_BOOTS);
        //endregion

        energyCore = new ItemSimpleFoiled().setUnlocalizedName(Reference.Names.ENERGY_CORE).setTextureName(Reference.MODID + ":" + Reference.Names.ENERGY_CORE).setCreativeTab(CreativeTab.CASKE_TAB);
        registerItem(energyCore, Reference.Names.ENERGY_CORE);

        armorUpgrade = new ItemArmorUpgrade();
        registerItem(armorUpgrade, Reference.Names.ARMOR_UPGRADE);
    }

    public static void init()
    {
    }

    public static void postInit()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(energyCore, new Object[]{"IVI", "XLX", "IVI", 'I', Blocks.redstone_block, 'V', Items.gold_ingot, 'X', TEItems.powerCoilElectrum, 'L', TEItems.capacitorBasic}));
        //region Armor Recipes
        GameRegistry.addRecipe(new ShapedOreRecipe(leadstoneHelmet, new Object[]{"IVI", "I I", 'I', "ingotLead", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(leadstoneChest, new Object[]{"I I", "IVI", "III", 'I', "ingotLead", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(leadstoneLegs, new Object[]{"IVI", "I I", "I I", 'I', "ingotLead", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(leadstoneBoots, new Object[]{"   ", "IVI", "I I", 'I', "ingotLead", 'V', energyCore}));

        GameRegistry.addRecipe(new ShapedOreRecipe(hardenedHelmet, new Object[]{"IVI", "I I", 'I', "ingotInvar", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(hardenedChest, new Object[]{"I I", "IVI", "III", 'I', "ingotInvar", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(hardenedLegs, new Object[]{"IVI", "I I", "I I", 'I', "ingotInvar", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(hardenedBoots, new Object[]{"   ", "IVI", "I I", 'I', "ingotInvar", 'V', energyCore}));

        GameRegistry.addRecipe(new ShapedOreRecipe(redstoneHelmet, new Object[]{"IVI", "I I", 'I', "ingotElectrum", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(redstoneChest, new Object[]{"I I", "IVI", "III", 'I', "ingotElectrum", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(redstoneLegs, new Object[]{"IVI", "I I", "I I", 'I', "ingotElectrum", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(redstoneBoots, new Object[]{"   ", "IVI", "I I", 'I', "ingotElectrum", 'V', energyCore}));

        GameRegistry.addRecipe(new ShapedOreRecipe(resonantHelmet, new Object[]{"IVI", "I I", 'I', "ingotEnderium", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(resonantChest, new Object[]{"I I", "IVI", "III", 'I', "ingotEnderium", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(resonantLegs, new Object[]{"IVI", "I I", "I I", 'I', "ingotEnderium", 'V', energyCore}));
        GameRegistry.addRecipe(new ShapedOreRecipe(resonantBoots, new Object[]{"   ", "IVI", "I I", 'I', "ingotEnderium", 'V', energyCore}));
        //endregion Recipes

        GameRegistry.addRecipe(new ArmorUpgrade(new ItemStack(leadstoneLegs, 1), 0, new Object[]{leadstoneLegs, new ItemStack(armorUpgrade, 1, 0)}));
        GameRegistry.addRecipe(new ArmorUpgrade(new ItemStack(leadstoneHelmet, 1), 0, new Object[]{leadstoneHelmet, new ItemStack(armorUpgrade, 1, 1)}));
    }

    private static void registerItem(Item item, String name)
    {
        if (item != null)
        {
            GameRegistry.registerItem(item, name);
        }
    }
}
