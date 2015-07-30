package com.caske2000.caskearmor.util;

import com.caske2000.caskearmor.lib.Reference;
import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

/*
    Code By Pahimar (https://goo.gl/Y7awrI)
 */

public class LogHelper
{
    private static void log(Level logLevel, Object object)
    {
        FMLLog.log(Reference.NAME, logLevel, String.valueOf(object));
    }

    public static void all(Object object)
    {
        log(Level.ALL, object);
    }

    public static void debug(Object object)
    {
        log(Level.DEBUG, object);
    }

    public static void error(Object object)
    {
        log(Level.ERROR, object);
    }

    public static void fatal(Object object)
    {
        log(Level.FATAL, object);
    }

    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    public static void off(Object object)
    {
        log(Level.OFF, object);
    }

    public static void warn(Object object)
    {
        log(Level.WARN, object);
    }
}