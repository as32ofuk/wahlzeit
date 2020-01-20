package de.henny022.wahlzeit.screenshots.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static de.henny022.wahlzeit.screenshots.Assert.assertNotNull;
import static de.henny022.wahlzeit.screenshots.Assert.assertNull;

public class ScreenshotType
{
    protected String name;
    protected ScreenshotManager manager;

    protected ScreenshotType superType;
    protected Set<ScreenshotType> subTypes;

    public ScreenshotType(String name, ScreenshotManager manager)
    {
        assertNotNull(manager, "manager must not be null");
        this.name = name;
        this.manager = manager;
        this.superType = null;
        this.subTypes = new HashSet<>();
    }

    public void addSubType(ScreenshotType type)
    {
        assertNotNull(type, "type must not be null");
        type.setSuperType(type);
        subTypes.add(type);
    }

    public Screenshot createInstance()
    {
        return new Screenshot(this, manager);
    }

    public String getName()
    {
        return name;
    }

    private void setSuperType(ScreenshotType superType)
    {
        assertNull(this.superType, "already has a supertype");
        assertNotNull(superType, "superType must not be null");
        this.superType = superType;
    }

    public boolean hasInstance(Screenshot screenshot)
    {
        if(screenshot == null)
        {
            return false;
        }
        if(screenshot.getType() == this)
        {
            return true;
        }
        for(ScreenshotType type : subTypes)
        {
            if(screenshot.getType() == type)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isSubtype(ScreenshotType type)
    {
        return type.hasSubtype(this);
    }

    public boolean hasSubtype(ScreenshotType type)
    {
        if(type == null)
        {
            return false;
        }
        if(subTypes.contains(type))
        {
            return true;
        }
        for(ScreenshotType type1 : subTypes)
        {
            if(type1.hasSubtype(type))
            {
                return true;
            }
        }
        return false;
    }

    public ScreenshotType getSuperType()
    {
        return superType;
    }

    public ScreenshotManager getManager()
    {
        return manager;
    }

    public Collection<ScreenshotType> getSubTypes()
    {
        return subTypes;
    }
}
