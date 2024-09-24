package 알쓰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
텀프로젝트를 수행하기 위해, 팀을 구성해야 한다.
프로젝트 팀원 수에는 제한이 없고, 혼자 할 수도 있다.
팀을 구성하기 위해 모든 학생들은 함께 하고 싶은 학생들을 선택한다.
    단, 혼자하고 싶은 학생은 자기 자신을 선택한다.

시작하는 학생에서 다시  같은 학생으로 돌아와야 팀이 결성된다.
팀에 속하지 못하는 학생들의 수를 계산하여 보자.

1. 테스트케이스 수를 입력받는다.
2. 학생의 수가 주어진다.
3. 각 학생들이 함께 하고 싶은 학생들의 정보가 공백을 기준으로 입력된다.
4. 학생들의 인접리스트와 방문체크를 통해 팀의 결성 여부를 체크한다.
    4-1.
*/
public class BOJ9466 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int totalStudents;
    static int[][] relation;
    static boolean[] visited;
    static Set<Integer> complete;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        for (int nowTest = 1; nowTest <= testCase; nowTest++) {
            initTest();
            for (int nowStudent = 1; nowStudent <= totalStudents; nowStudent++) {
                if (complete.contains(nowStudent)) continue;
                checkTeam(nowStudent);
                System.out.println(Arrays.toString(visited));
                System.out.println(complete.toString());
            }

            sb.append(String.format("#%d %d\n", nowTest, totalStudents - complete.size()));
        }
        System.out.println(sb);
    }

    private static void checkTeam(int student) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> rollbackQueue = new LinkedList<>();
        queue.add(student);
        visited[student] = true;
        while (!queue.isEmpty()) {
            int nowStudent = queue.poll();
            rollbackQueue.add(nowStudent);
            int nextStudent = relation[nowStudent][0];

            if (!visited[nextStudent]) {
                queue.offer(nextStudent);
                visited[nextStudent] = true;
            } else {
                //학생이 많아지는 경우의 팀을 선택한다는 조건은 없다!, 팀이 결성될 수 있다면 끝!
                if (nextStudent == student) {
                    while (!rollbackQueue.isEmpty()) {
                        complete.add(rollbackQueue.poll());
                    }
                    return;
                }

            }
        }

        while (!rollbackQueue.isEmpty()) {
            int nowStudent = rollbackQueue.poll();
            visited[nowStudent] = false;
        }
    }

    private static void initTest() throws IOException {
        totalStudents = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        relation = new int[totalStudents + 1][1];
        visited = new boolean[totalStudents + 1];
        complete = new HashSet<>();
        for (int index = 1; index <= totalStudents; index++) {
            relation[index][0] = Integer.parseInt(st.nextToken());
        }
    }

}
