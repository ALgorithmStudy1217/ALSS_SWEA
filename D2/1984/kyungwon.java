import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
 

class Solution {
 
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
         
         
        int testCase = Integer.parseInt(br.readLine().trim());
         
        for(int t = 1; t <= testCase; t++) {
            List<Integer> num = new ArrayList<>();
            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0; idx<10; idx++) {
                num.add(Integer.parseInt(st.nextToken()));
            }
             
            Collections.sort(num);
             
            int sum = 0;
            for(int idx = 1; idx<9; idx++) {
                sum += num.get(idx);
            }
             
            System.out.println("#" + t + " " + (int) Math.round((double) sum/8));
        }
         
    }
}
