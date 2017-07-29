package modules.implementations;

import modules.interfaces.ExpressionEvaluatorInterface;
import com.udojava.evalex.Expression;

public class ExpressionEvaluator implements ExpressionEvaluatorInterface
{

    @Override
    public String evaluateExpression(String expression) {
        return new Expression(expression).eval().toString();
    }

    /**
     * DEPRECATED
     * @param value
     * @param numOfSignificantDigits
     * @return 
     */
    @Override
    public String roundToSignificantDigits(String value, int numOfSignificantDigits) {
        return value;
    }
    
    /**
     * DEPRECATED
     * @param exp
     * @param sigFig
     * @return 
     */
    private String getSigFig(double exp, int sigFig){
        int length=0;
        String strExp = exp+"";
        boolean fig = false;
            for(int i = 0; i < strExp.length(); i++){
                if((strExp.charAt(i)=='0' || strExp.charAt(i)=='.') && !fig)
                    continue;
                else if(strExp.charAt(i)!='.' && strExp.charAt(i)!='-'){
                    length++;
                    fig = true;
                }
            }
            if((exp+"").length()<=sigFig){
                String ans = strExp;
                for(int i =0; i<sigFig-length; i++)
                    ans +="0";
                return ans;
            }
            else{
                for(char e : strExp.toCharArray()){
                    if(e=='E')
                        strExp = convertNotation(strExp);
                }
                double shortest = Double.parseDouble(strExp.substring(0,strExp.length()+sigFig-length)+"");
                return shortest+"";
        }
    }
    
    /**DEPRECATED
     * 
     * @param notation
     * @return 
     */
    private String convertNotation(String notation){
        int exp = 0;
        int base = 0;
        boolean exponent = false;
        for(int i = 0; i<notation.length(); i++){
            if(Character.isDigit(notation.charAt(i))&&!exponent)
                base = base * 10 + Integer.parseInt(notation.charAt(i)+"");
            if(notation.charAt(i)=='E')
                exponent = true;
            else if(exponent && notation.charAt(i)!='-')
                exp = exp * 10 + Integer.parseInt(notation.charAt(i)+"");
        }
        String strAns=base+"";
        for(int i = 0; i < exp; i++){
            if(exp-i == 1)
                strAns = "."+strAns;
            strAns = "0"+strAns;
        }
        return strAns;
    }
   
}
