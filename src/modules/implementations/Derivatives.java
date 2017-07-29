/*
 * 
 *  * (c) kiboy5
 *  * 3rd Year BS in Computer Science @ Holy Angel University
 */
package modules.implementations;
import modules.interfaces.DerivativesInterface;
/**
 *
 * @author Jericho
 */
public class Derivatives implements DerivativesInterface{
    
    public String deriveExpression(String expression) {
        String baseInt="";
        double exponent=0;
        String variable= "", sign = "", derivedEquation="";
        boolean isBase = true, decimal = false;
        for(int i = 0; i<expression.length(); i++)
        {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-')
            {
                sign = expression.charAt(i)+"";
            }
            else if (expression.charAt(i) == '^')
            {
                isBase = false;
            }
            else if ((Character.isDigit(expression.charAt(i))||expression.charAt(i)=='.') && isBase)
            {
                if(baseInt.equals("0"))
                    baseInt = expression.charAt(i)+"";
                else
                    baseInt += expression.charAt(i);
            }
            else if (!isBase && Character.isDigit(expression.charAt(i)))
            {
                exponent = Integer.parseInt(expression.charAt(i) + "");
            }
            else if (expression.charAt(i) != '^' && expression.charAt(i) != '+' && expression.charAt(i) != '-')
            {
                variable = expression.charAt(i)+"";
                if(baseInt.equals("0"))
                    baseInt = "1";
                if(expression.length() > i + 2){
                    if (expression.charAt(i+1) != '^')
                        exponent = 1;
                }
                else
                    if(expression.charAt(i) == 'x')
                        exponent = 1;
            }
            if(!baseInt.isEmpty() && exponent != 0 && !variable.equals(""))
            {
                float base = Float.parseFloat(baseInt);
                base *= exponent;
                exponent--;
                derivedEquation += sign + base +
                    ((exponent == 0) ? "" : variable+"" +
                    ((exponent == 1 || exponent == 0) ? "" : "^") +
                    ((exponent == 1 || exponent == 0) ? "" : exponent+""));
                baseInt = "";
                exponent = 0;
                variable = " ";
                isBase = true;
            }
        }
        return derivedEquation;
    }
}
