import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// SWEA 3234. 준환이의 양팔저울

/*
 * N개의 서로 다른 무게를 가진 무게 추와 양팔저울
 * 모든 무게 추를 양팔저울 위에 올리는 순서는 총 N!가지
 * 여기에 더해 각 추를 양팔저울의 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지를 정해야 해서 총 2^N * N!가지의 경우
 * but 오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안됨. 
 */

public class Solution {

	static int branches = 0;
	
	// 총 무게 추, 넣어야 하는 남은 무게 추의 갯수, 왼쪽 저울, 오른쪽 저울
	static void weigh(int[] weights, int remainedWeights, int leftScale, int rightScale) {
		// 남은 무게 추가 없으면 가짓수 추가
		if (remainedWeights == 0) {
			branches++;
			return;
		}
		// 다음에 넣을 무게추를 모두 구한다
		for (int index = 0; index < weights.length; ++index) {
			int weight = weights[index];
			// 이미 사용한 무게추 (무게가 0)이면 패스
			if (weight == 0) continue;
			// 사용 표시
			weights[index] = 0;
			// 왼쪽은 무게 신경 안쓰고 올려도 된다.
			weigh(weights, remainedWeights - 1, leftScale+weight, rightScale);
			// 오른쪽 저울이 왼쪽 무게를 넘지 않으면 넣어준다.
			if (leftScale >= rightScale + weight) {
				weigh(weights, remainedWeights - 1, leftScale, rightScale+weight);
			}
			// 다음 탐색을 위해 사용 표시 되돌림
			weights[index] = weight;
		}
	}
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			branches = 0;

			int num = scanner.nextInt();

			int[] weights = new int[num];
			for (int index = 0; index < num; ++index) {
				weights[index] = scanner.nextInt();
			}

			// 1 <= N <= 9
			// 각 무게추의 무게는 1 이상 999 이하
			// 항상 왼쪽 >= 오른쪽
			// 처음은 항상 왼쪽에 넣어야 한다.
			weigh(weights, num, 0, 0);
			System.out.println("#"+testCase+" "+branches);
		}
	}
}
