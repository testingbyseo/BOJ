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
    static int[][] map;
    static int answer;
    static int r;
    static int c;
    static void print() {
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void copyMap(int[][] from, int[][] to){
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                to[i][j] = from[i][j];
            }
        }
    }
    static void addFish(int min) {
        for(int i=1; i<n+1; i++){
            if(map[n][i] == min){
                map[n][i]++;
            }
        }
    }
    static void roll() {

        int sum = 0;
        int loop = 1;

        int w = 1;
        int h = 1;
        int nw = 1;
        int nh = 1;
        int start = 1;
        while(true){
            // 가장 아래에 바닥이 있을때까지 반복.
            sum += nh;
            if(sum >= n) {
                r = n - h;
                c = start;
                return;
            }

            rolling(nw, nh, start);

            w = nw;
            h = nh;
            start += w;

            nw = h;
            nh = w + 1;
            loop++;
        }
    }
    static void rolling(int w, int h, int start){
        int nx = n;
        int ny = start;

        for(int i=w; i > 0 ; i--){

            for(int j=0; j < h; j++){

                int tmp = map[nx][ny];
                map[n-i][ny + i + j] = tmp;
                // 빈칸 만들기
                map[nx][ny] = -1;
                nx--;
            }
            nx = n;
            ny++;
        }

    }

    static void mixFish() {
        int[][] temp = new int[n+1][n+1];
        copyMap(map, temp);

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        boolean[][] visit = new boolean[n+1][n+1];
        for(int i=r; i<n+1; i++){
            for(int j=c; j<n+1; j++){

                if(temp[i][j] > 0){

                    // 만났던 애들은 pass 하도록 저장해준다.
                    visit[i][j] = true;
                    for(int d=0; d<4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx < r || ny < c || nx > n || ny > n) continue;
                        // 빈칸이거나 이미 앞에서 계산 했으면
                        if(temp[nx][ny] == -1 || visit[nx][ny]) continue;

                        int diff = Math.abs(temp[i][j] - temp[nx][ny]) / 5;
                        if(diff > 0){
                            if(temp[i][j] > temp[nx][ny]){
                                map[i][j] -= diff;
                                map[nx][ny] += diff;
                            }
                            else{
                                map[i][j] += diff;
                                map[nx][ny] -= diff;
                            }
                        }

                    }
                }
            }
        }

    }
    static void makeLine() {
        int index = 1;
        for(int j = c; j<n+1; j++){
            for(int i=n; i >= r; i--){
                if(map[i][j] != -1){
                    map[n][index++] = map[i][j];

                    // 빈칸
                    if(i != n){
                        map[i][j] = -1;
                    }

                }
            }
        }

    }
    static void fold() {

        int quarter = n / 4;

        int[] cc = {0, n, n - quarter + 1, n};
        int[] dir = {0, -1, 1, -1};

        int start = 1;
        for(int lv=1; lv<4; lv++){

            int col = cc[lv];
            for(int i = 0 ; i < quarter; i++){

                map[n - lv][col] = map[n][start];
                map[n][start] = -1;
                col += dir[lv];
                start++;
            }

        }
        // make line 에 필요한 좌표 지정 잊지말자!!
        r = 1;
        c = n - quarter + 1;

    }

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();
        process();


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for(int i=1; i<n+1; i++){
            Arrays.fill(map[i], -1);
        }

        st = new StringTokenizer(br.readLine().trim());
        for(int i=1; i<n+1; i++){
            map[n][i] = Integer.parseInt(st.nextToken());
        }

    }
    static int isDone() {
        int max = 0;
        int min = 100000;
        for(int i=1; i<n+1; i++){
            max = Math.max(max, map[n][i]);
            min = Math.min(min, map[n][i]);
        }
        if(max - min <= k) return -1;

        return min;
    }
    static void process() {
        answer = 0;

        while(true){

            int ret = isDone();
            if(ret == -1){
                sb.append(answer);
                break;
            }

            addFish(ret);
            roll();

            mixFish();
            makeLine();

            fold();
            mixFish();

            makeLine();
            answer++;
        }
    }



}