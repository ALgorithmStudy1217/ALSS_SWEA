package SWEA;

import java.util.Scanner;

// SWEA 1859. 백만 장자 프로젝트 D2

/*
 * 미래를 보는 능력으로 사재기
 * 감시가 심해 한 번에 많은 양을 사재기 할 수 없다.
 * N일 동안의 물건의 매매가를 예측하여 알고 있다.
 * 감시망에 걸리지 않기 위해 하루에 최대 1만큼 구입할 수 있다.
 * 판매는 얼마든지 할 수 있다.
 */

public class SWEA1859 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int days = scanner.nextInt();

			int[] value = new int[days];
			for (int day = 0; day < days; ++day) {
				value[day] = scanner.nextInt();
			}

			// 싼값에 사서 비싼값에 팔아야 이득
			// 1. 제일 비싼 날을 찾는다.
			// 2. 비싼 날 전에 모두 매수해서 비싼날에 판다.
			// 3. 판 날 부터 다시 비싼 날을 찾아서 반복!
			// 100만일이 주어지고 매매가가 최대 만원이라
			// 100,000,000,000 오버플로우 주의 long으로 선언하자

			long gain = 0;

			int maxValue = 0;
			
			int maxDay = 0;
			int currentDay = 0;
			// maxDay가 days보다 작은 동안 반복
			while (currentDay < days) {
				// 최대 날짜를 찾는다.
				for (int day = currentDay; day < days; ++day) {
					// 최대값이 갱신이 된다면
					if (maxValue < value[day]) {
						maxDay = day;
						maxValue = value[day];
					}
				}
				// 구한 최대 날짜까지 판다!
				for (int day = currentDay; day < maxDay; ++day) {
					gain += value[maxDay] - value[day];
				}
				// 구한 날짜 다음날부터 다시 시작
				currentDay = maxDay + 1;
				maxValue = 0;
			}
			System.out.println("#"+testCase+" "+gain);
		}
	}
}
