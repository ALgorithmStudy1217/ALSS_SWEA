import java.util.Scanner;

// SWEA 21131. 행렬정렬

/* 2024-07-25
 * NxN 행렬 A
 * 행렬의 각 원소는 1 이상 NxN 이하의 자연수로 모두 서로 다르다.
 * i 행 j 열의 원소 A[i,j]로 표기
 * 행렬이 정렬되었다는 것은, 모든 1 <= i,j <= N에 대해 A[i,j] = (i-1) x N + j가 성립한다는 것
 * 연산을 원하는 만큼(0회 포함) 반복할 수 있다.
 * 
 * 1이상 N이하의 정수 x를 고른다.
 * 1행 ~ x행, 1열 ~ x열에 해당하는 x*x 크기의 부분행렬을 전치시킨다.
 * 그러면 모든 1 <= i, j <= x에 대해 A[i,j] 는 기존 A[j,i]의 값으로 바뀐다.
 * 이러한 방식으로 정렬 가능한 행렬 A가 주어졌을 때, A를 정렬하기 위해 연산을 최소 몇 회 사용해야 하는지 구하는 프로그램
 * 
 */

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalCase = sc.nextInt();
		
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int size = sc.nextInt(); // 행렬의 크기
			
			int[][] matrix = new int[size][size];
			for (int row = 0; row < size; ++row) {
				for (int col = 0; col < size; ++col) {
					matrix[row][col] = sc.nextInt();
				}
			}
			
			// 1,1 ~ x,x 행렬을 전치 시키면 1 ~ x 사이의 모든 값이 뒤바뀐다.
			// 그러므로 바깥쪽 부터 확인해서 정렬되어 있으면 냅두고 아니라면 전치시킨다.
			// 전치시킬때마다 안쪽도 전치되므로 전치시켜줘야함
			// 하지만 전치시키면 연산이 너무 많아지므로 몇번 돌렸는지를 기억하자.
			// 짝수번 돌렸다면 현재 정렬되어있음 == 정렬되어있음
			// 홀수번 돌렸다면 현재 정렬되어있음 != 정렬되어있음 -> 다시 돌려야함!!
			
			// size - 1 부터 1 까지 0,0은 돌릴 필요가 없당
			// 값 확인은 A[cnt][0] 만 보면 된다!!
			int answer = 0;
			for (int cnt = size - 1; cnt >= 1; --cnt) {
				if (matrix[cnt][0] == cnt * size + 1) { // 맞는 값이면!!
					if (answer % 2 != 0) { // 홀수 번 돌렸다면 틀린 답임
						answer++;
					}
				} else { // 틀린 값이면!!
					if (answer % 2 == 0) { // 짝수 번 돌렸다면 틀린거임
						answer++;
					}
				}
			}
			System.out.println(answer);
		}
	}
}
