package de.henny022.wahlzeit.screenshots.model;

import static de.henny022.wahlzeit.screenshots.Assert.*;

public class Screenshot
{
    private ScreenshotType type;
    private ScreenshotManager manager;

    public Screenshot(ScreenshotType type, ScreenshotManager manager)
    {
        assertNotNull(type, "type must not be null");
        assertNotNull(manager, "manager must not be null");
        this.type = type;
        this.manager = manager;
    }

    public boolean isInstance(ScreenshotType screenshotType)
    {
        assertNotNull(screenshotType, "type must not be null");
        return screenshotType.hasInstance(this);
    }

    public ScreenshotType getType()
    {
        return type;
    }

    public ScreenshotManager getManager()
    {
        return manager;
    }
}
