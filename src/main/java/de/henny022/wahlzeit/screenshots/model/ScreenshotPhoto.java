package de.henny022.wahlzeit.screenshots.model;

import de.henny022.wahlzeit.screenshots.doc.DesignPattern;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

@DesignPattern(
        name = "Abstract Factory",
        participants = {
                "ConcreteProduct"
        }
)
public class ScreenshotPhoto extends Photo
{
    public ScreenshotPhoto()
    {
    }

    public ScreenshotPhoto(PhotoId myId)
    {
        super(myId);
    }
}
