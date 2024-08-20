package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*1. 테스트 케이스 입력받기
 *2. 약품 투입횟수 1씩 올리면서 약품 투입할 행 조합 찾기
 *3. A 투입/B투입/투입 X 고르고 재귀 호출
 *4. 종료조건
 *	4-1. 조합 완성하면 성능검사 통과했는지 검사하기 -> checkPerformance 함수
 *	4-2. 모든 원소 확인했다면 종료
 *5. 성능 검사 통과했다면 조합의 요소 수를 늘리지 않고 종료
 * */

public class Swea_2112 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int height, width, passScore, selectNum;
	static int[][] film;
	static int[][] copy;
	static boolean isSuccess;	//성공 여부
	
	static boolean checkPerformance() {
		for(int col=0; col<width; col++) {
			int same=1;
			for(int row=1; row<height; row++) {
				if(copy[row][col]==copy[row-1][col]) {
					same+=1;
				}else {
					same=1;
				}
				
				//1개의 열에서 성능검사를 통과했다면 break 하고 다음열 검사하기
				if(same==passScore) {
					break;
				}
			}
			//1개의 열이라도 성능검사를 통과하지 못했다면 바로 false 리턴
			if(same<passScore) {
				return false;
			}
		}
		//모든 열이 성능검사를 통과했다면 true 리턴
		return true;
	}
	
	static void combination(int selectIdx, int elementIdx) {
		//4. 종료조건
		//4-1. 조합 완성하면 성능검사 통과했는지 검사하기 -> checkPerformance 함수
		if(selectIdx==selectNum) {
			if(checkPerformance()){
				isSuccess=true;
			}
			return;
		}
		
		//4-2. 모든 원소 확인했다면 종료
		if(elementIdx==height) {
			return;
		}
		
		
		//3. A 투입/B투입/투입 X 고르고 재귀 호출
		//element를 고름-A 투입
		for(int idx=0; idx<width; idx++) {
			copy[elementIdx][idx]=0;
		}
		combination(selectIdx+1,elementIdx+1);
		for(int idx=0; idx<width; idx++) {	//백트래킹
			copy[elementIdx][idx]=film[elementIdx][idx];
		}
		
		//element를 고름-B투입
		for(int idx=0; idx<width; idx++) {
			copy[elementIdx][idx]=1;
		}
		combination(selectIdx+1,elementIdx+1);
		for(int idx=0; idx<width; idx++) {	//백트래킹
			copy[elementIdx][idx]=film[elementIdx][idx];
		}
			
		//element를 고르지 않음
		combination(selectIdx,elementIdx+1);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case<=T; test_case++) {
			//1. 테스트 케이스 입력받기
			st=new StringTokenizer(br.readLine());
			height=Integer.parseInt(st.nextToken());
			width=Integer.parseInt(st.nextToken());
			passScore=Integer.parseInt(st.nextToken());
			
			isSuccess=false;
			
			film=new int [height][width];
			copy=new int [height][width];
			
			for(int row=0; row<height; row++) {
				st=new StringTokenizer(br.readLine());
				for(int col=0; col<width; col++) {
					int tempNum=Integer.parseInt(st.nextToken());
					film[row][col]=tempNum;	//원본 배열
					copy[row][col]=tempNum;	//변경시킬 배열
				}
			}
			
			//2. 약품 투입횟수 1씩 올리면서 약품 투입할 행 조합 찾기
			for(int idx=0; idx<height; idx++) {
				selectNum=idx;	//조합 element 수
				combination(0,0);
				//5. 성능 검사 통과했다면 조합의 요소 수를 늘리지 않고 종료
				if(isSuccess) {
					break;
				}
			}
			sb.append("#"+test_case+" "+selectNum+"\n");
		}
		System.out.println(sb);
	}

}
