package raytracer.scene;

import raytracer.Ray;
import raytracer.RayResponse;
import raytracer.math.Constants;
import raytracer.math.Vector3;

public class Object {

    public Pygment pygment;
    public Material material;
    public String type;
    public Vector3 position;
    public double radius;

    /**
     * *
     * Retorna **true** se o objeto tem interseção com o raio ou **false**, do
     * contrário.
     *
     * Além do valor retornado, caso exista interseção, este método preenche
     * _ray_ com informações cosbre o ponto de colisão: - ray.intersectionPoint,
     * contendo o ponto (x,y,z) de interseção - ray.intersectionNormal, contendo
     * o vetor normal do objeto no ponto de interseção. A normal deve ser
     * **normalizada** (norma = 1)
     *
     * @param ray
     * @return
     */
    public RayResponse intersectsWith(Ray ray) {
        Vector3 u = ray.v.normalized();
        Vector3 p = position.diff(ray.p0);
        double a = 1;
        double b = (-2) * u.dotProduct(p);
        double c = p.dotProduct(p) - Math.pow(radius, 2);
        double delta = Math.pow(b, 2) - 4 * a * c;
        double root1, root2;
        boolean hitFromInside = false;

        RayResponse response = new RayResponse();

        if (delta < -Constants.TINY) {
            return response;
        } else if (delta > Constants.TINY) {
            // duas interseções
            root1 = (-b - Math.sqrt(delta)) / 2 * a;
            root2 = (-b + Math.sqrt(delta)) / 2 * a;
            if (root1 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root1;
            } else if (root2 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root2;
                hitFromInside = true;
            } else {
                return response;
            }
        } else {
            // uma interseção
            root1 = (-b + Math.sqrt(delta)) / 2 * a;
            if (root1 > Constants.TINY) {
                response.intersected = true;
                response.intersectionT = root1;
            } else {
                return response;
            }
        }

        response.intersectionPoint = new Vector3(u.mult(response.intersectionT).add(ray.p0));
        response.intersectionNormal = response.intersectionPoint.diff(position).normalized();
        if (hitFromInside) {
            response.intersectionNormal = new Vector3((response.intersectionNormal.mult(-1)));
        }
        return response;
    }

}
