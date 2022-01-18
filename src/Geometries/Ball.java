package Geometries;

import Primitives.Material;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
public class Ball extends Geometries {

    private Point3D centerPoint;
    private double radius;

    public Ball(){
        super();
        this.radius = 1;
        this.centerPoint = new Point3D();
    }

    public Ball(Point3D point, double radius){
        super();
        this.radius = radius;
        this.centerPoint = new Point3D(point);
    }

    public Ball(Point3D point, double radius, Color ballColor, Material material){
        super(ballColor,material);
        this.radius = radius;
        this.centerPoint = new Point3D(point);
    }

    public  Ball(Ball copyBall){
        super(copyBall.getEmission(),copyBall.getMaterial());
        this.radius = copyBall.getRadius();
        this.centerPoint = new Point3D(copyBall.getCenterPoint());
    }

    public Point3D getCenterPoint(){
        return this.centerPoint;
    }
    public double getRadius(){
        return this.radius;
    }
    public void setCenterPoint(Point3D point){
        this.centerPoint.setPoint3D(point);
    }
    public void setRadius(double radius){
        this.radius = radius;
    }
    public void setBall(Ball ball){
        this.setCenterPoint(ball.getCenterPoint());
        this.setRadius(ball.getRadius());
    }

    @Override
    public String toString(){
        return "Center Point: "+this.getCenterPoint().toString() +
                "Radius: "+this.getRadius();
    }
    @Override
    public boolean equals(Object object){
        try{
            Ball ball = new Ball((Ball)object);
            return (this.getRadius() == ball.getRadius() &&
                    this.getCenterPoint().equals(ball.getCenterPoint()));
        }
        catch (ClassCastException classCastException){
            return false;
        }

    }

    @Override
    public List<GeoPoint> findIntersections(Ray cameraRay){
        List<GeoPoint> point3DList;
        Vector u = this.getCenterPoint().subtract(cameraRay.getP00());
        double tm = cameraRay.getDirection().dotProduct(u);
        double distance = Math.sqrt(
                                Math.pow(u.length(),2) -
                                Math.pow(tm,2)
                        );
        if(distance > this.getRadius())
            point3DList = null;
        else{
            point3DList = new ArrayList<GeoPoint>();
            double th = Math.sqrt(
                            Math.pow(this.getRadius(),2) -
                            Math.pow(distance,2)
                        );
            double t1 = tm-th;
            if (t1> 0) {
                Point3D point3D = new Point3D(cameraRay.getP00().add(cameraRay.getDirection().scale(t1)));
                point3D.setX(Double.parseDouble(String.format("%.11f",point3D.getX().getPointValue())));
                point3D.setY(Double.parseDouble(String.format("%.11f",point3D.getY().getPointValue())));
                point3D.setZ(Double.parseDouble(String.format("%.11f",point3D.getZ().getPointValue())));
                point3DList.add(new GeoPoint(point3D,this));
            }
            double t2 = tm+th;
            if (t2 > 0){
                Point3D point3D = new Point3D(cameraRay.getP00().add(cameraRay.getDirection().scale(t2)));
                point3D.setX(Double.parseDouble(String.format("%.11f",point3D.getX().getPointValue())));
                point3D.setY(Double.parseDouble(String.format("%.11f",point3D.getY().getPointValue())));
                point3D.setZ(Double.parseDouble(String.format("%.11f",point3D.getZ().getPointValue())));
                point3DList.add(new GeoPoint(point3D,this));
            }
            if (point3DList.isEmpty())
                point3DList = null;
        }
        return point3DList;
    }

    public boolean isOn(Point3D point){
        double dist = this.getCenterPoint().distance(point);
        dist = Double.parseDouble(String.format("%.5f",dist));
        return(
                 dist == this.getRadius()
                );
    }

    @Override
    public Vector getNormal(Point3D point) {
        if (this.isOn(point)){
            Vector normal = point.subtract(this.getCenterPoint()).normalize();
            return normal;
        }
        else{
            return null;
        }
    }
}
