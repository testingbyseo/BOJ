import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*

 */


public class Main {

    static StringBuilder sb;
    static int n;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int max = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];

        for(int i=0; i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    static void dfs(int[][] board, int cnt){
        if(cnt == 5){
            findMax(board);
            return;
        }

        for(int i=0; i<4; i++){
            //copy board
            int[][] cur = new int[n][n];
            for(int k=0; k<n; k++){
                cur[k] = board[k].clone();
            }
            move(cur, i);

            dfs(cur, cnt+1);

        }

    }
    static void findMax(int[][] board){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                max = Math.max(max, board[i][j]);
            }
        }
    }

    static int setStart(int index){
        if(index == 0 || index == 2){
            return n-1;
        }
        return 0;
    }
    static void move(int[][] cur, int idx){


        for(int i=0; i<n ;i++){
            int pos = setStart(idx);
            int now = 0;
            int j = pos;

            if(idx == 0 || idx == 1){
                // 왼쪽 or 오른쪽
                while(j >= 0 && j < n){

                    if(cur[i][j] != 0){
                        if(cur[i][j] == now){
                            cur[i][pos+dy[idx]] = 2 * now;
                            now = 0;
                            cur[i][j] = 0;
                        }
                        else{
                            now = cur[i][j];
                            cur[i][j] = 0;
                            cur[i][pos] = now;
                            if(idx == 0){
                                pos--;
                            }
                            else{
                                pos++;
                            }
                        }
                    }

                    if(idx == 0){
                        j--;
                    }
                    else{
                        j++;
                    }

                }


            }
            else{
                // 위 pos = 0 or 아래 pos = n-1
                while(j >= 0 && j < n){

                    if(cur[j][i] != 0){

                        if(cur[j][i] == now){
                            cur[pos + dx[idx]][i] = 2 * now;
                            now = 0;
                            cur[j][i] = 0;
                        }
                        else{
                            now = cur[j][i];
                            cur[j][i] = 0;
                            cur[pos][i] = now;
                            if(idx == 2){
                                pos--;
                            }
                            else{
                                pos++;
                            }
                        }
                    }


                    if(idx == 2){ // 아래
                        j--;
                    }
                    else{ // 위
                        j++;
                    }

                }

            }


        }

    }


}