
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int[] time;
    static int[] price;
    static int[] total;
    static int n;
    static int max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        time = new int[n+1];
        price = new int[n+1];
        total = new int[n+1];

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());

        }
        max = 0;
        combination(1, 0);

        sb.append(max);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void combination(int next, int sum){
        if(next == n+1){
            max = Math.max(max, sum);
            return;
        }

        if(next + time[next] <= n+1 ){
            total[next] = sum + price[next];
            combination(next + time[next], total[next]);
        }
        combination(next + 1, sum);
    }


}