import java.util.Scanner;

// SWEA 1225 암호 생성기

/*
 * 8개의 숫자를 입력 받는다.
 * 첫 번째 숫자를 1 감소한 뒤, 맨 뒤로 보낸다.
 * 다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 당므 3, 4, 5 감소
 * 이와 같은 작업을 한 사이클
 * 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지, 프로그램은 종료 이 때의 8자리의 숫자 값이 암호
 */

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int totalCase = 10;
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int currentCase = scanner.nextInt();
			
			// 1 Cycle
			// a1 a2 a3 a4 a5 a6 a7 a8
			// a6 a7 a8 a1-1 a2-2 a3-3 a4-4 a5-5
			// 2 Cycle
			// a3-3 a4-4 a5-5 a6-1 a7-2 a8-3 a1-5 a2-7
			// 3 Cycle
			// a8-3 a1-5 a2-7 a3-4 a4-6 a5-8 a6-5 a7-7
			// 4 Cycle
			// a5-8 a6-5 a7-7 a8-4 a1-7 a2-10 a3-8 a4-11
			// 5 Cycle
			// a2-10 a3-8 a4-11 a5-9 a6-7 a7-10 a8-8 a1-12
			// 6 Cycle
			// a7-10 a8-8 a1-12 a2-11 a3-10 a4-14 a5-13 a6-12
			// 7 Cycle
			// a4-14 a5-13 a6-12 a7-11 a8-10 a1-15 a2-15 a3-15
			// 8 Cycle
			// a1-15 a2-15 a3-15 a4-15 a5-15 a6-15 a7-15 a8-15
			// 8 cycle을 돌면 모든 값들이 15씩 줄어든다 어케 쉽게 구하는 법 없나 노가다 말고..
			// 근데 아마 1~8번까지 한번 씩 다 들어가서 12345000 빼지는 것 같음
			
			int[] numbers = new int[8];
			for (int index = 0; index < 8; ++index) {
				numbers[index] = scanner.nextInt();
			}
			
			// 8 싸이클 씩 돌려놓고 생각한다.
			// 8 싸이클 두 번을 남기는 이유는 0이 되버리지 않게 하기 위해
			int div = (int)(numbers[0] / 30);
			for (int index = 0; index < 8; ++index) {
				numbers[index] -= div * 30;
			}

			// numbers[7] 마지막 수가 0이 될때까지 사이클을 돌리자
			int cnt = 1;
			while (numbers[7] != 0) {
				if (cnt > 5) {
					cnt = 1;
				}
				int num = numbers[0] - cnt;
				for (int index = 0; index < 7; ++index) {
					numbers[index] = numbers[index + 1];
				}
				if (num < 0) {
					num = 0;
				}
				numbers[7] = num;
				cnt++;
			}
			System.out.print("#"+testCase+" ");
			for (int num : numbers) {
				System.out.print(num + " ");
			}
			System.out.println();
	
		}
	}
}
