package de.henny022.wahlzeit.screenshots.model;

public class Screenshot
{
    private ScreenshotType type;
    private ScreenshotManager manager;

    public Screenshot(ScreenshotType type, ScreenshotManager manager)
    {
        this.type = type;
        this.manager = manager;
    }
}
