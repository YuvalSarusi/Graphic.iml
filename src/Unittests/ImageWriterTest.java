package Unittests;

import Geometries.Ball;
import Renderer.ImageWriter;
import org.junit.Test;

import java.awt.*;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class ImageWriterTest {

    @Test
    public void testCreateYellowScreen() {
        ImageWriter imageWriter = new ImageWriter("imgWriterTest", 1600, 1000, 800, 500);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j)
                imageWriter.writePixel(j, i,
                        j % 50 == 0 || i % 50 == 0 ? new Color(255,0,0) : new Color(255,255,0));

        imageWriter.writeToImage();
    }

    @Test
    public void testCreateGreenScreen(){
        ImageWriter imageWriter = new ImageWriter("imgWriterGreenTest", 1600, 1000, 800, 500);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j)
                imageWriter.writePixel(j, i,
                        j % 100 == 0 || i % 100 == 0 ? new Color(255,0,0) : new Color(0,255,0));

        imageWriter.writeToImage();
    }

    @Test
    public void createBlueBlock(){
        ImageWriter imageWriter = new ImageWriter("imgWriterBlueBlockTest", 1600, 1600, 800, 800);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j)
                imageWriter.writePixel(j, i,
                        ((j<600 && j>200) && (i<600 && i>200) ? new Color(0,0,255) : new Color(250,150,70)));

        imageWriter.writeToImage();
    }

    @Test
    public void createGreenBlock(){
        ImageWriter imageWriter = new ImageWriter("imgWriterGreenBlockTest", 1600, 1600, 900, 900);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i)
            for (int j = 0; j < nX; ++j)
                imageWriter.writePixel(j, i,
                        ((j<800 && j>100) && (i<800 && i>100) ? new Color(0,255,0) : new Color(0,0,255)));

        imageWriter.writeToImage();
    }


}
