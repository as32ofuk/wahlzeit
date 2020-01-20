package de.henny022.wahlzeit.screenshots.model;

public class Screenshot
{
    private ScreenshotType type;
    private ScreenshotManager manager;

    public Screenshot(ScreenshotType type, ScreenshotManager manager)
    {
        // TODO not null
        this.type = type;
        this.manager = manager;
    }

    public boolean isInstance(ScreenshotType screenshotType)
    {
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
