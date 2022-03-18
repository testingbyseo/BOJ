import javafx.beans.property.ReadOnlyIntegerWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int[] d;
    static ArrayList<Integer>[] adj;
    static long answer;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        d = new int[n+1];

        adj = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        dfs(1);
        answer -= combi(n);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static int dfs(int now){
        d[now] = 1;
        for(int a : adj[now]){
            if(d[a] == 0){
                d[now] += dfs(a);
            }
        }
        //  u-->v 를 연결 간선을 선택하는 경우의 수
        //  [전체에서 2개 노드 선택 경우] - ["v와 그 서브트리를" 제외한 서브트리에서 두 노드를 선택하는 경우의 수]
        //     nC2                      (u-->v 간선 선택할일 전혀없음)

        answer += combi(n) - combi(n - d[now]);
        return d[now];

    }
    static long combi(int num){
        return (long) num*(num-1) / 2;
    }


}