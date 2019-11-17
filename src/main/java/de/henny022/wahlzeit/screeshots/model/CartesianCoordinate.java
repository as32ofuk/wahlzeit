package de.henny022.wahlzeit.screeshots.model;

import java.util.Objects;

import static java.lang.Math.atan;
import static java.lang.Math.sqrt;

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
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate()
    {
        return this;
    }


    @Override
    public SphericCoordinate asSphericCoordinate()
    {
        double r = sqrt(x * x + y * y + z * z);
        double phi = atan(y / x);
        double theta = atan(z / r);
        return new SphericCoordinate(phi, theta, r);
    }

    @Override
    public boolean isEqual(Coordiante other)
    {
        CartesianCoordinate that = other.asCartesianCoordinate();
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
}
