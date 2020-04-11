package raytracer.math;

public class Vector3 {

    public double x, y, z;

    /// ------------------------------------------------------------------------
    /// Construtores
    /// ------------------------------------------------------------------------
    public Vector3() {
    }

    /**
     * *
     * CONSTRUTOR que recebe 3 doubles como parâmetro Como usar: Vector3 v = new
     * Vector3(10.0f, 0, 1.0f);
     *
     * @param x valor de x.
     * @param y valor de y.
     * @param z valor de z.
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * *
     * CONSTRUTOR que recebe outro Vector3 e cria um clone Como usar: Vector3 v
     * = new Vector3(10.0f, 0, 1.0f); Vector3 u = new Vector3(v);
     *
     * Assim, v e u serão vetores com o mesmo (x, y, z)
     *
     * @param other o vetor que deseja-se copiar.
     */
    public Vector3(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector3)) {
            return false;
        }
        Vector3 v = (Vector3) other;
        return x == v.x && y == v.y && z == v.z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    ///-------------------------------------------------------------------------
    /// Operações
    ///-------------------------------------------------------------------------
    /**
     * *
     * MÉTODO para adicionar um vetor ao outro. Como usar: Vector3 a = new
     * Vector3(10, 20, 30); Vector3 b = new Vector3(5, 5, 5); Vector3 soma =
     * a.add(b);
     *
     * Ao final, soma = (15, 25, 35) e a e b continuam sendo (10, 20, 30) e (5,
     * 5, 5) respectivamente.
     *
     * @param other O vetor que será somado a este.
     * @return Um novo vetor contendo o resultado da soma dos dois vetores.
     */
    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x,
                y + other.y,
                z + other.z);

    }

    /**
     * *
     * MÉTODO para adicionar um escalar a cada componente do vetor. Como usar:
     * Vector3 a = new Vector3(10, 20, 30); Vector3 resultado = a.add(100);
     *
     * Ao final, resultado = (110, 120, 130) e a continua sendo (10, 20, 30).
     *
     * @param num O número (escalar) que será somado aos componentes. deste
     * vetor.
     * @return Um novo vetor contendo o resultado da soma.
     */
    public Vector3 add(double num) {
        return new Vector3(x + num,
                y + num,
                z + num);
    }

    /**
     * *
     * MÉTODO para subtrair um vetor do outro. Como usar: Vector3 a = new
     * Vector3(10, 20, 30); Vector3 b = new Vector3(5, 5, 5); Vector3 resultado
     * = a.diff(b);
     *
     * Ao final, resultado = (5, 15, 25) e a e b continuam sendo (10, 20, 30) e
     * (5, 5, 5) respectivamente.
     *
     * @param other O vetor a ser subtraído deste vetor.
     * @return Um novo vetor com o resultado da subtração.
     */
    public Vector3 diff(Vector3 other) {
        return new Vector3(x - other.x,
                y - other.y,
                z - other.z);
    }

    /**
     * *
     * MÉTODO para subtrair um escalar a cada componente do vetor. Como usar:
     * Vector3 a = new Vector3(10, 20, 30); Vector3 resultado = a.diff(10);
     *
     * Ao final, resultado = (0, 10, 20) e a continua sendo (10, 20, 30).
     *
     * @param num O número (escalar) que será subtraído aos componentes. deste
     * vetor.
     * @return Um novo vetor contendo o resultado da subtração.
     */
    public Vector3 diff(double num) {
        return new Vector3(x - num,
                y - num,
                z - num);
    }

    /**
     * *
     * MÉTODO para multiplicar um escalar a cada componente do vetor. Como usar:
     * Vector3 a = new Vector3(10, 20, 30); Vector3 resultado = a.mult(3);
     *
     * Ao final, resultado = (30, 60, 90) e a continua sendo (10, 20, 30).
     *
     * @param num O número (escalar) que será multiplicado aos componentes.
     * @return Um novo vetor contendo o resultado da multiplicação.
     */
    public Vector3 mult(double num) {
        return new Vector3(x * num,
                y * num,
                z * num);
    }

    /**
     * *
     * MÉTODO que retorna um novo vetor que é o resultado de uma multiplicação
     * componente-a-componente (component-wise) entre dois vetores. Como usar:
     * Vector3 a = new Vector3(10, 20, 30); Vector3 b = new Vector3(2, 3, 4);
     * Vector3 resultado = a.mult(b);
     *
     * Ao final, resultado = (10*2 20*3, 30*4) e a continua sendo (10, 20, 30).
     *
     * @param other O vetor cujos componentes multiplicarão os componentes
     * deste.
     * @return Um novo vetor cujas componentes são a multiplicação das
     * componentes dos outros dois.
     */
    public Vector3 cwMult(Vector3 other) {
        return new Vector3(x * other.x,
                y * other.y,
                z * other.z);
    }

    /**
     * *
     * MÉTODO para calcular o produto vetorial entre dois vetores (a x b). Como
     * usar: Vector3 a = new Vector3(1, 0, 0); Vector3 b = new Vector3(0, 1, 0);
     * Vector3 resultado = a.cross(b);
     *
     * Ao final, resultado = (0, 0, 1) e a e b continuam sendo (1, 0, 0) e (0,
     * 1, 0).
     *
     * @param other O vetor que será multiplicado a este.
     * @return Um novo vetor com o resultado do produto vetorial entre os dois
     * vetores.
     */
    public Vector3 cross(Vector3 other) {
        return new Vector3(
                y * other.z - z * other.y,
                z * other.x - x * other.z,
                x * other.y - y * other.x
        );
    }

    /**
     * *
     * MÉTODO para calcular o produto interno entre dois vetores (a . b). Como
     * usar: Vector3 a = new Vector3(1, 0, 0); Vector3 b = new Vector3(0, 1, 0);
     * double resultado = a.dotProduct(b);
     *
     * Ao final, resultado = 0.
     *
     * @param other O vetor que será multiplicado (produto interno) a este.
     * @return Um escalar com o resultado do produto interno entre os dois
     * vetores.
     */
    public double dotProduct(Vector3 other) {
        return x * other.x + y * other.y + z * other.z;
    }

    /**
     * *
     * MÉTODO para calcular a norma do vetor. Como usar: Vector3 a = new
     * Vector3(3, 0, 0); double tamanho = a.norm();
     *
     * Ao final, tamanho = 3.
     *
     * @return A norma (magnitude) do vetor.
     */
    public double norm() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * MÉTODO que ALTERA o vetor para sua forma normalizada (tamanho=1). Como
     * usar: Vector3 a = new Vector3(9, 1, 2); a.normalize();
     */
    public void normalize() {
        double norm = norm();
        if (norm != 0) {
            x /= norm;
            y /= norm;
            z /= norm;
        }
    }

    /**
     * *
     * MÉTODO que retorna um novo vetor normalizado, mas sem alterar o vetor
     * original. Como usar: Vector3 a = new Vector3(3, 0, 0); Vector3
     * aNormalizado = a.normalized();
     *
     * Ao final, aNormalizado = (1, 0, 0), mas a continua sendo (3, 0, 0).
     *
     * @return
     */
    public Vector3 normalized() {
        Vector3 v = new Vector3(this);
        v.normalize();
        return v;
    }

    /**
     * *
     * MÉTODO que ALTERA o vetor, fazendo suas componentes (x,y,z) serem
     * truncadas entre 0 e 1. Como usar: Vector3 a = Vector3(9, -1.56f, 0.25f);
     * a.truncate();
     *
     * Ao final, a = (1, -1, 0.25f).
     */
    public void truncate() {
        x = Math.min(1, Math.max(0, x));
        y = Math.min(1, Math.max(0, y));
        z = Math.min(1, Math.max(0, z));
    }

    public String debugInfo() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }

    @Override
    public String toString() {
        return debugInfo();
    }

    public static final Vector3 ZERO = new Vector3(0, 0, 0);
    public static final Vector3 ONE = new Vector3(1, 1, 1);

}
