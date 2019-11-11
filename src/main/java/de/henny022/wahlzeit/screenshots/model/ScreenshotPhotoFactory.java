package de.henny022.wahlzeit.screenshots.model;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.UserManager;

import java.util.logging.Logger;

public class ScreenshotPhotoFactory extends PhotoFactory
{
    /**
     * https://github.com/dirkriehle/wahlzeit/issues/49
     */
    private static final Logger log = Logger.getLogger(ScreenshotPhotoFactory.class.getName());

    public ScreenshotPhotoFactory(UserManager userManager)
    {
        super(userManager);
    }

    /**
     * @methodtype factory
     */
    public Photo createPhoto()
    {
        return new ScreenshotPhoto(userManager);
    }

    /**
     * Creates a new photo with the specified id
     */
    public Photo createPhoto(PhotoId id)
    {
        return new ScreenshotPhoto(userManager, id);
    }

}
