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
        int l = Integer.parseInt(st.nextToken());
        int[] pipe = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<n; i++){
            pipe[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pipe);


        int count  = 1;
        double end = (double) pipe[0] - 0.5 + l;

        for(int i=1; i<n; i++){
            if(pipe[i] > end){
                count++;
                end = (double) pipe[i] - 0.5 + l;
            }
        }
        sb.append(count);





        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}