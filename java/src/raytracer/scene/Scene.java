package raytracer.scene;

import java.util.ArrayList;

public class Scene {

    public Camera camera;
    public ArrayList<Light> lights;
    public ArrayList<Pygment> pygments;
    public ArrayList<Material> materials;
    public ArrayList<Object> objects;

    public Scene() {
        camera = new Camera();
    }

    public String debugInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Informacoes sobre a cena:\n");
        info.append("\tCamera:\n\t\teye: ").append(camera.eye.debugInfo());
        info.append("\n\t\ttarget: ").append(camera.target.debugInfo());
        info.append("\n\t\tup: ").append(camera.up.debugInfo());
        info.append("\n");

        info.append("\tLuzes: ").append(lights.size()).append("\n\n");
        for (Light l : lights) {
            info.append("\t\tposition: ").append(l.position.debugInfo()).append("\n");
            info.append("\t\tcolor: ").append(l.color.debugInfo()).append("\n");
            info.append("\t\tatenuacoes: ").append(String.format("%f, %f, %f",
                    l.constantAttenuation, l.linearAttenuation,
                    l.quadraticAttenuation)).append("\n");
        }

        info.append("\tPigmentos: ").append(pygments.size()).append("\n\n");
        for (Pygment p : pygments) {
            info.append("\t\type: ").append(p.type).append("\n");
            info.append("\t\tcolor: ").append(p.color.debugInfo()).append("\n");
        }

        info.append("\tMateriais: ").append(materials.size()).append("\n\n");
        for (Material m : materials) {
            info.append("\t\tambiente: ").append(m.ambientCoefficient).append("\n");
            info.append("\t\tdifuso: ").append(m.diffuseCoefficient).append("\n");
            info.append("\t\tespecular: ").append(m.specularCoefficient).append("\n");
            info.append("\t\texpoente espec.: ").append(m.specularExponent).append("\n");
            info.append("\t\tindice reflex.: ").append(m.reflectionCoefficient).append("\n");
            info.append("\t\tindice refrac.: ").append(m.transmissionCoefficient).append("\n");
            info.append("\t\tsnell: ").append(m.snellCoefficient).append("\n");
        }

        info.append("\tObjetos: ").append(objects.size()).append("\n\n");
        for (Object o : objects) {
            info.append("\t\ttype: ").append(o.type).append("\n");
            info.append("\t\tposition: ").append(o.position.debugInfo()).append("\n");
            info.append("\t\traio: ").append(o.radius).append("\n");
        }

        return info.toString();
    }
}
