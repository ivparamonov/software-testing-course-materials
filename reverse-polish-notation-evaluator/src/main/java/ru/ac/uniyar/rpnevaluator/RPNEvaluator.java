package ru.ac.uniyar.rpnevaluator;

import java.util.EmptyStackException;
import java.util.Stack;

public class RPNEvaluator {

    /**
     * The class evaluates an expression in the reverse Polish notation (RPN).
     * Valid binary operators are: +, -, *, and /.
     * All evaluations are done in integers.
     * @param tokens Array of RPN tokens as Strings.
     * @return Result of evaluation.
     * @throws NumberFormatException when a token is not a valid operator sign and cannot
     *                               be converted to integer.
     * @throws EmptyStackException when trying to evaluate an operator on empty stack.
     * @throws IllegalArgumentException when not a single element is left in a stack after evaluation.
     */
    public static int evaluate(String[] tokens) {
		String operators = "+-*/";
		Stack<Integer> stack = new Stack<>();
		for (String t : tokens) {
			if (!operators.contains(t)) {
				stack.push(Integer.valueOf(t));
			} else {
				int a = stack.pop();
				int b = stack.pop();
                stack.push(applyOperator(a, b, t));
            }
		}
		if (stack.size() != 1) {
		    throw new IllegalArgumentException("Not a single element left in a stack after evaluation");
        }
        return stack.pop();
	}

    private static int applyOperator(int a, int b, String t) {
        switch (t) {
        case "+":
            return b + a;
        case "-":
            return b - a;
        case "*":
            return b * a;
        case "/":
            return b / a;
        }
        throw new IllegalArgumentException("Invalid operator");
    }
}
