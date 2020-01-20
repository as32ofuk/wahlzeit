package de.henny022.wahlzeit.screenshots.model;

import java.util.HashMap;
import java.util.Map;

import static de.henny022.wahlzeit.screenshots.Assert.*;

public class ScreenshotManager
{
    protected Map<String, ScreenshotType> types;

    public ScreenshotManager()
    {
        types = new HashMap<>();
    }

    public Screenshot createScreenshot(String typename)
    {
        assertTrue(types.containsKey(typename), "unknown typename");
        ScreenshotType type = getScreenshotType(typename);
        Screenshot screenshot = type.createInstance();
        return screenshot;
    }

    public ScreenshotType createScreenshotType(String typename)
    {
        return createScreenshotType(typename, null);
    }

    public ScreenshotType createScreenshotType(String typename, ScreenshotType superType)
    {
        assertFalse(types.containsKey(typename), "typename taken");
        ScreenshotType type = new ScreenshotType(typename, this);
        if(superType != null)
        {
            superType.addSubType(type);
        }
        types.put(typename, type);
        return type;
    }

    public ScreenshotType getScreenshotType(String typename)
    {
        assertTrue(types.containsKey(typename), "type does not exist");
        return types.get(typename);
    }
}
