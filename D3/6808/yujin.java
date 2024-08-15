import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int CARD = 9;
	static int[] K_cardList = new int[CARD];
	static int[] I_cardList = new int[CARD];
	static int[] I_selectedCardList = new int[CARD];
	static boolean[] I_usedCardList = new boolean[CARD];
	
	static int winCount = 0;
	static int loseCount = 0;

	public static void setCardInfo() throws IOException {
		// 2. 규영이 카드 정보를 입력 받는다.
		st = new StringTokenizer(br.readLine().trim());

		// 인영이가 가진 카드 정보를 알기 위한 배열
		boolean[] allCardList = new boolean[CARD * 2];

		for (int cardIdx = 0; cardIdx < CARD; cardIdx++) {
			int cardNum = Integer.parseInt(st.nextToken());

			K_cardList[cardIdx] = cardNum;
			
			// 규영이가 가진 카드를 true해준다.
			allCardList[cardNum - 1] = true;
		}

		// 3. 인영이가 가진 카드 정보를 찾는다.
		int I_cardIdx = 0;
		for (int cardIdx = 0; cardIdx < allCardList.length; cardIdx++) {
			if (!allCardList[cardIdx]) {
				I_cardList[I_cardIdx++] = cardIdx+1;
			}
		}

	}
	
	public static void battle() {
		// 5-1. 앞 카드부터 차례로 비교한다.
		int K_score = 0;
		int I_score = 0;
		
		for (int cardIdx = 0; cardIdx < CARD; cardIdx++) {
			// 5-2. 이기면 점수에 더한다.
			if (K_cardList[cardIdx] > I_selectedCardList[cardIdx]) {
				K_score += K_cardList[cardIdx] + I_selectedCardList[cardIdx];
			} else {
				I_score += K_cardList[cardIdx] + I_selectedCardList[cardIdx];
			}
		}
		
		// 5-3. 최종 점수에 대해 결판을 낸다.
		// 6. 이기는 경우와 지는 경우를 저장한다.
		if (K_score > I_score) {
			winCount++;
		} else if (K_score < I_score) {
			loseCount++;
		}
		
	}
	
	public static void makeCardList(int selectIdx) {
		if (selectIdx == CARD) {
			// 5. 해당 순열로 규영이와 대결을 한다.
			
			battle();
			
			return;
		}
		
		for (int cardIdx = 0; cardIdx < CARD; cardIdx++) {
			if (I_usedCardList[cardIdx]) continue;
			
			I_usedCardList[cardIdx] = true;
			I_selectedCardList[selectIdx] = I_cardList[cardIdx];
			
			makeCardList(selectIdx + 1);
			
			I_usedCardList[cardIdx] = false;
		}
	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 1. 테스트 케이스 개수를 입력받는다.
		int testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			setCardInfo();
			
			makeCardList(0);
			
			sb.append("#").append(tc).append(" ").append(winCount).append(" ").append(loseCount).append("\n");

			// 점수 초기화
			winCount = 0;
			loseCount = 0;
		}
		
		System.out.println(sb);

	}

}
