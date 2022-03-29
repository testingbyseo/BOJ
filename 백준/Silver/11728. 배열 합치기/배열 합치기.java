
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
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        long[] brr = new long[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            brr[i] = Integer.parseInt(st.nextToken());
        }


        int a = 0;
        int b = 0;
        long tmp = 0;
        while(a < n || b < m){
            if(a == n){
                sb.append(brr[b++]).append(" ");
                continue;
            }
            else if(b == m){
                sb.append(arr[a++]).append(" ");
                continue;
            }

            if(arr[a] < brr[b]){
                tmp = arr[a++];
            }
            else{
                tmp = brr[b++];
            }
            sb.append(tmp).append(" ");

        }


        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();


    }



}