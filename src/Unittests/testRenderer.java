package Unittests;

import Elements.Camera;
import Geometries.*;
import Helper.Helper;
import Primitives.*;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;
import org.junit.Test;

import java.awt.*;

public class testRenderer {

/*
    @Test
    public void basicRenderSomeColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(100);
        scene.setBackgroundColor(new Color(255, 150, 180));
        scene.addGeometry(new Ball(new Point3D(0, 0, 100), 50,Color.RED));
        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100),
                new Point3D(100, 100, 100),Color.BLACK));
        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100),
                new Point3D(100, -100, 100),Color.BLUE));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100),
                new Point3D(-100, 100, 100),Color.GREEN));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100),
                new Point3D(-100, -100, 100),Color.CYAN));
        ImageWriter imageWriter = new ImageWriter("someColorTest", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }

    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(100);
        scene.setBackgroundColor(new Color(255, 150, 180));
        scene.addGeometry(new Ball(new Point3D(0, 0, 100), 50,Color.CYAN));
        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100),
                new Point3D(100, 100, 100),Color.CYAN));
        scene.addGeometry(new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100),
                new Point3D(100, -100, 100),Color.CYAN));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100),
                new Point3D(-100, 100, 100),Color.CYAN));
        scene.addGeometry(new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100),
                new Point3D(-100, -100, 100),Color.CYAN));
        ImageWriter imageWriter = new ImageWriter("twoColorTest", 500, 500, 500, 500);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }



    @Test
    public void createHouseTest() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(200);
        scene.setBackgroundColor(new Color(33, 93, 224));
        scene.addGeometry(new Triangle(new Point3D(0, -100, 200), new Point3D(-200, 100, 200),
                new Point3D(200, 100, 200),new Color(103, 25, 25)));
        scene.addGeometry(new Triangle(new Point3D(200, 100, 200), new Point3D(-200, 100, 200),
                new Point3D(-200, 800, 200),new Color(255,255,255)));
        scene.addGeometry(new Triangle(new Point3D(200, 800, 200), new Point3D(200, 100, 200),
                new Point3D(-200, 800, 200),new Color(255,255,255)));
        ImageWriter imageWriter = new ImageWriter("houseTest2", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }

    @Test
    public void createHouseTest2() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(200);
        scene.setBackgroundColor(new Color(255, 150, 180));
        scene.addGeometry(new Triangle(new Point3D(0, -150, 200), new Point3D(-400, 90, 200),
                new Point3D(400, 90, 200)));
        scene.addGeometry(new Triangle(new Point3D(200, 100, 200), new Point3D(-200, 100, 200),
                new Point3D(-200, 600, 200)));
        scene.addGeometry(new Triangle(new Point3D(200, 600, 200), new Point3D(200, 100, 200),
                new Point3D(-200, 600, 200)));
        ImageWriter imageWriter = new ImageWriter("house2Test", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }

    @Test
    public void createHouseTest3() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(2000);
        scene.setBackgroundColor(new Color(0, 169, 236));
        scene.addGeometry(new Ball(new Point3D(-400,-400,2000),80,new Color(255, 255, 0)));
        scene.addGeometry(new Triangle(new Point3D(0, -150, 2000), new Point3D(-400, 100, 2000),
                new Point3D(400, 100, 2000),new Color(136, 20, 20)));
        scene.addGeometry(new Triangle(new Point3D(200, 100, 2000), new Point3D(-200, 100, 2000),
                new Point3D(-200, 600, 2000),new Color(255,255,255) ));
        scene.addGeometry(new Triangle(new Point3D(200, 600, 2000), new Point3D(200, 100, 2000),
                new Point3D(-200, 600, 2000), new Color(255,255,255)));
        ImageWriter imageWriter = new ImageWriter("Color House Test", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }

    @Test
    public void createBallTest() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(200);
        scene.setBackgroundColor(new Color(255, 150, 180));
        scene.addGeometry(new Ball(new Point3D(0, 0, 200), 100));
        ImageWriter imageWriter = new ImageWriter("ballTest", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }

    @Test
    public void createHouseTest4() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Geometries.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(2000);
        scene.setBackgroundColor(new Color(0, 169, 236));
        scene.addGeometry(new Ball(new Point3D(-400,-400,2000),80,new Color(255, 255, 0)));
        scene.addGeometry(new Triangle(new Point3D(0, -150, 2000), new Point3D(-400, 100, 2000),
                new Point3D(400, 100, 2000),new Color(255, 1, 1)));
        scene.addGeometry(new Triangle(new Point3D(200, 100, 2000), new Point3D(-200, 100, 2000),
                new Point3D(-200, 600, 2000),new Color(255,255,255) ));
        scene.addGeometry(new Triangle(new Point3D(200, 600, 2000), new Point3D(200, 100, 2000),
                new Point3D(-200, 600, 2000), new Color(255,255,255)));
        scene.addGeometry(new Triangle(new Point3D(-100,300,1990),new Point3D(100,300,1990),
                new Point3D(-100,600,1990), new Color(133, 41, 41)));
        scene.addGeometry(new Triangle(new Point3D(-100,600,1990),new Point3D(100,300,1990),
                new Point3D(100,600,1990), new Color(133, 41, 41)));
        scene.addGeometry(new Ball(new Point3D(-75,450,1980),8,new Color(0, 0, 0)));
        scene.addGeometry(new Surface(new Point3D(0,600,2000),new Vector(0,1,0.00000001),new Color(0,160,0)));
        ImageWriter imageWriter = new ImageWriter("Color House Test2", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }
 */


    @Test
    public void createPizzaTest() {
        Scene scene = new Scene("Test scene");
        scene.setSceneCamera(new Camera(Helper.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setScreenDistance(2000);
        scene.setBackgroundColor(new Color(0, 169, 236));
        scene.addGeometry(new Triangle(new Point3D(-400, -300, 2000), new Point3D(400, -300, 2000),
                new Point3D(0, 400, 2000),new Color(246, 200, 29),new Material(0.1,0.1,1)));
        scene.addGeometry(new Triangle(new Point3D(-420, -335, 2001), new Point3D(420, -335, 2001),
                new Point3D(0, 400, 2001),new Color(255, 104, 0, 209),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(-270,-200,1990),40,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(-100,0,1990),40,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(0,110,1990),35,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(75,-150,1990),48,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(-20,300,1990),20,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        scene.addGeometry(new Ball(new Point3D(250,-200,1990),35,new Color(204, 6, 6),new Material(0.1,0.1,1)));
        ImageWriter imageWriter = new ImageWriter("Pizza", 1600, 1600, 800, 800);
        Renderer render = new Renderer(scene,imageWriter);
        render.renderImage();
    }


}
