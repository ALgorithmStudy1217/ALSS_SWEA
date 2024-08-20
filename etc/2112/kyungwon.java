import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    static StringBuilder sb;
    static int thick;
    static int width;
    static int pass;
    static int[][] film;
    static int[][] newFilm;
    static int[] changedFilm;
    static int minChange;
     
    public static void changeFilm() {
        for(int row = 0; row <thick; row++) {        
            for(int col = 0; col < width; col++) {
                if(changedFilm[row] !=2) {
                    newFilm[row][col] = changedFilm[row];
                } else {
                    newFilm[row][col] = film[row][col];
                }
            }
        }
    }
     
    public static boolean checkFilm() {
        boolean isPass = true;
        for(int col = 0; col<width; col++) {
            int check = 1;
         
            for(int row = 1; row<thick; row++) {
                 
                if(newFilm[row-1][col] == newFilm[row][col]) {
                    check++;
                    if(check == pass) {
                        break;
                    }
                } else {
                    check = 1;
                }
                 
            }
            if(check != pass) {
                isPass = false;
                break;
            }
        }
         
        return isPass;
    }
     
    public static void powerSet(int elementIdx, int cnt) {
         
        if(minChange < cnt) return;
         
        if(elementIdx == thick) {
            changeFilm();
            if(checkFilm()) {
                minChange = Math.min(minChange, cnt);
            }
             
            return;
        }
         
        changedFilm[elementIdx] = 1;
        powerSet(elementIdx+1, cnt+1);
         
        changedFilm[elementIdx] = 0;
        powerSet(elementIdx+1, cnt+1);
         
        changedFilm[elementIdx] = 2;
        powerSet(elementIdx+1, cnt);
    }
     
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int Test = Integer.parseInt(bf.readLine().trim());
         
        for(int tcase = 1; tcase <= Test; tcase++) {
             
            st = new StringTokenizer(bf.readLine());
            thick = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            pass = Integer.parseInt(st.nextToken());
             
            minChange = 987654321;
            film = new int[thick][width];
            changedFilm = new int[thick];
            newFilm = new int[thick][width];
            for(int row = 0; row < thick; row++) {
                st = new StringTokenizer(bf.readLine());
                for(int col = 0; col<width; col++) {
                    film[row][col]= Integer.parseInt(st.nextToken());
                }
            }
             
            powerSet(0, 0);
             
            sb.append("#" + tcase + " " +  minChange + "\n");
        }
         
        System.out.println(sb.toString());
    }
}
