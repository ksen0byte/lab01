package lab01;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class Matrix {
    private int iSize;
    private int jSize;
    private List<Element> nonZeroElements = new ArrayList<>();
    private double sparseElem;

    public Matrix(double[][] array) {
        iSize = array.length;
        jSize = array[0].length;

        if (jSize == 0) {
            throw new IllegalArgumentException("Invalid nonZeroElements input");
        }

        for (int i = 0; i < iSize; i++) {
            if (jSize != array[i].length) {
                throw new IllegalArgumentException("Invalid nonZeroElements input");
            }
            for (int j = 0; j < jSize; j++) {
                if (array[i][j] != sparseElem) {
                    nonZeroElements.add(new Element(i, j, array[i][j]));
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    private class Element {
        private int i;
        private int j;
        private double value;
    }

    public double[][] getAsArray() {
        double[][] array = new double[iSize][jSize];
        for (double[] doubles : array) {
            Arrays.fill(doubles, sparseElem);
        }
        nonZeroElements.forEach(element ->
                array[element.getI()][element.getJ()] = element.getValue());
        return array;
    }

    public double[][] addValue(double value) {
        nonZeroElements.forEach(element -> element.setValue(element.getValue() + value));
        this.sparseElem = value;
        return getAsArray();
    }

    public double[][] subtractValue(double value) {
        return addValue(-value);
    }

    public double[][] multiply(double value) {
        nonZeroElements.forEach(element -> element.setValue(element.getValue() * value));
        sparseElem *= value;
        return getAsArray();
    }

    public double[][] divide(double value) {
        nonZeroElements.forEach(element -> element.setValue(element.getValue() / value));
        sparseElem /= value;
        return getAsArray();
    }

}
