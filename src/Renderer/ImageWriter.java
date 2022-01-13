package Renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

/*
Made by:
//Yuval Sarusi
Eden Amzaleg
 */

public class ImageWriter {
    private double imageWidth, imageHeight;
    private int nX, nY;

    private final String PROJECT_PATH = System.getProperty("user.dir") + "/images";

    private BufferedImage image;

    private String imageName;

    // ***************** Constructors ********************** //
    /**
     * Image Writer constructor accepting image name and View Plane parameters,
     * @param imageName the name of jpeg file
     * @param width View Plane width in size units
     * @param height View Plane height in size units
     * @param nX amount of pixels by Width
     * @param nY amount of pixels by height
     */
    public ImageWriter(String imageName, double width, double height, int nX, int nY) {
        this.imageName = imageName;
        this.imageWidth = width;
        this.imageHeight = height;
        this.nX = nX;
        this.nY = nY;

        this.image = new BufferedImage(this.nX, this.nY, BufferedImage.TYPE_INT_RGB);
    }

    // ***************** Getters/Setters ********************** //
    /**
     * View Plane width getter
     * @return the width
     */
    public double getWidth()  { return imageWidth;  }
    /**
     * View Plane height getter
     * @return the height
     */
    public double getHeight() { return imageHeight; }

    /**
     * View Plane Y axis resolution
     * @return the amount of vertical pixels
     */
    public int getNy() { return nY; }
    /**
     * View Plane X axis resolution
     * @return the amount of horizontal pixels
     */
    public int getNx() { return nX; }

    // ***************** Operations ******************** //

    /**
     * Function writeToImage produces unoptimized jpeg file of
     * the image according to pixel color matrix in the directory
     * of the project
     */
    //function move the image from the program to the file
    public void writeToImage(){
        File ouFile = new File(PROJECT_PATH + "/" + imageName + ".jpg");
        try {
            javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(1f);
            jpgWriter.setOutput(new FileImageOutputStream(ouFile));
            jpgWriter.write(null,new IIOImage(image, null, null), jpgWriteParam);
            //ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function writePixel writes a color of a specific pixel
     * into pixel color matrix
     * @param xIndex X axis index of the pixel
     * @param yIndex Y axis index of the pixel
     * @param color final color of the pixel
     */
    //function get i,j (location at the image) and color the wright pixel at the image with the given color
    public void writePixel(int xIndex, int yIndex, Color color){
        this.image.setRGB(xIndex, yIndex, color.getRGB());
    }
}
