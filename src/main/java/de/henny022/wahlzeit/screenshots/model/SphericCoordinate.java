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
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate()
    {
        assertClassInvariants();
        double x = radius * sin(theta) * cos(phi);
        double y = radius * sin(theta) * sin(phi);
        double z = radius * cos(theta);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate()
    {
        return this;
    }

    protected void assertClassInvariants()
    {
        assert phi > 0;
        assert phi < PI;
        assert theta > -PI;
        assert theta < PI;
        assert radius > 0;
    }
}
