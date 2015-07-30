package com.caske2000.caskearmor.crafting;


import cofh.api.energy.IEnergyContainerItem;
import com.caske2000.caskearmor.item.ItemArmorUpgrade;
import com.caske2000.caskearmor.item.ItemRFArmor;
import com.caske2000.caskearmor.util.NBTHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.List;

public class ArmorUpgrade extends ShapelessOreRecipe
{
    private final IEnergyContainerItem resultItem;
    private final int itemMeta;
    public static final String[] upgradeTypes = new String[]{"SPEED", "NIGHT_VISION", "AUTO_FEEDER", "EFFICIENCY", "FLIGHT"};

    private List<String> unappliedUpgrades = new ArrayList<String>();

    public ArmorUpgrade(ItemStack result, Object... recipe)
    {
        super(result, recipe);
        this.resultItem = (IEnergyContainerItem) result.getItem();
        this.itemMeta = result.getItemDamage();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.itemMeta);

        ItemStack stackInSlot;
        NBTTagCompound tagCompound = null;
        ItemArmorUpgrade armorUpgrade = null;
        for (int i = 0; i < var1.getSizeInventory(); i++)
        {
            stackInSlot = var1.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() != null)
            {
                if (stackInSlot.getItem() instanceof ItemRFArmor)
                {
                    tagCompound = (NBTTagCompound) NBTHelper.getNBT(stackInSlot).copy();
                } else if (stackInSlot.getItem() instanceof ItemArmorUpgrade)
                {
                    armorUpgrade = (ItemArmorUpgrade) stackInSlot.getItem();
                    String upgrade = armorUpgrade.getUpgrade(stackInSlot);
                    if (tagCompound != null)
                        tagCompound.setBoolean(upgrade, true);
                    else
                        unappliedUpgrades.add(upgrade);
                }
            }
        }

        if (tagCompound != null)
        {
            if (!unappliedUpgrades.isEmpty())
            {
                for (String upgrade : unappliedUpgrades)
                    tagCompound.setBoolean(upgrade, true);
            }
            result.setTagCompound(tagCompound);
        }

        return result;
    }
}
