import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static StringBuilder sb;
    static BufferedWriter bw;
    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int[][] board;
    static int max;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        int k = Integer.parseInt(st.nextToken());

        for(int t=0; t < k; t++) {
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            board = new int[2][n+1];

            for(int i=0; i<2; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j=1; j<=n; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp = new int[2][n+1];

            dp[0][1] = board[0][1];
            dp[1][1] = board[1][1];

            for (int j = 2; j <= n; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + board[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + board[1][j];
            }
            int ans = dp[0][n] > dp[1][n]? dp[0][n] : dp[1][n];
            sb.append(ans).append("\n");


   
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}