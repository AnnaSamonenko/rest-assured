package utils;

import java.io.File;
import java.awt.image.*;
import javax.imageio.*;

public class ComparatorOfImages {

    public static boolean imagesIsEqual(File folder1, File folder2) {
        boolean isEquals = true;
        File[] listOfImages1 = folder1.listFiles();
        File[] listOfImages2 = folder2.listFiles();

        for (int i = 0; i < listOfImages1.length; i++){
            if(!imagesIsEqualBySingleFile(listOfImages1[i].getAbsoluteFile(), listOfImages2[i].getAbsoluteFile())){
                return false;
            }
        }
        return isEquals;
    }

    private static boolean imagesIsEqualBySingleFile(File f1, File f2) {
        BufferedImage image1, image2;

        try {
            image1 = ImageIO.read(f1);
            image2 = ImageIO.read(f2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight())
            return false;

        for (int x = 0; x < image1.getWidth(); x++)
            for (int y = 0; y < image1.getHeight(); y++)
                if (image1.getRGB(x, y) != image2.getRGB(x, y))
                    return false;

        return true;
    }
}