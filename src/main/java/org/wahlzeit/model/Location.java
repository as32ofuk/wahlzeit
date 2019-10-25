package org.wahlzeit.model;

public class Location
{
    public Coordinate coordinate;

    public Location()
    {
        coordinate = new Coordinate();
    }

    public Location(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
}
