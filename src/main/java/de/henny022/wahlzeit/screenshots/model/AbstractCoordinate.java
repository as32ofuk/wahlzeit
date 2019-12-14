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
        CartesianCoordinate otherCartesian = null;
        try
        {
            otherCartesian = other.asCartesianCoordinate();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("other coordinate is invalid", e);
        }
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
        SphericCoordinate otherSperical = null;
        try
        {
            otherSperical = other.asSphericCoordinate();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("other coordinate is invalid", e);
        }
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
        CartesianCoordinate otherCartesian = null;
        try
        {
            otherCartesian = other.asCartesianCoordinate();
        }
        catch(IllegalStateException e)
        {
            throw new IllegalArgumentException("other coordinate is invalid", e);
        }
        return abs(thisCartesian.x - otherCartesian.x) < COMPARE_EPSILON &&
                abs(thisCartesian.y - otherCartesian.y) < COMPARE_EPSILON &&
                abs(thisCartesian.y - otherCartesian.y) < COMPARE_EPSILON;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof Coordinate)
        {
            return CoordinateManager.equals(this, (Coordinate) o);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return CoordinateManager.hashCode(this);
    }
}
