
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 제약 조건을 만족하는 부분집합
// 1. 버거의 재료 개수와 조합이 맞지 않는 재료 쌍의 개수를 입력받는다
// 2. 재료의 개수만큼 element 배열을 만든다
// 3. 조합이 맞지 않는 재료 2개를 M번 입력받는다
// 4. 재료를 인덱스 값으로 하여 조합이 맞지 않는 재료를 저장한다
// 4-1. 값이 작은 재료의 인덱스에 값이 큰 값을 넣는다
// 5. 부분집합을 만들며 조합을 확인한다
// 5-1. elementIdx가 조합이 맞지 않는 재료 배열에 들어있다면
// 5-2. 해당 재료를 선택하지 않고 넘어간다
// 6. 모든 값을 확인했다면 조합 개수를 +1한다

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    static boolean unGroup[][];
    static int ingredient;
    static int unGroupNum;
    static int elementArr[];
    static boolean usedElement[];
    static int combiNum;

    public static void powerSet(int elementIdx) {

        // 6. 모든 값을 확인했다면 조합 개수를 +1한다
        if(elementIdx == ingredient) {

            combiNum++;
            return;
        }
        boolean isPossible = true;
        // 5. 부분집합을 만들며 조합을 확인한다
        // 5-1. elementIdx가 조합이 맞지 않는 재료 배열에 들어있다면
        // 5-2. 해당 재료를 선택하지 않고 넘어간다
        for(int idx = 0; idx < ingredient; idx++) {
            if(usedElement[idx] && unGroup[idx+1][elementIdx+1]) {

                isPossible = false;
                break;
            }
        }
        if(isPossible) {
            usedElement[elementIdx] = true;
            powerSet(elementIdx+1);
        }
        usedElement[elementIdx] = false;
        powerSet(elementIdx+1);
    }

    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));
        int Test = Integer.parseInt(bf.readLine());


        for(int tcase =1; tcase <= Test; tcase++) {
            //테스트케이스마다 초기화
            combiNum = 0;

            // 1. 버거의 재료 개수와 조합이 맞지 않는 재료 쌍의 개수를 입력받는다
            st = new StringTokenizer(bf.readLine());
            ingredient = Integer.parseInt(st.nextToken());
            unGroupNum = Integer.parseInt(st.nextToken());
            elementArr = new int[ingredient];
            usedElement = new boolean[ingredient];
            // 2. 재료의 개수만큼 element 배열을 만든다
            for(int idx = 0; idx < ingredient; idx++) {
                elementArr[idx]= idx+1;
            }
            unGroup = new boolean[ingredient+1][ingredient+1];
            // 3. 조합이 맞지 않는 재료 2개를 M번 입력받는다
            for(int idx=0; idx<unGroupNum; idx++) {
                st = new StringTokenizer(bf.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                // 4. 재료를 인덱스 값으로 하여 조합이 맞지 않는 재료를 저장한다
                // 4-1. 값이 작은 재료의 인덱스에 값이 큰 값을 넣는다

                unGroup[first][second] = true;
                unGroup[second][first] = true;

            }

            powerSet(0);

            System.out.println("#" + tcase + " " + combiNum);

        }
    }
}
