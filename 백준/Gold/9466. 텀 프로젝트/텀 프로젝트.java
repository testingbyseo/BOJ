import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
/*
2
7
3 1 3 7 3 4 6
8
1 2 3 4 5 6 7 8
*/
    static StringBuilder sb;
    static int[] arr;
    static boolean[] visit;
    static boolean[] finished;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        while(n-- > 0){

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            arr = new int[s+1];

            st = new StringTokenizer(br.readLine());

            for(int i=1; i<s+1;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            visit = new boolean[s+1];
            finished = new boolean[s+1];
            cnt = 0;
            for(int i=1; i<s+1; i++){
                dfs(i);
            }

            sb.append(s-cnt).append("\n");

        }

        bw.write(sb.substring(0, sb.length()-1));
        bw.flush();
        bw.close();




    }
    static void dfs(int now){
        if(visit[now]){
            return;
        }
        visit[now] = true;
        int next = arr[now];

        if(!visit[next]){
            dfs(next);
        }
        else{
            if(!finished[next]){
                cnt++;
                for(int i=next; i != now; i = arr[i]){
                    cnt++;
                }

            }
        }
        finished[now] = true;



    }
}