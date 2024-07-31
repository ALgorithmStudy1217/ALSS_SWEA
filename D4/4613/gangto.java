import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{	
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T;
    
	public static void main(String args[]) throws Exception
	{	
        
		T=Integer.parseInt(input.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{	
            st = new StringTokenizer(input.readLine().trim());
            int rowNum = Integer.parseInt(st.nextToken());
            int colNum = Integer.parseInt(st.nextToken());
            
            // 50 x 50 배열에서의 최대값 2500, 대소비교 후 갱신
            int answer = 2500;
            
            // Blue, Red 의 누적합 배열
            int[] whiteSum = new int[rowNum];
            int[] blueSum = new int[rowNum];
            int[] redSum = new int[rowNum];
            
            // 배열에 알맞은 값 추가
            for (int rIndex=0; rIndex<rowNum; rIndex++) {
                String row = input.readLine();
                
                for (int cIndex=0; cIndex<colNum; cIndex++) {
					 if (row.charAt(cIndex)=='W') {
                         whiteSum[rIndex] += 1;
                     } else if (row.charAt(cIndex)=='B') {
                         blueSum[rIndex] += 1;
                     } else {
                         redSum[rIndex] += 1;
                     }
                }
                
           	}
			
            // 'W'와 'B'는 위에서 아래로 , 'R'은 아래에서 위로 누적합
            for (int rIndex=1;  rIndex<rowNum; rIndex++) {
                whiteSum[rIndex] += whiteSum[rIndex-1];
                blueSum[rIndex] += blueSum[rIndex-1];
                redSum[rowNum-rIndex-1] += redSum[rowNum-rIndex];
            }
            
            // 'B' 와 'R'이 만나는 위치의 "바꾸지 않아도 되는 값" 저장
            // 'B'의 위치를 기준으로 처음과 마지막을 제외하고 저장
            int[] BandR = new int[rowNum];
            
            for (int rIndex=1; rIndex<rowNum-1; rIndex++) {
                BandR[rIndex] = blueSum[rIndex]+redSum[rIndex+1];
            }
            
            // 모든 블럭을 바꿨을 때의 값
            int change = rowNum * colNum;
                
            // White 값은 아래에서 세 번째 까지만 가능
            // White의 층을 하나씩 내리면서 모든 경우의 수 탐색
            for (int rIndex=0; rIndex<rowNum-2; rIndex++) {
                
                // White가 덮은 만큼 Blue의 누적합 빼주기
                int minusB = blueSum[rIndex];
                int whiteNum = whiteSum[rIndex];
                
                // change = 변경할 모든 블럭, (whiteNum+BandR[index]-minusB) = 변경되는 블럭의 색과 같은 색(변경하지 않는 블럭)
                for (int index=1; index<rowNum; index++) {
                    int num = change-(whiteNum+BandR[index]-minusB);
                        
                    if (answer > num) {
                        answer = num;
                    }
                }
            }
            
            System.out.println("#"+test_case+" "+(int)answer);

		}
	}
}
