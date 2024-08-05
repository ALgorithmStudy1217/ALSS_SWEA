import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Solution
{
 
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[][] arr= new int[305][305];
    public static void main(String args[]) throws Exception
    {
        int T;
        T=Integer.parseInt(br.readLine());
        init();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine(), " ");
 
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
 
            BoardPoint boardPointP = findValue(p);
            BoardPoint boardPointQ = findValue(q);
            BoardPoint result = new BoardPoint(
                boardPointP.x+ boardPointQ.x, boardPointP.y + boardPointQ.y);
 
            System.out.println("#"+test_case+" "+arr[result.x][result.y]);
        }
    }
 
    private static void init(){
        int result = 0;
        arr[0][1] = 0;
        for(int i=1; i<=300;i++){
            result = arr[i-1][1] + i;
            for(int j=1;j<=300;j++){
                if (j != 1) {
                    result += j + i - 2;
                }
                arr[i][j] = result;
            }
        }
    }
 
    private static class BoardPoint {
        int x;
        int y;
 
        BoardPoint(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
 
    private static BoardPoint findValue(int x) {
        for(int i=1;i<=300;i++){
            for(int j=1;j<=300;j++){
                if(arr[i][j] == x) return new BoardPoint(i,j);
            }
        }
        return new BoardPoint(0,0);
    }
 
}