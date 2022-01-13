package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public abstract class Light {

    protected Color intensity;


    public Light(Color color){
        this.intensity = new Color(color.getRed(),color.getGreen(),color.getBlue());
    }

    public Light(){
        this.intensity = new Color(255,255,255);
    }


    public Color getIntensity(){
        return this.intensity;
    }
    public void setIntensity(Color intensity) {
        this.intensity = intensity;
    }


    public abstract Vector getL(Point3D point);

    public abstract Color getIntensity(Point3D point);
}
