package com.caske2000.caskearmor.proxy;

import com.caske2000.caskearmor.item.ModItems;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent e)
    {
        ModItems.preInit();
    }

    public void init(FMLInitializationEvent e)
    {
        ModItems.init();
    }

    public void postInit(FMLPostInitializationEvent e)
    {
        ModItems.postInit();
    }

    public void registerKeyBindings()
    {  // NO-OP
    }
}