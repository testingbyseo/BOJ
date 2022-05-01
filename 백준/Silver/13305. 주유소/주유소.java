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
    static int[] distance;
    static int[] price;


    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        distance = new int[n-1];
        price = new int[n];

        long totalDist = 0;
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<n-1; i++){
            int d = Integer.parseInt(st.nextToken());
            distance[i] = d;
        }
        long  answer = 0;
        long min = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<n; i++){
            int p = Integer.parseInt(st.nextToken());
            price[i] = p;

        }

        for(int i=0; i < n-1; i++){
            int cur = price[i];

            if(min > cur){
                min = cur;
            }
            answer += distance[i]*min;
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}