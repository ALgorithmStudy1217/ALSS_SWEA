import java.util.Scanner;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
        // (1, 1) 부터 (300, 1) 까지의 모든 값 배열
        int[] numList = new int[301];
        
        for (int index=1; index<=300; index++) {
            numList[index] = numList[index-1] + index;
        }
        
		for(int test_case = 1; test_case <= T; test_case++)
		{	
            // input 숫자 정렬을 위한 장치
            int[] inputNumList = new int[2];
            for (int inputNum=0; inputNum<2; inputNum++) {
                inputNumList[inputNum] = sc.nextInt();
            }
            Arrays.sort(inputNumList);
            
			int firstNum = inputNumList[0];
            int secNum = inputNumList[1];
            
            // 값 초기화
            int firstNumX = 0, firstNumY = 0, secNumX = 0, secNumY = 0, targetX = 0, targetY = 0;
            
            // (x, 1) 값보다 작으면 좌표 찾기
            boolean firstCheck = false;
            for (int index=1; index<=300; index++) {
                
                if (numList[index]>=firstNum && !firstCheck) {
                    firstNumX = index-(numList[index]-firstNum);
                    firstNumY = 1+(numList[index]-firstNum);
                    firstCheck = true;
                }
                if (numList[index]>=secNum) {
                    secNumX = index-(numList[index]-secNum);
                    secNumY = 1+(numList[index]-secNum);
                    
                    break;
                }
            }
            
            targetX = firstNumX+secNumX;
            targetY = firstNumY+secNumY;
            
            // (targetX, targetY) 값으로 역산하여 답 추적
            System.out.println("#"+test_case+" "+(numList[targetX+targetY-1]-(targetY-1)));
		}
	}
}