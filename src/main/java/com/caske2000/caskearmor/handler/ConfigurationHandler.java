package com.caske2000.caskearmor.handler;

import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler
{
    public static Configuration config;
    public static boolean extendedHUD = false;

    public static void init(File configFile)
    {
        if (config == null)
        {
            config = new Configuration(configFile);
            loadConfigs();
        }
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent e)
    {
        if (e.modID.equalsIgnoreCase(Reference.MODID)) // Resync configs
            loadConfigs();
    }

    private static void loadConfigs()
    {
        extendedHUD = config.getBoolean("extendedHUD", Configuration.CATEGORY_GENERAL, true, "Do you want an extended HUD for displaying the energylevel of your armor?");

        if (config.hasChanged())
            config.save();
    }
}
