package Elements;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */
public class Camera {
    private Point3D p00;
    private Vector vUp;
    private Vector vLeft;
    private Vector vTo;

    public Camera(){
        this.p00 = new Point3D(0,0,0);
        this.vLeft = new Vector(new Point3D(1,0,0));
        this.vTo = new Vector(new Point3D(0,0,1));
        this.vUp = new Vector(new Point3D(0,-1,0));
    }

    public Camera(Camera camera){
        this.p00 = new Point3D(camera.getP00());
        this.vLeft = new Vector(camera.getVLeft());
        this.vTo = new Vector(camera.getVTo());
        this.vUp = new Vector(camera.getVUp());
    }

    public Camera(Point3D p00, Vector vTo,Vector vUp){
        this.p00 = new Point3D(p00);
        this.vTo = new Vector(vTo);
        this.vUp = new Vector(vUp);
        this.vLeft = new Vector(vTo.crossProduct(vUp));
    }

    public Camera(Point3D p00, Vector vUp, Vector vLeft, Vector vTo) {
        this.p00 = new Point3D(p00);
        this.vUp = new Vector(vUp);
        this.vLeft = new Vector(vLeft);
        this.vTo = new Vector(vTo);
    }

    public Point3D getP00() {
        return p00;
    }

    public void setP00(Point3D p00) {
        this.p00.setPoint3D(p00);
    }

    public Vector getVUp() {
        return this.vUp;
    }

    public void setVUp(Vector vUp) {
        this.vUp.setVector(vUp);
    }

    public Vector getVLeft() {
        return this.vLeft;
    }

    public void setVLeft(Vector vLeft) {
        this.vLeft.setVector(vLeft);
    }

    public Vector getVTo() {
        return this.vTo;
    }

    public void setVTo(Vector vTo) {
        this.vTo.setVector(vTo);
    }

    /*
    nX - number of pixels at width,
    nY - number of pixels at height,
    int j - the location of the point at Y (up),
    int i - the location of the point at X (left),
    *at the plane the point will be shown as (j,i) and not (i,j), because the axials are reverses.
    screenDist - the distance from the camera to the screen
    screenHeight - the height of the screen
    screenWidth - the width of the screen
     */
    public Ray constructRayThroughPixel(int nX,int nY, int j, int i,double screenDist, double screenHeight, double screenWidth){
        Point3D pC = new Point3D(this.getP00().add(this.getVTo().scale(screenDist)));
        double rY = screenHeight/nY;
        double rX = screenWidth/nX;
        double xJ = (j-((double)(nX-1)/2))*rX;
        double yI = (i-((double)(nY-1)/2))*rY;
        Point3D PointIJ = new Point3D(pC);
        if(xJ != 0)
            PointIJ = PointIJ.add(
                    this.getVLeft().scale(xJ)
            );
        if (yI != 0)
            PointIJ = PointIJ.add(
                    this.getVUp().scale(-yI)
            );
        Vector vectorIJ = new Vector(PointIJ.subtract(this.getP00()));
        Ray returnedRay = new Ray(this.getP00(),vectorIJ.normalize());
        return returnedRay;
    }
}