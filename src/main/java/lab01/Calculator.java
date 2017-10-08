package lab01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public Double calculate(String input) {
        Integer openParenthesiseIndex;
        input = input.replaceAll(" ", "");
        while ((openParenthesiseIndex = input.lastIndexOf("(")) != -1) {

            String minExp = input.substring(openParenthesiseIndex + 1,
                    input.indexOf(")", openParenthesiseIndex));
            input = input.replace("(" + minExp + ")", evaluateExp(minExp));
        }
        return Double.valueOf(evaluateExp(input));
    }

    private String evaluateExp(String minExp) {
        ArrayList<String> operators = new ArrayList<>();
        Collections.addAll(operators, minExp.replaceAll("\\.", "").
                replaceAll("\\d", " ").split(" "));
        operators.removeIf(String::isEmpty);

        List<String> operandsStr = new ArrayList<>();
        Collections.addAll(operandsStr, minExp.split("[+\\-/*^]"));
        List<Double> operands = operandsStr.stream().map(Double::valueOf).
                collect(Collectors.toList());

        checkIfValid(operands, operators);

        while (!operators.isEmpty()) {
            applyOperation(operands, operators);
        }
        return operands.get(0).toString();

    }


    private void applyOperation(List<Double> operands, ArrayList<String> operators) {
        int powIndex = operators.indexOf("^");
        if (powIndex != -1) {
            operators.remove(powIndex);
            operands.set(powIndex, Math.pow(operands.get(powIndex), operands.get(powIndex + 1)));
            operands.remove(powIndex + 1);
            return;
        }

        int mulIndex = operators.indexOf("*");
        int divIndex = operators.indexOf("/");
        if (mulIndex != divIndex) {
            if (divIndex == -1 || (mulIndex < divIndex && mulIndex != -1)) {
                operators.remove(mulIndex);
                operands.set(mulIndex, operands.get(mulIndex) * operands.get(mulIndex + 1));
                operands.remove(mulIndex + 1);
            } else {
                operators.remove(divIndex);
                operands.set(divIndex, operands.get(divIndex) / operands.get(divIndex + 1));
                operands.remove(divIndex + 1);
            }
            return;
        }

        int plusIndex = operators.indexOf("+");
        int minusIndex = operators.indexOf("-");
        if (plusIndex != minusIndex) {
            if (minusIndex == -1 || (plusIndex < divIndex && plusIndex != -1)) {
                operators.remove(plusIndex);
                operands.set(plusIndex, operands.get(plusIndex) + operands.get(plusIndex + 1));
                operands.remove(plusIndex + 1);
            } else {
                operators.remove(minusIndex);
                operands.set(minusIndex, operands.get(minusIndex) - operands.get(minusIndex + 1));
                operands.remove(minusIndex + 1);
            }
            return;
        }
    }

    private void checkIfValid(List<Double> operands, ArrayList<String> operators) {
        if (operands.size() - operators.size() != 1) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }
}
