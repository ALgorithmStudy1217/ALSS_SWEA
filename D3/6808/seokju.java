import java.util.Scanner;

//SWEA6808. 규영이와 인영이의 카드게임

/*
 * 1~18 18장의 카드
 * 9장씩 나누고 아홉 라운드 게임
 * 한 장씩 카드를 낸 다음 적힌 수를 비교해서 계산
 * 높은 수가 적힌  사람 합만큼 점수
 * 낮은 수 점수x
 * 총점이 더 높은 사람의 승리
 * 같으면 무승부
 * 규영이는 카드 순서 고정
 * 인영이가 어떻게 내는지에 따라 승패가 정해질 것
 * 규영이가 이기는 경우와 지는 경우가 총 몇가지 인지 구하는 프로그램
 */

/*
 * 23,552 kb
메모리
2,974 ms
실행시간
2,274
코드길이
풀이시간 40분
시간복잡도 O(N!*N) 개오래걸림
 */

public class Solution {
	static final int ROUND = 9;
	static final int CARDS = 18;
	static final int USE = 1;
	static final int NO = 0;
	static final int WIN_POINT = 86;
	static final int GAMES = 362880;
	static int win = 0;
	
	static boolean GyuWin(int[] Gyu, int[] In) {
		// 규영이가 이겼는지 확인하는 함수
		int point = 0;
		for (int round = 0 ; round < ROUND; ++round) {
			if (Gyu[round] > In[round]) {
				point += In[round] + Gyu[round];
			}
		}
		return point >= WIN_POINT;
	}
	
	static void makePermutation(int[] Gyu, int[] In, int[] src, int round) {
		if (round == ROUND) {
			// 9라운드 까지 사용할 카드 순서를 정했다면
			if (GyuWin(Gyu, In)) {
				win++;
			}
			return;
		}
		
		for (int index = 0; index < CARDS; ++index) {
			if (src[index] == NO) {
				src[index] = USE;
				In[round] = index+1;
				makePermutation(Gyu, In, src, round+1);
				src[index] = NO;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int totalCase = scanner.nextInt();
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			win = 0;
			int[] GyuCards = new int[ROUND];
			int[] cards = new int[CARDS]; // 카드를 사용했는지 안했는지 나타냄 1 사용 0 사용안함
			for (int index = 0; index < ROUND; ++index) {
				GyuCards[index] = scanner.nextInt();
				cards[GyuCards[index]-1] = USE;
			}
			
			// 1~18 총 점수는 171 홀수이니까 무승부는 없다 9! = 362880번의 승패가 나눠진다
			// 목표 점수는 86점이긴함 86:85면 이긴다. 86점 이상이면 내가 이긴다
			
			// 단순하게 생각하면 3초 9!? 할만할지도 dfs??
			// 1. 남은 숫자들로 배열을 만든다.
			// 2. 순열을 만든다 -> 총 9!가지 362880개
			// 3. 만든 순열을 비교하여 점수를 구한다.
			
			int[] InCards = new int[ROUND];
			makePermutation(GyuCards, InCards, cards, 0);
			System.out.println("#"+testCase+" "+win+" "+(GAMES-win));
		}
	}
}
