import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int[] arr;
    static boolean[] visit;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        arr = new int[n+1];

        for(int i=1;i<n+1;i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[n+1];
        result = new ArrayList<>();
        for(int i=1;i<n+1; i++){
            dfs(i, i);
        }

        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(int e : result){
            sb.append(e).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
    static int dfs(int start, int now){
        visit[now] = true;
        int next = arr[now];

        if(!visit[next]){
            int node = dfs(start, next);

            if(node > 0){
                result.add(now);
                return now;
            }



        }
        else{
            if(start == next){
                result.add(now);
                return now;
            }
        }
        visit[now] = false;
        return 0;

    }

}