package de.henny022.wahlzeit.screenshots.model;

import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.UserManager;

public class ScreenshotPhoto extends Photo
{
    public ScreenshotPhoto(UserManager userManager)
    {
        super(userManager);
    }

    public ScreenshotPhoto(UserManager userManager, PhotoId myId)
    {
        super(userManager, myId);
    }
}
