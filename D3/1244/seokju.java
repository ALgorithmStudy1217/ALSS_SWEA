import java.util.Arrays;
import java.util.Scanner;

// SWEA1244. 최대 상금 D3

/*
 * 숫자판들 중에 두 개를 선택해서 정해진 횟수만큼 서로의 자리를 위치를 교환할 수 있다.
 * 반드시 횟수만큼 교환
 * 중복 교환 가능
 * 가장 큰 금액 계산
 * 
 * 24,008 kb
메모리
200 ms
실행시간
3,876
코드길이
Pass
결과

그리디로 접근하려고 하니까 반례 때문에 실패함
이게 6자리에서 2개 바꾸는 지라 6P2, 최대 10자리라 ^10해서 엄청 커서 절대 안돌아갈 것 같은데
n자리의 수는 최대 n-1번 자리를 바꾸면 무조건 정렬된 상태이기 때문에
n-1로 끊어줌 

그래서 dfs 로 n-1번 구했고
또 정렬이 됐다면 중복된 값이 있으면 단순히 둘을 자리 바꾸면 되니까 정렬된게 정답
아니라면 남은 횟수가 짝수면 정렬된것
홀수라면 뒤 2자리 값을 바꿔준게 정답

풀이시간 - 2시간
시간복잡도 (n^n-1)? 
n이 최대 6이라서 가능했다고 생각
 */

public class Solution {
	static int minCount;
	static int[] answerNumber;

	static boolean arrayCompare(int[] num1, int[] num2) {
		for (int index = 0; index < num1.length; ++index) {
			if (num1[index] < num2[index]) {
				return true;
			} else if (num1[index] > num2[index]) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
	
	static void copyArray(int[] src, int[] dest) {
		for (int index = 0; index < src.length; ++index) {
			dest[index] = src[index];
		}
	}

	// input 숫자 배열, max 배열, 현재 바꾼 횟수, 바꿀 수 있는 횟수, 같은 값이 있는지
	static void findMax(int[] number, int[] maxNumber, int cnt, int maxCnt, boolean same) {
			
		if (cnt == maxCnt) {
			// 다 바꿨으면 종료
			if (arrayCompare(answerNumber, number)) {
				copyArray(number, answerNumber);
			}
			return;
		}

		// 정렬되어 있는지 확인
		boolean sorted = true;
		for (int index = 0; index < number.length; ++index) {
			if (number[index] != maxNumber[index]) {
				sorted = false;
				break;
			}
		}
		if (sorted) {
			if (same) {
				copyArray(maxNumber, answerNumber);
			} else {
				if ((maxCnt - cnt) % 2 == 0) {
					// 남은 횟수가 짝수라면 끝
					copyArray(maxNumber, answerNumber);
				} else {
					// 남은 횟수가 홀수라면 마지막 두자리를 바꿔준다.
					int temp = number[number.length - 1];
					number[number.length - 1] = number[number.length - 2];
					number[number.length - 2] = temp;
					if (arrayCompare(answerNumber, number)) {
						copyArray(number, answerNumber);
					}
				}
			}

			return;
		}
		
		// 배열의 길이 - 1만큼 탐색했다면 탐색 종료
		if (cnt == number.length-1) {
			return;
		}
		
		for (int index = 0; index < number.length; ++index) {
			for (int nextIndex = index + 1; nextIndex < number.length; ++nextIndex) {
				int temp = number[index];
				number[index] = number[nextIndex];
				number[nextIndex] = temp;
				findMax(number, maxNumber, cnt + 1, maxCnt, same);
				number[nextIndex] = number[index];
				number[index] = temp;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int inputNum = scanner.nextInt();
			int inputCnt = scanner.nextInt();

			// 자릿수 구하기
			int length = (int) (Math.log10(inputNum) + 1);
			answerNumber = new int[length];
			minCount = 10;
			
			// 자릿수만큼 Number의 배열 만들기
			// Number는 자리 값과 현재 몇번째 자릿수인지를 저장
			// 0~9 까지의 숫자 중복 확인
			int[] sameValue = new int[10];
			int[] number = new int[length];
			int[] maxNumber = new int[length];
			for (int index = length - 1; index >= 0; --index) {
				number[index] = maxNumber[index] = inputNum % 10;
				sameValue[inputNum % 10]++;
				inputNum /= 10;
			}

			// 내림차순 정렬로 최대값 배열 구하기
			for (int index = 0; index < length - 1; ++index) {
				for (int nextIndex = index + 1; nextIndex < length; ++nextIndex) {
					if (maxNumber[index] < maxNumber[nextIndex]) {
						int temp = maxNumber[index];
						maxNumber[index] = maxNumber[nextIndex];
						maxNumber[nextIndex] = temp;
					}
				}
			}

			// 이렇게 되면 numbers는 value에 자기의 값
			// index에 현재 위치가 들어가 있다.
			// numbers[index].index 값이 index와 다르다면
			// 위치가 다르다는 것

			boolean same = false;
			for (int value : sameValue) {
				if (value > 1) {
					same = true;
				}
			}
			int cnt = 0;
			findMax(number, maxNumber, cnt, inputCnt, same);
			System.out.print("#"+testCase+" ");
			for (int index = 0; index <length; ++index) {
				System.out.print(answerNumber[index]);
			}
			System.out.println();
		}
	}
}
