package org.wahlzeit.main;

import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.persistence.ImageStorage;

import java.util.HashMap;
import java.util.Map;

public class SingletonProvider
{
    private static SingletonProvider instance;

    public static SingletonProvider getInstance()
    {
        if(instance == null)
        {
            throw new IllegalStateException("SingletonProvider wasn't initialized");
        }
        return instance;
    }

    public static <T> T getInstance(Class<T> clazz)
    {
        return (T) getInstance().singletons.get(clazz);
    }

    public static void init(PhotoManager photoManager, UserManager userManager, ImageStorage imageStorage, WebPartHandlerManager webPartHandlerManager)
    {
        if(instance == null)
        {
            instance = new SingletonProvider(photoManager, userManager, imageStorage, webPartHandlerManager);
        }
        else
        {
            throw new IllegalStateException("SingletonProvider is already initialized");
        }
    }

    public static boolean isInitialized()
    {
        return instance != null;
    }

    private Map<Class, Object> singletons;

    private SingletonProvider(PhotoManager photoManager, UserManager userManager, ImageStorage imageStorage, WebPartHandlerManager webPartHandlerManager)
    {
        singletons = new HashMap<>();
        singletons.put(PhotoManager.class, photoManager);
        singletons.put(UserManager.class, userManager);
        singletons.put(ImageStorage.class, imageStorage);
        singletons.put(WebPartHandlerManager.class, webPartHandlerManager);
    }

}
