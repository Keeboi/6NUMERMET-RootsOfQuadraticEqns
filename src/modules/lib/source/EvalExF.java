package modules.lib.source;

import javax.swing.JOptionPane;
import java.util.Stack;

/**
 *
 * Class that contains the methods to convert infix to postfix and to evaluate postfix expressions
 */
public class EvalExF {
        private String postfix = "";//output string
	private int counter = 0;
	private int checker = 0; //flag variable
            /**
         * Method the converts infix expression to postfix expression
         * @param infix an infix expression to be converted to postfix expression
         */
	public void toPostfix(String infix){
	Stack<Character> container = new Stack<Character>();
	String operands = "";
            if(!errorChecker(infix)){// if math error is false
                for(int counter2 = 0; counter2 < infix.length(); counter2++){
		char token = infix.charAt(counter2);//in = input token
			if(isItOperator(token)){ //check if charAt(index) is an operator
				counter = 0;
				if(token == '('){
					container.push(token); //if token is an opening parenthesis, push it to the stack
				}else if(token == ')'){ //if token is closing parenthesis
					if(!container.empty()){
						while(container.peek() != '('){ 
							postfix = postfix + " " + container.pop(); //pop it
							if(container.isEmpty()){ //break the loop if stack is empty
								break;
							}
						}
						if(container.isEmpty()){ //if container is empty after peeking
                                                        JOptionPane.showMessageDialog(null, "Error: unbalanced parenthesis, excess ')' ");
							postfix = "";
							break;
						}
						container.pop();
					}else{//if stack is empty
						JOptionPane.showMessageDialog(null, "Error: unbalanced parenthesis, excess ')' ");
						postfix = "";
						break;
					}	
				}else if(token == '+' || token == '-'){
					
					if(counter2 == 0 || counter2 == infix.length()-1){ //if first character is an operand of +, -
						if(token == '+' || token == '-'){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
					
					if(counter2+1<infix.length() || counter2-1>=0){
						if(isItAllowedInput(infix.charAt(counter2+1)) && isItAllowedInput(infix.charAt(counter2-1)) || infix.charAt(counter2+1) == '(' || infix.charAt(counter2-1) == ')'){
							if(!container.empty()){
								while(validateOperator(container.peek()) && !container.empty()){
									postfix = postfix + " " + container.pop();
									if(container.empty()){
										break;
									}
								}
								container.push(token);
							}else{
								container.push(token);
							}	
						}else if(validateOperator(infix.charAt(counter2+1)) || validateOperator(infix.charAt(counter2-1)) ){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
					
				}else if(token == '%' || token == '/' || token == '*'){
					
					if(counter2 == 0 || counter2 == infix.length()-1){ //if first character is operand %, /, *
						if(token == '%' || token == '/' || token == '*'){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
					
					if(counter2+1<infix.length() || counter2-1>=0){
						if(isItAllowedInput(infix.charAt(counter2+1)) && isItAllowedInput(infix.charAt(counter2-1)) || infix.charAt(counter2+1) == '(' || infix.charAt(counter2-1) == ')'){
							if(!container.empty()){
								while(validateOperator2(container.peek())){
									postfix = postfix + " " + container.pop();
									if(container.empty()){
										break;
									}
								}
								container.push(token);
							}else{
								container.push(token);
							}	
						}else if(validateOperator(infix.charAt(counter2+1)) || validateOperator(infix.charAt(counter2-1)) ){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
					
				}else if(token == '^'){
				
					if(counter2 == 0 || counter2 == infix.length()-1){ //checks if first character is ^
						if(token == '^'){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
					
					if(counter2+1<infix.length() || counter2-1>=0){
						if(isItAllowedInput(infix.charAt(counter2+1)) && isItAllowedInput(infix.charAt(counter2-1)) || infix.charAt(counter2+1) == '(' || infix.charAt(counter2-1) == ')'){
							if(!container.empty()){
								while(validateOperator3(container.peek())){
									postfix = postfix + " " + container.pop();
									if(container.empty()){
										break;
									}
								}
								container.push(token);
							}else{
								container.push(token);
							}	
						}else if(validateOperator(infix.charAt(counter2+1)) || validateOperator(infix.charAt(counter2-1)) ){
							JOptionPane.showMessageDialog(null, "Error: Missing Operand");
							postfix = "";
							break;
						}
					}
				}
			}else if (isItAllowedInput(token)){//operands
				counter = 1;
			}else{//invalid
				JOptionPane.showMessageDialog(null, "Error: Unknown Operator detected.");
				postfix = "";
				break;
			}//end first if
			
			if(counter == 1){// if operand
				operands = operands + Character.toString(token);
				postfix = postfix + Character.toString(token);
				counter = counter - 1;
							
			}else if(counter == 0){// if operator				
				if(token == '(' || token == ')'){
					counter = 0;
				}else{
					
					postfix = postfix + " ";
				}
				operands = "";
			}//end second if
			
			if(counter2 == (infix.length()-1)){ // if the current character is at the end of the string
				while(!container.empty()){
					if(container.peek() != '('){
						postfix = postfix + " " + container.pop(); //pop out all remaining items in the stack
					}else{
						JOptionPane.showMessageDialog(null, "Error: Unbalanced parentheses. Excess '('. ");
						postfix = "";
						break;
					}
				}
			}//end third if
			}//end for

		}else{// if math error is true
			JOptionPane.showMessageDialog(null, "Error: Division by Zero");
			postfix = "";
		}	
	}	
        
        /**
         * Method that checks if character is an operator (,),+,-,*,/,%,^
         * @param operator the character to be tested
         * @return returns true if char is operator; otherwise false
         */
	private boolean isItOperator(char operator){
		switch(operator){
			case '(':
			case ')': //complete set of operators
			case '+':
			case '-':
			case '/':
			case '*':
			case '%':
			case '^':
				return true;
			default://what if hindi allowed yung tinype
				return false;
		}
	}
        
        /**
         * Method that checks if character is a Numeric Input 0,1,2,3,4,5,6,7,8,9 and a decimal point .
         * @param operand the character to be tested
         * @return returns true if char is operand; otherwise false
         */
	private boolean isItAllowedInput(char operand){
		switch(operand){
		case '.':
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}
	}
        /**
         * Method that checks if character is an operator of lowest precedence +, -
         * @param operator the character to be tested
         * @return returns true if char is operator +-; otherwise false
         */
	private boolean validateOperator(char operator){
		switch(operator){ //w/o parethenses and input token is + -
			case '+':
			case '-':
			case '/':
			case '*':
			case '%':
			case '^':
				return true;
			default:
				return false;
		}
	}
        /**
         * Method that checks if character is an operator of mid precedence * / %
         * @param operator the character to be tested
         * @return returns true if char is operator * / %; otherwise false
         */
	private boolean validateOperator2(char operator){
		switch(operator){//if input token is % / *
			case '/':
			case '*':
			case '%':
			case '^':
				return true;
			default:
				return false;
		}
	}
        /**
         * Method that checks if character is an operator of highest precedence ^
         * @param operator the character to be tested
         * @return returns true if char is operator ^; otherwise false
         */
	private boolean validateOperator3(char operator){
		switch(operator){//if input token is ^
			case '^':
				return true;
			default:
				return false;
		}
	}
        /**
         * Method that checks for any errors with the expression
         * @param infix expression to be error checked
         * @return returns true if no errors found; otherwise false
         */
	private boolean errorChecker(String infix){ // for math error
		int out = 0; //controlling variable for checking math error
		
		for(int i = 0; i<infix.length(); i++){
			if(infix.charAt(i) == '/'){
				checker = 1; // detected '/'
			}else{
                          if(checker==1){
                            if(infix.charAt(i) == '0'){ //division by zero
				out = 1;
			    }else if(infix.charAt(i) == '*'){
				if(out == 1){
                                    out = 1;
                                    break;
				}else{
                                    out = 0;
				}
                            }else if(infix.charAt(i) == '1' || infix.charAt(i) == '2' || infix.charAt(i) == '3' 
                                    || infix.charAt(i) == '4' || infix.charAt(i) == '5' 
                                    || infix.charAt(i) == '6' || infix.charAt(i) == '7' 
                                    ||infix.charAt(i) == '8' || infix.charAt(i) == '9'){
						out = 0;
						checker = 0;
                            }
			}
                }
		}
		
		if(out == 1){
			return true;
		}else{
			return false;
		}
		
	}
        /**
         * Method that returns the resulting postfix
         * @return the resulting postfix expression
         */
	private String getPostfix(){
		return postfix;
	}
        /**
         * Method that evaluates the postfix expression
         * @return returns the double value evaluated from the postfix expression
         */
	public double evaluatePostfix(){
		
		Stack<Double> thestack = new Stack<Double>();
		double first = 0;
		double sec = 0;
		double oper = 0;
		double out = 0;
		double inum = 0;
		double a = 0;
		double b = 0;
		int flag = 0; //1=false; 0=true
		int counter3 = 1;
		
		if(postfix != ""){
			for(int i=0; i<postfix.length(); i++){
			char post = postfix.charAt(i); //holds the current character at index i
			if(validateOperator(post)){//if operator
				first = Double.parseDouble(""+thestack.pop()); //concats char to a "" and converts the new string into a double data type
				sec = Double.parseDouble(""+thestack.pop()); //gets the two numeric values to perform operations with
				
				if(post == '+'){
					oper = sec + first;
					thestack.push(oper);
				}else if(post == '-'){
					oper = sec - first;
					thestack.push(oper);
				}else if(post == '/'){
					oper = sec/first;      //switch case for performing the arithmetic operations
					thestack.push(oper);
				}else if(post == '*'){
					oper = first*sec;
					thestack.push(oper);
				}else if(post == '%'){
					oper = sec%first;
					thestack.push(oper);
				}else if(post == '^'){
					oper = Math.pow(sec,first);
					thestack.push(oper);
				}
				
			}else if(post == ' '){
				//wala siyang gagawin lol
			}else{
				if(post == '.'){
					flag = 1;
					if(i-1 > 0){
						if(postfix.charAt(i-1) == ' '){ //checks char before the decimal
							thestack.push(0.0);
						}
					}else if(i-1<0){
						thestack.push(0.0);
					}
				}else{
					thestack.push(Double.parseDouble(""+post));
					//flag = 0;
					if(i-1>=0){
						if(postfix.charAt(i-1) == ' '){
							flag = 0;
							counter3 = 1;
						}else{
							inum = Double.parseDouble(""+post);
							if(flag == 0){// kapag walang point something
								thestack.pop();
								a = thestack.pop();
								thestack.push((a*10)+inum);
							}else if(flag == 1){// kapag may point something
								thestack.pop();
								a = thestack.pop();
								b = 1/(Math.pow(10,counter3));
								thestack.push((inum*b)+a);
								counter3++;
							}
						}
					}//end if(i-1>=0)
				}//end else
			}//end else operand
		}//end for loop
		
		out = thestack.pop();
		}else{// end if(postfix != "")
			out = Math.sqrt(-1); // output NaN 
		}		
		return out;			
	}
        
        public String replaceTrigoFuncs(String expression){
            final double e = 2.71828; //approx
            if(expression.indexOf("sin") != -1){
                expression = expression.replaceAll("sin", "Math.sin");
            }
            
            if(expression.indexOf("cos") != -1){
                expression = expression.replaceAll("cos", "Math.cos");
            }
            
            if(expression.indexOf("tan") != -1){
                expression = expression.replaceAll("tan", "Math.sin");
            }
            
            if(expression.indexOf("e") != -1){
                expression = expression.replaceAll("e", "*"+e);
            }
            return expression;
        }
}
