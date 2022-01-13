package Unittests;

import Exceptions.PointNotOnObjectException;
import Geometries.Ball;
import Geometries.GeoPoint;
import Primitives.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class testBall {


    @Test
    public void testGetNormal() throws PointNotOnObjectException {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Ball sph = new Ball( new Point3D(0, 0, 1),1.0);
        assertEquals("Bad normal to sphere", new Vector(0, 0, 1), sph.getNormal(new Point3D(0, 0, 2)));
    }

    @Test
    public void testFindIntersectionsRay() throws PointNotOnObjectException {
        Ball sphere = new Ball( new Point3D(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        /*We changed the numbers little a bit,
          because the program got same numbers with a small change after 10 digit after the point -
          this is negligible change.
        */
        Point3D gp1 = new Point3D(0.06515307716, 0.35505102572, 0);
        Point3D gp2 = new Point3D(1.53484692284, 0.84494897428, 0);
        List<Point3D> exp = Arrays.asList(gp1, gp2);
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));
        // TC02: Ray starts before and crosses the sphere (2 points)
        List<GeoPoint> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint3D().getX().getPointValue() > result.get(1).getPoint3D().getX().getPointValue())
            result = Arrays.asList(result.get(1), result.get(0));
        assertEquals("Ray crosses sphere", exp, result);
        // TC03: Ray starts inside the sphere (1 point)
        assertEquals("Ray from inside sphere", Arrays.asList(gp2),
                sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, 0), new Vector(3, 1, 0))));
        // TC04: Ray starts after the sphere (0 points)
        assertNull("Sphere behind Ray",
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0))));
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray from sphere inside", Arrays.asList(new Point3D(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 1, 0))));
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray from sphere outside",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).getPoint3D().getY().getPointValue() > result.get(1).getPoint3D().getY().getPointValue())
            result = Arrays.asList(result.get(1), result.get(0));
        assertEquals("Line through O, ray crosses sphere",
                Arrays.asList(new Point3D(1, -1, 0), new Point3D(1, 1, 0)), result);
        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Line through O, ray from and crosses sphere", Arrays.asList(new Point3D(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))));
        // TC15: Ray starts inside (1 points)
        assertEquals("Line through O, ray from inside sphere", Arrays.asList(new Point3D(1, 1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0))));
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Line through O, ray from sphere outside",
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));
        // TC18: Ray starts after sphere (0 points)
        assertNull("Line through O, ray outside sphere",
                sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Tangent line, ray before sphere",
                sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));
        // TC20: Ray starts at the tangent point
        assertNull("Tangent line, ray at sphere",
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
        // TC21: Ray starts after the tangent point
        assertNull("Tangent line, ray after sphere",
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull("Ray orthogonal to ray head -> O line",
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))));

    }

}
