import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {


    static int r;
    static int c;
    static int t;
    static int[][] map;
    static int[][] opMap;
    static int ven;
    static StringBuilder sb;

    static int answer;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        ven  = -1;
        answer = 0;

        for(int i=0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                // 공청기 위치 : 위쪽 행 저장 ( ven, 0 ) (ven+1, 0)
                if(map[i][j] == -1 && ven == -1) {
                    ven = i;
                }
            }
        }

        process();


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static void count() {
        answer = 0;
        for(int i=0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(map[i][j] > 0){
                    answer += map[i][j];
                }
            }
        }
    }

    static void process() {

        for(int i=0; i<t; i++){

            spread();

            operationUpper();
            operationLower();
            copyMap(opMap, map);

        }
        count();

    }
    static void copyMap(int[][] from, int[][] to){
        for(int i=0; i<r; i++){
            for(int j = 0; j<c; j++){
                to[i][j] = from[i][j];
            }
        }
    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static void spread() {
        int[][] tempMap = new int[r][c];
        copyMap(map, tempMap);

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j] > 0){

                    int num = 0;
                    int amount = map[i][j] / 5;
                    for(int k=0; k<4; k++){
                        int nr = i + dx[k];
                        int nc = j + dy[k];

                        if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;

                        if(map[nr][nc] != -1){
                            num++;
                            tempMap[nr][nc] += amount;
                        }
                    }
                    tempMap[i][j] -= amount*num;

                }
            }
        }
        copyMap(tempMap, map);

    }
    static void setMap() {

        opMap[ven][0] = -1;
        opMap[ven+1][0] = -1;

        for(int i=1; i<ven; i++){
            for(int j=1; j<c-1; j++){
                opMap[i][j] = map[i][j];
            }
        }

        for(int i=ven+2; i<r-1; i++){
            for(int j=1; j<c-1; j++){
                opMap[i][j] = map[i][j];
            }
        }
    }

    static int[] udx = {0, -1, 0, 1};
    static int[] udy = {1, 0, -1, 0};
    static void operationUpper() {

        opMap = new int[r][c];
        setMap();
        // 공청기 바로 옆에서 시작
        int sx = ven;
        int sy = 1;

        for(int i=0; i<4; i++){

            while(true) {
                //System.out.println("sx " + sx + " sy " + sy);

                int nx = sx + udx[i];
                int ny = sy + udy[i];

                if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    break;
                }
                if(map[nx][ny] == -1){
                    // 공청기 만나면 미세 먼지 사라짐
                    return;
                }

                if(map[sx][sy] != 0){
                    opMap[nx][ny] = map[sx][sy];
                    //System.out.println("nx " + nx + " ny " + ny);
                }
                sx = nx;
                sy = ny;
            }
        }

    }
    static int[] ldx = {0, 1, 0, -1};
    static int[] ldy = {1, 0, -1, 0};
    static void operationLower() {
        // 공청기 바로 옆에서 시작
        int sx = ven + 1;
        int sy = 1;
        int[][] tempMap = new int[r][c];

        for(int i=0; i<4; i++) {

            while (true) {
                int nx = sx + ldx[i];
                int ny = sy + ldy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                    break;
                }
                if (map[nx][ny] == -1) {
                    // 공청기 만나면 미세 먼지 사라짐
                    return;
                }

                if (map[sx][sy] != 0) {
                    opMap[nx][ny] = map[sx][sy];
                }
                sx = nx;
                sy = ny;
            }
        }

    }

    static void print() {

        for(int i=0; i<r; i++){
            for(int j=0; j<c;j++){
                System.out.print(map[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}