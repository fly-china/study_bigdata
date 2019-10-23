package com.lpf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author lipengfei
 * @date 2019-09-10 17:02
 **/
public class ImageDemo {

    private static final String FILE_PATH = "C:\\Users\\Administrator\\Pictures\\大图\\";

    public static void main(String[] args) throws Exception {

        String newFileName = "temp.jpg";


        Image imageObj = ImageIO.read(new File(FILE_PATH + "陶然亭.jpg"));

        BufferedImage bufferedImage = new BufferedImage(132, 132, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(imageObj, 0, 0, 132, 132, null);

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(FILE_PATH + newFileName));

        String formatName = newFileName.split("\\.") != null ? newFileName.split("\\.")[1] : "jpg";

        ImageIO.write(bufferedImage, formatName, out);
    }
}
