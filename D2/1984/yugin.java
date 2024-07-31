import java.io.*;
import java.util.*;

public class D2_1984 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc < test_case+1; tc++) {
			// 리스트 생성
			List<Integer> nums = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine().trim());
			
			// 10개의 값을 입력받아서 리스트에 추가
			for (int idx = 0; idx < 10; idx++) {
				nums.add(Integer.parseInt(st.nextToken()));
			}
			
			// 리스트 정렬
			Collections.sort(nums);
			
			int len = nums.size();
			
			// 리스트 첫번째와 마지막 요소 삭제
			nums.remove(len-1);
			nums.remove(0);
			
			// 리스트 요소들의 합 구하기
			int sum = 0;
			for (int num : nums) {
				sum += num;
			}
			
			// 평균 구하기 -> 0.5를 더해서 반올림을 할 수 있다.
			int avg = (int) ((double) sum / nums.size() + 0.5);

			System.out.println(String.format("#%d %d", tc, avg));

		}

	}

}
