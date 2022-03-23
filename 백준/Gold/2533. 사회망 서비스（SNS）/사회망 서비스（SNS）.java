
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int minDepth;
    static ArrayList<Integer>[] list;
    static HashSet<Integer> visit;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        visit = new HashSet<>();
        list = new ArrayList[n+1];
        for(int i =1; i<n+1;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<n;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        dp = new int[n+1][2];

        dfs(1);
        int answer = Math.min(dp[1][0], dp[1][1]);


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    static void dfs(int start){

        visit.add(start);
        dp[start][0] = 1;

        for(int i=0;i<list[start].size();i++){
            int next = list[start].get(i);
            if(!visit.contains(next)){
                dfs(next);
                dp[start][1] += dp[next][0];
                dp[start][0] += Math.min(dp[next][0], dp[next][1]);
            }
        }


    }



}