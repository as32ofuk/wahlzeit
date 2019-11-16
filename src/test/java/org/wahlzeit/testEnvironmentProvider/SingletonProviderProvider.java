package org.wahlzeit.testEnvironmentProvider;

import org.junit.Before;
import org.junit.rules.ExternalResource;
import org.wahlzeit.handlers.WebPartHandlerManager;
import org.wahlzeit.main.SingletonProvider;
import org.wahlzeit.model.GlobalsManager;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;

public class SingletonProviderProvider extends ExternalResource
{
    @Override
    protected void before() throws Throwable
    {
        if(!SingletonProvider.isInitialized())
        {
            UserManager userManager = new UserManager();
            PhotoFactory photoFactory = new PhotoFactory(userManager);
            ImageStorage imageStorage = new DatastoreAdapter();
            GlobalsManager globalsManager = new GlobalsManager(userManager);
            PhotoManager photoManager = new PhotoManager(photoFactory, userManager, globalsManager, imageStorage);
            WebPartHandlerManager webPartHandlerManager = new WebPartHandlerManager();

            SingletonProvider.init(photoManager, userManager, imageStorage, webPartHandlerManager);
        }
    }
}
