import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int n;
    static int[] num;
    static int[] op;
    static int max, min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        num = new int[n];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n ;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        // 0 + 1 - 2 * 3 /
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, op, num[0]);
        sb.append(max).append("\n");
        sb.append(min).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int idx, int[] ops, int sum){

        if(idx == n){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for(int i=0; i<4; i++){
            
            if(ops[i] != 0){
                int[] nops = ops.clone();
                nops[i]--;
                dfs(idx+1, nops, getSum(i, num[idx], sum));
            }

        }

    }

    static int getSum(int index, int cur, int sum){
        if(index == 0){
            return sum + cur;
        }
        else if(index == 1){
            return sum - cur;
        }
        else if(index == 2){
            return sum * cur;
        }

        return sum / cur;
    }




}