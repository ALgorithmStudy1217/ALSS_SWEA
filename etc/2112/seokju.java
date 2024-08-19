import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// SWEA 2112. [모의 SW 역량테스트] 보호 필름

/*
 * 막을 D장 쌓아서 제작
 * 막은 동일한 크기를 가진 바 모양의 셀들이 가로 방향으로 W개 붙여서 만들어짐.
 * 두께 D, 가로 크기 W의 보호 필름
 * 각 셀들은 특성 A 또는 B를 가지고 있다.
 * 성능은 셀들의 특성이 어떻게 배치됨에 따라 결정
 * 성능을 검사하기 위해 합격기준 K
 * 단면의 세로 방향으로 가해지므로, 세로 방향 셀들의 특성이 중요
 * 단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능검사 통과
 * 성능검사에 통과하기 위해서 약품 사용
 * 약품은 막 별로 투입, 이 경우 투입하는 막의 모든 셀들은 하나의 특성으로 변경
 * 약품 투입 횟수를 최소로 하여 성능검사를 통과할 수 있는 방법을 찾고, 약품 투입 횟수를 출력하라.
 * 약품 투입x 검사 통과 경우 0 출력
 * 
 * 시간 제한 5초, D는 3이상 13이하의 정수 W는 1이상 20이하의 정수 K는 1이상 D 이하의 정수 셀특성 A,B 두가지
 * 
 * 첫 줄에 총 테스트 케이스 T
 * 두 번째 줄부터 T개의 테스트 케이스 , D W K
 * D줄에 보호 필름 단면의 정보, 각 줄에는 셀들의 특성 W (특성 A는 0, 특성 B는 1)
 * 
 * 1. 합격 기준 K에 맞는지 확인
 *  1-1. 맞다면 0 출력
 * 2. 합격 기준에 맞지 않는다면 약품을 투여할 막을 고른다.
 * 3. A 약품을 투여
 *  3-1. 합격 기준에 맞는 지 확인
 *  3-2. 다음 투여할 막 고르기
 * 4. B 약품을 투여
 *  4-1. 합격 기준에 맞는 지 확인
 *  4-2. 다음 투여할 막 고르기
 * 4. 투여한 막을 취소함
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int depth;
	static int width;
	static int criteria;
	
	static int[][] film;
	
	static final int A = 0;
	static final int B = 1;
	static final int NONE = -1;
	
	static int answer;
	
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		depth  = Integer.parseInt(st.nextToken());
		width  = Integer.parseInt(st.nextToken());
		criteria  = Integer.parseInt(st.nextToken());
		
		answer = depth;
		
		film = new int[depth][width];
		for (int depthIndex= 0; depthIndex < depth; ++depthIndex) {
			st = new StringTokenizer(br.readLine().trim());
			for (int widthIndex = 0; widthIndex < width; ++widthIndex) {
				film[depthIndex][widthIndex] = Integer.parseInt(st.nextToken());
			}
		}
		
		depthUsed = new int[depth];
		for (int index = 0; index < depth; ++index) {
			depthUsed[index] = NONE;
		}
	}
	
	public static int getSpec(int depthIndex, int widthIndex) {
		if (depthUsed[depthIndex] == A) {
			return A;
		} else if (depthUsed[depthIndex] == B) {
			return B;
		} else {
			return film[depthIndex][widthIndex];
		}
	}
	
	// 합격 기준을 통과했는지 확인
	public static boolean checkCriteria() {
		for (int widthIndex = 0; widthIndex < width; ++widthIndex) {
			int sameCnt = 1;
			boolean passCriteria = false;
			int currentSpec = getSpec(0, widthIndex);

			for (int depthIndex = 1; depthIndex < depth; ++depthIndex) {
				int nextSpec = getSpec(depthIndex, widthIndex);
				if (currentSpec == nextSpec) {
					sameCnt++;
				} else {
					if (sameCnt >= criteria) {
						passCriteria = true;
						break;
					}
					currentSpec = nextSpec;
					sameCnt = 1;
				}
			}
			if (sameCnt >= criteria) {
				passCriteria = true;
			}
			if (passCriteria) {
				continue;
			}
			return false;
		}
		return true;
	}
	
	static int[] depthUsed; 
	
	// depthIndex 약품 투여해볼 depth, 약품 투여 횟수
	public static void admin(int depthIndex, int count) {
		// 현재 찾은 최소 투여값 보다 더 투여했다면 종료
		if (count >= answer) {
			return;
		}
		
		// 현재 필름이 합격기준을 만족하는 지 확인
		if (checkCriteria()) {
			// 만족 한다면 최솟값을 정답으로
			answer = Math.min(answer,  count);
			return;
		}
		
		// 모든 막 투여를 했다면 종료
		if (depthIndex == depth) {
			return;
		}
		
		// A 약품 투여
		depthUsed[depthIndex] = A;
		admin(depthIndex + 1, count + 1);
		
		// B 약품 투여
		depthUsed[depthIndex] = B;
		admin(depthIndex + 1, count + 1);
		
		// 해담 막에 투여 x
		depthUsed[depthIndex] = NONE;
		admin(depthIndex + 1, count);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int totalCase = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			init();
			admin(0, 0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
