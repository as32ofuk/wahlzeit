package de.henny022.wahlzeit.screeshots.model;

public abstract class AbstractCoordinate implements Coordiante
{
    @Override
    public double getCartesianDistance(Coordiante other)
    {
        CartesianCoordinate thisCartesian = this.asCartesianCoordinate();
        CartesianCoordinate otherCartesian = other.asCartesianCoordinate();
        double distanceSquared = (thisCartesian.x * thisCartesian.x) - (otherCartesian.x * otherCartesian.x) +
                (thisCartesian.y * thisCartesian.y) - (otherCartesian.y * otherCartesian.y) +
                (thisCartesian.z * thisCartesian.z) - (otherCartesian.z * otherCartesian.z);
        return Math.sqrt(distanceSquared);
    }

    @Override
    public double getCentralAngle(Coordiante other)
    {
        return 0;
    }
}
