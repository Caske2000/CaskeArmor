package com.caske2000.caskearmor.item;

import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.ItemArmorAdv;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;
import com.caske2000.caskearmor.client.model.CustomArmorModel;
import com.caske2000.caskearmor.creativetab.CreativeTab;
import com.caske2000.caskearmor.handler.ConfigurationHandler;
import com.caske2000.caskearmor.lib.Reference;
import com.caske2000.caskearmor.util.CStringHelper;
import com.caske2000.caskearmor.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

public class ItemRFArmor extends ItemArmorAdv implements IEnergyContainerItem, ISpecialArmor, IItemRFUpgradable
{
    ModelBiped model = null;
    // TODO Own the code!
    private int maxEnergy;
    private int maxTransfer;
    private ArmorMetal armorMetal;
    private int upgradeSlots;

    public ItemRFArmor(ArmorMaterial material, int type, int maxEnergy, int maxTransfer, ArmorMetal metal, int upgradeSlots)
    {
        super(material, type);
        this.maxEnergy = maxEnergy;
        this.maxTransfer = maxTransfer;
        this.armorMetal = metal;
        this.upgradeSlots = upgradeSlots;
        setCreativeTab(CreativeTab.CASKE_TAB);
        setMaxStackSize(1);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if (player.getCurrentArmor(1).getItem() instanceof ItemRFArmor)         // LEGGINGS
        {
            if (player.getCurrentArmor(1).getTagCompound().getBoolean("SPEED"))
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 3, 3));
        }
    }

    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type)
    {
        switch (slot)
        {
            case 2:
                return Reference.MODID + ":models/armor/tester_layer_2.png"; //2 should be the slot for legs
            default:
                return Reference.MODID + ":models/armor/tester_layer_1.png";
        }
    }

    // Took from Blood Magic mod for testing
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        if (itemStack != null && (itemStack.getItem() instanceof ItemRFArmor))
        {
            int type = ((ItemArmor) itemStack.getItem()).armorType;
            if (type == 1 || type == 3)
            {
                model = new CustomArmorModel(1.0f, true);
            } else
            {
                model = new CustomArmorModel(0.5f, false);
            }
        }
        if (model != null)
        {
            model.bipedHead.showModel = armorSlot == 0;
            model.bipedHeadwear.showModel = armorSlot == 0;
            model.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            model.bipedRightArm.showModel = armorSlot == 1;
            model.bipedLeftArm.showModel = armorSlot == 1;
            model.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            model.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
            model.isSneak = entityLiving.isSneaking();
            model.isRiding = entityLiving.isRiding();
            model.isChild = entityLiving.isChild();
            model.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
            if (entityLiving instanceof EntityPlayer)
            {
                model.aimedBow = ((EntityPlayer) entityLiving).getItemInUseDuration() > 2;
            }
            return model;
        }
        return model;
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack)
    {
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {
        if (stack.getTagCompound() == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);

        if (stack.getTagCompound().getInteger("MAX_ENERGY") == 0)
            NBTHelper.setInteger(stack, "MAX_ENERGY", maxEnergy);

        if (StringHelper.isShiftKeyDown())
        {
            list.add(CStringHelper.localize("info.caske.energy") + ": " + stack.stackTagCompound.getInteger("ENERGY") + " / " + maxEnergy + " RF");
            list.add(CStringHelper.localize("info.caske.io") + ": " + maxTransfer + " RF/t");
            if (stack.getTagCompound().getBoolean("SPEED") != false)
                list.add(stack.getTagCompound().getBoolean("SPEED"));
        } else
        {
            list.add(CStringHelper.shiftForInfo());
        }
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public int receiveEnergy(ItemStack stack, int maxReceive, boolean simulate)
    {
        if (stack.stackTagCompound == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.stackTagCompound.getInteger("ENERGY");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate)
        {
            stored += receive;
            stack.stackTagCompound.setInteger("ENERGY", stored);
        }
        return receive;
    }

    // Copied from the RedstoneArsenal repository
    // I don't know of a way to make this into a better piece of code
    @Override
    public int extractEnergy(ItemStack stack, int maxExtract, boolean simulate)
    {
        if (stack.stackTagCompound == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        int stored = stack.stackTagCompound.getInteger("ENERGY");
        int extract = Math.min(maxExtract, stored);

        if (!simulate)
        {
            stored -= extract;
            stack.stackTagCompound.setInteger("ENERGY", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack stack)
    {
        if (stack.stackTagCompound == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        return stack.stackTagCompound.getInteger("ENERGY");
    }

    @Override
    public int getMaxEnergyStored(ItemStack stack)
    {
        return maxEnergy;
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack stack, DamageSource source, double damage, int slot)
    {
        int absorbMax = getEnergyPerDamage(stack) > 0 ? 25 * getEnergyStored(stack) / getEnergyPerDamage(stack) : 0;
        return new ArmorProperties(0, getAbsorbRatio(armorMetal) * getArmorMaterial().getDamageReductionAmount(armorType) * 0.025, absorbMax);
    }

    // Copied from the RedstoneArsenal repository
    protected int getEnergyPerDamage(ItemStack stack)
    {
        int unbrLvl = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack), 0, 4);
        int energyPerDamage = 150;
        return energyPerDamage * (5 - unbrLvl) / 5;
    }

    // Copied from the RedstoneArsenal repository
    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack stack, int slot)
    {
        if (getEnergyStored(stack) >= getEnergyPerDamage(stack))
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
    public int getDisplayDamage(ItemStack stack)
    {
        if (stack.stackTagCompound == null)
            NBTHelper.setInteger(stack, "ENERGY", 0);
        return maxEnergy - stack.stackTagCompound.getInteger("ENERGY");
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
    public String getHUDString(ItemStack stack)
    {
        String str = "";
        if (ConfigurationHandler.extendedHUD)
            str = CStringHelper.getHUDEnergy(stack.stackTagCompound.getInteger("ENERGY"), maxEnergy, armorType);
        else if ((double) getEnergyStored(stack) / (double) maxEnergy < 0.1)
            str = CStringHelper.getEnergyLow(armorType);
        return str;
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
