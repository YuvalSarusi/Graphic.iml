package Primitives;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
import Geometries.Geometries;

public class Ray {
    private Point3D p00;
    private Vector direction;

    public Ray(Point3D p00, Vector direction){
        this.p00 = new Point3D(p00);
        this.direction = new Vector(direction.normalize());
    }

    public Ray(Ray ray){
        this.p00 = new Point3D(ray.getP00());
        this.direction = new Vector(ray.getDirection().normalize());
    }
    public Ray(){
        this.p00 = new Point3D();
        this.direction = new Vector();
    }

    public Point3D getP00(){
        return this.p00;
    }

    public Vector getDirection(){
        return this.direction;
    }

    public void setP00(Point3D p00){
        this.p00 = p00;
    }
    public void setDirection(Vector direction){
        this.direction = direction;
    }

    public void setRay(Ray ray){
        this.setP00(ray.getP00());
        this.setDirection(ray.getDirection());
    }

    @Override
    public boolean equals(Object object){
        try{
            Ray ray = new Ray((Ray)object);
            return this.getP00().equals(ray.getP00()) &&
                   this.getDirection().equals(ray.getDirection());
        }
        catch (ClassCastException classCastException){
            return false;
        }
    }

    @Override
    public String toString(){
        String stringValue = "P00: "+this.getP00().toString()+", Vector: "+
                this.getDirection().toString();
        return stringValue;
    }

}
