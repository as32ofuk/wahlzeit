package de.henny022.wahlzeit.screenshots.model;

import de.henny022.wahlzeit.screenshots.doc.DesignPattern;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;


@DesignPattern(
        name = "Abstract Factory",
        participants = {
                "ConcreteFactory"
        }
)
public class ScreenshotPhotoFactory extends PhotoFactory
{
    /**
     * https://github.com/dirkriehle/wahlzeit/issues/49
     */
    private static final Logger log = Logger.getLogger(ScreenshotPhotoFactory.class.getName());


    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance()
    {
        if(instance == null)
        {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
            setInstance(new ScreenshotPhotoFactory());
        }

        return instance;
    }

    protected ScreenshotPhotoFactory()
    {
    }

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
