import java.util.Scanner;

// SWEA1984. 중간 평균값 구하기 D2

/*
 * 10개의 수를 입력 받아, 최대 수와 최소 수를 제외한 나머지의 평균값을 출력하는 프로그램
 * 각 수는 0 이상 10000 이하의 정수
 */

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int totalCase = scanner.nextInt();
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			
			// 입력을 받으면서 sum, max, min 값을 찾는다
			// sum - max - min / 8 을 한 값이 평균
					
			int sum = 0, max = 0, min = 10000;
			for (int index = 0; index < 10; ++index) {
				int num = scanner.nextInt();
				sum += num;
				max = Math.max(max, num);
				min = Math.min(min, num);
			}
			
			// 소수점 첫째자리에서 반올림해야함
			System.out.printf("#%d %d\n", testCase, (int)((sum - max - min) / 8.0 + 0.5));
			
		}
	}
}
