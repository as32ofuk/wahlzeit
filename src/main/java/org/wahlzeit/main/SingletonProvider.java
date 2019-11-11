package org.wahlzeit.main;

import java.util.HashMap;
import java.util.Map;

public class SingletonProvider
{
    private static SingletonProvider instance;

    public static SingletonProvider getInstance()
    {
        return instance;
    }

    public static <T> T getInstance(Class<T> clazz)
    {
        return (T) getInstance().singletons.get(clazz);
    }

    public static void init()
    {
        if(instance == null)
        {
            instance = new SingletonProvider();
        }
        else
        {
            throw new IllegalStateException("SingletonProvider is already initialized");
        }
    }

    private Map<Class, Object> singletons;

    private SingletonProvider()
    {
        singletons = new HashMap<>();
    }

}
