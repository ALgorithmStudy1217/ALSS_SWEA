import java.util.*;

/**
 * N일동안 물건의 매매가 예측
 * 하루에 최대 1만 구매
 * 판매는 언제든 가능
 */

public class Solution {

	public static void main(String[] args)  {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc < T + 1; tc++) {
			// N일 입력, 가격 입력
			int day = sc.nextInt();
			
			long[] price = new long [day];
			
			for (int idx = 0; idx < day; idx++) {
				price[idx] = sc.nextInt();
			}
			
			long totalPrice = 0;
			long max_price = price[day-1];

			// 뒤에서 순회
			for (int idx = day-1; idx >= 0; idx--) {
				// 최고 가격을 만나면
				if (max_price <= price[idx]) {					
					// 최고 가격 교체
					max_price = price[idx];
				} else {
					// 최고 가격보다 적은 금액
					// 최고 금액 만큼 더하기
					totalPrice += max_price;
					// 구매 금액 빼기
					totalPrice -= price[idx];
				}		
			}
			
			System.out.println("#" + tc + " " + totalPrice);
		}
	}
}
