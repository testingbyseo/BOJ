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
        int k = Integer.parseInt(st.nextToken());

        long count = 0;

        Queue<Integer>[] nameLen = new Queue[21];
        for(int i=2; i<21; i++){
            nameLen[i] = new LinkedList<>();
        }


        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            String name = st.nextToken();
            int cur = name.length();

            while(!nameLen[cur].isEmpty()){
                if(i - nameLen[cur].peek() > k){
                    nameLen[cur].poll();
                }
                else{
                    break;
                }
            }

            count += nameLen[cur].size();
            nameLen[cur].add(i);
        }




        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}