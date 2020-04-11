package raytracer;

import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import raytracer.math.Vector3;
import raytracer.scene.Camera;
import raytracer.scene.Light;
import raytracer.scene.Material;
import raytracer.scene.Object;
import raytracer.scene.Pygment;
import raytracer.scene.Scene;

public class Raytracer {

    /**
     * *
     * Cria um raio que vai da posição da câmera e passa pelo pixel indicado por
     * (column, row).
     *
     * @param camera A câmera com as configurações de eye, target e up.
     * @param row A coordenada y do pixel por onde o raio deve passar.
     * @param column A coordenada x do pixel por onde o raio deve passar.
     * @param height O número de linhas totais da imagem.
     * @param width O número de colunas totais da image.
     * @return Um raio que sai da origem da câmera e passa pelo pixel (column,
     * row).
     */
    private Ray generateInitialRay(Camera camera, int row, int column,
            int height, int width) {
        Vector3 gridPointInCameraSpace = new Vector3(
                column - width / 2.0f,
                row - height / 2.0f,
                -1
        );

        Ray ray = new Ray();
        ray.p0 = camera.eye;
        ray.v = new Vector3(gridPointInCameraSpace.diff(ray.p0));
        ray.v.normalize();

        return ray;
    }

    /**
     * *
     * Lança um raio para a cena (camera) que passa por um certo pixel da cena.
     * Retorna a cor desse pixel.
     *
     * @param scene A cena onde o raio será lançado.
     * @param ray O raio a ser lançado.
     * @return A cor com que o pixel (acertado pelo raio) deve ser colorido.
     */
    private Vector3 castRay(Scene scene, Ray ray) {
        // Para todos os objetos da cena, verifica se o raio o acerta e pega aquele
        // que foi atingido primeiro (menor "t")
        RayResponse closestIntersection = new RayResponse();
        Object closestObjectHit = null;
        for (Object obj : scene.objects) {
            RayResponse response = obj.intersectsWith(ray);
            if (response.intersected) {
                if (response.intersectionT < closestIntersection.intersectionT) {
                    closestIntersection = response;
                    closestObjectHit = obj;
                }
            }
        }
        // Verifica se um objeto foi atingido. Se tiver sido, colore o pixel
        if (closestObjectHit != null) {
            // Um objeto foi atingido. Vamos descobrir sua cor no ponto de 
            // interseção do raio
            // material e pigmento do objeto atingido
            Material material = closestObjectHit.material;
            Pygment pygment = closestObjectHit.pygment;
            // Esta é a variável contendo a COR RESULTANTE do pixel,
            // que deve ser devidamente calculada e retornada ao final
            // deste método (castRay)
            Vector3 shadingColor = new Vector3(1, 1, 1);
            //------------------------------------------------------------------
            // Aqui começamos a implementar a equaçăo de Phong (e armazenar o 
            //   resultado parcial em shadingColor)
            // Sugiro seguir as anotaçőes do prof. David Mount (p. 83)
            // ---
            // Exercício 1: Coloque a componente ambiente na cor resultante
            // luz ambiente: coefAmbienteLuz*corMat
            // Agora, precisamos saber se as fontes de luz estão iluminando 
            // este ponto do objeto
            shadingColor= shadingColor.mult(material.ambientCoefficient);
            shadingColor= shadingColor.cwMult(closestObjectHit.pygment.color);
            for (Light light : scene.lights) {
                // Para verificar,
                // ---
                // Exercício 2: crie um raio do ponto de interseção com o 
                //   objeto até a fonte de luz (basta instanciar devidamente 
                //   um Ray, ~4 linhas)
                Ray raioz=new Ray();
                raioz.p0=closestIntersection.intersectionPoint;
                raioz.v=light.position.diff(raioz.p0).normalized();
                // Verificamos se o raio atinge algum objeto ANTES da fonte de 
                //   luz
                // Se for o caso, esta fonte de luz não contribui para a luz 
                //   do objeto
                boolean hitsAnotherObjectBeforeLight = false;
                // ---
                // Exercício 3: Percorra os objetos da cena verificando se 
                //   houve interseção com eles, antes da interseção com a 
                //   fonte luminosa
                // Salve essa informação na variável 
                //   hitsAnotherObjectBeforeLight (~10 linhas)
                for (Object obj : scene.objects) {
                        RayResponse response = obj.intersectsWith(raioz);
                        if (response.intersected) {
                            hitsAnotherObjectBeforeLight=true;
                        }
                }
                        if (!hitsAnotherObjectBeforeLight) {
                    // ---
                    // Exercício 4: Devemos terminar de calcular a equaçăo 
                    //   de Phong (atenuação, componente difusa e componente 
                    //   especular) e somar o resultado na cor resultante 
                    //   (na variável shadingColor, ~15 linhas)
                    Vector3 Vh,cor,Vl=new Vector3();
                    double x,y,z,w,m1,m2,ax,al,pd,ps;
                    x=light.constantAttenuation;
                    y=light.linearAttenuation;
                    z=light.quadraticAttenuation;
                    w=raioz.v.norm();//
                    al=material.specularExponent;
                    Vl=light.position.diff(closestIntersection.intersectionPoint);
                    Vl.normalize();
                    ax=closestIntersection.intersectionNormal.normalized().dotProduct(Vl);
                    m1=Math.max(0, ax);
                    Vh=raioz.v.add(Vl);
                    Vh.normalize();
                    ax=closestIntersection.intersectionNormal.normalized().dotProduct(Vh);
                    m2=Math.max(0, ax);
                    ax=1/(x+(y*w)+(z*w*w));
                    pd=material.diffuseCoefficient*m1*ax;
                    ps=material.specularCoefficient*(Math.pow(m2, al)*ax);
                    cor=closestObjectHit.pygment.color;
                    cor=cor.mult(pd);
                    shadingColor=shadingColor.add(cor);
                    shadingColor=shadingColor.add(ps);      
                }
            } 
            // trunca: faz (r,g,b) ficarem entre [0,1], caso tenha excedido
            shadingColor.truncate();
            return shadingColor;
        }
        // nada foi atingido. Retorna uma cor padrão (de fundo)
        return Vector3.ZERO;
    }
    /**
     * *
     * MÉTODO que renderiza uma cena, gerando uma matriz de cores.
     *
     * @param scene um objeto do tipo Scene contendo a descrição da cena (ver
     * Scene.java)
     * @param height altura da imagem que estamos gerando (e.g., 600px)
     * @param width largura da imagem que estamos gerando (e.g., 800px)
     * @return uma matriz de cores (representadas em Vector3 - r,g,b)
     */
    public Vector3[][] renderScene(Scene scene, int height, int width) {
        Vector3[][] pixels = new Vector3[height][width];

        // Para cada pixel, lança um raio
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // cria um raio primário
                Ray ray = generateInitialRay(scene.camera, i, j, height, width);

                // lança o raio e recebe a cor
                Vector3 color = castRay(scene, ray);

                // salva a cor na matriz de cores
                pixels[i][j] = color;
            }
        }
        return pixels;
    }
}