package Primitives;
import Helper.Helper;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
import Geometries.Geometries;

public class Vector {
    private Point3D headPoint;

    private  boolean checkNotZeroVector(Point3D p){
        return !p.equals(Helper.ZERO);
    }

    public Vector(Point3D headPoint){
        if (this.checkNotZeroVector(headPoint))
            this.headPoint = new Point3D(headPoint);
        else
            throw new IllegalArgumentException("Can't create ZERO vector");
    }

    public Vector(Point3D point1, Point3D point2){
        this.headPoint = new Point3D(point1.subtract(point2).getHeadPoint());
    }

    public Vector (Vector vector){
        if (this.checkNotZeroVector(vector.getHeadPoint()))
            this.headPoint = new Point3D(vector.getHeadPoint());
        else
            throw new IllegalArgumentException("Can't create ZERO vector");
    }

    public Vector(){
        this.headPoint = new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(1));
    }

    public Vector(double p1,double p2, double p3 ){
        if (this.checkNotZeroVector(new Point3D(p1,p2,p3)))
            this.headPoint = new Point3D(p1,p2,p3);
        else
            throw new IllegalArgumentException("Can't create ZERO vector");
    }

    public void setHeadPoint(Point3D point){
        if (this.checkNotZeroVector(point))
            this.headPoint.setPoint3D(point);
        else
            throw new IllegalArgumentException("Can't create ZERO vector");
    }

    public Point3D getHeadPoint(){
        return this.headPoint;
    }

    public void setVector(Vector v){
        setHeadPoint(v.getHeadPoint());
    }

    @Override
    public String toString(){
        String stringValue = "Head Point: "+this.getHeadPoint().toString();
        return stringValue;
    }

    @Override
    public boolean equals(Object vector){
        try{
            Vector v = new Vector((Vector)vector);
            return this.getHeadPoint().equals(v.getHeadPoint());
        }
        catch (ClassCastException classCastException){
            return false;
        }

    }

    public double length(){
        return Math.sqrt(
                Math.pow(this.getHeadPoint().getX().getPointValue(),2)+
                Math.pow(this.getHeadPoint().getY().getPointValue(),2)+
                Math.pow(this.getHeadPoint().getZ().getPointValue(),2)
        );
    }

    public Vector scale(double scalar){
        if (scalar == 0){
            throw new IllegalArgumentException("Can't create vector 0");
        }
        return new Vector(
                (this.getHeadPoint().getX().getPointValue() * scalar),
                (this.getHeadPoint().getY().getPointValue() * scalar),
                (this.getHeadPoint().getZ().getPointValue() * scalar)
        );
    }

    public Vector normalize() {
        return scale(Double.parseDouble(String.format("%.12f",(1/this.length()))));
    }

    public Vector add(Vector other){
        if (this.equals(other.scale(-1))) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        else {
            return new Vector(
                    new Point3D(
                            this.getHeadPoint().getX().getPointValue()+other.getHeadPoint().getX().getPointValue(),
                            this.getHeadPoint().getY().getPointValue()+other.getHeadPoint().getY().getPointValue(),
                            this.getHeadPoint().getZ().getPointValue()+other.getHeadPoint().getZ().getPointValue()
                    )
            );
        }
    }

    public Vector subtract(Vector other){
        if (this.equals(other)) {
            throw new IllegalArgumentException("Vector can't be (0,0,0)");
        }
        else{
            return new Vector(
                    new Point3D(
                            this.getHeadPoint().getX().getPointValue()-other.getHeadPoint().getX().getPointValue(),
                            this.getHeadPoint().getY().getPointValue()-other.getHeadPoint().getY().getPointValue(),
                            this.getHeadPoint().getZ().getPointValue()-other.getHeadPoint().getZ().getPointValue()
                    )
            );
        }
    }

    //return a vector that is vertical to both of the vectors
    public Vector crossProduct(Vector other){
        if (this.normalize().equals(other.normalize()) || this.normalize().equals(other.normalize().scale(-1)))
            throw new IllegalArgumentException("Can't find vertical vector for 2 same normalizes vectors");
        return new Vector(
          new Point3D(
                  this.getHeadPoint().getY().getPointValue() * other.getHeadPoint().getZ().getPointValue() -
                          this.getHeadPoint().getZ().getPointValue() * other.getHeadPoint().getY().getPointValue(),
                  this.getHeadPoint().getZ().getPointValue() * other.getHeadPoint().getX().getPointValue() -
                          this.getHeadPoint().getX().getPointValue() * other.getHeadPoint().getZ().getPointValue(),
                  this.getHeadPoint().getX().getPointValue() * other.getHeadPoint().getY().getPointValue() -
                          this.getHeadPoint().getY().getPointValue() * other.getHeadPoint().getX().getPointValue()
          )
        );
        /*
        Vector v(x1,y1,z1)
        Vector u(x2, y2, z2)
        new Vector (
            y1*z2 - z1*y2
            z1*x2 - x1*z2
            x1*y2 - x2*y1
        )
        */
    }
    //return a number that is the dot product (a number that is equals to: v1.length * v2.length * cos(angle))
    public double dotProduct(Vector vector){
        return (
                this.getHeadPoint().getX().getPointValue() * vector.getHeadPoint().getX().getPointValue() +
                this.getHeadPoint().getY().getPointValue() * vector.getHeadPoint().getY().getPointValue() +
                this.getHeadPoint().getZ().getPointValue() * vector.getHeadPoint().getZ().getPointValue()
                );
    }

    public double getAngle(Vector vector){
        return Math.acos(
                this.dotProduct(vector)/
                        (this.length()*vector.length())
        );
    }


}