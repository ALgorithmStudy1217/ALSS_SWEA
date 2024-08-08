import java.util.Arrays;
import java.util.Scanner;

// SWEA4408. 자기 방으로 돌아가기 D4

/*
 * 최단 시간에 모든 학생이 자신의 방으로 돌아가려고 한다
 * 숙소는 긴 복도를 따라 총 400개의 방
 * 1 3 5 ... 397 399
 * 2 4 6 ... 398 400
 * 
 * 만약 두 학생이 자기방으로 돌아가면서 지나는 복도의 구간이 겹치면 두 학생은 동시에 돌아갈 수 없다.
 */

/* 29,104 kb
메모리
241 ms
실행시간
2,234
코드길이
풀이시간 50분
시간복잡도 O(N^2)

https://algosu.tistory.com/89 이 글을 보고 탐색하는 부분을 참고함
처음 접근 - 처음 부터 탐색하면서 경우의 수에 따라 분기치기
 -> 문제점 겹치는 상황에서 고려해야할 게 너무 많음
 
 -> 안겹치는 것 끼리 구한다
 -> 모든 학생들을 돌면서 안겹치고, 방을 아직 안옴겼다면 같이 체크
 -> 모든 학생이 옮길때까지 반복 N^2
*/

public class Solution {
	static class Student {
		int start;
		int end;
		boolean done;

		Student(int start, int end) {
			if (start < end) {
				this.start = makeRoomNum(start);
				this.end = makeRoomNum(end);
			} else {
				this.start = makeRoomNum(end);
				this.end = makeRoomNum(start);
			}
		}
	}

	// 1,2 3,4 5,6 이렇게 룸은 같음
	static int makeRoomNum(int num) {
		if (num % 2 == 1) {
			return num;
		}
		return num - 1;
	}
	
	static boolean allDone(Student[] students) {
		for (Student student : students) {
			if (!student.done) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			int numOfStudents = scanner.nextInt();

			// 시작과 끝이 겹치지 않으면 학생들이 같이 움직일 수 있음
			// 겹치면 못 움직임

			Student[] students = new Student[numOfStudents];
			for (int index = 0; index < numOfStudents; ++index) {
				int start = scanner.nextInt();
				int end = scanner.nextInt();
				students[index] = new Student(start, end);
			}

			// 고려할 점
			// start ~ end 까지 복도구간이 겹치면 같이 이용 못함

			// start를 기준으로 정렬
			Arrays.sort(students, (student1, student2) -> {
				if (student1.start > student2.start) {
					return 1;
				} else if (student1.start < student2.start) {
					return -1;
				} else {
					return 0;
				}
			});

			int cnt = 0;
			while (true) {
				// 모든 학생이 옮겼다면 break
				if (allDone(students)) {
					break;
				}
				
				int end = 0;
				
				// 안겹치는 구간 구하기
				for (int index = 0; index < students.length; ++index) {
					if (!students[index].done && students[index].start > end) {
						students[index].done = true;
						
						end = students[index].end;
					}
				}
				cnt++;
			}
			
			System.out.println("#" + testCase + " " + cnt);
		}
	}
}
