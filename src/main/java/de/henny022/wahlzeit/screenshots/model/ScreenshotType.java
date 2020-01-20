package de.henny022.wahlzeit.screenshots.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ScreenshotType
{
    protected String name;
    protected ScreenshotManager manager;

    protected ScreenshotType superType;
    protected Set<ScreenshotType> subTypes;

    public ScreenshotType(String name, ScreenshotManager manager)
    {
        this.name = name;
        this.manager = manager;
        this.superType = null;
        this.subTypes = new HashSet<>();
    }

    public void addSubType(ScreenshotType type)
    {
        // TODO precondition type != null
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
        // TODO precondition superType not set
        this.superType = superType;
    }

    public boolean hasInstance(Screenshot screenshot)
    {
        // TODO screenshot not null
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
