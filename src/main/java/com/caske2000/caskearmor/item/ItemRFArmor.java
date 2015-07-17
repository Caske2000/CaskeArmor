package com.caske2000.caskearmor.item;

import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.ItemArmorAdv;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import com.caske2000.caskearmor.creativetab.CreativeTab;
import com.caske2000.caskearmor.handler.ConfigurationHandler;
import com.caske2000.caskearmor.lib.Reference;
import com.caske2000.caskearmor.util.CStringHelper;
import com.caske2000.caskearmor.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

public class ItemRFArmor extends ItemArmorAdv implements IEnergyContainerItem, ISpecialArmor, IHUDInfoItem
{
    // TODO Own the code!
    private int maxEnergy;
    private int maxTransfer;
    private int energyPerDamage = 150;
    private ArmorMetal armorMetal;

    public ItemRFArmor(ArmorMaterial material, int type, int maxEnergy, int maxTransfer, ArmorMetal metal)
    {
        super(material, type);
        this.maxEnergy = maxEnergy;
        this.maxTransfer = maxTransfer;
        this.armorMetal = metal;
        setCreativeTab(CreativeTab.CASKE_TAB);
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack)
    {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {

        if (stack.getTagCompound().getInteger("MAX_ENERGY") == 0)
            NBTHelper.setInteger(stack, "MAX_ENERGY", maxEnergy);

        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        if (StringHelper.isShiftKeyDown())
        {
            list.add(CStringHelper.localize("info.caske.energy") + ": " + stack.stackTagCompound.getInteger("ENERGY") + " / " + maxEnergy + " RF");
            list.add(CStringHelper.localize("info.caske.io") + ": " + maxTransfer + " RF/t");
        } else
        {
            list.add(CStringHelper.shiftForInfo());
        }
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public int receiveEnergy(ItemStack armor, int maxReceive, boolean simulate)
    {
        if (armor.stackTagCompound == null)
            NBTHelper.setInteger(armor, "ENERGY", 0);
        int stored = armor.stackTagCompound.getInteger("ENERGY");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate)
        {
            stored += receive;
            armor.stackTagCompound.setInteger("ENERGY", stored);
        }
        return receive;
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public int extractEnergy(ItemStack armor, int maxExtract, boolean simulate)
    {
        if (armor.stackTagCompound == null)
            NBTHelper.setInteger(armor, "ENERGY", 0);
        int stored = armor.stackTagCompound.getInteger("ENERGY");
        int extract = Math.min(maxExtract, stored);

        if (!simulate)
        {
            stored -= extract;
            armor.stackTagCompound.setInteger("ENERGY", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack armor)
    {
        if (armor.stackTagCompound == null)
            NBTHelper.setInteger(armor, "ENERGY", 0);
        return armor.stackTagCompound.getInteger("ENERGY");
    }

    @Override
    public int getMaxEnergyStored(ItemStack armor)
    {
        return maxEnergy;
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        if (source.isUnblockable())
        {
            int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
            return new ArmorProperties(0, getAbsorbRatio(armorMetal) * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
        }
        int absorbMax = getEnergyPerDamage(armor) > 0 ? 25 * getEnergyStored(armor) / getEnergyPerDamage(armor) : 0;
        return new ArmorProperties(0, getAbsorbRatio(armorMetal) * getArmorMaterial().getDamageReductionAmount(armorType) * 0.05, absorbMax);
        // 0.05 = 1 / 20 (max armor)
    }

    // Copied from the RedstoneArsenal repository
    protected int getEnergyPerDamage(ItemStack stack)
    {
        int unbLvl = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        return energyPerDamage * (5 - unbLvl) / 5;
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        if (getEnergyStored(armor) >= getEnergyPerDamage(armor))
            return getAbsorptionRatio() / 5;
        return 0;
    }

    //Copied from the RedstoneArsenal repository
    // Returns a % that each piece absorbs, set sums to 100.
    protected int getAbsorptionRatio()
    {

        switch (armorType)
        {
            case 0:
                return 15;
            case 1:
                return 40;
            case 2:
                return 30;
            case 3:
                return 15;
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        extractEnergy(stack, damage * getEnergyPerDamage(stack), false);
    }

    @Override
    public int getMaxDamage(ItemStack stack)
    {
        return maxEnergy;
    }

    @Override
    public int getDisplayDamage(ItemStack armor)
    {
        if (armor.stackTagCompound == null)
            NBTHelper.setInteger(armor, "ENERGY", 0);
        return maxEnergy - armor.stackTagCompound.getInteger("ENERGY");
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }

    // Copied from the RedstoneArsenal repository, to add an item without energy and one with
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        list.add(CStringHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
        list.add(CStringHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
    }
    // TODO maybe add isDamaged

    @Override
    @SideOnly(Side.CLIENT)
    public void addInfoToHUD(List<String> list, ItemStack stack)
    {
        if (ConfigurationHandler.extendedHUD)
            list.add(CStringHelper.getHUDEnergy(stack.stackTagCompound.getInteger("ENERGY"), maxEnergy, armorType));
        else if ((double) getEnergyStored(stack) / (double) maxEnergy < 0.1)
            list.add(CStringHelper.getEnergyLow(armorType));
    }

    private double getAbsorbRatio(ArmorMetal type)
    {
        double absorbRatio = 0.7D;
        switch (type)
        {
            case LEADSTONE:
                absorbRatio = 0.5D;
                break;
            case HARDENED:
                absorbRatio = 0.7D;
                break;
            case REDSTONE:
                absorbRatio = 0.9D;
                break;
            case RESONANT:
                absorbRatio = 1.2D;
                break;
        }
        return absorbRatio;
    }

    public enum ArmorMetal
    {
        LEADSTONE, HARDENED, REDSTONE, RESONANT
    }
}
