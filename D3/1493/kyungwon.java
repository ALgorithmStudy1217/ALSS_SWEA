import java.util.Scanner;
import java.io.FileInputStream;
 
public class Solution {
 
    public static int[] find(int a) {
        int row = 1;
        int col = 1;
         
         
        for(int i=1; i<201; i++) {
            if(((i-1) *i )/2 +1 > a) {
                row = i-1;
                break;
            }
        }
         
         
        col = a - (((row-1)*row)/2 +1);
        row -= col;
        col++;
         
        return new int[]{row, col};
         
    }
     
    public static void main(String[] args) {
 
         
        Scanner sc = new Scanner(System.in);
         
        int test = sc.nextInt();
         
        for(int t = 1; t<=test; t++) {
            int p = sc.nextInt();
            int q = sc.nextInt();
             
            int[] p_loc = find(p);
            int[] q_loc = find(q);
             
            int[] result = {p_loc[0] + q_loc[0], p_loc[1] + q_loc[1]};
             
            int start_row = result[0] + (result[1]-1);
             
            int result_val = (((start_row -1) * start_row)/2 + 1) + result[1] -1;
             
             
            System.out.println("#" + t + " " + result_val);
        }
    }   
     
     
 
}
