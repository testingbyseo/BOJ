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
    static int[] dp;
    static int count;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 2];

        dp[1] = 1;
        dp[2] = 2;
        for(int i=3; i <= n; i++){

            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        sb.append(dp[n]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}