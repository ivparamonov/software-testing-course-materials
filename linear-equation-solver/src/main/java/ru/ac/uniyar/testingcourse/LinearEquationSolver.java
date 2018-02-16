package ru.ac.uniyar.testingcourse;

/** The class intended to solve linear equations */
public class LinearEquationSolver {
    public static class AnyNumberIsRootException extends RuntimeException {

        public AnyNumberIsRootException() {
            super("Any number is a root");
        }
        
    }    
    
    /** 
     * Solve linear equation a * x = b.
     * If there is no roots, returns <tt>null</tt>.
     * If any number is a root, throws LinearEquationSolver.AnyNumberIsRootException.
     */
    public static Double solve(double a, double b) {
        if (a == 0) {
            if (b == 0) {
                throw new AnyNumberIsRootException();
            } else {
                return null;
            }
        }
        return b / a;
    }
}
