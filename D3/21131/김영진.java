// Online Java Compiler
// Use this editor to write, compile and run your Java code online
 
import java.util.*;
import java.io.*;
 
class Solution
{
    public static int size;
    public static int[][] map;
    public static int answer;
    public static void print() {
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                System.out.print(map[row][col]);
            }
            System.out.println();
        }
    }
    public static void transfer(int x) {
        for (int row=0; row<=x; row++) {
            for (int col=0; col<=x; col++) {
                if (row<col) {
                    int tmp = map[row][col];
                    map[row][col] = map[col][row];
                    map[col][row] = tmp;
                }
            }
        }
    }
    public static void recursive(int col) {
        if (col==0) return;
        if (map[0][col] != col+1) {
            transfer(col);
            answer++;
        }
        recursive(col-1);
    }
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> result = new ArrayList<>();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            size = Integer.parseInt(br.readLine());
            map = new int[size][size];
            for (int row=0; row<size; row++) {
                String[] str = br.readLine().split(" ");
                for (int col=0; col<size; col++) {
                    map[row][col] = Integer.parseInt(str[col]);
                }
            }
            answer=0;
            recursive(size-1);
            result.add(answer);
        }
        for (int test_case=0; test_case<T; test_case++) {
            System.out.println(result.get(test_case));
        }
    }
}
