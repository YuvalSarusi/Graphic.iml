package Unittests;

import Elements.Camera;
import Geometries.Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;
import Helper.Helper;

import static org.junit.Assert.assertEquals;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class testCamera {

        @Test
        public void testConstructRayThroughPixel() {
            Camera camera = new Camera(Helper.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
            // ============ Equivalence Partitions Tests ==============
            // TC01: 3X3 Corner (0,0)
            assertEquals("Bad ray1", new Ray(Helper.ZERO, new Vector(-2, -2, 10).normalize()),
                    camera.constructRayThroughPixel(3, 3, 0, 0, 10, 6, 6));

            // TC02: 4X4 Corner (0,0)
            assertEquals("Bad ray2", new Ray(Helper.ZERO, new Vector(-3, -3, 10).normalize()),
                    camera.constructRayThroughPixel(4, 4, 0, 0, 10, 8, 8));

            // TC03: 4X4 Side (0,1)
            assertEquals("Bad ray3", new Ray(Helper.ZERO, new Vector(-1, -3, 10).normalize()),
                    camera.constructRayThroughPixel(4, 4, 1, 0, 10, 8, 8));

            // TC04: 4X4 Inside (1,1)
            assertEquals("Bad ray4", new Ray(Helper.ZERO, new Vector(-1, -1, 10).normalize()),
                    camera.constructRayThroughPixel(4, 4, 1, 1, 10, 8, 8));

            // =============== Boundary Values Tests ==================
            // TC11: 3X3 Center (1,1)
            assertEquals("Bad ray5", new Ray(Helper.ZERO, new Vector(0, 0, 10).normalize()),
                    camera.constructRayThroughPixel(3, 3, 1, 1, 10, 6, 6));

            // TC12: 3X3 Center of Upper Side (0,1)
            assertEquals("Bad ray6", new Ray(Helper.ZERO, new Vector(0, -2, 10).normalize()),
                    camera.constructRayThroughPixel(3, 3, 1, 0, 10, 6, 6));

            // TC13: 3X3 Center of Left Side (1,0)
            assertEquals("Bad ray7", new Ray(Helper.ZERO, new Vector(-2, 0, 10).normalize()),
                    camera.constructRayThroughPixel(3, 3, 0, 1, 10, 6, 6));

        }

}
