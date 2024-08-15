import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
#SWEA 1493. 수의 새로운 연산
점 (x, y)에 할당된 수는 #(x, y)로 나타낸다.
	#(1, 1) = 1
	#(2, 1) = 3
	#(2, 2) = 5
	#(4, 4) = 25
p가 할당된 점을 &(p)로 나타낸다.
	&(1) = (1, 1)
	&(3) = (2, 1)
	&(5) = (2, 2)
	&(25) = (4, 4)
두 점의 덧셈은 (x, y) + (z, w) = (x + z, y + w)로 정의한다.
p[star]q는 #(&(p)+&(q))으로 나타난다.
	1[star]5 = #(&(1)+&(5)) = #((1,1)+(2,2)) = #(3,3) = 13
각 테스트 케이스마다 p[star]q의 값을 구해라.

#입력
첫째 줄: 테스트케이스의 수 T
각 테스트 테이스의 첫 번째 줄: p, q (1 <= p, q <= 10,000)
#출력
#t [p[star]q]

#로직
대각선 줄에는 x + y - 1개의 점이 존재하고, 이를 K줄이라고 부르겠음
K줄의 마지막 값은 K.y(K.y+1)/2임 -> K가 300일 때 마지막 값은 45,150로 커버 가능

K줄의 마지막 값을 모두 계산한다.
해당 값을 기준으로 값 <-> 좌표 변환을 구현한다.
 */
class Solution {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] endValues = new int[300]; //10,000개의 값을 모두 커버하기 위한 값
    static StringTokenizer st;
    static int testCases, num1, num2;
    
	public static void main(String args[]) throws Exception {
    	//K줄의 마지막 값 계산
    	for (int x = 1; x < endValues.length; x++)
    		endValues[x] = endValues[x - 1] + x;
        
    	testCases = Integer.parseInt(br.readLine());
    	for (int testCase = 1; testCase <= testCases; testCase++) {
    		//입력
            st = new StringTokenizer(br.readLine().trim());
    		num1 = Integer.parseInt(st.nextToken());
    		num2 = Integer.parseInt(st.nextToken());
    		
            //로직
    		int[] point1 = numToPoint(num1);
    		int[] point2 = numToPoint(num2);
    		int[] plusPoint = plusPoint(point1, point2);
    		int number = pointToNum(plusPoint);
    		
            //출력
    		System.out.printf("#%d %d\n", testCase, number);
    	}
    }
    
    //숫자를 새로운 좌표로 변환
    public static int[] numToPoint(int number) {
    	int[] point = new int[2];
    	
    	for (int idx = 1; idx < endValues.length; idx++) {
    		if (number <= endValues[idx] && number > endValues[idx] - idx) {
    			point[0] = idx - (endValues[idx] - number);
    			point[1] = endValues[idx] - number + 1;
    			break;
    		}
    	}
    	
    	return point;
    }
    
    //좌표의 덧셈
    public static int[] plusPoint(int[] point1, int[] point2) {
    	return new int[] {point1[0] + point2[0], point1[1] + point2[1]};
    }
    
    //좌표를 숫자로 변환
    public static int pointToNum(int[] point) {
    	int idx = point[0] + point[1] - 1;
    	return endValues[idx] - (point[1] - 1);
    }
}
