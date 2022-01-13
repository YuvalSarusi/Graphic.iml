package Elements;

import Helper.Helper;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.Renderer;

import java.awt.*;

public class SpotLight extends PointLight{

    private Vector direction;

    public SpotLight(Color color, Point3D position, double kC, double kL, double kQ, Vector direction) {
        super(color, position, kC, kL, kQ);
        this.direction = direction;
    }

    public SpotLight(Vector direction) {
        super();
        this.direction = direction;
    }

    public SpotLight(Color color, Vector direction) {
        super(color);
        this.direction = direction;
    }

    public SpotLight(){
        super();
        this.direction = new Vector(0,0,1);
    }


    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }


    @Override
    public Color getIntensity(Point3D point) {
        Color color = super.getIntensity(point);
        double scalar = direction.normalize().dotProduct(getL(point).normalize());
        scalar = Math.max(0,scalar);
        Color  returnedColor = Helper.multiplyScalarColor(color, scalar);

        /*
        Color returnedColor = Helper.multiplyScalarColor(
                color,
                Math.max(0,
                        this.direction.normalize()
                                .dotProduct(
                                    this.getL(point)
                                )
                )
        );
         */
        return returnedColor;
    }

    @Override
    public Vector getL(Point3D point) {
        return super.getL(point);
    }
}
