package de.henny022.wahlzeit.screenshots.model;

public class Location
{
    public Coordinate coordinate;

    public Location()
    {
        coordinate = CoordinateManager.getCoordinate();
    }

    public Location(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }
}
