import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. map 사이즈 입력 받기
 * 3. map 정보 입력 받기
 * 4. 배열의 모든 위치에서 dfs로 경로 탐색
 * 	4-1. 시작 위치, 방향을 인자로 전달
 * 5. dfs 구현
 * 	5-1. 시작 위치로 돌아왔을 때 디저트 배열이 비어있지 않으면
 * 	(기저조건 확인 후 디저트 추가 -> 리스트 비어있으면 처음 호출된 것)
 * 		5-1-1. (삼항연산자) 현재 디저트 수가 1개이면 -1, 아니면 가장 많은 디저트 수 갱신
 * 	5-2. 현재 위치가 map을 벗어났으면 return
 * 	5-3. 이전에 먹었던 디저트면 return
 * 	5-4. 디저트 추가
 * 	5-5. 다음 카페로 이동하기
 * 		5-5-1. 마지막 방향이면 해당 방향으로만 dfs
 * 		5-5-2. 현재 방향, 다음 방향에 대한 분기 나눠서 dfs
 *  	5-6. 현재 분기가 끝나면 디저트 제거
 * 
 * 이동 방향 정보 저장 -> 우측아래, 좌측아래, 좌측위, 우측위 순으로
 * 경로 저장할 리스트 (배열의 크기 = 먹은 디저트 수)
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;
	static int[][] map;
	
	//경로 저장할 리스트 (배열의 크기 = 먹은 디저트 수)
	static int maxEat;
	static List<Integer> dessert;
	static int startRow;
	static int startCol;
	
	//이동 방향 정보 저장 -> 우측아래(0), 좌측아래(1), 좌측위(2), 우측위(3) 순으로
	static int[] delta_x = {1, 1, -1, -1};
	static int[] delta_y = {1, -1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			//4. 배열의 모든 위치에서 dfs로 경로 탐색
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					startRow = row;
					startCol = col;
					dessert = new ArrayList<>();
					//4-1. 시작 위치, 방향을 인자로 전달
					dfs(startRow, startCol, 0);
				}
			}
			sb.append(maxEat).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 사이즈 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		maxEat = -1;
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void dfs(int row, int col, int dir) {
		//5-1. 시작 위치로 돌아왔을 때 디저트 배열이 비어있지 않으면
		//(기저조건 확인 후 디저트 추가 -> 리스트 비어있으면 처음 호출된 것)
		if(row == startRow && col == startCol && !dessert.isEmpty()) {
			//5-1-1. (삼항연산자) 현재 디저트 수가 1개이면 -1, 아니면 가장 많은 디저트 수 갱신
			maxEat = dessert.size() == 1 ? -1 : Math.max(dessert.size(), maxEat);
		}
		
		//5-2. 현재 위치가 map을 벗어났으면 return
		if(row < 0 || row >= mapSize || col < 0 || col >= mapSize){
			return;
		}
		
		//5-3. 이전에 먹었던 디저트면 return
		if(dessert.contains(map[row][col])) {
			return;
		}
		
		//5-4. 디저트 추가
		dessert.add(map[row][col]);
		
		//5-5. 다음 카페로 이동하기
		int nextRow = row + delta_x[dir];
		int nextCol = col + delta_y[dir];
		//5-5-1. 마지막 방향이면 해당 방향으로만 dfs
		if(dir == 3) {
			dfs(nextRow, nextCol, dir);
		}
		//5-5-2. 현재 방향, 다음 방향에 대한 분기 나눠서 dfs
		else {
			dfs(nextRow, nextCol, dir); //현재 방향
			
			//다음 방향
			nextRow = row + delta_x[dir+1];
			nextCol = col + delta_y[dir+1];
			dfs(nextRow, nextCol, dir+1);
		}
		//5-6. 현재 분기가 끝나면 디저트 제거
		dessert.remove(dessert.size()-1);
	}
}
