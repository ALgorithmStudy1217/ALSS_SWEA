import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
            
            // num은 각 자리마다 이동하기 위해 char[]로 선언
			char[] num = st.nextToken().toCharArray();
            int cnt = Integer.parseInt(st.nextToken());
            
            // 최대 값 배열 maxNum 생성. 왜 내림차순 정렬을 지원하지 않는 거지????
            char[] cloneNumArr = num.clone();
            Arrays.sort(cloneNumArr);
            
            int len = cloneNumArr.length;
            char[] maxNum = new char[len];
            
            // 배열 내에 같은 숫자가 있다면, 남은 cnt가 홀수여도 남아도 현 상황을 유지할 수 있다
            boolean isSameNum = false;
            
            for (int index=0; index < len; index++) {
                maxNum[index] = cloneNumArr[len-index-1];
                
                if (index != 0 && maxNum[index-1] == maxNum[index]) {
                    isSameNum = true;
                }
            }
            
            /*
            바꿀 수 있는 횟수를 하나씩 소모하면서 확인
            
            앞에서부터 바꿔야 할 값 targetNum를 찾음. (바꿀 수 있는 가장 큰 수)
            
            뒤에서부터 남은 cnt의 수 이내의 targetNum와 같은 값을 모두 찾고,
            앞에서부터 남은 cnt의 수 이내의 바꿔야 할 값을 모두 찾는다.
            
            위치를 한 번에 바꾸면서 가장 최적의 경우를 찾는다.
            */
            while (cnt > 0) {
                char targetNum = 0;
                
                for (int index=0; index < len; index++) {
                    if (num[index] != maxNum[index]) {
                        targetNum = maxNum[index];
                        break;
                    }
                }
                
                // 바꿀 targetNum 값의 index 를 모두 저장하는 List
                List<Integer> targetNumIdxList = new ArrayList<>();
                int targetNumCnt = 0;
                
                for (int index=len-1; index > 0; index--) {
                    
                    // 뒤에서부터 targetNum를 찾고, 바꾸지 않아도 되는 (최적해의 값의 위치와 같은) 값이 아니면 바꿈
                    // targetNumIdxList는 자연스럽게 역순으로 정렬
                    if (num[index] == targetNum && num[index] != maxNum[index]) {
                        targetNumIdxList.add(index);
                        targetNumCnt++;
                        
                        // 이동횟수 이상의 값은 필요하지 않음
                        if (targetNumCnt == cnt) {
                            break;
                        }
                    }
                }
                
                // 바뀌는 값을 모두 저장하는 List. [index, value] 값으로 저장하여 num 배열을 직접 바꿀 수 있게 함
                List<int[]> changeNumList = new ArrayList<>();
                int changeNumCnt = 0;
                
                // targetNumIdxList 가 비어있다면, 바꿀 값이 더이상 없다는 의미이므로 break
                if (targetNumIdxList.isEmpty()) {
                    break;
                }
                else {
                    for (int index=0; index < len; index++) {
                        
                        // 앞에서부터 바꿀 값을 찾으면 changeNumList에 [index, value] 값으로 저장
                        if (num[index] != maxNum[index]) {
                            changeNumList.add(new int[] {index, num[index]-'0'});
                            changeNumCnt ++;
                            
                            if (changeNumCnt == targetNumCnt) {
                                break;
                            }
                        }
                    }
                    
                    // 모든 바꿀 요소를 찾아서 List에 추가했다면 value를 기준으로 sort
                    Collections.sort(changeNumList, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
                }
                
                for (int index=0; index < targetNumCnt; index++) {
                    
                    // 수집한 값을 토대로 스위칭!!
                    num[changeNumList.get(index)[0]] = targetNum;
                    num[targetNumIdxList.get(index)] = (char) ('0'+(changeNumList.get(index)[1]));
                }
                
                cnt -= targetNumCnt;
            }
            
            // 같은 값이 없고, cnt가 홀수만큼 남아있다면 차선을 선택
            if (! isSameNum && cnt % 2 == 1) {
                char temp = num[len-1];
                num[len-1] = num[len-2];
                num[len-2] = temp;
            }
            
            StringBuilder answer = new StringBuilder();
            answer.append("#").append(test_case).append(" ");
            
            for (char charNum : num) {
                answer.append(charNum);
            }
            
            System.out.println(answer);
		}
	}
}
