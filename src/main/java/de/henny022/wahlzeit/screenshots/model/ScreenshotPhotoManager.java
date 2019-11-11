package de.henny022.wahlzeit.screenshots.model;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;

public class ScreenshotPhotoManager extends PhotoManager
{
    /**
     * @param photoFactory
     */
    public ScreenshotPhotoManager(PhotoFactory photoFactory)
    {
        super(photoFactory);
    }
}
