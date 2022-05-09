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
    static int m;
    static int result;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][m+1];

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=1; j<m+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        int cnt = Integer.parseInt(st.nextToken());
        int[][] queries = new int[cnt][4];
        for(int i=0; i < cnt; i++){
            st = new StringTokenizer(br.readLine().trim());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
            queries[i][2] = Integer.parseInt(st.nextToken());
            queries[i][3] = Integer.parseInt(st.nextToken());
        }
        int[][] prefix = new int[n+1][m+1];
        for(int i=0; i<n+1; i++){
            int sum = 0;
            for(int j=0; j<m+1; j++){
                sum += arr[i][j];
                prefix[i][j] = sum;
            }
        }

        for(int[] q : queries){
            int i = q[0];
            int j = q[1];
            int x = q[2];
            int y = q[3];
            result = 0;

            for(int r=i; r <= x; r++){
                result += prefix[r][y] - prefix[r][j-1];
            }
            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


}