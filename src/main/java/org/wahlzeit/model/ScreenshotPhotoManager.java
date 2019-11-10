package org.wahlzeit.model;

public class ScreenshotPhotoManager extends PhotoManager
{
    /**
     *
     */
    protected static final ScreenshotPhotoManager instance = new ScreenshotPhotoManager();

    /**
     *
     */
    public static final ScreenshotPhotoManager getInstance2()
    {
        return instance;
    }

    /**
     * https://youtu.be/-FRm3VPhseI
     */
    public ScreenshotPhotoManager()
    {
        photoTagCollector = ScreenshotPhotoFactory.getInstance().createPhotoTagCollector();
    }

    /**
     * https://youtu.be/-FRm3VPhseI
     */
    public Photo getPhotoFromId(PhotoId id)
    {
        if(id == null)
        {
            return null;
        }

        Photo result = doGetPhotoFromId(id);

        if(result == null)
        {
            result = ScreenshotPhotoFactory.getInstance().loadPhoto(id);
            if(result != null)
            {
                doAddPhoto(result);
            }
        }

        return result;
    }

}
