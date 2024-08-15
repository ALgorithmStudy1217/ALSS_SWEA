import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;
/*
x*x의 정사각형 형태로만 뒤집을 수 있고, 무조건 정렬이 가능
밖의 값을 뒤집으면 안의 값도 뒤집힌다.

첫 줄만 가지고도 확인 가능.
x번째 위치가 제자리에 있거나 | 뒤집혀 있거나 확인 후 정렬

*/
class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
	public static void main(String args[]) throws Exception
	{
		int T;
		T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int numSize = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine().trim(), " ");
            
            // 삽입 후 스택 형식으로 원소를 빼면서 cnt하기 위해 Deque 사용
            Deque<Integer> numArr = new ArrayDeque<Integer>();
            
            // 첫 줄만 가지고 답 도출 가능
            for (int rIndex=1; rIndex<=numSize; rIndex++) {
                int num = Integer.parseInt(st.nextToken());
                
                // 정렬이 되어있으면 1, 아니면 0
                if (num==rIndex) {
                    numArr.add(1);
                } else {
                    numArr.add(0);
                }
            }
            // 첫 번째는 무조건 정렬이 되어있으니 확인하지 않음
            numArr.remove();
            
            // 정렬이 되어있는지 뒤에서부터 확인
            int start = 1;
            int answer = 0;
            while (!(numArr.isEmpty())) {
                
                // 만약 정렬이 되어있지 않으면 answer += 1
                // 정사각형 범위 내의 모든 값이 반전되므로 정렬 값 또한 같이 반전
                if (numArr.removeLast() != start) {
                    answer += 1;
                    start = Math.abs(start-1);
                }
            }
            
            System.out.println(answer);
            
            // 나머지 필요없는 자료 폐기
           for (int cIndex=1; cIndex<numSize; cIndex++) {
               st = new StringTokenizer(br.readLine().trim(), " ");
           }
        }
	}
}
