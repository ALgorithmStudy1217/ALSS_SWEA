import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
모든 경우의 수 탐색.

현재 추를 오른쪽에 넣어도 왼쪽보다 커지지 않으면 (작거나 같으면)
왼쪽과 오른쪽 모두 넣을 수 있다.

순서가 바뀌면 다른 방법으로 인정.
따라서 같은 추가 중복으로 들어가지 않게 체크 필요.
*/
class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = 0;
    static int[] factorial = new int[10];
    
    // 재귀로 모든 경우의 수 탐색
    // 추를 오른쪽에 담았을 때 왼쪽보다 무거워지면 break
    static void mount(int[] weightArr, int left, int right, int check, int num, int stop, double halfTotal) {
         // 모든 추를 담으면 answer+=1 & break
        if (check == stop) {
            answer += 1;
            return;
        }
        
        // 만약 오른쪽에 남은 추를 전부 넣을 수 있다면, 왼쪽과 오른쪽에 넣는 모든 경우의 수를 바로 구하고 탐색하지 않음
        if (left >= halfTotal) {
            int index = num-Integer.bitCount(check);
            answer += Math.pow(2, index) * factorial[index];
            return;
        }
        
        for (int index=0; index<num; index++) {
            // 중복 방분시 탐색하지 않음
            int checked = check | (1<<index);
            
            if (checked == check) {
                continue;
            }
                
            // 오른쪽에 추를 넣어도 왼쪽보다 커지지 않으면 오른쪽에도 추가
            if (left >= right+weightArr[index]) {
                mount(weightArr, left, right+weightArr[index], checked, num, stop, halfTotal);
            }
            // 왼쪽에는 항상 추가 (중복 방문이 아니라면)
            mount(weightArr, left+weightArr[index], right, checked, num, stop, halfTotal);
        }
    }
        
    
	public static void main(String args[]) throws Exception
	{	
		int T;
		T = Integer.parseInt(br.readLine());
        
        // 순열 공식을 위한 팩토리얼 미리 구하기
        for (int facNum=1; facNum<=9; facNum++) {
        factorial[facNum] = facNum;
        }

        for (int facNum=2; facNum< 10; facNum++) {
            factorial[facNum] *= factorial[facNum-1];
        }
        
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int num = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine().trim(), " ");
            
            // 추 배열 생성
            int[] weightArr = new int[num];
            
            // 비트를 사용한 중복 검사 stop은 이진수('1'*num)
            int stop = 0;
            // 모든 추를 더했을 때의 총 무게
            double total = 0;
            for (int i=0; i<num; i++) {
                weightArr[i] = Integer.parseInt(st.nextToken());
                stop += 1<<i;
                total += weightArr[i];
            }
            
            answer = 0;
            mount(weightArr, 0, 0, 0, num, stop, total/2);
            
			System.out.println("#"+test_case+" "+answer);
		}
	}
}