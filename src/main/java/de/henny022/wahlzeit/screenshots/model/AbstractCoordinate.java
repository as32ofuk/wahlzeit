package de.henny022.wahlzeit.screenshots.model;

import java.util.Objects;

import static java.lang.Math.*;

public abstract class AbstractCoordinate implements Coordinate
{
    public static double COMPARE_EPSILON = 1e-15;

    @Override
    public double getCartesianDistance(Coordinate other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException("other coordinate must not be null");
        }
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        double distanceSquared = (thisCartesian.x * thisCartesian.x) - (otherCartesian.x * otherCartesian.x) +
                (thisCartesian.y * thisCartesian.y) - (otherCartesian.y * otherCartesian.y) +
                (thisCartesian.z * thisCartesian.z) - (otherCartesian.z * otherCartesian.z);
        return Math.sqrt(distanceSquared);
    }

    @Override
    public double getCentralAngle(Coordinate other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException("other coordinate must not be null");
        }
        SphericCoordinate thisSperical = this.asSphericCoordinate();
        SphericCoordinate otherSperical = other.asSphericCoordinate();
        double phi1 = thisSperical.phi;
        double phi2 = otherSperical.phi;
        double dTheta = abs(thisSperical.theta - otherSperical.theta);
        return acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(dTheta));
    }

    @Override
    public boolean isEqual(Coordinate other)
    {
        if(other == null)
        {
            throw new IllegalArgumentException("other coordinate must not be null");
        }
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        return abs(thisCartesian.x - otherCartesian.x) < COMPARE_EPSILON &&
                abs(thisCartesian.y - otherCartesian.y) < COMPARE_EPSILON &&
                abs(thisCartesian.y - otherCartesian.y) < COMPARE_EPSILON;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof Coordinate))
        {
            return false;
        }
        return isEqual((Coordinate) o);
    }

    @Override
    public int hashCode()
    {
        CartesianCoordinate cartesian = asCartesianCoordinate();
        return Objects.hash(cartesian.x, cartesian.y, cartesian.z);
    }
}
