import java.util.*;
import java.io.*;

public class Solution {

	/*
	 * 1493. 수의 새로운 연산
	 * 1. p, q의 값을 입력 받는다.
	 * 2. p, q 각각 해당하는 좌표를 다음 규칙을 활용해서 구한다.
	 * 2-1. (n-1)*n/2 < value(p or q) <= (n*(n+1)/2) 가 성립하는 n을 구한다면
	 * 		그 점은 << y = x + (n + 1) >> 위에 존재한다.
	 */
	
	static int testCase;
	static int[][] map;
	static int calculateSum(int n) {
		return (n*(n+1)/2);
	}
	static Point getLocation(int value) {
		// (n-1)*n/2 < value <= (n*(n+1)/2) -> x+y=n+1
		int n;
		for (n=1; n<200; n++) {
			if (calculateSum(n-1) < value && value <= calculateSum(n)) {
				break;
			}
		}
		int x = value - calculateSum(n-1);
		int y = -x + n + 1;
		return new Point(x, y);
	}
	static int getValue(Point point) {
		// (n-1)*n/2 < value <= (n*(n+1)/2) -> x+y=n+1
		int n = point.x + point.y - 1;
		return calculateSum(n-1) + point.x;
	}
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		int[] answers = new int[testCase];
		for (int test_case=1; test_case<=testCase; test_case++) {
			Point[] points = new Point[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			points[0] = getLocation(p);
			points[1] = getLocation(q);
			int newX = points[0].x + points[1].x;
			int newY = points[0].y + points[1].y;
			answers[test_case-1] = getValue(new Point(newX, newY));
		}
		for (int test_case=1; test_case<=testCase; test_case++) {
			System.out.printf("#%d %d%n",test_case, answers[test_case-1]);
		}
	}
	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
