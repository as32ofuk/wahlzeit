package de.henny022.wahlzeit.screenshots.model;

import static java.lang.Math.*;

public class SphericCoordinate extends AbstractCoordinate
{
    protected double phi;
    protected double theta;
    protected double radius;

    public SphericCoordinate(double phi, double theta, double radius)
    {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
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
        return CoordinateManager.getCartesianCoordinate(this);
    }

    @Override
    public SphericCoordinate asSphericCoordinate()
    {
        assertClassInvariants();
        return this;
    }

    protected void assertClassInvariants()
    {
        if(!(theta >= 0 && theta <= PI))
        {
            throw new IllegalStateException("theta must be between 0 and pi");
        }
        if(!(phi >= -PI && phi <= PI))
        {
            throw new IllegalStateException("phi must be between -pi and pi");
        }
        if(!(radius >= 0))
        {
            throw new IllegalStateException("radius must be bigger than or equal to zero");
        }
    }
}
