package Primitives;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

import java.awt.*;

public class Point3D {


    private Coordinate x;
    private Coordinate y;
    private Coordinate z;

    public Point3D(Coordinate first, Coordinate second, Coordinate third){
        this.x = new Coordinate(first);
        this.y = new Coordinate(second);
        this.z = new Coordinate(third);
    }

    public Point3D(Coordinate first, Coordinate second, Coordinate third, Color pointColor){
        this.x = new Coordinate(first);
        this.y = new Coordinate(second);
        this.z = new Coordinate(third);
    }

    public Point3D(double first, double second, double third){
        this.x = new Coordinate(first);
        this.y = new Coordinate(second);
        this.z = new Coordinate(third);
    }

    public Point3D(Point3D point3D){
        this.x = new Coordinate(point3D.getX());
        this.y = new Coordinate(point3D.getY());
        this.z = new Coordinate(point3D.getZ());
    }

    public Point3D(){
        this.x = new Coordinate();
        this.y = new Coordinate();
        this.z = new Coordinate();
    }


    public void setX(Coordinate p){
        x.setPointValue(p.getPointValue());
    }
    public void setY(Coordinate p){
        y.setPointValue(p.getPointValue());
    }
    public void setZ(Coordinate p){
        z.setPointValue(p.getPointValue());
    }
    public void setX(double p){
        x.setPointValue(p);
    }
    public void setY(double p){
        y.setPointValue(p);
    }
    public void setZ(double p){
        z.setPointValue(p);
    }

    public Coordinate getX(){
        return this.x;
    }
    public Coordinate getY(){
        return this.y;
    }
    public Coordinate getZ(){
        return this.z;
    }


    public void setPoint3D(Point3D point3D){
        this.setX(point3D.getX());
        this.setY(point3D.getY());
        this.setZ(point3D.getZ());
    }

    @Override
    public boolean equals(Object point){
        try{
            Point3D p = new Point3D((Point3D) point);
            return (this.getX().equals(p.getX()) &&
                    this.getY().equals(p.getY()) &&
                    this.getZ().equals(p.getZ()));
        }
        catch (ClassCastException classCastException){
            return false;
        }
    }

    @Override
    public String toString(){
        String stringValue = "("+
                this.getX().toString()+","+
                this.getY().toString()+","+
                this.getZ().toString()+
                ")";
        return stringValue;
    }

    public Point3D add(Vector v){
        return new Point3D(this.getX().add(v.getHeadPoint().getX()),
                this.getY().add(v.getHeadPoint().getY()),
                this.getZ().add(v.getHeadPoint().getZ()));
    }

    public Vector subtract(Point3D other){
        if (this.equals(other))
            throw new IllegalArgumentException("Can't subtract point from same point");
        return new Vector(
                new Point3D(
                        this.getX().getPointValue() - other.getX().getPointValue(),
                        this.getY().getPointValue() - other.getY().getPointValue(),
                        this.getZ().getPointValue() - other.getZ().getPointValue()
                )
        );
    }

    public double distance(Point3D p){
        return(
                Math.sqrt(
                        Math.pow(this.getX().getPointValue() - p.getX().getPointValue(),2) +
                        Math.pow(this.getY().getPointValue() - p.getY().getPointValue(),2) +
                        Math.pow(this.getZ().getPointValue() - p.getZ().getPointValue(),2)
                )
        );
    }
}
