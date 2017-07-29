/*
 * (c) jjjimenez
 * 3rd Year BS in Computer Science @ Holy Angel University
 */

package prototypes;

import java.util.List;
import modules.implementations.Bisection;
import modules.implementations.ExpressionEvaluator;

public class BisectionPrototype {
    public static void main(String[] args) {
        String expression = "2x^3-11.7x^2+17.7x-5";
        double xl = 2;
        double xu = 4;
        Bisection bisection = new Bisection(new ExpressionEvaluator(), expression, "x", xl, xu, 8);
        bisection.setIterations(5);
        
        
        List<String> x = bisection.getApproximateErrors();
        List<String> y = bisection.getXrs();
        int size = bisection.getTotalIterations();
 
        for(int index=0; index<size; index++){
            System.out.printf("%d. Xr: %s Ea: %s\n", index+1, y.get(index), x.get(index));
        }
    }
}
