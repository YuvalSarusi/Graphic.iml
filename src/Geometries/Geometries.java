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

public abstract class Geometries {

    protected Color emission;
    protected Material material;

    public Geometries(Color emission, Material material) {
        this.emission = emission;
        this.material = material;
    }

    public Geometries(Color color) {
        this.emission = color;
        this.material = null;
    }

    public Geometries() {
        this.emission =new Color(255,255,255);
        this.material = null;
    }


    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material.setKd(material.getKd());
        this.material.setKs(material.getKs());
        this.material.setNShininess(material.getNShininess());
    }



    public Color getEmission(){
        return emission;
    }

    public void setEmission(Color color){
        this.emission = color;
    }

    public abstract List<GeoPoint> findIntersections(Ray cameraRay) ;
    public abstract Vector getNormal(Point3D point) ;


}
