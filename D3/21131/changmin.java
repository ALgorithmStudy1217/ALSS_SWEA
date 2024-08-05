import java.io.FileInputStream;
import java.util.Scanner;
 
public class Solution {
 
    static int T;
    static int arrSize;
    static int ans;
    static boolean isCheck;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
         
        Scanner sc = new Scanner(System.in);
         
        T = sc.nextInt();
 
        for(int test_case = 0; test_case<T; test_case++) {
            arrSize = sc.nextInt();
            arr = new int[arrSize][arrSize];
            isCheck = false;
            ans = 0;
             
            for(int row = 0; row<arrSize; row++) {
                for(int col = 0; col<arrSize; col++) {
                    arr[row][col] = sc.nextInt();
                }
            }
             
            // 큰 사각형부터 진행 arrSize-1
            for(int row = arrSize-1; row > 0; row--) {
                 
                if(arr[row][row-1] <= arr[row-1][row]&&!isCheck) {
                    ans++;
                    isCheck = true;
                    // 전치가 된 상태에서는 크기를 반대로 확인
                } else if (arr[row][row-1] > arr[row-1][row]&&isCheck) {
                    ans++;
                    isCheck = false;
                     
                }   
            }
            System.out.println(ans);
        }
    }

}