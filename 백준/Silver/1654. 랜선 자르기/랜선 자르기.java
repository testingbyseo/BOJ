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
    static int k;
    static long max;
    static long min;
    static long[] arr;
    static long result;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new long[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine().trim());
            long e = Long.parseLong(st.nextToken());
            arr[i] = e;
            max = Math.max(e, max);
            min = Math.min(e, min);
        }


        sb.append(upperBound(0, max+1)  - 1);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long upperBound(long start, long end){

        while(start < end){
            long mid = (start + end) / 2;
            long total = getSum(mid);

            if(total < n){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return start;
    }
    static long getSum(long mid){
        long num = 0;

        for(long e : arr){
            long res = e / mid;
            num += res;
        }
        return num;
    }

}