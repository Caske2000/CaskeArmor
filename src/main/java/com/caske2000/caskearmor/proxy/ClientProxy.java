package com.caske2000.caskearmor.proxy;

import com.caske2000.caskearmor.client.settings.KeyBindings;
import com.caske2000.caskearmor.handler.HUDHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);
    }

    public void init(FMLInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(HUDHandler.instance);
        super.init(e);
    }

    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);
    }

    @Override
    public void registerKeyBindings()
    {
        ClientRegistry.registerKeyBinding(KeyBindings.optionsMenu);
    }
}
