package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light{

    private Vector direction;

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public DirectionalLight(Color color,Vector direction) {
        super(color);
        this.direction = direction;
    }
    public DirectionalLight(DirectionalLight directionalLight) {
        super (directionalLight.intensity);
        this.direction = directionalLight.getDirection();
    }
    public DirectionalLight() {
        super(new Color(255,255,255));
        this.direction = new Vector(1,0,0);
    }

    @Override
    public Vector getL(Point3D point) {
        return this.direction;
    }

    @Override
    public Color getIntensity(Point3D point) {
        return this.intensity;
    }
}
