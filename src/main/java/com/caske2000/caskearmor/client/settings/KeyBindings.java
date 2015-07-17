package com.caske2000.caskearmor.client.settings;

import com.caske2000.caskearmor.lib.Reference;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings
{
    public static KeyBinding optionsMenu = new KeyBinding(Reference.Keys.OPTIONS_MENU, Keyboard.KEY_O, Reference.Keys.CATEGORY); // 0x18 key
}
