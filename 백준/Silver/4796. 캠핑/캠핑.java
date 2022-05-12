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

        int idx = 1;
        while(true) {
            int days = 0;
            st = new StringTokenizer(br.readLine().trim());
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(l == 0) break;

            days = (v / p) * l;
            int remain = v % p;
            days += Math.min(remain, l);


            sb.append("Case ").append(idx++).append(": ");
            sb.append(days).append("\n");
        }





        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}