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
    static int m;
    static int result;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=1; j<n+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] queries = new int[m][4];
        for(int i=0; i < m; i++){
            st = new StringTokenizer(br.readLine().trim());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
            queries[i][2] = Integer.parseInt(st.nextToken());
            queries[i][3] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[n+1][n+1];
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                // (i, j)의 구간합
                // (위쪽 합) + (왼쪽 합) - (교집합) + (현재위치 값)
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
            }
        }


        for(int[] q : queries){
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];

            result = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}