package de.henny022.wahlzeit.screenshots.model;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;

import java.util.logging.Logger;

public class ScreenshotPhotoFactory extends PhotoFactory
{
    /**
     * https://github.com/dirkriehle/wahlzeit/issues/49
     */
    private static final Logger log = Logger.getLogger(ScreenshotPhotoFactory.class.getName());

    /**
     * @methodtype factory
     */
    public Photo createPhoto()
    {
        return new ScreenshotPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    public Photo createPhoto(PhotoId id)
    {
        return new ScreenshotPhoto(id);
    }

}
