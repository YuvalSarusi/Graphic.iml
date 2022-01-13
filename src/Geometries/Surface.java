package Geometries;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Surface extends Geometries {

    private Point3D centerPoint;
    private Vector verticalVector;

    public Surface(){
        super();
        this.centerPoint = new Point3D();
        this.verticalVector = new Vector();
    }

    public Surface(Point3D p1, Point3D p2, Point3D p3){
        super();
        this.centerPoint = new Point3D(p1);
        Vector v1 = new Vector(p1.subtract(p2));
        Vector v2 = new Vector(p1.subtract(p3));
        this.verticalVector = new Vector(v1.crossProduct(v2));
    }

    public Surface(Point3D p1, Point3D p2, Point3D p3, Color color){
        super(color);
        this.centerPoint = new Point3D(p1);
        Vector v1 = new Vector(p1.subtract(p2));
        Vector v2 = new Vector(p1.subtract(p3));
        this.verticalVector = new Vector(v1.crossProduct(v2));
    }

    public Surface(Point3D p1, Point3D p2, Point3D p3, Color color, Material material){
        super(color,material);
        this.centerPoint = new Point3D(p1);
        Vector v1 = new Vector(p1.subtract(p2));
        Vector v2 = new Vector(p1.subtract(p3));
        this.verticalVector = new Vector(v1.crossProduct(v2));
    }



    public Surface(Point3D point,Vector vector, Color color){
        super(color);
        this.centerPoint = new Point3D(point);
        this.verticalVector = new Vector(vector);
        this.emission = color;
        this.material = null;
    }

    public Surface(Point3D point, Vector vector, Color color, Material material){
        super(color,material);
        this.centerPoint = new Point3D(point);
        this.verticalVector = new Vector(vector);
    }

    public Surface(Point3D point,Vector vector){
        super();
        this.centerPoint = new Point3D(point);
        this.verticalVector = new Vector(vector);
    }

    public Surface(Surface surface){
        super(surface.getEmission(),surface.getMaterial());
        this.centerPoint = new Point3D(surface.getCenterPoint());
        this.verticalVector = new Vector(surface.getVerticalVector());
    }

    public Point3D getCenterPoint(){
        return this.centerPoint;
    }
    public Vector getVerticalVector(){
        return this.verticalVector;
    }

    public void setCenterPoint(Point3D point){
        this.centerPoint.setPoint3D(point);
    }

    public void setVerticalVector(Vector vector){
        this.verticalVector.setVector(vector);
    }

    public void setSurface(Point3D point3D, Vector vector){
        this.setCenterPoint(point3D);
        this.setVerticalVector(vector);
    }

    @Override
    public String toString(){
        return "Center Point: "+this.getCenterPoint().toString()+
                "Vertical Vector: "+this.getVerticalVector().toString();
    }

    @Override
    public boolean equals(Object object){
        try{
            Surface surface = new Surface((Surface) object);
            return this.getVerticalVector().equals(surface.getVerticalVector()) &&
                    this.getCenterPoint().equals(surface.getCenterPoint());
        }
        catch (ClassCastException classCastException){
            return false;
        }
    }

    @Override
    public List<GeoPoint> findIntersections(Ray cameraRay)  {
        Vector vector = this.getCenterPoint().subtract(cameraRay.getP00());
        double t = this.getVerticalVector().dotProduct(vector)/
                this.getVerticalVector().dotProduct(cameraRay.getDirection());
        Point3D returnPoint;
        //check if the ray is parallel ot merge with the surface
        if(cameraRay.getDirection().dotProduct(this.getVerticalVector()) ==0){
            return null;
        }
        if (t > 0){
            returnPoint = cameraRay.getP00().add(cameraRay.getDirection().scale(t));
            returnPoint.setX(Double.parseDouble(String.format("%.12f",returnPoint.getX().getPointValue())));
            returnPoint.setY(Double.parseDouble(String.format("%.12f",returnPoint.getY().getPointValue())));
            returnPoint.setZ(Double.parseDouble(String.format("%.12f",returnPoint.getZ().getPointValue())));
            List<GeoPoint> returnList = new ArrayList<GeoPoint>();
            returnList.add(new GeoPoint(returnPoint,this));
            return returnList;
        }
        else
            return null;
    }

    public boolean checkPointOnSurface(Point3D point){
        if (!this.getCenterPoint().equals(point)){
            Vector vector = this.getCenterPoint().subtract(point);
            double dotProduct = this.getVerticalVector().dotProduct(vector);
            dotProduct = Double.parseDouble(String.format("%.5f",dotProduct));
            return( dotProduct == 0);
        }
         else{
            return true;
        }
    }

    @Override
    public Vector getNormal(Point3D point) {
        if (this.checkPointOnSurface(point)){
            return this.getVerticalVector().normalize();
        }
        else{
            return null;
        }
    }
}
