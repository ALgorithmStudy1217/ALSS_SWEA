import java.util.Scanner;

// SWEA3421. 수제 버거 장인

/*
 * 1 ~ N번 까지 재료
 * 가능한 많은 종류의 버거 만들고 싶지만 서로 어울리지 않는 재료들이 있음
 * i번 재료와 j번 재료가 서로 궁합이 맞지 않는다면 이들을 동시에 포함하는 버거는 x
 * 궁합이 맞지 않는 재료들로 M개 쌍에 대한 정보가 주어졌을 때 만들 수 있는 버거의 종류를 구하는 프로그램
 * ( 정확하게 같은 종류의 재료들을 사용하면 같은 버거로 본다 )
 */

public class Solution {

	public static final byte NOTYET = 3; // 아직 재료 확인을 안함
	public static final byte OUTSIDE = 2; // 재료를 넣지 않음
	public static final byte INSIDE = 1; // 재료를 넣음
	public static final byte OK = 0; // 조합해도 됨
	public static final byte NO = -1; // 조합하면 안됨

	public static int burger = 0;

	// 버거를 조합하는 함수
	// 재료들의 페어를 나타내는 배열, 현재 확인할 재료의 순서, 현재 넣은 재료들의 배열
	public static void combineBurger(byte[][] ingradientPairs, int ingradientsNum, byte[] ingradients) {
		if (ingradientsNum == ingradientPairs.length) {
			// 모든 재료를 넣었다면 종료
			burger++;
			return;
		}

		// 현재 넣은 재료중에 같이 넣으면 안되는 재료가 있는지를 확인한다.
		boolean canCombine = true;
		for (int index = 0; index < ingradientsNum; ++index) {
			if (ingradients[index] == INSIDE) {
				if (ingradientPairs[index][ingradientsNum] == NO ) {
					canCombine = false;
					break;
				}
			}
		}
		if (canCombine) {
			// 같이 넣으면 안되는 재료가 없다면 넣어준다
			ingradients[ingradientsNum] = INSIDE;
			combineBurger(ingradientPairs, ingradientsNum + 1, ingradients);
		}
		// 넣어주지 않는 경우도 고려한다
		ingradients[ingradientsNum] = OUTSIDE;
		combineBurger(ingradientPairs, ingradientsNum + 1, ingradients);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			burger = 0;

			int ingradientNum = scanner.nextInt();
			int pairsNum = scanner.nextInt();

			// 재료 수 1~20
			// 같이 쓰면 안되는 재료의 쌍 수 0~400
			// 같은 쌍이 여러 번 주어질 수 도 있다.
			// [재료수][재료수] 배열을 만든다
			// [i번 재료][j번 재료] == 0이면 페어로 버거를 조합해도 된다는 뜻
			// [i번 재료][j번 재료] == -1이면 페어로 버거를 조합하면 안된다
			// [i번 재료][j번 재료] == 1이면 이미 버거에 넣었다는 뜻

			byte[][] ingradientPairs = new byte[ingradientNum][ingradientNum];

			for (int index = 0; index < pairsNum; ++index) {
				int firstIngradient = scanner.nextInt() - 1;
				int secondIngradient = scanner.nextInt() - 1;
				ingradientPairs[firstIngradient][secondIngradient] = NO;
				ingradientPairs[secondIngradient][firstIngradient] = NO;
			}

			// 버거에 넣은 재료들의 배열
			// i 번째 재료가 들어 갔는지 아닌지를 [i]로 접근해서 값을 확인한다
			byte[] ingradients = new byte[ingradientNum];
			for (int index = 0; index < ingradientNum; ++index) {
				ingradients[index] = NOTYET;
			}

			combineBurger(ingradientPairs, 0, ingradients);
			System.out.println("#" + testCase + " " + burger);
		}
	}

}
