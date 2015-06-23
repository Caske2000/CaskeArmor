package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.item.IHUDInfoItem;
import com.caske2000.caskearmor.lib.Reference;
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

public class HUDHandler extends Gui {

    // TODO Fix Graphical Output (not only showing chestplate)

    public static HUDHandler instance = new HUDHandler();
    // Had Problems using the COFH Resource, just made a copy
    private static final ResourceLocation POWERBAR = new ResourceLocation("caskearmor", "textures/hud/energy.png");

    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            newRenderToHUD();
        } else if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR) {
            ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int x = 100;
            int y = 2;
            renderPowerBar();
        }
    }

    private static Minecraft mc = Minecraft.getMinecraft();

    public static void newRenderToHUD() {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo) {
            ItemStack chest = mc.thePlayer.getCurrentArmor(2);
            if (chest != null && chest.getItem() instanceof IHUDInfoItem) {
                IHUDInfoItem provider = (IHUDInfoItem) chest.getItem();
                List<String> HUDInfo = new ArrayList<String>();

                provider.addInfoToHUD(HUDInfo, chest);
                if (HUDInfo.isEmpty())
                    return;

                int i = 0;
                for (String str : HUDInfo) {
                    drawToHUD(str, i, 0x13A8F2);
                    i++;
                }
            }
        }
    }

    public static void drawToHUD(String str, int lineNumber, int color) {
        int x = 2;
        int y = 2;
        y += lineNumber * 10;

        mc.fontRenderer.drawStringWithShadow(str, x, y, color);
    }

    private void renderPowerBar() {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo) {
            ItemStack chest = mc.thePlayer.getCurrentArmor(2);
            if (chest != null && chest.getItem() instanceof IHUDInfoItem) {
                // (double) because I was having problems with integer division
                int perc = (int) Math.round((double) chest.getTagCompound().getInteger("ENERGY") / (double) chest.getTagCompound().getInteger("MAX_ENERGY")* 100.0D);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                mc.renderEngine.bindTexture(POWERBAR);
                ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
                int x = res.getScaledWidth() - 35;
                // Empty Energy Bar: u=16, v=4, res=30x100
                this.drawTexturedModalRect(x, 5, 16, 4, 30, 100);
                // Filled Energy Bar: u=148, v=4, res=30x100
                this.drawTexturedModalRect(x, 105 - perc, 148, 4, 30, perc);
            }
        }
    }
}