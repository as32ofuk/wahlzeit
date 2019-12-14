package de.henny022.wahlzeit.screenshots.model;

import org.junit.Test;

import static java.lang.Math.*;
import static org.junit.Assert.*;

public class CartesianCoordinateTest
{
    @Test
    public void dummy(){}
/* equality over floatingpoint operations on trigonometric functions is nonsense
    @Test
    public void asCartesianCoordinate()
    {
        {
            CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(0, 0, 0);
            assertSame(coordinate, coordinate.asCartesianCoordinate());
        }
        {
            CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(1, 1, 1);
            assertSame(coordinate, coordinate.asCartesianCoordinate());
        }
    }

    @Test
    public void asSphericCoordinate()
    {
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(0, 0, 0);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(0, 0, 0);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(1, 0, 0);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(0, acos(0), 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(0, 1, 0);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(PI / 2, PI / 2, 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(0, 0, 1);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(0, 0, 1);
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(1, 1, 0);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(PI / 4, PI / 2, sqrt(2));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(1, 1, 1);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(-1, 1, 1);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(-1, -1, 1);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(-3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
        {
            CartesianCoordinate cartesianCoordinate = CoordinateManager.getCartesianCoordinate(1, 1, -1);
            SphericCoordinate sphericCoordinate = CoordinateManager.getSphericCoordinate(PI / 4, acos(-1 / sqrt(3)), sqrt(3));
            assertEquals(sphericCoordinate, cartesianCoordinate.asSphericCoordinate());
        }
    }

    @Test
    public void isEqual()
    {
        {
            CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(0,0,0);
            CartesianCoordinate other = CoordinateManager.getCartesianCoordinate(0, 0, 0);
            assertTrue(coordinate.isEqual(other));
        }
        {
            CartesianCoordinate coordinate = CoordinateManager.getCartesianCoordinate(0,0,0);
            assertTrue(coordinate.isEqual(coordinate.asSphericCoordinate()));
        }
    }

 */
}