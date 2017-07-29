/*
 * (c) jjjimenez
 * 3rd Year BS in Computer Science @ Holy Angel University
 */

package modules.implementations;

import java.util.ArrayList;
import java.util.List;
import modules.interfaces.ExpressionEvaluatorInterface;

public class Bisection {
    public Bisection(ExpressionEvaluatorInterface evaluator, 
    String expression, String variable, double xL, double xU,
    int significantDigits){
        this.evaluator = evaluator;
        this.expression = expression;
        this.variable = variable;
        this.currentXL = xL;
        this.currentXU = xU;
        this.significantDigits = significantDigits;
        iterations = 0;
        targetApproxError = 0;
        approximateErrorHolder = new ArrayList<String>();
        xrHolder = new ArrayList<String>();
        xlHolder = new ArrayList<String>();
        xuHolder = new ArrayList<String>();
        //init bisection functions
    }
    
    public void setIterations(int iterations){
        this.iterations = iterations;
        computeBisection();
    }
    
    public void setTargetApproxError(double targetApproxError){
        this.targetApproxError = targetApproxError;
        computeBisection();
    }
    
    private void computeBisection(){
        boolean loopControl = true;
        for(int iteration = 1; loopControl; iteration++){
            
            String strXR = getXR(currentXL, currentXU);
            xlHolder.add(Double.toString(currentXL));
            xuHolder.add(Double.toString(currentXU));
            
            double xR = Double.parseDouble(strXR);
            
            String strProdFuncXlXr = Double.toString(Double.parseDouble(evaluateFunction(currentXL)) 
                    * Double.parseDouble(evaluateFunction(xR)));
            
            double prodFuncXlXr = Double.parseDouble(strProdFuncXlXr);
            String strApproxError = (iteration == 1) ? "999" : 
                                    getApproxError(
                                            xR, Double.parseDouble(
                                                    this.xrHolder.get(this.xrHolder.size()-1)
                                            )
                                    );  
            
            double approximateError = Double.parseDouble(strApproxError);
            
            approximateErrorHolder.add(strApproxError);
            //this.xR.add(evaluator.roundToSignificantDigits(strXR, significantDigits));
            this.xrHolder.add(strXR);
            if(!checkConditions(currentXL, currentXU, xR, prodFuncXlXr)){
                loopControl = false;
            }
            
            else if(iteration == iterations){
                break;
            }
 
            else if(approximateError < targetApproxError){
                break;
            }
        }
        approximateErrorHolder.set(0, "N/A");
    }
    
    private String getApproxError(double currentVal, double prevVal){
        return evaluator.roundToSignificantDigits(Double.toString(Math.abs(((currentVal-prevVal)/currentVal)*100)), significantDigits);
    }
    
    private String evaluateFunction(double value){
        String function = expression.replaceAll(variable, "*" + value);
        
        return evaluator.roundToSignificantDigits(evaluator.evaluateExpression(function),
                significantDigits);
    }
    
    private String getXR(double xL, double xU){
        double xR = (xL+xU) / 2;
        
        return evaluator.roundToSignificantDigits(Double.toString(xR), significantDigits);
    }
    
    private boolean checkConditions(double xL, double xU, double xR, double prodFuncXlXr){      
        if(prodFuncXlXr < 0){
                currentXU = xR;
            }

        else if(prodFuncXlXr > 0){
                currentXL = xR;
            }

        else{
                return false;
            }
        
        return true;
    }
    
    public List<String> getApproximateErrors(){
        return approximateErrorHolder;
    }
    
    public List<String> getXrs(){
        return xrHolder;
    }
    
    public List<String> getXls(){
        return xlHolder;
    }
    
    public List<String> getXus(){
        return xuHolder;
    }
    
    public int getTotalIterations(){
        return xrHolder.size();
    }
    
    private ExpressionEvaluatorInterface evaluator;
    private double currentXL;
    private double currentXU;
    private double targetApproxError;
    private List<String> approximateErrorHolder;
    private List<String> xrHolder;
    private List<String> xlHolder;
    private List<String> xuHolder;
    private String expression;
    private String variable;
    private int iterations;
    private int significantDigits;
}
