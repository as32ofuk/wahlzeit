package de.henny022.wahlzeit.screenshots.model;

import java.util.Objects;

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
        assertClassInvariants();
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
        double r = sqrt(x * x + y * y + z * z);
        double phi = atan2(y, x);
        double theta;
        if(Double.compare(r, 0) == 0)
        {
            theta = 0;
        }
        else
        {
            theta = acos(z / r);
        }
        return new SphericCoordinate(phi, theta, r);
    }

    protected void assertClassInvariants()
    {
        if(!(Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z)))
        {
            throw new IllegalStateException("x, y, z must be finite numbers (not NaN of INF)");
        }
    }
}
