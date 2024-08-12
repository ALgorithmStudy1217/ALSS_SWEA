import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 20936. 상자 정렬하기 D4

/*
 * N개의 상자(1~500)와 이 상자들을 넣을 수 있는 N+1개의 칸으로 구성된 보관함
 * 모든 칸은 일렬로 놓여 있으며 각 칸에는 최대 1개의 박스가 들어갈 수 있음.
 * 편의상 각 상자에 1 이상 N 이하의 자연수 번호를 붙이고
 * 보관ㄴ함의 각 칸에 맨 왼쪽 칸부터 순서대로 1,2,...,N+1의 번호
 * 맨 처음에는 N+1번 칸은 비어있고 모든 1 <= i <= N에 대해 i번 칸에 a_i번 상자가 들어있다.
 * 모든 j번 상자가 j번 칸에 들어 있도록.
 * 이러한 방법을 아무거나 하나 구하는 프로그램
 */

public class Solution {

	static class Box {
		int num;
		int index;
		
		Box(int num, int index) {
			this.num = num;
			this.index = index;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalCase = sc.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int size = sc.nextInt();

			// Box 를 담을 배열을 만들자
			// 이 Box의 num 값이 index가 된다.
			// Box의 인덱스 값이 같게 만들어야 함
			Box[] boxes = new Box[size + 2]; // [0]은 없음 [N + 1]은 빈 상자
			
			// 실제 들어있는 값이 뭔지도 저장해 둘 배열을 만들자
			int[] realValue = new int[size + 2];

			for (int index = 1; index <= size; ++index) {
				int num = sc.nextInt();
				boxes[num] = new Box(num, index);
				realValue[index] = num; // 인덱스에 실제 저장되어 있는 값
			}
			
			// 최대 1500번에 끝낼 수 있음 -> 한 숫자당 3번이면 제자리에 놓을 수 있다.
			// 1. 자리를 찾아줄 숫자를 고른다.
			// 2. 그 자리의 있는 숫자를 빈 곳으로 옮긴다 (빈 곳은 K + 1 고정) - 1번
			// 3. 숫자를 자기 자리로 보낸다 - 2번
			// 4. 빈 자리에 다시 숫자를 보낸다 - 3번

			// n과 a_n에 대해서 n이 제자리가 아니면 a_n도 제자리가 아니다.
			// 바꿔서 제자리에 올 수도 있지만, 모든 경우의 수이기 떄문에 상관 없지 않을까
			// 그러면 제자리가 아닌 숫자들의 리스트를 만들고
			// 리스트에서 숫자를 하나씩 빼면서 위의 행동을 반복하면 1500번 안에 가능
			// 이러러면 현재 인덱스도 기억하고 있어야 함. 값, 현재 인덱스를 가지도록 하자
			// -> Box 클래스 값과 현재 인덱스값을 가지고 있음.
			// 1번 상자부터 제자리를 찾아주면 뒷 번호들은 앞 번호와 다시 자리를 바꿀 일이 없다!!
			
			int cnt = 0;
			List<Integer> list = new ArrayList<>();
			for (int index = 1; index <= size; ++index) {
				if (boxes[index].index != index) { // 현재 상자 숫자가 다른 인덱스에 들어 있다면
					// 1. 자리를 찾아줄 숫자를 고른다. 이 경우이네는 현자 상자 숫자가 다른 인덱스.
					int changeIndex = boxes[index].index;
					int changeValue = realValue[index];
					// 2. index에 있는 상자를 빈 상자로 옮긴다
					list.add(index);
					cnt++;
					
					// 3. 상자를 제자리에 놓는다.
					list.add(changeIndex);
					boxes[index].index = index;
					realValue[index] = index;
					cnt++;
					
					// 4. 맨 처음 옮긴 상자를 빈 자리에 놓는다.
					list.add(size + 1); // 항상 빈 곳
					boxes[changeValue].index = changeIndex; 
					realValue[changeIndex] = changeValue;
					cnt++;
				}
			}
			
			System.out.println(cnt);
			for (int box : list) {
				System.out.print(box+" ");
			}
			System.out.println();
		}
	}
}
