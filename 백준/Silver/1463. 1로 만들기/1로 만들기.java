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
    static int[] res;
    static int count;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        res = new int[n+1];


        dfs(n);
        sb.append(res[n]);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int dfs(int num) {

        if(num == 1) return 0;
        if(res[num] != 0){
            return res[num];
        }

        int result = dfs(num-1) + 1;
        if(num % 3 == 0){
            int ret = dfs(num/3)+1;
            if(result > ret){
                result = ret;
            }
        }

        if(num % 2 == 0){
            int ret = dfs(num/2)+1;
            if(result > ret){
                result = ret;
            }
        }

        res[num] = result;

        return result;
    }

}