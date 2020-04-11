package raytracer.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Vector3Test {
    
    public Vector3Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class Vector3.
     */
    @Test
    public void testAdd_Vector3() {
        System.out.println("add");
        Vector3 other = new Vector3(10, 0, -5.5);
        Vector3 instance = new Vector3(1, 1, 1);
        Vector3 expResult = new Vector3(11, 1, -4.5);
        Vector3 result = instance.add(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class Vector3.
     */
    @Test
    public void testAdd_double() {
        System.out.println("add");
        double num = 4.3;
        Vector3 instance = new Vector3(1, 0, 0);
        Vector3 expResult = new Vector3(5.3, 4.3, 4.3);
        Vector3 result = instance.add(num);
        assertEquals(expResult, result);
    }

    /**
     * Test of diff method, of class Vector3.
     */
    @Test
    public void testDiff_Vector3() {
        System.out.println("diff");
        Vector3 other = new Vector3(1, 0, 8.5);
        Vector3 instance = new Vector3(10, 0, -5.5);
        Vector3 expResult = new Vector3(9, 0, -14);
        Vector3 result = instance.diff(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of diff method, of class Vector3.
     */
    @Test
    public void testDiff_double() {
        System.out.println("diff");
        double num = 1.4;
        Vector3 instance = new Vector3(10.4, 0, -1);
        Vector3 expResult = new Vector3(9, -1.4, -2.4);
        Vector3 result = instance.diff(num);
        assertEquals(expResult, result);
    }

    /**
     * Test of mult method, of class Vector3.
     */
    @Test
    public void testMult() {
        System.out.println("mult");
        double num = 8.0;
        Vector3 instance = new Vector3(0, 1, 2);
        Vector3 expResult = new Vector3(0, 8, 16);
        Vector3 result = instance.mult(num);
        assertEquals(expResult, result);
    }

    /**
     * Test of cwMult method, of class Vector3.
     */
    @Test
    public void testCwMult() {
        System.out.println("cwMult");
        Vector3 other = new Vector3(0, 2, 1);
        Vector3 instance = new Vector3(1, 8, -2);
        Vector3 expResult = new Vector3(0, 16, -2);
        Vector3 result = instance.cwMult(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of cross method, of class Vector3.
     */
    @Test
    public void testCross() {
        System.out.println("cross");
        Vector3 other = new Vector3(0, 1, 0);
        Vector3 instance = new Vector3(1, 0, 0);
        Vector3 expResult = new Vector3(0, 0, 1);
        Vector3 result = instance.cross(other);
        assertEquals(expResult, result);
    }

    /**
     * Test of dotProduct method, of class Vector3.
     */
    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector3 other = new Vector3(0, 1, 0);
        Vector3 instance = new Vector3(1, 0, 0);
        double expResult = 0.0;
        double result = instance.dotProduct(other);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of norm method, of class Vector3.
     */
    @Test
    public void testNorm() {
        System.out.println("norm");
        Vector3 instance = new Vector3(1, 2, 2);
        double expResult = 3.0;
        double result = instance.norm();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of normalize method, of class Vector3.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector3 instance = new Vector3(200, 1, 1);
        instance.normalize();
        assertEquals(1.0, instance.norm(), Constants.TINY);
    }

    /**
     * Test of normalized method, of class Vector3.
     */
    @Test
    public void testNormalized() {
        System.out.println("normalized");
        Vector3 instance = new Vector3(1, 2, 2);
        Vector3 expResult = new Vector3(1/3.0, 2/3.0, 2/3.0);
        Vector3 result = instance.normalized();
        assertEquals(expResult, result);
    }

    /**
     * Test of truncate method, of class Vector3.
     */
    @Test
    public void testTruncate() {
        System.out.println("truncate");
        Vector3 instance = new Vector3(20, -10, 0.5);
        instance.truncate();
        assertEquals(new Vector3(1, 0, 0.5), instance);
    }

    /**
     * Test of debugInfo method, of class Vector3.
     */
    @Test
    public void testDebugInfo() {
        System.out.println("debugInfo");
        Vector3 instance = new Vector3();
        String expResult = String.format("(%.2f, %.2f, %.2f)", instance.x, instance.y, instance.z);
        String result = instance.debugInfo();
        assertEquals(expResult, result);
    }
    
}
