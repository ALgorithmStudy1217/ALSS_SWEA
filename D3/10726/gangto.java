import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

class Solution
{	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        
        // 이진수 누적 배열
        List<Integer> bitSumList = new ArrayList<Integer>(31);
        
        int bitSum = 0;
        for (int bitNum=0; bitNum<31; bitNum++) {
            bitSumList.add(bitSum+1);
            bitSum += 1 << bitNum;
        }
        
        int T;
		T= Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine().trim(), " ");
            
            int checkRange = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
			
            // checkRange 범위 이상의 값 폐기 (%)
            // 폐기 이후 모든 값이 1이라면 --> checkRange의 모든 값이 1인 값과 같다면 "ON" return
            checkRange = bitSumList.get(checkRange);
            StringBuilder sb = new StringBuilder();
                
            if (num%checkRange == checkRange-1) {
                sb.append("#").append(test_case).append(" ON\n");
            } else {
                sb.append("#").append(test_case).append(" OFF\n");
            }
            answer.append(sb);
		}
        
        System.out.println(answer.deleteCharAt(answer.length() - 1));
	}
}
