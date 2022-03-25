
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static ArrayList<Integer>[] adj;
    static int[] spent;
    static int[] dp;
    static int[] indgree;
    static int goal;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        while (n-- > 0){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            adj = new ArrayList[size+1];

            st = new StringTokenizer(br.readLine());
            spent = new int[size+1];
            dp = new int[size+1];
            indgree = new int[size+1];

            for(int i=1; i<size+1; i++){
                spent[i] = Integer.parseInt(st.nextToken());
                adj[i] = new ArrayList<>();
            }

            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj[u].add(v);
                indgree[v]++;
            }

            goal = Integer.parseInt(br.readLine());

            Queue<Integer> q = new LinkedList<>();
            for(int i = 1; i<size+1; i++){
                if(indgree[i] == 0)
                {
                    if(i == goal){
                        q.clear();
                        dp[goal] = spent[goal];
                        break;
                    }
                    else{
                        q.add(i);
                    }

                }
            }

            Queue<Integer> result = new LinkedList<>();
            while(!q.isEmpty()){
                int cur = q.poll();
                dp[cur] += spent[cur];
                result.add(cur);

                for(int next : adj[cur]){
                    indgree[next]--;
                    dp[next] = Math.max(dp[next], dp[cur]);

                    if(indgree[next] == 0){
                        q.add(next);
                    }

                }

            }


            sb.append(dp[goal]).append("\n");
        }

        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();


    }



}