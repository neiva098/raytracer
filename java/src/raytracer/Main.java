package raytracer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import raytracer.io.Input;
import raytracer.io.Output;
import raytracer.math.Vector3;
import raytracer.scene.Scene;

public class Main {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static void printHelp() {
        System.out.println(
                "Funcionamento do programa:\n"
                + "\n"
                + "\t java -jar raytracer.jar arquivo-de-cena.txt nome-imagem-saida\n"
                + "\n\n"
                + "Possivelmente o programa foi executado sem os devidos parâmetro(s)\n");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            printHelp();
            System.exit(1);
        }

        Raytracer tracer = new Raytracer();
        Scene scene = null;
        try {
            scene = Input.readSceneFromFile(args[0]);
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        // Matriz de pixels que serão "coloridos"
        Vector3[][] pixels = tracer.renderScene(scene, HEIGHT, WIDTH);

        // Transforma os pixels de double (0..1) para byte (0..255)
        int[] bufferI = new int[HEIGHT * WIDTH * 3];
        byte[] bufferB = new byte[HEIGHT * WIDTH * 3];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Vector3 p = pixels[i][j];
                int index = i * WIDTH * 3 + j * 3;
                bufferI[index + 0] = Math.min((int) (p.x * 255), 255);
                bufferI[index + 1] = Math.min((int) (p.y * 255), 255);
                bufferI[index + 2] = Math.min((int) (p.z * 255), 255);
                bufferB[index + 0] = (byte) bufferI[index + 0];
                bufferB[index + 1] = (byte) bufferI[index + 1];
                bufferB[index + 2] = (byte) bufferI[index + 2];
            }
        }

        // Escreve um arquivo ppm e outro bmp
        String outputName = args[1];
        System.out.println("Salvando arquivos: " + outputName + ".png e " + outputName + ".ppm");

        try {
            Output.writeImagePng(outputName + ".png", WIDTH, HEIGHT, bufferI);
            Output.writeImagePpm(outputName + ".ppm", WIDTH, HEIGHT, bufferB);
            //Output.writeImagePpmRaw(args[1]+"-java-raw.ppm", WIDTH, HEIGHT, bufferB);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

}
