package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration config;
    public static int HUDLayout;
    public static int HUDPosition;
    public static boolean extendedHUD;

    public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfigs();
        }
    }

    private static void loadConfigs()
    {
        HUDLayout = config.getInt("HUDLayout", Configuration.CATEGORY_GENERAL, 1, 1, 2, "1 = Horizontal, 2 = Vertical");
        HUDPosition = config.getInt("HUDPosition", Configuration.CATEGORY_GENERAL, 1, 1, 3, "1 = Top, 2 = Center, 3 = Bottom");
        extendedHUD = config.getBoolean("extendedHUD", Configuration.CATEGORY_GENERAL, true, "Do you want to show an extended HUD?");
        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent e)
    {
        if (e.modID.equalsIgnoreCase(Reference.MODID)) // Resync configs
            loadConfigs();
    }
}
