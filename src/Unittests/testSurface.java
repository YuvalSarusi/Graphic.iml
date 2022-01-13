package Unittests;

import Exceptions.PointBehindCameraException;
import Exceptions.PointNotOnObjectException;
import Exceptions.RayDoesntTouchObjectException;
import Geometries.Surface;
import Primitives.*;
import jdk.jshell.execution.Util;
import org.junit.Test;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class testSurface {

    @Test
    public void testGetNormalPoint3D() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Surface pl = new Surface(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        DecimalFormat df = new DecimalFormat("#.###############");
        sqrt3 = Double.parseDouble(String.format("%.12f",sqrt3));
        assertEquals("Bad normal to plane", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    @Test
    public void testfindIntersectionsRay() {
        Surface pl = new Surface(new Point3D(0, 0, 1), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals("Bad plane intersection", Arrays.asList(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))));

        // TC02: Ray out of plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC12: Ray in plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC13: Orthogonal ray into plane
        try {
            DecimalFormat df = new DecimalFormat("##############.#############");
            double num = 1d / 3;
            num = Double.parseDouble(String.format("%.12f",num));
            assertEquals("Bad plane intersection", Arrays.asList( new Point3D(num, num, num)),
                    pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC14: Orthogonal ray out of plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC15: Orthogonal ray out of plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC16: Orthogonal ray from plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC17: Ray from plane
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TC18: Ray from plane's Q point
        try {
            assertNull("Must not be plane intersection",
                    pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0))));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
