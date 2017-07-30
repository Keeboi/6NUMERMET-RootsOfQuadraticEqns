/*
 * (c) jjjimenez
 * 3rd Year BS in Computer Science @ Holy Angel University
 */

package prototypes;

import java.util.List;
import modules.implementations.Bisection;
import modules.implementations.ExpressionEvaluator;
import modules.interfaces.DerivativesInterface;

public class BisectionPrototype implements DerivativesInterface{
    public static void main(String[] args) {
           String expression = "2x^3-11.7x^2+17.7x-5";
           float stepSize = 1;
           float x = 2;
           float a, b, c, d;
           a = x+(2*stepSize);
           b= x+stepSize;
           c = x-stepSize;
           d = x-(2*stepSize);
           
           float derivative = (-(evaluateFunction(expression, "x", a)) + 
           (8*evaluateFunction(expression, "x", b)) 
                   - (8*evaluateFunction(expression, "x", c))
                   + evaluateFunction(expression, "x", d))/12*stepSize;
           System.out.println(derivative);

    }
    
    private static float evaluateFunction(String expression, String variable, double value){
        String function = expression.replaceAll(variable, "*" + value);
        return Float.parseFloat(new ExpressionEvaluator().evaluateExpression(function));
    }

    @Override
    public String deriveExpression(String expression) {
        return "";
    }
}
