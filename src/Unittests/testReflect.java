package Unittests;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Ball;
import Geometries.Surface;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;
import org.junit.Test;

import java.awt.*;

public class testReflect {

    @Test
    public void testReflect1(){
        Scene scene = new Scene("twoTriangles");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(100);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Triangle triangle1 = new Triangle(new Point3D(-300, 0, 300),
                new Point3D(300, 0, 300),
                new Point3D(0, -600, 300),
                new Color (0, 0, 100),
                new Material(1,1,1));

        Triangle triangle2 = new Triangle(new Point3D(-100, 100, 150),
                new Point3D(100, 100, 150),
                new Point3D(0, -100, 150),
                new Color (0, 100, 0),
                new Material(1,1,20));

        Material m1=new Material(1,1,20,1);
        Material m2=new Material(1,1,20,0);
        triangle1.setMaterial(m1);
        triangle2.setMaterial(m2);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(300, 200, 0),0.000005 , 0, 0.0000001));

        ImageWriter imageWriter = new ImageWriter("two Triangles reflect 1", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }


    @Test
    public void mirrorBall(){
        Scene scene = new Scene("twoTriangles");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(100);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Ball ball = new Ball(new Point3D(0,0,200),100,new Color(0,255,0),new Material(1,1,20,1));

        scene.addGeometry(ball);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(0, 200, 0),0.000005 , 0, 0.0000001));

        ImageWriter imageWriter = new ImageWriter("reflect ball", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
    }


    @Test
    public void surfaceTriangle(){
        Scene scene = new Scene("surfaceTriangle");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(100);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Surface surface = new Surface(new Point3D(0,0,400),new Vector(0,0,1));
        surface.setMaterial(new Material(1,1,20,1));

        Triangle triangle = new Triangle(
                new Point3D(-230,30,200),
                new Point3D(-170,30,200),
                new Point3D(-200,-30,200)
        );
        triangle.setMaterial(new Material(1,1,20,0));

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 0, 200),0.000005 , 0, 0.0000001,new Vector(-100,0,200)));
        scene.addGeometry(triangle);
        scene.addGeometry(surface);
        ImageWriter imageWriter = new ImageWriter("reflect mirror", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
    }
}
