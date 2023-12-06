package dis.ultrassom.util;

import java.io.IOException;
import java.util.UUID;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.jblas.DoubleMatrix;

public class ImageGenerator {
    public static void generateImage(DoubleMatrix f, String outputPath) {
        int width = f.columns;
        int height = f.rows;

        BufferedImage image = new BufferedImage(width, height , BufferedImage.TYPE_INT_RGB);

        // Normalizar os valores de f para o intervalo [0, 255]
        double maxIntensity = f.max();
        double minIntensity = f.min();
        double intensityRange = maxIntensity - minIntensity;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixelValue = (int) ((f.get(i, j) - minIntensity) / intensityRange * 255);
                int rgb = (pixelValue << 16) | (pixelValue << 8) | pixelValue;
                image.setRGB(j, i, rgb);
            }
        }
        
        // Salvar a imagem
        try {
            String uniqueFileName = UUID.randomUUID().toString() + ".png";
            ImageIO.write(image, "png", new File(String.format("%s\\%s", outputPath, uniqueFileName)));
            System.out.println("Imagem salva com sucesso em: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
