package de.henny022.wahlzeit.screenshots.model;

import java.util.Objects;

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

    @Override
    public boolean isEqual(Coordinate other)
    {
        SphericCoordinate that = other.asSphericCoordinate();
        return Double.compare(that.phi, phi) == 0 &&
                Double.compare(that.theta, theta) == 0 &&
                Double.compare(that.radius, radius) == 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        SphericCoordinate that = (SphericCoordinate) o;
        return Double.compare(that.phi, phi) == 0 &&
                Double.compare(that.theta, theta) == 0 &&
                Double.compare(that.radius, radius) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(phi, theta, radius);
    }
}
