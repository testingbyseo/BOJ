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
    static Integer[] expectTip;



    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());

        expectTip = new Integer[n];
        for(int i=0 ;i<n;i++){
            st = new StringTokenizer(br.readLine().trim());
            expectTip[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(expectTip, Collections.reverseOrder());

        long answer = 0;
        for(int i=0; i<n; i++){
            int realTip = expectTip[i] - i;
            if(realTip < 0){
                // 음수가 된후 는 더이상 하지 않는다.
                break;
            }
            answer += realTip;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}