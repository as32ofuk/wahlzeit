package de.henny022.wahlzeit.screenshots.model;

import org.junit.Test;

import static java.lang.Math.*;
import static org.junit.Assert.*;

public class CartesianCoordinateTest
{

    @Test
    public void asCartesianCoordinate()
    {
        {
            CartesianCoordinate coordinate = new CartesianCoordinate(0, 0, 0);
            assertSame(coordinate, coordinate.asCartesianCoordinate());
        }
        {
            CartesianCoordinate coordinate = new CartesianCoordinate(1, 1, 1);
            assertSame(coordinate, coordinate.asCartesianCoordinate());
        }
    }

    @Test
    public void asSphericCoordinate()
    {
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 0);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 0);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 0, 0);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, PI / 2, 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 1, 0);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 2, PI / 2, 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 1);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, 0);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, PI / 2, sqrt(2));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, 1);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(-1, 1, 1);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(-1, -1, 1);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(-3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, -1);
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, acos(-1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
    }

    @Test
    public void isEqual()
    {
        {
            CartesianCoordinate coordinate = new CartesianCoordinate(0,0,0);
            CartesianCoordinate other = new CartesianCoordinate(0, 0, 0);
            assertTrue(coordinate.isEqual(other));
        }
        {
            CartesianCoordinate coordinate = new CartesianCoordinate(0,0,0);
            assertTrue(coordinate.isEqual(coordinate.asSphericCoordinate()));
        }
    }
}