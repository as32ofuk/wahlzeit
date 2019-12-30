package de.henny022.wahlzeit.screenshots.model;

import de.henny022.wahlzeit.screenshots.doc.DesignPattern;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;

import java.util.NoSuchElementException;

@DesignPattern(
        name = "Singleton",
        participants = {
                "Singleton"
        }
)
public class ScreenshotPhotoManager extends PhotoManager
{
    /**
     *
     */
    protected static final ScreenshotPhotoManager instance = new ScreenshotPhotoManager();

    /**
     * https://youtu.be/-FRm3VPhseI
     */
    public ScreenshotPhotoManager()
    {
        photoTagCollector = ScreenshotPhotoFactory.getInstance().createPhotoTagCollector();
    }

    @Override
    protected Photo doGetPhotoFromId(PhotoId id)
    {
        Photo result = super.doGetPhotoFromId(id);
        if(result != null)
        {
            return result;
        }
        throw new NoSuchElementException("no photo found for this id");
    }

    /**
     * https://youtu.be/-FRm3VPhseI
     */
    public Photo getPhotoFromId(PhotoId id)
    {
        if(id == null)
        {
            throw new IllegalArgumentException("photo id must not be null");
        }

        Photo result;
        try
        {
            result = doGetPhotoFromId(id);
        }
        catch(NoSuchElementException e)
        {
            result = ScreenshotPhotoFactory.getInstance().loadPhoto(id);
            doAddPhoto(result);
        }

        return result;
    }

}
