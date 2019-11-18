package de.henny022.wahlzeit.screenshots.model;

import static java.lang.Math.*;

public abstract class AbstractCoordinate implements Coordinate
{
    @Override
    public double getCartesianDistance(Coordinate other)
    {
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
        SphericCoordinate thisSperical = this.asSphericCoordinate();
        SphericCoordinate otherSperical = other.asSphericCoordinate();
        double phi1 = thisSperical.phi;
        double phi2 = otherSperical.phi;
        double dTheta = abs(thisSperical.theta - otherSperical.theta);
        return acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(dTheta));
    }
}
