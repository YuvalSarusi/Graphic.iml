package Unittests;

import Elements.*;
import Geometries.*;
import Primitives.*;
import Renderer.*;
import Scene.Scene;
import org.junit.Test;

import java.awt.*;

public class testLight {

    @Test
    public void emmissionTest(){

        Scene scene = new Scene("emmissionTest");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(100);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometry(new Ball( new Point3D(0.0, 0.0, 149),50, new Color(255,0,0),new Material(0.1,0.1,1)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149),
                new Color(0,255,0),
                new Material(0.1,0.1,1)
                );

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149),
                new Color(0,0,255),
                new Material(0.1,0.1,1)
        );

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149),
                new Color(255,255,0),
                new Material(0.1,0.1,1)
        );

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149),
                new Color(255,0,255),
                new Material(0.1,0.1,1)
        );

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Emmition test", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
    }







    @Test
    public void spotLightTest(){

        Scene scene = new Scene("spotLightTest");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));


        Ball sphere = new Ball(new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100),new Material(0.1,0.1,1));
        Material m=new Material(1,1,20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, 100),
                0.000005, 0, 0.00001,new Vector(2, 2, -3) ));

        ImageWriter imageWriter = new ImageWriter("Spot test", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }




    @Test
    public void pointLightTest(){

        Scene scene = new Scene("pointLightTest");
        scene.setBackgroundColor(new Color(255, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Ball sphere = new Ball ( new Point3D(0.0, 0.0, 1000),600,new Color(0, 0, 100),new Material(0.1,0.1,1));
        Material m=new Material(1,1,20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);
        scene.addLight(new PointLight(new Color(255,255,0), new Point3D(-200, 200, 600),
                0, 0.00001, 0.000005));
        ImageWriter imageWriter = new ImageWriter("Point test", 1600, 1600, 1600, 1600);
        Renderer render = new Renderer(scene, imageWriter);
        render.renderImage();
    }





    @Test
    public void pointLightTest2(){

        Scene scene = new Scene("pointLightTest2");
        scene.setBackgroundColor(new Color(0, 255, 255));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Material m1=new Material(1,1,1);
        Material m2=new Material(1,1,20);

        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100),new Material(0.1,0.1,1));
        sphere.setMaterial(m2);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, 2000),
                new Point3D( -3500, -3500, 1000),
                new Point3D(  3500, -3500, 2000),
                new Color(0,0,0),
                new Material(0.1,0.1,1)
        );

        triangle1.setMaterial(m1);
        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, 2000),
                new Point3D( -3500,  3500, 1000),
                new Point3D( -3500, -3500, 1000),
                new Color(0,0,0),
                new Material(0.1,0.1,1)
        );

        triangle2.setMaterial(m1);

        scene.addGeometry(sphere);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(200, -200, 100),  0, 0.000001, 0.0000005));


        ImageWriter imageWriter = new ImageWriter("Point test 2", 1600, 1600, 1600, 1600);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }




    @Test
    public void spotLightTest3(){

        Scene scene = new Scene("spotLightTest3");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Material m=new Material(1,1,1);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, 2000),
                new Point3D( -3500, -3500, 1000),
                new Point3D(  3500, -3500, 2000),
                new Color(0,0,0),
                new Material(0.1,0.1,1)
        );

        triangle1.setMaterial(m);
        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, 2000),
                new Point3D( -3500,  3500, 1000),
                new Point3D( -3500, -3500, 1000),
                new Color(0,0,0),
                new Material(0.1,0.1,1)
        );

        triangle2.setMaterial(m);

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, -200, -100),0.0000005 , 0, 0.000001, new Vector(-2, -2, -3)));
        ImageWriter imageWriter = new ImageWriter("Spot test 3", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();
    }



    @Test
    public void spotLightTest2(){

        Scene scene = new Scene("spotLightTest2");
        scene.setBackgroundColor(new Color(0, 0, 0));
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));


        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100), new Material(0.1,0.1,1));
        Material m=new Material(1,1,20);
        sphere.setMaterial(m);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, 225, 260),
                new Point3D(-225, 125, 260),
                new Point3D(-225, 225, 270),
                new Color (0, 0, 100),
                new Material(0.1,0.1,1));

        Material m1=new Material(1,1,4);
        triangle.setMaterial(m1);
        scene.addGeometry(triangle);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, 150),
                0.000005, 0.1, 0.00001,new Vector(2, 2, 3) ));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }
}
