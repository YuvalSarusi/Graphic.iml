package Exceptions;

import Geometries.*;
import Primitives.Ray;
import Primitives.Vector;

import javax.net.ssl.SSLException;

public class RayDoesntTouchObjectException extends Exception {

    public RayDoesntTouchObjectException(Ray ray, Geometries geometries){
        super("Ray: "+ray.toString()+"Doesn't Touch Object: "+geometries.toString());
    }

    public RayDoesntTouchObjectException(Ray ray, Surface surface, Vector normal){
        super("Ray: "+ray.toString() +" Is Parallel To Surface: "+surface.toString()+" (Vertical To "+normal.toString()+")");
    }

    public RayDoesntTouchObjectException(Ray ray, Surface surface, Geometries geometries){
        super("Ray: "+ray.toString()+"Is Merge With The Surface "+surface.toString()+" Of The Object: "+geometries.toString());
    }

    public RayDoesntTouchObjectException(Ray ray, Surface surface){
        super("Ray: "+ray.toString()+"Is Merge With The Surface "+surface.toString());
    }
}
