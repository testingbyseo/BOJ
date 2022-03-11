import javafx.beans.property.ReadOnlyIntegerWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int[][] arr;
    static int[][] via;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        via = new int[n][n];

        for(int i=0; i< m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[u-1][v-1] = w;
            arr[v-1][u-1] = w;

            via[u-1][v-1] = v;
            via[v-1][u-1] = u;
        }

        for(int k =0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(arr[i][k] == 0 || arr[k][j] == 0)
                        continue;

                    if(arr[i][j] == 0){
                        arr[i][j] = arr[i][k] + arr[k][j];
                        if(via[i][k] != k+1){
                            via[i][j] = via[i][k];
                        }
                        else{
                            via[i][j] = k+1;
                        }
                        continue;
                    }

                    if(arr[i][j] > arr[i][k] + arr[k][j]){

                        arr[i][j] = arr[i][k] + arr[k][j];
                        if(via[i][k] != k+1){
                            via[i][j] = via[i][k];
                        }
                        else{
                            via[i][j] = k+1;
                        }

                    }



                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(j == i) sb.append("-").append(" ");
                else sb.append(via[i][j]).append(" ");
            }
            sb.append("\n");
        }



        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


}