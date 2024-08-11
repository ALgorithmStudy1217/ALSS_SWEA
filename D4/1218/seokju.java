import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

//SWEA1218. 괄호 짝짓기

/*
 * () [] {} <> 로 이루어진 문자열
 * 이 괄호들의 짝이 모두 맞는지 판별
 */

public class Solution {

	static final int VALID = 1;
	static final int INVALID = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int totalCase = 10;
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int length = scanner.nextInt();

			String str = scanner.next();

			// 문자열이 모두 짝이 맞으려면 length가 짝수여야함
			if (length % 2 == 1) {
				System.out.println("#" + testCase + " " + INVALID);
				continue;
			}

			// 엳는 괄호 저장할 스택
			Deque<Character> openStack = new ArrayDeque<Character>();

			boolean isValid = true;
			for (int index = 0; index < length; ++index) {
				char curChar = str.charAt(index);
				if (curChar == '(' || curChar == '[' || curChar == '<' || curChar == '{') {
					openStack.push(curChar);
				} else {
					// 닫는 괄호가 나왔을 경우 openStack에서 꺼내서 확인해본다.
					// 열어야 할 게 없으면 실패
					if (openStack.isEmpty()) {
						isValid = false;
						break;
					}
					char openChar = openStack.pop();
					switch (openChar) {
					case '{':
						if (curChar != '}') {
							isValid = false;
						}
						break;
					case '[':
						if (curChar != ']') {
							isValid = false;
						}
						break;
					case '<':
						if (curChar != '>') {
							isValid = false;
						}
						break;
					default:
						if (curChar != ')') {
							isValid = false;
						}
					}
				}
			}
			
			// for 루프 결과 유효하고, openStack이 비어있다면 유효함
			System.out.println("#" + testCase + " " + ((isValid && openStack.isEmpty()) ? VALID : INVALID));
		}
	}
}
