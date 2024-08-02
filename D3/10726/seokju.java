package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
// SWEA10726. 이진수 표현 D3

/*
 * 정수 N, M이 주어질 때, M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져  인쓴지 아는지 판별
 * 1 <= N <= 30 0 <= M <= 10^8
 * N개 의비트가 모두 켜져 있다면 ON
 * 아니면 OFF 출력
 * 
 * 36,248 kb
메모리
184 ms
실행시간
1,886
코드길이
풀이시간 10분
시간복잡도 O(N) N-입력받은 비트수

풀이시간 줄이려고 해봤는데
scanner보다 bufferedReader가 훨씬 빠르다
출력도 StringBuilder를 쓰면 더 빠르다
roof 돌면서 bit 옮기는게 pow 계산하는 것보다 빠를지도
 */
public class SWEA10726 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		
		// pow값 미리 계산해놔도 쥐꼬리만큼 빨라짐
		int[] pow = new int[31];
		pow[0] = 1;
		for (int cnt = 1; cnt <= 30; ++cnt) {
			pow[cnt] = pow[cnt-1]*2;
		}

		int totalCase = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			String[] split = br.readLine().split(" ");
			int lastBit = Integer.parseInt(split[0]);
			int integer = Integer.parseInt(split[1]);

			// int의 비트수는 32
			// 그 중 첫번째 비트는 부호를 나타내므로 31비트만 고려
			// N이 30 이하이고 M이 1억 이하이므로 int로 가능
//			boolean allOn = true;
//			// 1부터 N까지 for 루프를 돌면서
//			for (int cnt = 1; cnt <= lastBit; ++cnt) {
//				// 마지막 비트를 확인해본다.
//				// 마지막 비트가 1이라면 홀수이다. -> 실행시간 315ms
//				// & 연산을 통해 확인해보자 -> c랑 다르다.. 잘 모르겠다
//				if ((integer & 1) != integer) {
//					allOn = false;
//					break;
//				}
//				// 다음 비트를 확인하기 위해 비트연산을 해준다
//				// >> 연산자를 통해 다음 비트를 마지막 비트 자리로 옮겨준다.
//				integer = integer >> 1;
//			}
			
			// 마지막 N 비트가 모두 1이다 -> 1을 더하면 모두 0 그러면 2^n으로 나누면 나누어 떨어진다
			integer += 1;
			if (integer % pow[lastBit] == 0) {
				sb.append("#"+testCase+" ON\n");
			} else {
				sb.append("#"+testCase+" OFF\n");
			}
			
			//System.out.println("#" + testCase + " " + (allOn ? "ON" : "OFF"));
		}
		System.out.println(sb.toString());
	}
}
