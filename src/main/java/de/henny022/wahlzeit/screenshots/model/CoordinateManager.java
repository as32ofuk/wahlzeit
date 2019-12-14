package de.henny022.wahlzeit.screenshots.model;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Objects;

import static java.lang.Math.*;

public class CoordinateManager
{
    // global state is evil, but im not gonna try doing wahlzeit with dependency injection again
    private static final BiMap<String, CartesianCoordinate> cartesianCoordinates = HashBiMap.create();
    // fu
    private static final BiMap<String, SphericCoordinate> sphericCoordinates = HashBiMap.create();

    private CoordinateManager()
    {
    }

    /**
     * default value
     *
     * @return
     */
    public static Coordinate getCoordinate()
    {
        return getCartesianCoordinate(0, 0, 0);
    }

    private static void createCoordinate(double x, double y, double z)
    {
        String identifier = getIdentifier(x, y, z);
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);
        SphericCoordinate sphericCoordinate = convert(cartesianCoordinate);
        cartesianCoordinates.put(identifier, cartesianCoordinate);
        sphericCoordinates.put(identifier, sphericCoordinate);
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z)
    {
        String identifier = getIdentifier(x, y, z);
        CartesianCoordinate result = cartesianCoordinates.get(identifier);
        if(result == null)
        {
            synchronized(cartesianCoordinates)
            {
                result = cartesianCoordinates.get(identifier);
                if(result == null)
                {
                    createCoordinate(x, y, z);
                    result = cartesianCoordinates.get(identifier);
                }
            }
        }
        return result;
    }

    public static CartesianCoordinate getCartesianCoordinate(SphericCoordinate sphericCoordinate)
    {
        String identifier = sphericCoordinates.inverse().get(sphericCoordinate);
        return cartesianCoordinates.get(identifier);
    }

    public static SphericCoordinate getSphericCoordinate(double phi, double theta, double radius)
    {
        double x = radius * sin(theta) * cos(phi);
        double y = radius * sin(theta) * sin(phi);
        double z = radius * cos(theta);
        String identifier = getIdentifier(x, y, z);
        getCartesianCoordinate(x, y, z);// side effect ensures coordinate exists
        return sphericCoordinates.get(identifier);
    }

    public static boolean equals(Coordinate first, Coordinate second)
    {
        if(first == second)
        {
            return true;
        }
        String identifier1 = getIdentifier(first);
        String identifier2 = getIdentifier(second);
        return identifier1.equals(identifier2);
    }

    public static int hashCode(Coordinate coordinate)
    {
        return getIdentifier(coordinate).hashCode();
    }

    public static SphericCoordinate getSphericCoordinate(CartesianCoordinate cartesianCoordinate)
    {
        String identifier = cartesianCoordinates.inverse().get(cartesianCoordinate);
        return sphericCoordinates.get(identifier);
    }

    private static SphericCoordinate convert(CartesianCoordinate coordinate)
    {
        double x = coordinate.x;
        double y = coordinate.y;
        double z = coordinate.z;
        double r = sqrt(x * x + y * y + z * z);
        double phi = atan2(y, x);
        double theta;
        if(Double.compare(r, 0) == 0)
        {
            theta = 0;
        }
        else
        {
            theta = acos(z / r);
        }
        return new SphericCoordinate(phi, theta, r);
    }

    private static String getIdentifier(Coordinate coordinate)
    {
        if(coordinate instanceof CartesianCoordinate)
        {
            return getIdentifier(((CartesianCoordinate) coordinate).x, ((CartesianCoordinate) coordinate).y, ((CartesianCoordinate) coordinate).z);
        }
        else if(coordinate instanceof SphericCoordinate)
        {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) coordinate;
            double x = sphericCoordinate.radius * sin(sphericCoordinate.theta) * cos(sphericCoordinate.phi);
            double y = sphericCoordinate.radius * sin(sphericCoordinate.theta) * sin(sphericCoordinate.phi);
            double z = sphericCoordinate.radius * cos(sphericCoordinate.theta);
            return getIdentifier(x, y, z);
        }
        throw new IllegalArgumentException("illegal coordinate implementation");
    }

    private static String getIdentifier(double x, double y, double z)
    {
        return "" + x + y + z;
    }
}
