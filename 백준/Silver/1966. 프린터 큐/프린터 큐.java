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
    static int result;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        int test = Integer.parseInt(st.nextToken());

        for(int i = 0; i < test; i++){
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
            Queue<int[]> q = new LinkedList<>();
            int[] count = new int[10];
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine().trim());
            for(int idx = 0; idx < n; idx++){
                int e = Integer.parseInt(st.nextToken());
                q.add(new int[] {e, idx});
                count[e]++;
            }

            for (int pri=1; pri<10; pri++){
                if(count[pri] > 0){
                    pq.add(new int[]{pri, count[pri]});

                }
            }

            int[] high = pq.poll();
            result = 1;
            while(!q.isEmpty()){
                if(high[1] == 0 && !pq.isEmpty()){
                    high = pq.poll();
                }

                int[] cur = q.poll();
                if(high[0] == cur[0]) {
                    high[1]--;

                    if(cur[1] == target){
                        sb.append(result).append("\n");
                        break;
                    }
                    result++;
                }
                else{
                    q.add(cur);
                }

            }


        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}