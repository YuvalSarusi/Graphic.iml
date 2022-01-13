package Geometries;

import Primitives.Point3D;

public class GeoPoint {

    private Point3D point3D;
    private Geometries geometry;

    public GeoPoint(Point3D point3D, Geometries geometries) {
        this.point3D = new Point3D(point3D);
        this.geometry = geometries;
    }

    public GeoPoint(GeoPoint geoPoint) {
        this.point3D = new Point3D(geoPoint.getPoint3D());
        this.geometry = geoPoint.getGeometry();
    }

    public Point3D getPoint3D() {
        return point3D;
    }

    public void setPoint3D(Point3D point3D) {
        this.point3D = point3D;
    }

    public Geometries getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometries geometry) {
        this.geometry = geometry;
    }
}
