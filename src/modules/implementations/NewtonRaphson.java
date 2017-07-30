/*
 * *(c) mekkiharu
 * * 3rd Year BS in Computer Science @ Holy Angel University
*/

package modules.implementations;
/**
 *
 * @author Saionji
 */
import java.util.ArrayList;
import java.util.List;
import modules.interfaces.*;
public class NewtonRaphson {
    private double xi;
    private int iterations;
    private double targetApproximate;
    private List<String> approximateError;
    private List<String> xiplusone;
    private List<String> xI;
    private ExpressionEvaluatorInterface evaluate;
    private DerivativesInterface derive;
    private String expression;
    private String derivedFunction;
    private int significantDigits;
    
    public NewtonRaphson(ExpressionEvaluatorInterface evaluate,DerivativesInterface derive, double xi, String expression, int significantDigits){
        this.xi = xi;
        this.significantDigits = significantDigits;
        this.expression = expression;
        xiplusone = new ArrayList<String>();
        approximateError = new ArrayList<String>();
        xI = new ArrayList<String>();
        iterations = 0;
        targetApproximate = 0;
        this.evaluate = evaluate;
        this.derive = derive;
    }
    
    public void setIteration(int iteration){
        this.iterations = iteration;
        computeNewton();
    }
    public void setTargetApproximateError(double targetApproximate){
        this.targetApproximate = targetApproximate;
        computeNewton();
    }
    
    public List<String> getXiPlusOne(){
        return xiplusone;
    }
    public List<String> getApproximateError(){
        return approximateError;
    }
    public List<String> getXi(){
        return xI;
    }
    
    private String evaluateFunction(double xi){
        String function = expression.replaceAll("x", "*"+xi);
        return evaluate.roundToSignificantDigits(evaluate.evaluateExpression(function), significantDigits);
    }
    private String evaluateDerivedFunction(double xi){
        String derived = derive.deriveExpression(expression);
        derivedFunction = derived.replaceAll("x", "*"+xi);
        return evaluate.roundToSignificantDigits(evaluate.evaluateExpression(derivedFunction), significantDigits);
    }
    private String getXiPlusOne(double xi){
        double xiplusone = xi - (Double.parseDouble(evaluateFunction(xi))/Double.parseDouble(evaluateDerivedFunction(xi)));
        return evaluate.roundToSignificantDigits(Double.toString(xiplusone), 8);
    }
    private String getApproximateError(double currentXiPlus, double previousXiPlus){
        return evaluate.roundToSignificantDigits(Double.toString(Math.abs(((currentXiPlus-previousXiPlus)/currentXiPlus)*100)), 8);
    } 
    public int getTotalIterations(){
        return xiplusone.size();
    }
    private void replaceXi(double xiplusone){
         xi = xiplusone;
    }
    
    private void computeNewton(){
        boolean control = true;
        for(int iteration = 1; control ; iteration++){
            
           String strXiPlus = getXiPlusOne(xi);
           double xiplusone = Double.parseDouble(strXiPlus);
           String approxError = (iteration == 1) ? "N/A" : getApproximateError(xiplusone, 
                   Double.parseDouble(this.xiplusone.get(this.xiplusone.size()-1)));   
           this.xiplusone.add(strXiPlus);
           xI.add(Double.toString(xi));
           replaceXi(xiplusone);
           this.approximateError.add(approxError);
           if(iterations == iteration) control = false;
           else if(iteration != 1){
             if (Double.parseDouble(approxError) < targetApproximate) break;   
           }
         
        }

    
    }
   
    
    
}
