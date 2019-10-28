package org.wahlzeit.model;

public class Coordinate
{
    private double x;
    private double y;
    private double z;

    public Coordinate()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public Coordinate(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    double getDistance(Coordinate other)
    {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y) + (z - other.z) * (z - other.z));
    }

    boolean isEqual(Coordinate other)
    {
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Coordinate)
        {
            return isEqual((Coordinate) o);
        }
        return false;
    }
}
