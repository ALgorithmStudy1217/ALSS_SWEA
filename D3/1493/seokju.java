import java.util.Scanner;

// SWEA 1493. 수의 새로운 연산 D3

/*
 * 2차원 평면 제 1사분면 위의 격자점 (x,y)에 대각선 순서로 점에 수를 붙인다.
 * 점 (x,y)에 할당된 수는 #(x,y)
 * 수 p가 할당된 점을 &(p)로 나타낸다.
 * 덧셈을 정의 (x,y) (z,w) 더하면 (x+z,y+w)
 * 새로운 연산 별을 구현
 * p별q = #(&p + &q)
 */

public class Solution {

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		// 두 포인트를 더해서 새로운 포인트를 리턴해주는 더하기 함수
		public Point operatorPlus(Point otherPoint) {
			int x = this.x + otherPoint.x;
			int y = this.y + otherPoint.y;
			return new Point(x, y);
		}
	}

	// #(x,y) 연산
	// 포인트를 받으면 할당된 정수 값을 리턴해준다.
	static int operatorSharp(Point point) {
		// Point의 x+y-1 값이 (n,1) 의 값이 된다.
		// x 좌표를 먼저 구해주자
		int currentX = point.x + point.y - 1;
		// 합은 (n^2 + n) /2
		int checkNum = (currentX * currentX + currentX) / 2;

		// y좌표 차이만큼 값을 빼주면 된다
		return checkNum - (point.y - 1);
	}

	// &(x) 연산
	// 숫자를 받으면 포인트를 리턴해준다
	static Point operatorAmpersand(int num) {
		// (n,1) 의 값은 1~n 까지의 합과 같다
		// 넘어온 num 값이 1~n 까지의 합보다 작을때 까지 while 루프를 돈다.
		int checkNum = 1;
		int currentX = 1;
		// >= 30개 틀림 why?
		// >= 이면 다음 칸으로 넘어가버린다!!!
		// > 로 막아야함 ㅠㅠ
		while (num > checkNum) {
			// 현재 확인용 x를 1 늘려주고
			currentX++;
			// 확인할 번호는 x값을 더해준다.
			checkNum += currentX;
		}
		// while 루프를 탈출 했다면 (currentX, 1) ~ (1, currentX) 사이의 숫자이다.
		int currentY = 1;
		while (num != checkNum) {
			// x축으로 한칸뒤 y축으로 한칸 위, 숫자는 1씩 감소하면서 확인
			currentX--;
			currentY++;
			checkNum--;
		}

		return new Point(currentX, currentY);
	}

	// 우리가 만든 새로운 연산자 스타
	// #(&(num1)+&(num2))가 된다.
	static int operatorStar(int num1, int num2) {
		Point point1 = operatorAmpersand(num1);
		Point point2 = operatorAmpersand(num2);
		Point newPoint = point1.operatorPlus(point2);
		return operatorSharp(newPoint);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int numP = scanner.nextInt();
			int numQ = scanner.nextInt();

			// #(x,y) 가 할당된 수
			// 할당된 점을 &p
			// numP numQ가 할당된 점을 구해서
			// 점을 더하고
			// 그 점에 할당된 수를 구해야 한다.
			// (1,1) 1
			// (1,2) 2 (2,1) 3
			// (1,3) 4 (2,2) 5 (3,1) 6
			// (1,cnt) ~ (cnt,1) 씩 수를 할당해줘야함
			// 할당한 수를 알고 있어야 하나?

			System.out.println("#" + testCase + " " + operatorStar(numP, numQ));

		}
	}

}
