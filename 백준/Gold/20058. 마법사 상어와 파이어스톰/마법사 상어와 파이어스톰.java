import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int N;
    static int Q;
    static int size;
    static int[] L;
    static int[][] map;
    static boolean[][] visit;
    static StringBuilder sb;
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        map = new int[size][size];
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q;i++){
            int l = Integer.parseInt(st.nextToken());
            L[i] = (int) Math.pow(2, l);
        }

        process();

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    static void process() {
        // print();
        // System.out.println();
        for(int i=0; i<Q; i++){
            roll(L[i]);
            // print();
            meltIce();
        }

        visit = new boolean[size][size];
        int max = 0;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(!visit[i][j] && map[i][j] != 0){
                    max = Math.max(max, dfs(i, j));
                }
            }
        }
        sb.append(answer).append("\n");
        sb.append(max);
    }
    static void print() {
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(map[i][j] + " ");

            }
            System.out.println();
        }
    }
    static int dfs(int x, int y){

        visit[x][y] = true;
        answer += map[x][y];
        int sum = 1;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

            if(!visit[nx][ny] && map[nx][ny] != 0){
                sum += dfs(nx, ny);
            }
        }

        return sum;

    }


    static void roll(int l) {

        int range = size - l;
        for(int sx = 0; sx <= range; sx += l){
            for(int sy = 0; sy <= range; sy+=l){

                rotate(sx, sy, l);
            }
        }

    }
    static void rotate(int x, int y, int len){

        int[][] tempMap = new int[len][len];

        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                tempMap[j][len -1-i] = map[x+i][y+j];
            }
        }

        for(int i=0; i<len; i++){
            for (int j=0; j<len; j++){
                map[x+i][y+j] = tempMap[i][j];
            }
        }

    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static void meltIce(){
        boolean[][] isUpdated = new boolean[size][size];

        for(int i=0; i<size; i++){
            for(int j=0; j<size;j++){
                if(map[i][j] != 0){
                    int ice = 0;

                    for(int k=0; k<4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

                        if(map[nx][ny] != 0 || isUpdated[nx][ny]) ice++;
                    }

                    if(ice < 3){
                        map[i][j] = Math.max(0, map[i][j] - 1);
                        isUpdated[i][j] = true;
                    }
                }
            }
        }

    }




}