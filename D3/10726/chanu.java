import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t <= T; t ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken().trim());
			int M = Integer.parseInt(st.nextToken().trim());
			
			int num = (int)(Math.pow(2, N)) - 1;
			String result = ((num & M) == num) ? "ON" : "OFF";
			System.out.println("#" + t + " " + result);
		}
	}
}
