package Geometries;

import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Cylinder extends Geometries{

    private double radius;
    private Ray axis;
    private double height;

    public Cylinder(Color color, Material material, double radius, Ray axis, double height) {
        super(color,material);
        this.radius = radius;
        this.axis = new Ray(axis);
        this.height = height;
    }

    public Cylinder(Cylinder cylinder) {
        super(cylinder.getEmission(),cylinder.getMaterial());
        this.radius = cylinder.getRadius();
        this.axis = new Ray(cylinder.getAxis());
        this.height = cylinder.getHeight();
    }

    public Cylinder() {
        super();
        this.radius = 1;
        this.axis = new Ray(new Point3D(0,0,0), new Vector(0,0,1));
        this.height = 1;
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Ray getAxis() {
        return this.axis;
    }

    public void setAxis(Ray axis) {
        this.axis = axis;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Radius: " + this.getRadius() +
                ", Axis: " + this.getAxis() +
                ", Height: " + this.getHeight();
    }

    @Override
    public boolean equals(Object object) {
        try{
            Cylinder cylinder = new Cylinder((Cylinder) object);
            return(
                    this.getRadius() == cylinder.getRadius() &&
                    this.getAxis().equals(cylinder.getAxis()) &&
                    this.getHeight() == cylinder.getHeight()
                    );
        }catch (ClassCastException e){
            return false;
        }
    }

    @Override
    public List<GeoPoint> findIntersections(Ray cameraRay) {
        return null;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
