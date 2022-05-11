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
        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<=n; i++) {
            q.add(i);
        }

        while(q.size() > 1){

            q.poll();
            int toTail = q.poll();
            q.add(toTail);

        }
        sb.append(q.poll());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}