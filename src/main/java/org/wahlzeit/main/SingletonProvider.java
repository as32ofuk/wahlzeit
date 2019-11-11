package org.wahlzeit.main;

import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;

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

    public static void init(PhotoManager photoManager, UserManager userManager)
    {
        if(instance == null)
        {
            instance = new SingletonProvider(photoManager, userManager);
        }
        else
        {
            throw new IllegalStateException("SingletonProvider is already initialized");
        }
    }

    private Map<Class, Object> singletons;

    private SingletonProvider(PhotoManager photoManager, UserManager userManager)
    {
        singletons = new HashMap<>();
        singletons.put(PhotoManager.class, photoManager);
        singletons.put(UserManager.class, userManager);
    }

}
