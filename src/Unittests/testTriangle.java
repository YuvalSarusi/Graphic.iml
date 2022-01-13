package Unittests;

import Exceptions.PointBehindCameraException;
import Exceptions.PointNotOnObjectException;
import Exceptions.RayDoesntTouchObjectException;
import Geometries.Surface;
import Geometries.Triangle;
import Primitives.*;
import jdk.jshell.execution.Util;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class testTriangle {


    @Test
    public void testGetNormal() throws PointNotOnObjectException {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        sqrt3 = Double.parseDouble(String.format("%.12f",sqrt3));
        assertEquals(new PointNotOnObjectException().getMessage(), new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }



    @Test
    public void testfindIntersectionsRay() throws RayDoesntTouchObjectException, PointBehindCameraException, PointNotOnObjectException {
        Triangle tr = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));

        Surface pl = new Surface(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        ray = new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1));
        double num = Double.parseDouble(String.format("%.12f",1d / 3));
        assertEquals("Bad intersection", Arrays.asList(new Point3D(num, num, num)), tr.findIntersections(ray));


        // TC02: Against edge
        ray = new Ray(new Point3D(0, 0, -1), new Vector(1, 1, 0));
        assertEquals(new PointNotOnObjectException(ray).getMessage(), Arrays.asList(new Point3D(1, 1, -1)), pl.findIntersections(ray));
        assertNull(new PointNotOnObjectException(ray).getMessage(), tr.findIntersections(ray));



        // TC03: Against vertex
        ray = new Ray(new Point3D(0, 0, 2), new Vector(-1, -1, 0));
        assertEquals("Wrong intersection with plane", Arrays.asList(new Point3D(-0.5, -0.5, 2)), pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // =============== Boundary Values Tests ==================
        // TC11: In vertex
        ray = new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0));
        assertEquals("Wrong intersection with plane", Arrays.asList(new Point3D(0, 1, 0)), pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));


        // TC12: On edge
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(1, 1, 0));
        assertEquals("Wrong intersection with plane", Arrays.asList(new Point3D(0.5, 0.5, 0)), pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));

        // TC13: On edge continuation
        ray = new Ray(new Point3D(-2, 0, 0), new Vector(1, 1, 0));
        assertEquals("Wrong intersection with plane",Arrays.asList(new Point3D(-0.5, 1.5, 0)), pl.findIntersections(ray));
        assertNull("Bad intersection", tr.findIntersections(ray));


    }


}
