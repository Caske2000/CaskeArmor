package com.caske2000.caskearmor.item;

import cofh.lib.util.helpers.StringHelper;
import com.caske2000.caskearmor.crafting.ArmorUpgrade;
import com.caske2000.caskearmor.creativetab.CreativeTab;
import com.caske2000.caskearmor.lib.Reference;
import com.caske2000.caskearmor.util.CStringHelper;
import com.caske2000.caskearmor.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemArmorUpgrade extends Item
{
    private int metaItems;
    private IIcon[] icons;

    public ItemArmorUpgrade()
    {
        setUnlocalizedName("armorUpgrade");
        setTextureName("armorUpgrade");
        setHasSubtypes(true);
        setMaxDamage(0);
        setCreativeTab(CreativeTab.CASKE_TAB);
        metaItems = ArmorUpgrade.upgradeTypes.length;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
    {
        if (StringHelper.isShiftKeyDown())
        {
            list.add(CStringHelper.localize("info.caske.upgrade"));
        } else
        {
            list.add(CStringHelper.shiftForInfo());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list)
    {
        for (int i = 0; i < metaItems; i++)
            list.add(new ItemStack(item, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        if (meta > metaItems)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, metaItems);
        return this.getUnlocalizedName() + "." + ArmorUpgrade.upgradeTypes[i];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.icons = new IIcon[metaItems];

        for (int i = 0; i < metaItems; ++i)
        {
            this.icons[i] = iconRegister.registerIcon(Reference.MODID + ":" + this.getIconString() + "_" + ArmorUpgrade.upgradeTypes[i]);
        }
    }

    public String getUpgrade(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() >= metaItems || itemStack.getItemDamage() < 0)
        {
            LogHelper.warn("METAITEMS: " + metaItems);
            throw new NumberFormatException("Integer is out of range: " + itemStack.getItemDamage());
        } else
            return ArmorUpgrade.upgradeTypes[itemStack.getItemDamage()];
    }
}
