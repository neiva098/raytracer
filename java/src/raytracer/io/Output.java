package raytracer.io;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Output {

    public static void writeImagePng(String filename, int width, int height,
            int[] buffer) throws IOException {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bi.getRaster().setPixels(0, 0, width, height, buffer);
        File outputfile = new File(filename);
        ImageIO.write(bi, "png", outputfile);
    }

    public static void writeImagePpm(String filename, int width, int height,
            byte[] buffer) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write("P6\n".getBytes());
        fos.write((width + " " + height + "\n").getBytes());
        fos.write("255\n".getBytes());
        fos.write(buffer);
        fos.close();
    }

    public static void writeImagePpmRaw(String filename, int width, int height,
            byte[] buffer) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.append(String.format("P3\n%d %d\n255\n", width, height));
        for (int i = 0, c = 1; i < height; i++) {
            for (int j = 0; j < width; j++, c++) {
                int index = i * width * 3 + j * 3;
                bw.append(String.format(" %d %d %d ",
                        unsignedByte(buffer[index + 0]),
                        unsignedByte(buffer[index + 1]),
                        unsignedByte(buffer[index + 2]))
                );
                if (c % 5 == 0) {
                    bw.append("\n");
                }
            }
        }

        bw.close();
    }

    private static int unsignedByte(byte b) {
        return b & 0xFF;
    }
}
