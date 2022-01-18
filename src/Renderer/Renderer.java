package Renderer;

import Elements.Light;
import Geometries.*;
import Helper.Helper;
import Primitives.*;
import Scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Scene scene;
    private ImageWriter imageWriter;
    private static final int RECURSIVE_LEVEL = 3;

    public Renderer(Scene scene, ImageWriter imageWriter) {
        this.scene = new Scene(scene);
        this.imageWriter = imageWriter;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }

    public void setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
    }

    public void renderImage() {
        for (int i =0; i<this.imageWriter.getNy(); i++){
            for (int j = 0; j<this.imageWriter.getNx(); j++){
                Ray ray = this.scene
                        .getSceneCamera()
                        .constructRayThroughPixel(
                                this.imageWriter.getNx(),
                                this.imageWriter.getNy(),
                                j,i,
                                this.scene.getScreenDistance(),
                                this.imageWriter.getHeight(),
                                this.imageWriter.getWidth()
                        );
                List<GeoPoint> intersectionPoints = this.getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty()){
                    this.imageWriter.writePixel(j,i,this.scene.getBackgroundColor());
                }
                else{
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints, scene.getSceneCamera().getP00());
                    imageWriter.writePixel(j,i,calcColor(closestPoint, ray));
                }
            }
        }
        imageWriter.writeToImage();
    }

    private List<GeoPoint> getSceneRayIntersections(Ray ray) {
        List<GeoPoint> intersectionPoints = new ArrayList<GeoPoint>();
        for (Geometries geometry : scene.getGeometriesList()) {
            List<GeoPoint> geometryIntersectionPoints = geometry.findIntersections(ray);
            if (geometryIntersectionPoints != null){
                for (GeoPoint geoPoint : geometryIntersectionPoints) {
                    intersectionPoints.add(geoPoint);
                }
            }
        }
        return intersectionPoints;
    }

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints, Point3D point3D){
        double distance = Double.MAX_VALUE;
        GeoPoint minDistancePoint = null;
        for (GeoPoint geoPoint : intersectionPoints){
            if (point3D.distance(geoPoint.getPoint3D()) < distance){
                minDistancePoint = new GeoPoint(geoPoint);
                distance = point3D.distance(geoPoint.getPoint3D());
            }
        }
        return minDistancePoint;
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray){
        Point3D point3D = geoPoint.getPoint3D();
        Geometries geometries = geoPoint.getGeometry();
        return calcColor(geometries, point3D, ray, RECURSIVE_LEVEL);
    }

    private Color calcColor(Geometries geometries, Point3D point3D, Ray ray , int level){
        Color returnedColor = new Color(0,0,0);
        if (level != 0){
            Color emissionLight = geometries.getEmission(); //gp.getGeo.getEm
            Color ambientLight = this.scene.getAmbientLight().getIntensity(point3D);
            Color diffuseLight = new Color(0,0,0);
            Color specularLight = new Color(0,0,0);
            Color reflectedLight = new Color(0,0,0);
            for (Light light : scene.getSceneLights()){
                if(
                        !shaded(
                                light,
                                light.getL(point3D),
                                point3D,
                                geometries.getNormal(
                                        point3D
                                )
                        )
                ){
                    diffuseLight = Helper.calcAddingColors(
                            diffuseLight,
                            calcDiffuseColor(
                                    geometries.getMaterial().getKd(),
                                    new Vector(light.getL(point3D)),
                                    new Vector(geometries.getNormal(point3D)),
                                    light.getIntensity(point3D)
                            ));
                    specularLight = Helper.calcAddingColors(
                            specularLight,
                            calcSpecularColor(
                                    geometries.getMaterial().getKs(),
                                    new Vector(point3D.subtract(this.scene.getSceneCamera().getP00()).normalize()),
                                    new Vector(geometries.getNormal(point3D).normalize()),
                                    new Vector(light.getL(point3D).normalize()),
                                    geometries.getMaterial().getNShininess(),
                                    light.getIntensity(point3D)
                            ));
                    reflectedLight = Helper.multiplyScalarColor(
                            calcReflect(ray,point3D,geometries.getNormal(point3D),level),
                            geometries.getMaterial().getKr()
                    );
                }
            }
            returnedColor =  Helper.calcAddingColors(
                    ambientLight, emissionLight, diffuseLight, specularLight, reflectedLight
            );
        }
        return returnedColor;

    }

    private Color calcDiffuseColor(double kd,Vector lightVector, Vector normal, Color lightIntensity ){
        return Helper.multiplyScalarColor(
                lightIntensity,
                kd*lightVector.normalize().dotProduct(
                        normal.normalize()
                )
        );
    }

    private Color calcSpecularColor(double ks, Vector gpToCamera, Vector normal,Vector lightVector, int shininess, Color lightIntensity ){
        Vector vectorR = new Vector(
                lightVector.subtract(
                        normal.scale(
                            2 * lightVector.dotProduct(
                                    normal
                            )
                        )
                ).normalize()
        );
        double scalar = Math.pow(gpToCamera.dotProduct(vectorR),shininess);
        return Helper.multiplyScalarColor(
                lightIntensity,ks*scalar
        );
    }

    private boolean shaded(Light light,Vector l, Point3D point3D, Vector n){
        boolean isNotShaded = true;
        Vector epsVector = n.scale(Helper.ESP);
        Point3D newPoint = point3D.add(epsVector);
        Ray shadowRay = new Ray(newPoint, l.scale(-1));
        List<GeoPoint> intersectionPoint = getSceneRayIntersections(shadowRay);
        if (intersectionPoint.isEmpty())
            isNotShaded = false;
        return isNotShaded;
    }

    private Color calcReflect(Ray inRay, Point3D point3D, Vector n, int level){
        Vector v = inRay.getDirection();
        Vector r = new Vector(v.subtract(n.scale(-2*v.dotProduct(n))));
        Ray reflected = new Ray(point3D, r);
        List<GeoPoint> reflectIntersectionPoints = getSceneRayIntersections(reflected);
        Color returnColor = new Color(0,0,0);
        if (!reflectIntersectionPoints.isEmpty()){
            GeoPoint closestPoint = getClosestPoint(reflectIntersectionPoints, point3D);
            returnColor = calcColor(
                    closestPoint.getGeometry(),
                    closestPoint.getPoint3D(),
                    reflected,
                    level-1
                    );
        }
        return returnColor;
    }
}
