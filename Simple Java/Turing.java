import java.util.Scanner;
import java.util.Stack;

public class Turing {

	static int[][] state;
	static char[][] symbol;
	static char[][] lr;

	public static void main(String[] args){
		Scanner scr = new Scanner(System.in);

		System.out.print("Enter to number of states: ");
		int n = scr.nextInt();
		scr.nextLine();
		String[] stateNames = new String[n];					//Array of the names of the states

		stateNames = createNames(n,stateNames);					//Method to return name of states

		System.out.print("Enter number of symbols: ");
		int m = scr.nextInt();
		scr.nextLine();
		char[] symbols = new char[m];							//Array of symbols

		symbols = createSymbols(m,symbols);						//Method to return symbols

		state = new int[n][m];
		symbol = new char[n][m];
		lr = new char[n][m];

		createRules(n,m,stateNames,symbols);					//Create Rules of Turing Machine

		printRules(n,m,stateNames,symbols);						//Print set of rules

		System.out.println("");

		System.out.print("Enter string for Turing Machine: ");
		String TM = scr.nextLine();
		Stack<Character> rightStack = new Stack<Character>();
		Stack<Character> leftStack = new Stack<Character>();
		
		leftStack = putInStack(TM);									//Put input in stack
		TM = putInBox(TM);										//Make String look like every char is at a box

		machine(n,m,TM,stateNames,symbols,rightStack,leftStack);						//Calculate and print if string is accepted
	}
	public static String[] createNames(int n, String[] stateNames){
		Scanner scr = new Scanner(System.in);
		for(int i = 0;i<n;i++){
			if(i == 0){
				stateNames[i] = "Start";									//First state is start state
				System.out.println("Starting state is called start.");
			}
			else if(i < n-2){
				System.out.print("What does state" + i + " is called? ");
				stateNames[i] = scr.nextLine();
			}
			else if(i == n-2){
				stateNames[i] = "accept";
				System.out.println("State" + i + " is the accept state");	//Last-but-one is accept state
			}
			else{
				stateNames[i] = "reject";
				System.out.println("State" + i + " is the reject state");	//Last is reject state
			}
		}
		return stateNames;
	}
	public static char[] createSymbols(int m, char[] symbols){
		Scanner scr = new Scanner(System.in);
		for(int i = 0;i<m;i++){
			if(i == 0){
				symbols[i] = '_';
				System.out.println("S" + i + " is the blank symbol");		//First is blank
			}
			else{
				System.out.print("What symbol is s" + i + "? ");
				symbols[i] = scr.next().charAt(0);
			}
		}
		return symbols;
	}
	public static void createRules(int n, int m, String[] stateNames, char[] symbols){
		Scanner scr = new Scanner(System.in);
		for(int i = 0;i<n-2;i++){										//Ask for every state except last two
			System.out.println("State numbers and names:");
			for(int j =0;j<n;j++){
				System.out.println(j + ". " + stateNames[j]);			//Print state names every change of state, for efficiency on input
			}
			for(int k = 0;k<m;k++){
				System.out.println("For state " + stateNames[i] + " and symbol " + symbols[k]);
				System.out.print("What is the next state? Only input number of state ");
				state[i][k] = scr.nextInt();
				if(state[i][k] == n-2 || state[i][k] == n-1){			//If accept or reject state, assume no change and stay in place.
					symbol[i][k] = 'N';
					lr[i][k] = '_';
				}
				else{
					scr.nextLine();
					System.out.print("What should be written on tape?(N for nothing) ");
					symbol[i][k] =scr.next().charAt(0);
					scr.nextLine();
					System.out.print("Does it move Right(input R), Left(input L) or stays in place(input underscore -> _)? ");
					lr[i][k] = scr.next().charAt(0); 
				}
			}
			System.out.println("");
		}
	}
	//Print the rules in Turing machine form
	public static void printRules(int n,int m,String[] stateNames, char[] symbols){
		System.out.println("Rules:");
		for(int i = 0; i<n-2 ; i++){
			for(int k = 0;k<m;k++){
				System.out.println(stateNames[i] + "," + symbols[k] + " -> " + stateNames[state[i][k]] + "," + symbol[i][k] + "," + lr[i][k]);
			}
		}
	}
	//Makes it seem like input is in box
	public static String putInBox(String TM){
		String newString = "" + TM.charAt(0);
		for(int i = 1;i<TM.length();i++){
			newString = newString + "|" + TM.charAt(i);
		}
		return newString;
	}
	//Step by step turing machine for input
	public static void machine(int n,int m,String TM,String[] stateNames,char[] symbols,Stack<Character> rightStack,Stack<Character> leftStack){
		int currentState = 0;
		int index = 0;
		String newString = "";
		boolean done = false;
		
		for(int i = 0;!done;i += 2){
			boolean found = false;
			int k;
			for(k = 0;!found;k++){								//Search for symbol in array
				if(symbols[k] == leftStack.peek()){
					found = true;
					break;
				}
				else if(k>=m){
					System.out.println("Wrong symbol");
					break;
				}
			}
			System.out.println(TM);
			System.out.println("Current State: " + stateNames[currentState]);
			System.out.println("Symbol Read: " + symbols[k]);
			System.out.println("Index of Head: " + index);

			if(symbol[currentState][k] != 'N'){								//If there is something to write into string, put it there.
				newString = newString + symbol[currentState][k];
			}
			else{
				if(leftStack.peek() != '_')
					newString = newString + leftStack.peek();					//Keep string as it is
			}
			if(lr[currentState][k] == 'R'){
				index++;
				rightStack.push(leftStack.pop());
			}
			if(lr[currentState][k] == 'L'){
				index--;
				leftStack.push(rightStack.pop());
			}
			if(lr[currentState][k] == '_'){									//If second blank is seen, stop
				currentState = state[currentState][k];
				done = true;
				break;
			}
			currentState = state[currentState][k];
		}
		System.out.println(currentState);
		System.out.println("Final string: " + newString);
		if(currentState == n-2)
			System.out.println("Accepted.");
		else if(currentState == n-1)
			System.out.println("Rejected.");
		else{
			System.out.println("Wrong Input");
		}
	}
	//Put input into a stack
	public static Stack<Character> putInStack(String input){
		Stack<Character> newStack = new Stack<Character>();
		newStack.push('_');
		for(int i=input.length()-1;i>=0;i--){
			newStack.push(input.charAt(i));
		}
		newStack.push('_');
		return newStack;
	}
}
