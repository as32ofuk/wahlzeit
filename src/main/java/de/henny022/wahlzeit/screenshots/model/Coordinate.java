package de.henny022.wahlzeit.screenshots.model;

public interface Coordinate
{
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate other);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordinate other);

    boolean isEqual(Coordinate other);
}
