package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.client.gui.ModGuiConfig;
import com.caske2000.caskearmor.client.settings.KeyBindings;
import com.caske2000.caskearmor.lib.Key;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;

public class KeyInputEventHandler
{
    private static Key getPressedKey()
    {
        if (KeyBindings.optionsMenu.isPressed())
        {
            return Key.OPTIONS_MENU;
        }
        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (getPressedKey() == Key.OPTIONS_MENU && Minecraft.getMinecraft().theWorld.isRemote)
            Minecraft.getMinecraft().displayGuiScreen(new ModGuiConfig(Minecraft.getMinecraft().currentScreen));
    }
}
