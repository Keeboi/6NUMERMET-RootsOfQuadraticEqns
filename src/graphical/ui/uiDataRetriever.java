/*
 * 
 *  * (c) kiboy5
 *  * 3rd Year BS in Computer Science @ Holy Angel University
 */
package graphical.ui;
import java.util.List;
import modules.implementations.*;
/**
 *
 * @author User
 */
public class uiDataRetriever {
    public int iteration, type;
    private Bisection bisection;
    private NewtonRaphson newton;
    private ModifiedSecant mSecant;
    private Secant secant;
    public List<String> xu, xl, xr, ea, xi1, ximin1, xi;
    public uiDataRetriever(String exp, String iter, String ea, String xl, String xu){
            bisection = new Bisection(
                    new ExpressionEvaluator(), 
                    exp, 
                    "x", 
                    Double.parseDouble(xl), 
                    Double.parseDouble(xu),
                    8);
            if(!iter.isEmpty())
                bisection.setIterations(Integer.parseInt(iter));
            else
                bisection.setTargetApproxError(Double.parseDouble(ea));
            
            iteration = bisection.getTotalIterations();
            type = 1;
    }
    public uiDataRetriever(String exp, String iter, String ea, String xi, String ximin1, int type){
        secant = new Secant(
                    new ExpressionEvaluator(), 
                    exp, 
                    "x", 
                    Double.parseDouble(xi), 
                    Double.parseDouble(ximin1),
                    8);
            if(!iter.isEmpty())
                secant.setIterations(Integer.parseInt(iter));
            else
                secant.setTargetApproxError(Double.parseDouble(ea));
            
            iteration = secant.getIterations();
            this.type = 3;
    }
    public uiDataRetriever(String exp, String iter, String ea, String xi, int type){
        if(type==2){
            newton = new NewtonRaphson(
                    new ExpressionEvaluator(), 
                    new Derivatives(),
                    Double.parseDouble(xi), 
                    exp, 
                    8);
            if(!iter.equalsIgnoreCase(""))
                newton.setIteration(Integer.parseInt(iter));
            else
                newton.setTargetApproximateError(Double.parseDouble(ea));

            iteration = newton.getTotalIterations();
            this.type = 2;
        }
        else if(type==4){
            mSecant = new ModifiedSecant(
                    new ExpressionEvaluator(),
                    Double.parseDouble(xi), 
                    exp, 
                    8);
            if(!iter.equalsIgnoreCase(""))
                    mSecant.setIteration(Integer.parseInt(iter));
                else
                    mSecant.setTargetApproximateError(Double.parseDouble(ea));

            iteration = mSecant.getTotalIterations();
            this.type = 4;
        }
    }
    public void getData(){
        switch(type){
            case 1:
                xu = bisection.getXus();
                xl = bisection.getXls();
                xr = bisection.getXrs();
                ea = bisection.getApproximateErrors();
                break;
            case 2:
                xi1 = newton.getXiPlusOne();
                xi = newton.getXi();
                ea = newton.getApproximateError();
                
                break;
            case 3:
                xi1 = secant.getXiPlusOnes();
                xi = secant.getXis();
                ximin1 = secant.getXiMunesOnes();
                ea = secant.getApproximateErrors();
                break;
            case 4:
                xi1 = mSecant.getXiPlusOne();
                ea = mSecant.getApproximateError();
                xi= mSecant.getXi();
                break;
        }
        
    }
}
