package Elements;

import Helper.Helper;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.Renderer;

import java.awt.*;

public class PointLight extends Light{

    private Point3D position;
    private double kC;
    private double kL;
    private double kQ;

    public PointLight(Color color, Point3D position, double kC, double kL, double kQ) {
        super(color);
        this.position = new Point3D(position);
        this.kC = kC;
        this.kL = kL;
        this.kQ = kQ;
    }

    public PointLight() {
        super();
        this.kC = 0.1;
        this.kL = 0.1;
        this.kQ = 0.1;
        this.position = new Point3D(0,0,0);
    }

    public PointLight(Color color) {
        super(color);
        this.kC = 0.1;
        this.kL = 0.1;
        this.kQ = 0.1;
        this.position = new Point3D(0,0,0);
    }

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public double getkC() {
        return kC;
    }

    public void setkC(double kC) {
        this.kC = kC;
    }

    public double getkL() {
        return kL;
    }

    public void setkL(double kL) {
        this.kL = kL;
    }

    public double getkQ() {
        return kQ;
    }

    public void setkQ(double kQ) {
        this.kQ = kQ;
    }

    @Override
    public Vector getL(Point3D point) {
        return new Vector(point.subtract(position));
    }

    @Override
    public Color getIntensity(Point3D point) {
        double d = this.position.distance(point);
        double dSquare = d * d;
        double number = this.kC+this.kL*d+this.kQ*dSquare;
        double scalar = 1/number;
        Color returnedColor = Helper.multiplyScalarColor(this.intensity,scalar);
        return returnedColor;
    }
}
