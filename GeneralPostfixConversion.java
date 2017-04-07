package Homework6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GeneralPostfixConversion {
	
	/**
	 * Prints all elements of the stack beginning with the stack top element.
	 * @param st
	 * The formal parameter st is a linked list-based stack of strings.
	 */
	public void printStack(LinkedStack<String>st) {
		for (int i=0;i<st.size();i++){
			System.out.print(st.peek()+" ");
			st.pop();
		}
	}
	/**
	 * Converts a Infix to Postfix
	 *@preCondition tokens is not empty 
	 * @param tokens
	 * The formal parameter tokens is an array of tokens of an infix expression.
	 * @return
	 * Returns the corresponding postfix expression as a string.
	 */
	public static String toPostfix(String[] tokens) {
		//Stack Declaration
		LinkedStack<Character> st = new LinkedStack<Character>();
		String[] postfixer =new String[tokens.length];
		String postfix = " ";
		char ch;
		//Array Iteration
		for (int i=0;i<tokens.length;i++){
			//Clear the postfix String after each array increment
			postfix = "";
			//String Iteration
			for (int j=0;j<tokens[i].length();j++){
				//Set value of character at position to variable
				ch = tokens[i].charAt(j);
				//If a space is seen continue (Ignore)
				if (ch==' ') continue;
				//If there is a left parentheses push it to the stack
				else if(ch=='(') {
					st.push(ch);
				}
				/*If there is a right one set the top of the stat to a variaable,and while
				 * the top is not a left parentheses and is also not empty add the top of the
				 * stack to the postfix expression. Pop the stack and set top to be the current top of stack.
				 * 
				 * after while loop (if even reached) pop the stack
				 */
				else if (ch == ')') {
					Character top = st.peek();
					while (!top.equals('(') && !(st.isEmpty())){
						postfix += top.charValue();
						st.pop();
						top = st.peek();
					}
					st.pop();
				}
				/*If the operation is addition or subtraction
				 * and if the stack is empty push the current character
				 * 
				 * else
				 * set the top of the stack to a variable and while the stack is not empty or the top is not a parentheses
				 * pop the stack	
				 * after the while loop push the character to the stack
				 * 
				 */
				else if (ch == '+' || ch == '-') {
					if (st.isEmpty()) {
						st.push(ch);
					}
					else{
						Character top = st.peek();
						while (!(st.isEmpty() || top.equals(new Character('(')) || top.equals(new Character(')')))){
							st.pop();
							postfix += top.charValue();
						}
						st.push(ch);
					}
				}
				/**
				 * If the chararactwer is multiplication or division and if the stack is empty push the character
				 * but if it is not the set the top of the stack to a variale
				 * 	while the top of the stack isnt addition or subtraction and stack isnt empty
				 * pop the stack and add the top of stack to postfix variable
				 * push the character	 
				 * */
				else if (ch == '*' || ch == '/') {
					if (st.isEmpty()) {
						st.push(ch);
					}
					else{
						Character top = st.peek();
						while (!top.equals(new Character('+')) && !top.equals(new Character('-')) && !st.isEmpty() ){
							st.pop();
							postfix += top.charValue();
						}
						st.push(ch);
					}
				}
				// Add all that is left to the postfix string
				else{
					postfix += ch;
				}
			}
			//While the stack isnt empty continue to add to the postfix
				while (!st.isEmpty()){
					Character top = st.peek();
					if(!top.equals(new Character('('))){
						st.pop();
						postfix+= top.charValue();
					}
					//Set final postfix to array at i
					postfixer[i]=postfix;
				}
		}
		//print out all elements but the last one (avoid duplicate printing)
		for (int t =0;t<postfixer.length-1;t++){
			System.out.println(postfixer[t]);}
		//prints last element 
		return postfix	;	
	}
		
	
	public static void main(String[] args) throws IOException {
		//Initialize Counter
			int counter=0;
		
		// Open the file
			FileInputStream fstream = new FileInputStream("C:/CodeRepository/CS 342 Homework/src/Homework6/infixExpressions.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String preLine;

		//Read File Line By Line
			while ((preLine = br.readLine()) != null)   {
		  // Put in Array add to counter
				counter++;
		}

		//Close the input stream
			br.close();
		
		//Create String Tokens List
			String[] tokens = new String[counter];
		
		// Open the file
			fstream = new FileInputStream("C:/CodeRepository/CS 342 Homework/src/Homework6/infixExpressions.txt");
			br = new BufferedReader(new InputStreamReader(fstream));

			int counter2 = 0;
		
		//Read File Line By Line
			while ((preLine = br.readLine()) != null)   {
				  // Put in Array 
				   tokens[counter2] = preLine;
				   counter2++;
				}

		//Close the input stream
			br.close();
			
			
		//
		System.out.println(toPostfix(tokens));
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
