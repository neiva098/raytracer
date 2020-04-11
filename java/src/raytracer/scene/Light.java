package raytracer.scene;

import raytracer.math.Vector3;

public class Light {

    // Posição da luz (x,y,z)
    public Vector3 position;
    // Cor da luz em (r,g,b)
    public Vector3 color;

    // Coeficientes de atenuação da luz de acordo com a distância até o objeto
    public double constantAttenuation;
    public double linearAttenuation;
    public double quadraticAttenuation;
}
