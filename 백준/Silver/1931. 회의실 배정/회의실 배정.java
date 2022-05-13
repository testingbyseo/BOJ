import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if(this.end == o.end){
                return  this.start - o.start;
            }
            if(this.end < o.end){
                return -1;
            }
            return 1;
        }
    }
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
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Info(s, e));
        }

        int count = 0;
        int time = 0;
        while(!pq.isEmpty()){
            Info cur = pq.poll();

            if(cur.start >= time){

                count++;
                time = cur.end;

                // System.out.println(cur.start + " " + cur.end);
            }

        }


        sb.append(count);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}