package de.henny022.wahlzeit.screeshots.model;

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
        return null;
    }

    @Override
    public double getCentralAngle(Coordiante other)
    {
        return 0;
    }

    @Override
    public boolean isEqual(Coordiante other)
    {
        return false;
    }
}
