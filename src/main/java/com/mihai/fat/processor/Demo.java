package com.mihai.fat.processor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Demo {

    private static final int NUMBER_OF_CLUSTERS = 5;
    private static final String INPUT_PATH = "C:\\input.jpg";
    private static final String OUTPUT_PATH = "C:\\output.jpg";
    private static final String FILE_EXTENSION = "jpg";

    public static void main(String[] args) throws Exception {

        KMeansClusteringProcessor processor = new KMeansClusteringProcessor(INPUT_PATH,  NUMBER_OF_CLUSTERS);
        BufferedImage image = processor.process();
        ImageIO.write(image, FILE_EXTENSION, new File(OUTPUT_PATH));

    }

}
