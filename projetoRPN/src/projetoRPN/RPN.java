package projetoRPN;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;




public class RPN {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Token> tokens = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		
		 Scanner in =  new Scanner(new FileReader("src/Calc1.stk"));
		 String valor = "";
		 
	        while(in.hasNext()) {
	            Token token;
	            if(in.hasNextInt()){
	            	valor = in.nextLine();
	                token = new Token(TokenType.NUM, valor);
	                tokens.add(token);
	            }else{
	                String operator = in.nextLine();
	                switch (operator) {
	                    case "+":
	                        token = new Token(TokenType.PLUS, operator);
	                        tokens.add(token);
	                        break;
	                    case "-":
	                        token = new Token(TokenType.MINUS, operator);
	                        tokens.add(token);
	                        break;
	                    case "*":
	                        token = new Token(TokenType.STAR, operator);
	                        tokens.add(token);
	                        break;
	                    case "/":
	                        token = new Token(TokenType.SLASH, operator);
	                        tokens.add(token);
	                        break;
	                    default:
	                        //UnexpectedCharacterException error = new UnexpectedCharacterException(operator);
	                        throw new Exception("Unexpected value: " + valor);
	                }
	            }
	        }
	        
	        for(Token token : tokens) {
	            try{
	                stack.push(Integer.parseInt(token.lexeme));
	            }catch (NumberFormatException e){
	                int x = 0, y = 0;
	                y = stack.pop();
	                x = stack.pop();

	                switch (token.type){
	                    case MINUS -> stack.push(x-y);
	                    case PLUS -> stack.push(x+y);
	                    case STAR -> stack.push(x*y);
	                    case SLASH -> stack.push(x/y);
	                    case EOF -> {}
	                    case NUM -> {}
	                    default -> {
	                    	throw new Exception(token.lexeme);	                        
	                    }
	                }
	            }
	        }
	        
	        System.out.println(tokens);
	        System.out.println("Saída: " + stack.pop());
	}

}
