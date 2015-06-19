package com.caske2000.caskearmor.item;

import com.caske2000.caskearmor.CaskeArmor;
import com.caske2000.caskearmor.creativetab.CreativeTab;
import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTestArmor extends ItemArmor{

    public String textureName;

    public ItemTestArmor() {
        super(ArmorMaterial.IRON, 2, 1);
        this.setUnlocalizedName(Reference.Names.TEST_CHEST);
        this.setTextureName(Reference.MODID + ":" + Reference.Names.TEST_CHEST);
        this.textureName = "armor";
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.CASKE_TAB);
    }

    @Override
    //@SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return Reference.MODID + ":textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40));
    }
}
