
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
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());

        }

        int left = 0;
        int right = 0;
        int even = 0;
        int answer = 0;

        while(right < n){
            if(even <= k && arr[right] % 2 == 0){
                right++;
                answer = Math.max(answer, right-left-even);
            }

            if(right == n) break;

            if(arr[right] % 2 != 0){
                right++;
                even++;
            }
            else if(even > k){
                if(arr[left] % 2 != 0){
                    even--;
                }
                left++;
            }
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();


    }



}