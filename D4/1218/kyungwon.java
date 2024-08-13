import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//1. 문자열의 길이와 문자열을 입력받는다
//2. 문자열의 길이만큼 반복문을 돌며 여는 괄호는 스택에 넣고 닫는 괄호는 스택에서 제거한다
//2-1. 여는 괄호와 짝인 경우에만 제거하고, 그렇지 않으면 유효하지 않다고 판단한다
//2-2. 스택이 비어있을때, 닫는 괄호가 들어온다면 유효하지 않다고 판단한다
//2-3. 반복문이 종료된 이후, 스택에 값이 남아있으면 유효하지 않다고 판단한다

public class Solution {
	
	static BufferedReader bf;
	static StringTokenizer st;
	static int input;
	static Stack<Character> stack;


	public static void main(String[] args) throws NumberFormatException, IOException {
		
		bf = new BufferedReader(new InputStreamReader(System.in));
		
		 for(int tcase = 1; tcase<=10; tcase++) {
			//1. 문자열의 길이와 문자열을 입력받는다
			 int char_length = Integer.parseInt(bf.readLine().trim());
			 char[] sentence = bf.readLine().toCharArray();
			 
			 stack = new Stack<>();
			 int result = 1;

				//2. 문자열의 길이만큼 반복문을 돌며 여는 괄호는 스택에 넣고 닫는 괄호는 스택에서 제거한다
			 for(int idx = 0; idx < char_length; idx++) {

				 if(sentence[idx] == '(' || sentence[idx] == '{' || sentence[idx] == '[' || sentence[idx] == '<') {
					 stack.push(sentence[idx]);
				 } else {
					//2-1. 여는 괄호와 짝인 경우에만 제거하고, 그렇지 않으면 유효하지 않다고 판단한다
					 if(!stack.isEmpty()) {
						 char pair = stack.peek();
						 if(pair == '(' && sentence[idx] == ')') {
							 stack.pop();
						 } else if(pair == '{' && sentence[idx] == '}') {
							 stack.pop();
						 } else if(pair == '[' && sentence[idx] == ']') {
							 stack.pop();
						 } else if(pair == '<' && sentence[idx] == '>') {
							 stack.pop();
						 } else {
							 result = 0;
							 break;
						 }
 					 } else {
 						//2-2. 스택이 비어있을때, 닫는 괄호가 들어온다면 유효하지 않다고 판단한다
						 result = 0;
						 break;
					 }
				 }
				 
			 }
			 
			//2-3. 반복문이 종료된 이후, 스택에 값이 남아있으면 유효하지 않다고 판단한다
			 if(!stack.isEmpty()) result = 0;
			 
			 System.out.println("#"+tcase + " " + result);
			 
		 }
	}
}
