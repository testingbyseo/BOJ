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
    static long[] arr;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr = new long[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long start = 1;
        long end = arr[n-1];

        long result = 0;
        while(start <= end){
            long mid = (start + end) / 2;

            int total = 1;
            int right = 0 ,left = 0;
            for(right = 0; right < n; right++){

                if(arr[right] - arr[left] < mid){
                    continue;
                }
                else if(arr[right] - arr[left] >= mid){
                    total++;
                    left = right;
                }

            }
            // n 이 못 온채로 끝나면...
            if(left != n-1){
                while(left < n){
                    if(arr[right-1] - arr[left++] >= mid){
                        total++;
                        break;
                    }
                }
            }

            if(total < c){
                end = mid - 1;
            }
            else {
                result = mid;
                start = mid + 1;
            }

        }

        sb.append(result);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}