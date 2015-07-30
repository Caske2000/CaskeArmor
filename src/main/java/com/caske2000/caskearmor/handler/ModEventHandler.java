package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.item.ItemRFArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ModEventHandler
{
    private int flyTime = 0;

    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event)
    {
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getCurrentArmor(2) != null)
            {
                ItemStack stack = player.getCurrentArmor(2);
                if (stack.getItem() instanceof ItemRFArmor)
                {
                    ItemRFArmor armor = (ItemRFArmor) stack.getItem();
                    if (stack.stackTagCompound.hasKey("FLIGHT") && stack.stackTagCompound.getBoolean("FLIGHT"))
                    {
                        if (stack.stackTagCompound.getInteger("ENERGY") > 1020)
                        {
                            armor.extractEnergy(stack, 20, false);
                            flyTime++;
                        }
                    }
                }
            }
            if (flyTime == 0 && !player.capabilities.isCreativeMode)
            {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
            }
            if (flyTime > 0)
            {
                flyTime--;
                player.capabilities.allowFlying = true;
                player.fallDistance = 0.0F;
            }
        }
    }
}
