package Helper;

import Primitives.Point3D;

import java.awt.*;

public class Helper {


    public static final double ESP = 0.1;
    public static Point3D ZERO = new Point3D(0,0,0);

    public static Color multiplyScalarColor(Color color, double scalar){
        int red = (int)Math.abs(scalar*color.getRed());
        int green = (int)Math.abs(scalar*color.getGreen());
        int blue = (int)Math.abs(scalar*color.getBlue());
        return reduceColor(red, green, blue);
    }

    public static Color calcAddingColors(Color ... colors){
        Color returnedColor;
        int red =0, green =0, blue =0;
        for (Color color:colors){
            red = red + color.getRed();
            green = green + color.getGreen();
            blue = blue + color.getBlue();
        }
        returnedColor = reduceColor(red, green, blue);
        return returnedColor;
    }




    public static Color reduceColor(int red, int green, int blue ){
        red = red > 255? 255 : red;
        green =  green >255?255:green;
        blue = blue >255 ?255 : blue;
        return new Color(red,green,blue);
    }
}
