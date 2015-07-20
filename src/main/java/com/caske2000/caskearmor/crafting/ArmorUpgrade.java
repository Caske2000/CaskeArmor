package com.caske2000.caskearmor.crafting;


import cofh.api.energy.IEnergyContainerItem;
import com.caske2000.caskearmor.item.IItemRFUpgradable;
import com.caske2000.caskearmor.item.ItemRFArmor;
import com.caske2000.caskearmor.util.LogHelper;
import com.caske2000.caskearmor.util.NBTHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ArmorUpgrade extends ShapelessOreRecipe
{
    private final IEnergyContainerItem resultItem;
    private final int itemMeta;

    public ArmorUpgrade(ItemStack result, Object... recipe)
    {
        super(result, recipe);
        this.resultItem = (IEnergyContainerItem) result.getItem();
        this.itemMeta = result.getItemDamage();
        //result.getEnchantmentTagList();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        ItemStack result = new ItemStack((Item) this.resultItem, 1, this.itemMeta);

        ItemStack stackInSlot;
        NBTTagCompound tagCompound = null;
        for (int i = 0; i < var1.getSizeInventory(); i++)
        {
            stackInSlot = var1.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() != null)
            {
                if (stackInSlot.getItem() instanceof ItemRFArmor)
                {
                    tagCompound = (NBTTagCompound) NBTHelper.getNBT(stackInSlot).copy();
                } else if (stackInSlot.getItem() == Items.sugar)
                {
                    NBTHelper.setBoolean(result, "SPEED", true);
                    LogHelper.info(result.getTagCompound().getBoolean("SPEED"));
                }
            }
        }

        if (tagCompound != null)
            result.setTagCompound(tagCompound);

        return result;
    }
}
