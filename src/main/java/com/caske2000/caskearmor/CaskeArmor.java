package com.caske2000.caskearmor;

import com.caske2000.caskearmor.handler.ConfigurationHandler;
import com.caske2000.caskearmor.handler.KeyInputEventHandler;
import com.caske2000.caskearmor.handler.ModEventHandler;
import com.caske2000.caskearmor.lib.Reference;
import com.caske2000.caskearmor.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.ID, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class CaskeArmor
{

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.Instance
    public static CaskeArmor instance = new CaskeArmor();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        ConfigurationHandler.init(e.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
        MinecraftForge.EVENT_BUS.register(new ModEventHandler());
        proxy.registerKeyBindings();
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        FMLCommonHandler.instance().bus().register(new KeyInputEventHandler());
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }
}