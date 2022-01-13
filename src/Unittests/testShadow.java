package Unittests;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.PointLight;
import Elements.SpotLight;
import Geometries.Ball;
import Geometries.Triangle;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;
import org.junit.Test;

import java.awt.*;

public class testShadow {

    @Test
    public void twoTriangles(){

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

        Material m1=new Material(1,1,20);
        triangle1.setMaterial(m1);
        triangle2.setMaterial(m1);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(0, 200, 0),0.000005 , 0, 0.0000001, new Vector(0,-100,150)));

        ImageWriter imageWriter = new ImageWriter("twoTriangles", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }
    @Test
    public void spotLightTest2(){

        Scene scene = new Scene("spotLightTest2");
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100), new Material(1,1,1));
        Material m1=new Material(1,1,20);
        sphere.setMaterial(m1);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, 225, 260),
                new Point3D(-225, 125, 260),
                new Point3D(-225, 225, 270),
                new Color (0, 0, 100),
                new Material(1,1,1));

        Material m2=new Material(1,1,4);
        triangle.setMaterial(m2);
        scene.addGeometry(triangle);

//			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, -150), new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, 200, -100),  0, 0.000001, 0.0000005));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }


    @Test
    public void threeTriangles(){

        Scene scene = new Scene("threeTriangles");
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

        Triangle triangle3 = new Triangle(new Point3D(-0, 0, 50),
                new Point3D(-200, 100, 50),
                new Point3D(-100, -100, 20),
                new Color (199, 5, 186),
                new Material(1,1,20));

        Material m1=new Material(1,1,20);
        triangle1.setMaterial(m1);
        triangle2.setMaterial(m1);
        triangle3.setMaterial(m1);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);

        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(0, 200, 0),0.000005 , 0, 0.0000001, new Vector(0,-100,150)));
        scene.addLight(new SpotLight(new Color(4, 53, 248), new Point3D(-100, 400, 0),0.000005 , 0, 0.0000001, new Vector(200,-100,150)));

        ImageWriter imageWriter = new ImageWriter("threeTriangles", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }

    @Test
    public void spotLightTest2Balls(){

        Scene scene = new Scene("spotLightTest2Balls");
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100), new Material(1,1,1));
        Material m1=new Material(1,1,20);
        sphere.setMaterial(m1);
        scene.addGeometry(sphere);

        Ball ball = new Ball(new Point3D(-175,175,260),60,new Color(250,160,70),new Material(1,1,20));


        Material m2=new Material(1,1,4);
        ball.setMaterial(m2);
        scene.addGeometry(ball);

//			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, -150), new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, 200, -100),  0, 0.000001, 0.0000005));

        ImageWriter imageWriter = new ImageWriter("Spot test 2 Balls", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }



    @Test
    public void spotLightTest2BallsTriangle(){

        Scene scene = new Scene("spotLightTest2BallsTriangle");
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1000),500,new Color(0, 0, 100), new Material(1,1,1));
        Material m1=new Material(1,1,20);
        sphere.setMaterial(m1);
        scene.addGeometry(sphere);

        Ball ball = new Ball(new Point3D(-175,175,260),60,new Color(250,160,70),new Material(1,1,20));

        Triangle triangle = new Triangle(new Point3D(125, 225, 260),
                new Point3D(225, 125, 260),
                new Point3D(225, 225, 270),
                new Color (255, 128, 235),
                new Material(1,1,1));

        Material m2=new Material(1,1,4);
        ball.setMaterial(m2);
        triangle.setMaterial(m2);
        scene.addGeometry(triangle);
        scene.addGeometry(ball);

//			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, -150), new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));
        scene.addLight(new PointLight(new Color(255, 100, 100), new Point3D(-200, 200, -100),  0, 0.000001, 0.0000005));
        scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100),  0, 0.000001, 0.0000005, new Vector(0,0,100)));

        ImageWriter imageWriter = new ImageWriter("Spot test 2 Balls Triangle", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }


    @Test
    public void smile(){

        Scene scene = new Scene("smile");
        scene.setSceneCamera(new Camera( new Point3D(0, 0, 0),new Vector(0.0, 0.0, 1.0),new Vector(0,-1, 0.0)));
        scene.setScreenDistance(200);
        scene.setAmbientLight(new AmbientLight(new Color(252, 248, 248), 0.1));

        Ball sphere = new Ball( new Point3D(0.0, 0.0, 1500),500,new Color(248, 14, 49), new Material(1,1,1));
        Material m1=new Material(1,1,20);
        sphere.setMaterial(m1);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(325, 200, 800),
                new Point3D(-325, 200, 800),
                new Point3D(0, 400, 800),
                new Color (255, 128, 235),
                new Material(1,1,1));

        Triangle triangle2 = new Triangle(new Point3D(-200, 150, 800),
                new Point3D(-100, 150, 800),
                new Point3D(-150, 250, 800),
                new Color (255, 128, 235),
                new Material(1,1,1));
        Triangle triangle3 = new Triangle(new Point3D(200, 150, 800),
                new Point3D(100, 150, 800),
                new Point3D(150, 250, 800),
                new Color (255, 128, 235),
                new Material(1,1,1));
        Material m2=new Material(1,1,4);
        triangle.setMaterial(m2);
        triangle2.setMaterial(m2);
        triangle3.setMaterial(m2);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);

//			scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, 200, -150), new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));
        scene.addLight(new PointLight(new Color(0, 100, 100), new Point3D(0, 500, 0),  0, 0.000001, 0.0000005));

        ImageWriter imageWriter = new ImageWriter("Smile", 500, 500, 500, 500);

        Renderer render = new Renderer(scene, imageWriter);

        render.renderImage();

    }

}
