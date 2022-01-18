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
import java.util.List;

public class Triangle extends Geometries{
    private final int NUMBER_OF_POINTS = 3;
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;


    public Triangle(Point3D first, Point3D second, Point3D third){
        super();
        this.p1 = new Point3D(first);
        this.p2 = new Point3D(second);
        this.p3 = new Point3D(third);
    }
    public Triangle(Triangle triangle){
        super(triangle.getEmission(),triangle.getMaterial());
        this.p1 = new Point3D(triangle.getP1());
        this.p2 = new Point3D(triangle.getP2());
        this.p3 = new Point3D(triangle.getP3());
        this.emission = triangle.getEmission();
    }

    public Triangle(Point3D first, Point3D second, Point3D third, Color triangleColor, Material material){
        super(triangleColor,material);
        this.p1 = new Point3D(first);
        this.p2 = new Point3D(second);
        this.p3 = new Point3D(third);
    }

    public void setTriangle(Triangle triangle){
        this.setEmission(triangle.getEmission());
        this.setMaterial(triangle.getMaterial());
        this.setP1(triangle.getP1());
        this.setP2(triangle.getP2());
        this.setP3(triangle.getP3());
    }

    public void setP1(Point3D p) {
        this.p1.setPoint3D(p);
    }

    public void setP2(Point3D p){
        this.p2.setPoint3D(p);
    }

    public void setP3(Point3D p){
        this.p3.setPoint3D(p);
    }

    public Point3D getP1(){
        return this.p1;
    }

    public Point3D getP2(){
        return this.p2;
    }
    public Point3D getP3(){
        return this.p3;
    }

    @Override
    public String toString(){
        return "First Point: "+this.getP1().toString() +
                "Second Point: "+this.getP2().toString() +
                "Third Point: "+this.getP3().toString();
    }
    @Override
    public boolean equals(Object object){
        try{
            Triangle triangleTemp = new Triangle((Triangle) object);
            return (this.getP1().equals(triangleTemp.getP1()) &&
                    this.getP2().equals(triangleTemp.getP2()) &&
                    this.getP3().equals(triangleTemp.getP3())
            );
        }
        catch (ClassCastException classCastException){
            return false;
        }

    }

    private boolean checkPositiveNumbers(Vector rayVector,Vector [] vectors){
        return (
                (rayVector.dotProduct(vectors[0]) >0 &&
                    rayVector.dotProduct(vectors[1]) >0 &&
                    rayVector.dotProduct(vectors[2]) >0)
                        ||
                (rayVector.dotProduct(vectors[0]) <0 &&
                    rayVector.dotProduct(vectors[1]) <0 &&
                    rayVector.dotProduct(vectors[2]) <0)
                );
    }

    @Override
    public List<GeoPoint> findIntersections(Ray cameraRay){
        Vector vector2 = new Vector(this.getP1().subtract(this.getP2()));
        Vector vector3 = new Vector(this.getP1().subtract(this.getP3()));
        Vector normal = vector3.crossProduct(vector2);
        Surface triangleSurface = new Surface(this.getP2(),normal,this.emission);
        List<GeoPoint> geoPoint3DList;
        geoPoint3DList = triangleSurface.findIntersections(cameraRay);
        if (geoPoint3DList != null){
            for (int i =0; i<geoPoint3DList.size();i++){
                geoPoint3DList.get(i).setGeometry(this);
            }
        }
        Vector [] vectors = new Vector[this.NUMBER_OF_POINTS];
        Vector [] normals = new Vector[this.NUMBER_OF_POINTS];
        vectors[0] = this.getP1().subtract(cameraRay.getP00());
        vectors[1] = this.getP2().subtract(cameraRay.getP00());
        vectors[2] = this.getP3().subtract(cameraRay.getP00());
        normals[0] = vectors[0].normalize().crossProduct(vectors[1]).normalize();
        normals[1] = vectors[1].normalize().crossProduct(vectors[2]).normalize();
        normals[2] = vectors[2].normalize().crossProduct(vectors[0]).normalize();
        if (this.checkPositiveNumbers(cameraRay.getDirection(),normals)){
            return geoPoint3DList;
        }
        else {
            return null;
        }
    }
    /*this function is not perfect, but it can give us right information in some cases,
    and it better than nothing.*/
    //check if the inner angles that the point create is bigger from the triangle's angles.

    public boolean isOn(Point3D point){
        if (!
            (this.getP1().equals(point) ||
            this.getP2().equals(point) ||
            this.getP3().equals(point))
        ){
            Vector vector1 = new Vector(this.getP1().subtract(this.getP2()));
            Vector vector2 = new Vector(this.getP1().subtract(this.getP3()));
            Vector vector3 = new Vector(this.getP2().subtract(this.getP3()));
            Vector normal = vector1.crossProduct(vector2);
            Surface triangleSurface = new Surface(this.getP1(),normal);
            double originalAngle1 = vector1.getAngle(vector2);
            double originalAngle2 = vector2.getAngle(vector3);
            double originalAngle3 = vector3.getAngle(vector1);
            Vector vectorP1 = new Vector(point.subtract(this.getP1()));
            Vector vectorP2 = new Vector(point.subtract(this.getP2()));
            Vector vectorP3 = new Vector(point.subtract(this.getP3()));
            return(triangleSurface.checkPointOnSurface(point) &&
                    vectorP2.getAngle(vectorP3)>= originalAngle1 &&
                    vectorP1.getAngle(vectorP2)>= originalAngle2 &&
                    vectorP3.getAngle(vectorP1) >= originalAngle3
            );
        }
        else
            return true;

    }

    @Override
    public Vector getNormal(Point3D point)  {
        /*
        if (this.isOn(point)){
            Vector vector2 = new Vector(this.getP1().subtract(this.getP2()));
            Vector vector3 = new Vector(this.getP1().subtract(this.getP3()));
            Vector normal = vector2.crossProduct(vector3);
            Point3D headPoint = new Point3D(normal.getHeadPoint());
            headPoint.setX(Double.parseDouble(String.format("%.12f",headPoint.getX().getPointValue())));
            headPoint.setY(Double.parseDouble(String.format("%.12f",headPoint.getY().getPointValue())));
            headPoint.setZ(Double.parseDouble(String.format("%.12f",headPoint.getZ().getPointValue())));
            normal.setHeadPoint(headPoint);
            return normal.normalize();
        }
        else{
            return null;
        }

         */
        Vector vector2 = new Vector(this.getP1().subtract(this.getP2()));
        Vector vector3 = new Vector(this.getP1().subtract(this.getP3()));
        Vector normal = vector2.crossProduct(vector3);
        Point3D headPoint = new Point3D(normal.getHeadPoint());
        headPoint.setX(Double.parseDouble(String.format("%.12f",headPoint.getX().getPointValue())));
        headPoint.setY(Double.parseDouble(String.format("%.12f",headPoint.getY().getPointValue())));
        headPoint.setZ(Double.parseDouble(String.format("%.12f",headPoint.getZ().getPointValue())));
        normal.setHeadPoint(headPoint);
        return normal.normalize();
    }
}
