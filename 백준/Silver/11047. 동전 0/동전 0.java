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
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] coins = new int[n];
        int end = n-1;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            int value = Integer.parseInt(st.nextToken());
            if(value > target) {
                end = --i;
                break;
            }
            coins[i] = value;
        }

        long count = 0;

        while(target > 0){
            if(target < coins[end]){
                end--;
                continue;
            }
            count += target / coins[end];
            target = target % coins[end];

        }


        sb.append(count);





        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}