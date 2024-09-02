import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 출구는 무조건 두 개가 나온다.
 * 
 * 최대 10 명의 사람이 출구로 나갈 수 있는 경우의 수 모두 확인
 * 
 * 출구는 최대 3명 까지만 이용할 수 있다.
 * 
 * ## 구현 방식
 * 
 * #1 사람이 한 출구로 나갈 수 있는 모든 경우의 수 구하기
 * 
 * #2 포함되지 않은 사람은 두 번째 출구로 내보내기
 * 
 * #3 모든 조합이 완성 되었을 때 출구와 가까운 사람 먼저 내보내기
 * 	3-1 PriorityQueue 활용
 * 
 * #4 모든 사람이 나갔을 때의 최소 시간 확인 후 갱신하기
 */

public class Solution {
	
	static Person[] peopleArray = new Person[10];
	static int personNum;
	
	static Exit[] exits = new Exit[2];
	static int exitsNum;
	
	// 1번 출구로 나가는 사람들의 모든 경우의 수
	static int allCount;
	
	static int minTime;
	
	// 1번과 2번 출구로 나갈 사람들 줄세우기
	static PriorityQueue<Integer> firstExit = new PriorityQueue<>();
	static PriorityQueue<Integer> secExit = new PriorityQueue<>();
	
	// Integer로 선언하여 각각의 PriorityQueue에 값이 없는 경우 null을 받을 수 있게 한다.
	static Integer[] threePeople = new Integer[3];
	
	static final int first = 0;
	static final int sec = 1;
	static final int trd = 2;
	
	
	// 출구와의 거리를 계산하기 위한 row, col 저장
	static class Person{
		int row, col;

		public Person(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	
	// 거리를 계산하기 위한 row, col 과 계단의 깊이 저장
	static class Exit{
		int row, col, exitLen;
		
		public Exit(int row, int col, int exitLen) {
			this.row = row;
			this.col = col;
			this.exitLen = exitLen;
		}
	}
	
	
	// 출구로 나가는 모든 경우를 구한 후 걸리는 시간 도출
	// #1 사람이 한 출구로 나갈 수 있는 모든 경우의 수 구하기
	static void combinations() {
		
		// combination의 2진수 중 1이 1번 출구로 나가는 사람들이다.
		for (int combination=0; combination < allCount; combination++) {
			for (int personIndex=0; personIndex < personNum; personIndex++) {
				
				// combination에 없다는 의미이므로 2번 출구로 보낸다
				// #2 포함되지 않은 사람은 두 번째 출구로 내보내기
				if ((combination & 1 << personIndex) == 0) {
					
					secExit.add(Math.abs(peopleArray[personIndex].col-exits[1].col)
							+ Math.abs(peopleArray[personIndex].row-exits[1].row));
				}
				
				// 1번 출구로 보낸다
				else {
					firstExit.add(Math.abs(peopleArray[personIndex].col-exits[0].col)
							+ Math.abs(peopleArray[personIndex].row-exits[0].row));
				}
			}
			
			// 조합에 맞게 사람 배분 후 goExits 호출
			goExits();
		}
		
		
	}
	
	
	// #3 모든 조합이 완성 되었을 때 출구와 가까운 사람 먼저 내보내기
	static void goExits() {
		
		Integer nextPerson = null;
		
		// 두 개의 출구 중 모두 빠져나갈 때 까지 가장 오래 걸리는 출구의 시간을 기록 (총 시간)
		int times = 0;
		
		// 계단은 동시에 3명만 이용할 수 있다.
		for (int index=0; index < 3; index++) {
			threePeople[index] = firstExit.poll();
		}
		
		// firstExit가 빌 때 까지 반복문 진행 (모든 사람이 출구로 빠져나갈 때 까지)
		while (!firstExit.isEmpty()) {
			/* 
			 * Integer형은 기본형이 아니므로 equals 메소드를 사용하여야 하지만,
			 * Integer 객체 내부에 Inner Class로 정의되어 있는 IntegerCache 객체 내부의 Cache 배열에 -128 ~ 127 까지의 값을 저장하여 사용하므로 이 범위 내의 값은 == 으로 비교 가능하다.
			 */
			for (int index=0; index < 3; index++) {
				nextPerson = firstExit.poll();
				
				// nextPerson이 null이면 고려하지 않아도 됨
				// nextPerson이 내려가기 시작할 수 있는 최소 시간은 nextPerson의 3번째 앞 순서가 전부 내려간 시간이다
				threePeople[index] = nextPerson != null?
						nextPerson <= threePeople[index] + exits[0].exitLen? threePeople[index] + exits[0].exitLen: nextPerson: null;
			}
		}
		
		// 모든 사람이 계단을 이용한 경우 마지막으로 계단 내에 남아있는 사람을 처리한다.
		if (threePeople[first] != null) {
			
			if (threePeople[sec] == null) {
				times = threePeople[first] + exits[0].exitLen + 1;
			}
			
			else if (threePeople[trd] == null) {
				times = threePeople[sec] + exits[0].exitLen + 1;
			}
			
			else {
				times = threePeople[trd] + exits[0].exitLen + 1;
			}
		}
		
		
		// 두 번째 출구도 첫 번째 출구와 똑같이 진행한다.
		
		for (int index=0; index < 3; index++) {
			threePeople[index] = secExit.poll();
		}
		
		while (!secExit.isEmpty()) {
			
			for (int index=0; index < 3; index++) {
				nextPerson = secExit.poll();
				
				threePeople[index] = nextPerson != null?
						nextPerson <= threePeople[index] + exits[1].exitLen? threePeople[index] + exits[1].exitLen: nextPerson: null;
			}
		}
		
		// 마지막 처리
		if (threePeople[first] != null) {
			
			if (threePeople[sec] == null) {
				times = Math.max(times, threePeople[first] + exits[1].exitLen + 1);
			}
			
			else if (threePeople[trd] == null) {
				times = Math.max(times, threePeople[sec] + exits[1].exitLen + 1);
			}
			
			else {
				times = Math.max(times, threePeople[trd] + exits[1].exitLen + 1);
			}
		}
		
		// #4 모든 사람이 나갔을 때의 최소 시간 확인 후 갱신하기
		minTime = minTime > times? times: minTime;
	}
	
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		

		for(int test_case = 1; test_case <= T; test_case++)
		{	
			
			personNum = 0;
			exitsNum = 0;
			minTime = Integer.MAX_VALUE;
			
			int boardNum = Integer.parseInt(br.readLine().trim());
			
			for (int row=0; row < boardNum; row++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for (int col=0; col < boardNum; col++) {
					int num = Integer.parseInt(st.nextToken());
					
					if (num == 1) {
						
						peopleArray[personNum++] = new Person(row, col);
					}
					
					else if (num != 0) {
						
						exits[exitsNum++] = new Exit(row, col, num);
					}
					
				}
			}
			
			allCount = 1 << personNum;
			
			combinations();
			
			answer.append("#").append(test_case).append(" ").append(minTime).append("\n");
		}
		
		System.out.print(answer);
	}
}
