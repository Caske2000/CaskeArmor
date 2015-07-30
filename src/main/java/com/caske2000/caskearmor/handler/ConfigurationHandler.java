package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    private static final String CATEGORY_CASKE = "caske";
    public static Configuration config;
    public static boolean enableHUD;
    public static boolean extendedHUD;
    public static int HUDLayout;
    public static int HUDPosition;

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
        enableHUD = config.getBoolean("enableHUD", CATEGORY_CASKE, true, "Do you want to enable the ingame HUD?");
        extendedHUD = config.getBoolean("extendedHUD", CATEGORY_CASKE, false, "Do you want to show an extended HUD?");
        HUDLayout = config.getInt("HUDLayout", CATEGORY_CASKE, 1, 1, 2, "1 = Horizontal, 2 = Vertical");
        HUDPosition = config.getInt("HUDPosition", CATEGORY_CASKE, 1, 1, 3, "1 = Top, 2 = Center, 3 = Bottom");
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
