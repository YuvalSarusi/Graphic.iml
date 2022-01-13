package Elements;

import Helper.Helper;
import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class AmbientLight extends Light{

    private double kA;

    public void setKA(double kA){
        this.kA = kA;
    }

    public double getKA(){
        return this.kA;
    }

    public AmbientLight(AmbientLight ambientLight){
        super(ambientLight.intensity);
        this.kA = ambientLight.getKA();
    }
    public AmbientLight(){
        super();
        this.kA = 0.1;
    }
    public AmbientLight(Color color){
        super(color);
        this.kA = 0.1;
    }
    public AmbientLight(Color color, double kA){
        super(color);
        this.kA = kA;
    }

    @Override
    public Vector getL(Point3D point) {
        return new Vector(0,0,1);
    }

    @Override
    public Color getIntensity(Point3D point) {
        return Helper.multiplyScalarColor(this.intensity,this.getKA());
    }
}
