/*
 * (c) jjjimenez
 * 3rd Year BS in Computer Science @ Holy Angel University
 */

package modules.implementations;

import java.util.ArrayList;
import java.util.List;
import modules.interfaces.ExpressionEvaluatorInterface;

public class Secant {
    public Secant(ExpressionEvaluatorInterface evaluator, 
    String expression, String variable, double xi, 
    double xiMinusOne, int significantDigits){
        this.evaluator = evaluator;
        this.expression = expression;
        this.variable = variable;
        this.xi = xi;
        this.xiMinusOne = xiMinusOne;
        this.significantDigits = significantDigits;
        xiHolder = new ArrayList<>();
        xiPlusOneHolder = new ArrayList<>();
        xiMinusOneHolder = new ArrayList<>();
        approximateErrorHolder = new ArrayList<>();
        iterations = 0;
        targetApproxError = 0;
    }
    
    public void setIterations(int iterations){
        this.iterations = iterations;
        computeSecant();
    }
    
    public void setTargetApproxError(double targetApproxError){
        this.targetApproxError = targetApproxError;
        computeSecant();
    }
    
    private void computeSecant(){
        boolean controlLoop = true;
        for(int iteration = 1; controlLoop; iteration++){
            xiPlusOne = Double.parseDouble(getXiPlusOne());
            String strApproxError = (iteration == 1) ? "999" : 
                                    getApproxError(
                                            xiPlusOne, Double.parseDouble(
                                                    this.xiPlusOneHolder.get(this.xiPlusOneHolder.size()-1)
                                            )
                                    ); 
            xiHolder.add(Double.toString(xi));
            xiPlusOneHolder.add(Double.toString(xiPlusOne));
            xiMinusOneHolder.add(Double.toString(xiMinusOne));
            approximateErrorHolder.add(strApproxError);
            
            xiMinusOne = xi;
            xi = xiPlusOne;
            
            if(iteration == iterations){
                break;
            }
            
            else if(Double.parseDouble(strApproxError) < targetApproxError){
                break;
            }
        }
        approximateErrorHolder.set(0, "N/A");
    }
    
    private String getXiPlusOne(){
        String strXiPlusOne = evaluator.roundToSignificantDigits(
                Double.toString(
                        xi - (
                (Double.parseDouble(evaluateFunction(xi)) * (xiMinusOne - xi)) /
                        (Double.parseDouble(evaluateFunction(xiMinusOne)) - 
                        Double.parseDouble(evaluateFunction(xi)))
                             )
                )
                , significantDigits);
        
        return strXiPlusOne;
    }
    
    private String getApproxError(double currentVal, double prevVal){
        return evaluator.roundToSignificantDigits(Double.toString(Math.abs(((currentVal-prevVal)/currentVal)*100)), significantDigits);
    }
    
    private String evaluateFunction(double value){
        String function = expression.replaceAll(variable, "*" + value);
        
        return evaluator.roundToSignificantDigits(evaluator.evaluateExpression(function),
                significantDigits);
    }
    
    public List<String> getXiPlusOnes(){
        return xiPlusOneHolder;
    }
    
    public List<String> getXiMunesOnes(){
        return xiMinusOneHolder;
    }
    
    public List<String> getXis(){
        return xiHolder;
    }
    
    public List<String> getApproximateErrors(){
        return approximateErrorHolder;
    }
    
    public int getIterations(){
        return xiHolder.size();
    }
    
    private ExpressionEvaluatorInterface evaluator;
    private List<String> xiPlusOneHolder;
    private List<String> xiMinusOneHolder;
    private List<String> xiHolder;
    private List<String> approximateErrorHolder;
    private int significantDigits;
    private int iterations;
    private double xiPlusOne;
    private double xi;
    private double xiMinusOne;
    private double targetApproxError;
    private String expression;
    private String variable;
}
