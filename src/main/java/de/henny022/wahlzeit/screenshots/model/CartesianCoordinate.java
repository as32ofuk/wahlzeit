package de.henny022.wahlzeit.screenshots.model;

import static java.lang.Math.*;

public class CartesianCoordinate extends AbstractCoordinate
{
    protected double x;
    protected double y;
    protected double z;

    public CartesianCoordinate(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        try
        {
            assertClassInvariants();
        }
        catch(IllegalStateException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate()
    {
        assertClassInvariants();
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate()
    {
        assertClassInvariants();
        return CoordinateManager.getSphericCoordinate(this);
    }

    protected void assertClassInvariants()
    {
        if(!(Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z)))
        {
            throw new IllegalStateException("x, y, z must be finite numbers (not NaN of INF)");
        }
    }
}
