import lab01.Calculator;
import lab01.Matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        checkCalculatorTask();
        checkMatrixTask();
    }

    private static void checkCalculatorTask() {
        Calculator c = new Calculator();
        System.out.println(c.calculate("(15.2*5)^2"));
        System.out.println("\n=========================================\n");
    }

    private static void checkMatrixTask() {
        Matrix m = new Matrix(new double[][]{
                {0, 1, 0},
                {0, 3, 0},
                {0, 5, 0}
        });
        System.out.println(Arrays.deepToString(m.addValue(3)));
        System.out.println(Arrays.deepToString(m.subtractValue(3)));
        System.out.println(Arrays.deepToString(m.multiply(3)));
        System.out.println(Arrays.deepToString(m.divide(3)));
    }


}
