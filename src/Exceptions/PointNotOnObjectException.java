package Exceptions;

import Primitives.Point3D;
import Primitives.Ray;

public class PointNotOnObjectException extends Exception {
    public PointNotOnObjectException(){
        super("Point Not On Object.");
    }

    public PointNotOnObjectException(Ray ray){
        super("Point Not On Object." + ray.toString() + " doesn't Touch The Object");
    }
}
