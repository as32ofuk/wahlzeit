package de.henny022.wahlzeit.screenshots.model;

public class Location
{
    public Coordinate coordinate;

    public Location()
    {
        coordinate = new SphericCoordinate(0, 0, 0);
    }

    public Location(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
}