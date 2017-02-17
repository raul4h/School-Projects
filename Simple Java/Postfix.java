import java.util.Scanner;
import java.util.Stack;

public class Postfix {

	public static void main(String[] args){

		System.out.println("Enter expression(No blank space): ");
		Scanner scr = new Scanner(System.in);									//Scan regular expression
		String expr = scr.nextLine();

		String postfix = "";
		boolean wrongInput = false;

		Stack<Character> stack = new Stack<Character>();

		int i =0;
		for(i = 0;i < expr.length(); i++){										//Traverse string to transform to postfix
			if(expr.charAt(i) == '+' || expr.charAt(i) == '-'){
				if(stack.isEmpty()){
					stack.push(expr.charAt(i));
				}
				else{
					if(stack.peek() == '(')
						stack.push(expr.charAt(i));								//If top of stack is opening parenthesis push operand
					else{
						postfix += stack.pop();
						if(!stack.isEmpty() && stack.peek() != '(')				//Since + and - are the lowest in the hierarchy of operations
							postfix += stack.pop();								//Always pop twice when scanned, to leave stack without operands
						stack.push(expr.charAt(i));								//Then push operand to stack
					}
				}
			}
			else if(expr.charAt(i) == '/' || expr.charAt(i) == '*'){
				if(stack.isEmpty())
					stack.push(expr.charAt(i));
				else{
					if(stack.peek() == '+' || stack.peek() == '-')				//If there is a + or - push operand to stack to keep hierarchy
						stack.push(expr.charAt(i));
					else{
						if(stack.peek() == '(')
							stack.push(expr.charAt(i));							//If opening parenthesis is at top of stack, push operand scanned
						else{
							postfix += stack.pop();								//Pop what is in stack and then push operand
							stack.push(expr.charAt(i));
						}
					}
				}
			}
			else if(expr.charAt(i) == '(')
				stack.push(expr.charAt(i));										//Push opening parenthesis to know which operands go together
			else if(expr.charAt(i) == ')'){
				char temp = stack.pop();
				while(!stack.isEmpty() && temp != '('){							//Empty stack when parenthesis close.
					postfix += temp;
					temp = stack.pop();
				}
			}
			else if(Character.isDigit(expr.charAt(i)))							//When a digit is scanned, put it in postfix string
				postfix += expr.charAt(i);
			else{
				wrongInput = true;												//If an unknown character is scanned, the input is wrong
				break;
			}
		}

		while(!stack.isEmpty())													//Pop any remaining operands
			postfix += stack.pop();


		if(wrongInput)
			System.out.println("Wrong Input.");
		else
			System.out.println("Postfix: " + postfix);

		Stack<Integer> result = new Stack<Integer>();							//New stack of int to store result

		for(i = 0;i<postfix.length();i++){
			if(Character.isDigit(postfix.charAt(i)))
				result.push(Character.getNumericValue(postfix.charAt(i)));		//Push any digits scanned
			else{
				int num1 = result.pop();
				int num2 = result.pop();
				if(postfix.charAt(i) == '+')
					result.push(num1 + num2);
				else if(postfix.charAt(i) == '-')								//Do operation in postfix and then push result
					result.push(num2 - num1);
				else if(postfix.charAt(i) == '*')
					result.push(num1 * num2);
				else if(postfix.charAt(i) == '/')
					result.push(num2 / num1);
			}
		}
		System.out.println("Result: " + result.pop());							//Pop final result
	}
}
