import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*
* ex 1 102
4
1
100
100
1
*
* ex2 16
5
1
4
3
1
9
* */
public class Main {

    static StringBuilder sb;
    static BufferedWriter bw;
    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int[] dp;
    static int[] steps;
    static int count;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        dp = new int[n+1];
        steps = new int[n+1];


        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            steps[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = steps[1];
        if(n >= 2){
            dp[2] = steps[1] + steps[2];
        }

        for(int i=3; i < n+1; i++){
            dp[i] = steps[i] + Math.max(dp[i-2], steps[i-1] + dp[i-3]);
        }

        /*for(int i =0; i<n+1; i++){
            System.out.println("dp[" +i +"] = " + dp[i]);
        }*/


        sb.append(dp[n]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }



}