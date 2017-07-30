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
public class ModifiedSecant {
    private double xi;
    private int iterations;
    private double targetApproximate;
    private List<String> xI;
    private List<String> approximateError;
    private List<String> xiplusone;
    private ExpressionEvaluatorInterface evaluate;
    private String expression;
    private int significantDigits;
    final private double delta;
     public ModifiedSecant(ExpressionEvaluatorInterface evaluate, double xi, String expression, int significantDigits){
        this.xi = xi;
        this.significantDigits = significantDigits;
        this.expression = expression;
        xiplusone = new ArrayList<String>();
        approximateError = new ArrayList<String>();
        xI = new ArrayList<String>();
        iterations = 0;
        targetApproximate = 0;
        this.evaluate = evaluate;
        delta = 0.01;
    }
       public void setIteration(int iteration){
        this.iterations = iteration;
        computeModifiedSecant();
    }
    public void setTargetApproximateError(double targetApproximate){
        this.targetApproximate = targetApproximate;
        computeModifiedSecant();
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
     public int getTotalIterations(){
        return xiplusone.size();
    }
    
    private String evaluateFunction(double xi){
        String function = expression.replaceAll("x", "*"+xi);
        return evaluate.roundToSignificantDigits(evaluate.evaluateExpression(function), significantDigits);
    }
    private String getXiPlusOne(double xi){
        double xiplusone = xi - (((delta*xi)*Double.parseDouble(evaluateFunction(xi)))
                /(Double.parseDouble(evaluateFunction(xi+(delta*xi)))-Double.parseDouble(evaluateFunction(xi))));
        return Double.toString(xiplusone);
    }
     private String getApproximateError(double currentXiPlus, double previousXiPlus){
        return evaluate.roundToSignificantDigits(Double.toString(Math.abs(((currentXiPlus-previousXiPlus)/currentXiPlus)*100)), 8);
    } 
     private double replaceXi(double xiplusone){
        return xi = xiplusone;
    }
    private void computeModifiedSecant(){
        boolean control = true;
        for(int iteration=1; control ; iteration++){
            String strXiPlus = getXiPlusOne(xi);
            double xiplusone = Double.parseDouble(strXiPlus);
<<<<<<< HEAD

=======
>>>>>>> a57599d55ae92890b97baebce4b7b66c2bcce0f0
           String approxError = (iteration == 1) ? "999" : getApproximateError(xiplusone, 
                   Double.parseDouble(this.xiplusone.get(this.xiplusone.size()-1)));
           this.xiplusone.add(strXiPlus);
           xI.add(Double.toString(xi));
           replaceXi(xiplusone);
           this.approximateError.add(approxError);
           if(iterations == iteration) control = false;
           if(iteration!=1){
                if (Double.parseDouble(approxError) < targetApproximate) break;   
           }
            
        }
    }
    
}
