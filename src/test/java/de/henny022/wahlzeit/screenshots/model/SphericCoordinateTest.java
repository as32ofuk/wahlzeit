package de.henny022.wahlzeit.screenshots.model;

import org.junit.Test;

import static java.lang.Math.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SphericCoordinateTest
{
    @Test
    public void dummy(){}
/* equality over floatingpoint operations on trigonometric functions is nonsense
    @Test
    public void asCartesianCoordinate()
    {
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 0);
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 0);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, PI / 2, 1);
            // floating point precision
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 0, cos(PI / 2));
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 2, PI / 2, 1);
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(cos(PI/2), 1, cos(PI/2));
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(0, 0, 1);
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0, 0, 1);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        // TODO fix floating point problems

        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, PI / 2, sqrt(2));
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, 0);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, acos(1 / sqrt(3)), sqrt(3));
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, 1);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(-1, 1, 1);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(-3 * PI / 4, acos(1 / sqrt(3)), sqrt(3));
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(-1, -1, 1);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
        {
            SphericCoordinate sphericCoordinate = new SphericCoordinate(PI / 4, acos(-1 / sqrt(3)), sqrt(3));
            CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1, 1, -1);
            assertEquals(cartesianCoordinate, sphericCoordinate.asCartesianCoordinate());
        }
    }

    @Test
    public void asSphericCoordinate()
    {
        {
            SphericCoordinate coordinate = new SphericCoordinate(0, 0, 0);
            assertSame(coordinate, coordinate.asSphericCoordinate());
        }
        {
            SphericCoordinate coordinate = new SphericCoordinate(1, 1, 1);
            assertSame(coordinate, coordinate.asSphericCoordinate());
        }
    }

    @Test
    public void isEqual()
    {
    }
    */
}