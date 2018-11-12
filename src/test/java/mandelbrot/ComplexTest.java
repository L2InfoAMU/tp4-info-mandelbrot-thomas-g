package mandelbrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1,1);
    private final Complex minusI = new Complex(0,-1);
    private final Complex minusOne = new Complex(-1,0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0,2);
    private final Complex two = new Complex(2,0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor(){
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal(){
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1,1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary(){
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne(){
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI(){
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero(){
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate(){
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real,-imaginary).negate());
    }

    @Test
    void testReciprocal(){
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5,0), two.reciprocal());
        assertEquals(new Complex(0.5,0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract(){
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real-1,imaginary-1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide(){
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI,oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero(){
        assertThrows(ArithmeticException.class, ()->Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate(){
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation(){
        assertEquals(Complex.I, Complex.rotation(Math.PI/2));
        assertEquals(minusI, Complex.rotation(-Math.PI/2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2)/2., Math.sqrt(2)/2.),
                Complex.rotation(Math.PI/4));
        assertEquals(new Complex(1./2., Math.sqrt(3)/2.),
                Complex.rotation(Math.PI/3));
    }

    @Test
    void testToString(){
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real="+real+", imaginary="+imaginary+"}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testReal(){
        assertEquals(new Complex(1, 0), Complex.real(1));
        assertEquals(new Complex(-1, 0), Complex.real(-1));
        assertEquals(Complex.ZERO, Complex.real(0));
    }

    @Test
    void testAdd(){
        assertEquals(new Complex(2, 2), two.add(twoI));
        assertEquals(new Complex(-1, -1), minusOne.add(minusI));
        assertEquals(new Complex(0, 1), minusI.add(twoI));
    }

    @Test
    void testSubtract() {
        assertEquals(new Complex(2, -2), two.subtract(twoI));
        assertEquals(new Complex(0, -2), oneMinusI.subtract(onePlusI));
        assertEquals(new Complex(0, 0), two.subtract(two));
    }

    @Test
    void testMultiply(){
        assertEquals(new Complex(0, 4), two.multiply(twoI));
        assertEquals(new Complex(-1, -1), minusI.multiply(oneMinusI));
        assertEquals(new Complex(0, 2), onePlusI.multiply(onePlusI));
    }

    @Test
    void testSquaredModulus(){
        assertEquals(4, two.squaredModulus());
        assertEquals(4, twoI.squaredModulus());
        assertEquals(2, onePlusI.squaredModulus());
    }

    @Test
    void testModulus(){
        assertEquals(2, two.modulus());
        assertEquals(Math.sqrt(8), two.add(twoI).modulus());
        assertEquals(Math.sqrt(2), onePlusI.modulus());
    }

    @Test
    void testPow(){
        assertEquals(new Complex(1, 0), onePlusI.pow(0));
        assertEquals(new Complex(1, 1), onePlusI.pow(1));
        assertEquals(new Complex(16, -16), oneMinusI.pow(9));
    }

    @Test
    void testScale(){
        assertEquals(new Complex(0, -1), oneMinusI.scale(0));
        assertEquals(new Complex(8, 0), two.scale(4));
        assertEquals(new Complex(0, 2), twoI.scale(4));
    }

    @Test
    void testEquals(){
        assertEquals(true, new Complex(0, 0).equals(Complex.ZERO));
        assertEquals(false, new Complex(0, 1).equals(Complex.ONE));
        assertEquals(false, new Complex(0, 0).equals(null));
    }

}