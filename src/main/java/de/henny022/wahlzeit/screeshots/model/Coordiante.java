package de.henny022.wahlzeit.screeshots.model;

public interface Coordiante
{
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordiante other);

    SphericCoordinate asSphericCoordinate();

    double getCentralAngle(Coordiante other);

    boolean isEqual(Coordiante other);
}
