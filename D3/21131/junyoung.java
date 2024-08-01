import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 행렬정렬
 * 
 * N*N의 행렬, 각 원소는 1~N*N의 수이며 서로 모두 다르다.
 * 행렬이 정렬되었다 = 모든 1<=i,j<=N에 대해 A[i,j] =  (i-1)xN+j
 * 
 * - 1~N의 정수 x를 고른다.
 * - 1~x행, 1~x열에 해당하는 크기를 전치시킨다.
 * 
 * => 정렬가능한 행렬이 주어졌을때, 연산을 최소 몇회 사용해야 정렬할 수 있는지?
 */

/**
 * 풀이방법:
 * - x행,x열까지의 부분행렬을 전치시키는거니까.. 뒤에서부터 정렬해가야겠다. (더이상 바뀔일이 없도록?)
 * - 대각선은 항상 정렬된 상태일테니, 정렬여부를 판단불가
 * - 정렬 여부를 판단하기 위해서 array[(x-1)][0] 부분(다른부분도 가능)이 정렬되어있는지 보고, 정렬되어있으면 x행,x열은 정렬되어있다고 판단가능!
 */

public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int testNum = Integer.parseInt(br.readLine()); //Integer.valueOf?
    	int[] answers = new int[testNum];
    	
    	
    	for(int testIdx=0; testIdx<testNum; testIdx++) { // 각 테스트마다 반
    		int arrayNum = Integer.parseInt(br.readLine());
    		// 배열 입력받기 
        	int[][] array = new int[arrayNum][arrayNum];
        	for(int row=0; row<arrayNum; row++) {
        		String[] line = br.readLine().split(" ");
        		for (int col = 0; col < arrayNum; col++) {
        			array[row][col] = Integer.parseInt(line[col]);
                }
        	}
        	
        	// 정렬 위해서는 전치 몇번 필요한지 카운트 
        	int transposeCount = 0;
        	boolean isTransposed = false;
        	for(int currentIdx = arrayNum-1; currentIdx>0; currentIdx--) { //젤 뒤에부터 정렬상태 검사 //주의! 0은 빼야함 -> 크기1은 전치시켜봤자 결과가 같음!!
        		//System.out.printf("%d should be %d (should not be equal? = %b) \n",array[currentIdx][0],currentIdx*arrayNum+1, isTransposed);
        		if(isTransposed) { //전치된 상태라면 
        			if(array[currentIdx][0]==currentIdx*arrayNum+1) {
        				transposeCount++;
        				isTransposed = !isTransposed;
        			}
        		}else {
        			if(array[currentIdx][0]!=currentIdx*arrayNum+1) {
        				transposeCount++;
        				isTransposed = !isTransposed;
        			}
        		}
        	}
        	
        	//System.out.println(transposeCount);
        	answers[testIdx] = transposeCount;
    	}
    	
    	for(int a : answers) {
    		System.out.println(a);
    	}
    	
    }
}