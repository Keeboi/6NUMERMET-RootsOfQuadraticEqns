/*
 * (c) jjjimenez
 * 3rd Year BS in Computer Science @ Holy Angel University
 */
package modules.interfaces;

/**
 *
 * @author Joshua
 */
public interface ExpressionEvaluatorInterface {
    /**
     * Method to evaluate a given expression.
     * @param expression the expression to be evaluated
     * @return returns a string type of the result of evaluation
     */
    public String evaluateExpression(String expression);
    
    /**
     * Method to round off the value into the specified number of significant digits.
     * @param value value to be rounded off
     * @param numOfSignificantDigits number of significant digits to be rounded off
     * @return returns a string type of the result of rounding off.
     */
    public String roundToSignificantDigits(String value, int numOfSignificantDigits);
}
