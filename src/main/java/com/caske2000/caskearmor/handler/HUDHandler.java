package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.item.IHUDInfoItem;
import com.caske2000.caskearmor.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class HUDHandler extends Gui
{
    // Had Problems using the COFH Resource, just made a copy
    private static final ResourceLocation POWERBAR_V = new ResourceLocation("caskearmor", "textures/hud/energy_V.png");
    private static final ResourceLocation POWERBAR_H = new ResourceLocation("caskearmor", "textures/hud/energy_H.png");
    public static final HUDHandler instance = new HUDHandler();
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT)
        {
            renderHUD();
        } else if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
        {
            renderPowerBar();
        }
    }

    public static void renderHUD()
    {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo)
        {
            ItemStack helmet = mc.thePlayer.getCurrentArmor(3);
            if (helmet != null && helmet.getItem() instanceof IHUDInfoItem)
            {
                IHUDInfoItem helmetProvider = (IHUDInfoItem) helmet.getItem();
                drawToHUD(helmetProvider.getHUDString(helmet), 2);
            }
            ItemStack chest = mc.thePlayer.getCurrentArmor(2);
            if (chest != null && chest.getItem() instanceof IHUDInfoItem)
            {
                IHUDInfoItem chestProvider = (IHUDInfoItem) chest.getItem();
                drawToHUD(chestProvider.getHUDString(chest), 12);
            }
            ItemStack legs = mc.thePlayer.getCurrentArmor(1);
            if (legs != null && legs.getItem() instanceof IHUDInfoItem)
            {
                IHUDInfoItem legsProvider = (IHUDInfoItem) legs.getItem();
                drawToHUD(legsProvider.getHUDString(legs), 22);
            }
            ItemStack boots = mc.thePlayer.getCurrentArmor(0);
            if (boots != null && boots.getItem() instanceof IHUDInfoItem)
            {
                IHUDInfoItem bootsProvider = (IHUDInfoItem) boots.getItem();
                drawToHUD(bootsProvider.getHUDString(boots), 32);
            }
        }
    }

    public static void drawToHUD(String str, int yPos)
    {
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int yOffset = 0;
        switch (ConfigurationHandler.HUDPosition)
        {
            case 2:
                yOffset = res.getScaledHeight() / 2 - 20;
                break;
            case 3:
                yOffset = res.getScaledHeight() - 40;
                break;
        }
        yPos += yOffset;
        mc.fontRenderer.drawStringWithShadow(str, 2, yPos, 0x13A8F2);
    }

    //region Powerbar
    private void renderPowerBar()
    {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo)
        {
            ItemStack boots = mc.thePlayer.getCurrentArmor(3);
            if (boots != null && boots.getItem() instanceof IHUDInfoItem)
            {
                drawPowerBar((int) Math.round((double) boots.getTagCompound().getInteger("ENERGY") / (double) boots.getTagCompound().getInteger("MAX_ENERGY") * 100.0D), 3);
            }
            ItemStack legs = mc.thePlayer.getCurrentArmor(2);
            if (legs != null && legs.getItem() instanceof IHUDInfoItem)
            {
                drawPowerBar((int) Math.round((double) legs.getTagCompound().getInteger("ENERGY") / (double) legs.getTagCompound().getInteger("MAX_ENERGY") * 100.0D), 2);
            }
            ItemStack chest = mc.thePlayer.getCurrentArmor(1);
            if (chest != null && chest.getItem() instanceof IHUDInfoItem)
            {
                drawPowerBar((int) Math.round((double) chest.getTagCompound().getInteger("ENERGY") / (double) chest.getTagCompound().getInteger("MAX_ENERGY") * 100.0D), 1);
            }
            ItemStack helmet = mc.thePlayer.getCurrentArmor(0);
            if (helmet != null && helmet.getItem() instanceof IHUDInfoItem)
            {
                drawPowerBar((int) Math.round((double) helmet.getTagCompound().getInteger("ENERGY") / (double) helmet.getTagCompound().getInteger("MAX_ENERGY") * 100.0D), 0);
            }
        }
    }

    private void drawPowerBar(int perc, int armorType)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int x = res.getScaledWidth() - 103;
        int y = 2;
        if (ConfigurationHandler.HUDLayout == 1)
        {
            switch (armorType)
            {
                case 3:
                    break;
                case 2:
                    y = 15;
                    break;
                case 1:
                    y = 28;
                    break;
                case 0:
                    y = 41;
                    break;
            }
            drawHorzBar(x, y, perc);
        }
        if (ConfigurationHandler.HUDLayout == 2)
        {
            x = res.getScaledWidth() - 52;
            switch (armorType)
            {
                case 2:
                    x += 13;
                    break;
                case 1:
                    x += 26;
                    break;
                case 0:
                    x += 39;
                    break;
            }
            drawVertBar(x, y, perc);
        }
    }

    private void drawHorzBar(int x, int y, int perc)
    {
        mc.renderEngine.bindTexture(POWERBAR_H);
        this.drawTexturedModalRect(x + perc, y, 0, 0, 100 - perc, 10);
        this.drawTexturedModalRect(x, y, 0, 128, perc, 10);
    }

    private void drawVertBar(int x, int y, int perc)
    {
        mc.renderEngine.bindTexture(POWERBAR_V);
        this.drawTexturedModalRect(x, y, 0, 0, 10, 100 - perc);
        this.drawTexturedModalRect(x, y + 100 - perc, 128, 0, 10, perc);
    }
    //endregion
}