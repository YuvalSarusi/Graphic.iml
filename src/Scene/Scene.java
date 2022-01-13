package Scene;
import Elements.AmbientLight;
import Elements.Camera;
import Elements.Light;
import Geometries.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
public class Scene  {
    private String sceneName;
    private ArrayList<Geometries> geometriesList;
    private Camera sceneCamera;
    private double screenDistance;
    private Color backgroundColor;
    private ArrayList<Light> sceneLights;
    private AmbientLight ambientLight;

    public ArrayList<Light> getSceneLights() {
        return sceneLights;
    }

    public void setSceneLights(ArrayList<Light> sceneLights) {
        this.sceneLights = sceneLights;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public Scene(){
        this.sceneName = "Empty Scene";
        this.geometriesList = new ArrayList<Geometries>();
        this.sceneCamera = new Camera();
        this.screenDistance = 10;
        this.backgroundColor = new Color(0,0,0);
        this.sceneLights = new ArrayList<>();
    }

    public Scene(String sceneName){
        this.sceneName = sceneName;
        this.geometriesList = new ArrayList<Geometries>();
        this.sceneCamera = new Camera();
        this.screenDistance = 10;
        this.backgroundColor = new Color(0,0,0);
        this.sceneLights = new ArrayList<>();
        this.ambientLight = new AmbientLight();
    }

    public Scene(String name, ArrayList<Geometries> list, Camera camera,double screenDistance, Color color, ArrayList<Light> sceneLights, AmbientLight ambientLight){
        this.sceneName = name;
        this.geometriesList = new ArrayList<Geometries>(list);
        this.sceneCamera = new Camera(camera);
        this.screenDistance = screenDistance;
        this.backgroundColor = new Color(color.getRed(),color.getGreen(),color.getBlue());
        this.sceneLights = sceneLights;
        this.ambientLight = ambientLight;
    }

    public Scene(Scene scene){
        this.sceneName = scene.getSceneName();
        this.geometriesList = new ArrayList<Geometries>(scene.getGeometriesList());
        this.sceneCamera = scene.getSceneCamera();
        this.screenDistance = scene.getScreenDistance();
        this.backgroundColor = new Color(scene.getBackgroundColor().getRed(),scene.backgroundColor.getGreen(), scene.backgroundColor.getBlue());
        this.sceneLights = scene.getSceneLights();
        this.ambientLight = scene.getAmbientLight();
    }

    public void setScene(Scene scene){
        this.setSceneName(scene.getSceneName());
        this.setGeometriesList(scene.getGeometriesList());
        this.setSceneCamera(scene.getSceneCamera());
        this.screenDistance = scene.getScreenDistance();
        this.backgroundColor = new Color(scene.getBackgroundColor().getRed(),scene.backgroundColor.getGreen(), this.backgroundColor.getBlue());
        this.sceneLights = scene.getSceneLights();
        this.ambientLight = scene.getAmbientLight();
    }

    public void addGeometry(Geometries geometries){
        this.geometriesList.add(geometries);
    }
    public void addLight(Light light){
        this.sceneLights.add(light);
    }
    public Camera getSceneCamera() {
        return sceneCamera;
    }

    public void setSceneCamera(Camera sceneCamera) {
        this.sceneCamera = sceneCamera;
    }

    public ArrayList<Geometries> getGeometriesList(){
        return this.geometriesList;
    }
    public String getSceneName(){
        return this.sceneName;
    }
    public void setSceneName(String newName){
        this.sceneName = newName;
    }
    public void setGeometriesList(ArrayList<Geometries> geometriesList){
        this.geometriesList = geometriesList;
    }


    public double getScreenDistance() {
        return screenDistance;
    }

    public void setScreenDistance(double screenDistance) {
        this.screenDistance = screenDistance;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public String toString(){
        return "Scene Name: "+this.getSceneName().toString() +
                "Geometries List: "+this.getGeometriesList().toString();
    }

    @Override
    public boolean equals(Object object){
        try{
            Scene scene = new Scene((Scene)object);
            return this.getSceneName().equals(scene.getSceneName()) &&
                    this.getGeometriesList().equals(scene.getGeometriesList());
        }
        catch (ClassCastException classCastException){
            return false;
        }
    }
}
