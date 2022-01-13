package Exceptions;

public class PointBehindCameraException extends Exception{

    public PointBehindCameraException(){
        super("Point Behind Camera. Ray doesn't Touch The Object");
    }
}
