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
        int m = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            set.add(st.nextToken());
        }

        int result = 0;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            if(set.contains(st.nextToken())){
                result++;
            }
        }

        sb.append(result);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}