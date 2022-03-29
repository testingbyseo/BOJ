
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;

        int sum = 0;
        int min = 100001;


        while(true){
            if(sum >= s){
                min = Math.min(min, end - start);
                sum -= arr[start++];
            }
            else if(end == n) break;
            else sum += arr[end++];

        }


        if(min == 100001) sb.append(0);
        else sb.append(min);

        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();


    }



}