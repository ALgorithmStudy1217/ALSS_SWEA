import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// SWEA 5215. 햄버거 다이어트 D3

/*
 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지 않는 해매버거 주문 하기
 * 재료는 미리 만들어서 준비해놓기 때문에 잘라서 조합x 준비해놓은 재료를 그대로 사용
 * 햄버거의 재료에 대한 점수와 가게에서 재공하는 재료에 대한 칼로리가 주어졌을 때,
 * 정해진 칼로리 이하의 조합 중 가장 선호하는 햄버거를 조합해주는 프로그램
 * 
 * 선호도는 조합된 재료들의 맛에 대한 점수의 합
 * 같은 재료를 여러 번 사용할 수 없음
 */

public class Solution {
	
	static int maxScore;
	
	// parameter - 재료 리스트, 현재 인덱스, 칼로리 수 합, 선호도 점수 합, 재료 총 갯수(종료 조건 확인용), 제한 칼로리(종료 확인용)
	static void combineBurger(List<Ingredient> list, int curIndex, int kcalSum, int scoreSum, int ingredients, int limitKcal) {
		// kcal 제한 넘으면 탐색 종료
		if (kcalSum > limitKcal) {
			return;
		}
		// 종료 조건
		if (curIndex == ingredients) {
			maxScore = maxScore < scoreSum ? scoreSum : maxScore;
			return;
		}
		// 현재 재료를 넣고 dfs
		combineBurger(list, curIndex+1, kcalSum + list.get(curIndex).kcal, scoreSum + list.get(curIndex).score, ingredients, limitKcal);
		// 현재 재료를 안넣고 dfs
		combineBurger(list, curIndex+1, kcalSum, scoreSum, ingredients, limitKcal);
	}
	
	static class Ingredient {
		int score;
		int kcal;

		public Ingredient(int score, int kcal) {
			this.score = score;
			this.kcal = kcal;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalCase = sc.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			maxScore = 0; // 정답 초기화
			
			int ingredients = sc.nextInt(); // 재료의 수 1<= <= 20
			int limitKcal = sc.nextInt(); // 제한 칼로리 1 <= <= 10^4

			List<Ingredient> ingredientList = new ArrayList<>();
			for (int cnt = 0; cnt < ingredients; ++cnt) {
				int score = sc.nextInt();
				int kcal = sc.nextInt();
				ingredientList.add(new Ingredient(score, kcal));
			}
			
			// 햄버거의 재료는 최대 20개
			// 20개의 재료를 넣을 지 말 지의 조합은 2^20 약 100만개
			// 모든 조합중에서 점수가 가장 높고
			// 칼로리는 제한 칼로리를 넘으면 더이상 탐색 진행 x
			combineBurger(ingredientList, 0, 0, 0, ingredients, limitKcal);
			
			System.out.println("#"+testCase+" "+maxScore);
		}
	}
}
