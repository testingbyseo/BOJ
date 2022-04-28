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

    static int R;
    static int C;
    static int K;
    static int W;
    static int[][] map;
    static ArrayList<Integer> detection;
    static ArrayList<int[]> heater;
    static int[][] wall;
    static int answer;
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
    static void print(int[][] arr) {
        for(int i=1; i<=R; i++){
            for(int j=1;j<=C; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    static void process() {
        answer = 0;

        while(answer <= 100){

            if(isDone()){
                break;
            }

            turnOnHeat();
            mixTemputure();
            downTemputure();
            answer++;

        }

        sb.append(answer);
    }

    static void  turnOnHeat() {
        for(int[] h : heater){

            turnOn(h[0], h[1]);
        }

    }
    static int[] dir = {0, 1, -1, -1, 1};
    static int[] next = {-1, 0, 1};
    static void turnOn(int heat, int hnum){
        int[][] tempMap = new int[R+1][C+1];

        if(hnum == 1 || hnum == 2){
            int start = heat + dir[hnum];
            HashSet<Integer> set = new HashSet<>();

            Queue<int[]> q = new LinkedList<>();
            // 현재 위치 현재 점수
            q.add(new int[]{start, 5});
            set.add(start);
            tempMap[start / 100][start % 100] = 5;
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int cr = cur[0] / 100;
                int cc = cur[0] % 100;

                if(cur[1] <= 1) continue;

                for(int i=0; i<3; i++){
                    int nr = cr + next[i];
                    int nc = cc + dir[hnum];
                    int nret = nr*100 + nc;
                    if(nr <= 0 || nc <= 0 || nr > R || nc > C || set.contains(nret))
                        continue;

                    int mid = nr*100 + cc;
                    if(wall[cur[0]][mid] == 1 || wall[mid][nret] == 1){
                        // 벽이 있다면 pass
                        continue;
                    }

                    // 방문하지 않았고 벽이 없으면 갈수 있다.
                    q.add(new int[] {nret, cur[1]-1});
                    set.add(nret);
                    tempMap[nr][nc] = cur[1]-1;
                }

            }
        }
        else if(hnum == 3 || hnum == 4){
            int start = heat + dir[hnum]*100;
            HashSet<Integer> set = new HashSet<>();

            Queue<int[]> q = new LinkedList<>();
            // 현재 위치 현재 점수
            q.add(new int[]{start, 5});
            set.add(start);
            tempMap[start / 100][start % 100] = 5;
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int cr = cur[0] / 100;
                int cc = cur[0] % 100;

                if(cur[1] <= 1) continue;

                for(int i=0; i<3; i++){
                    int nr = cr + dir[hnum];
                    int nc = cc + next[i];
                    int nret = nr*100 + nc;
                    if(nr <= 0 || nc <= 0 || nr > R || nc > C || set.contains(nret))
                        continue;

                    int mid = cr*100 + nc;
                    if(wall[cur[0]][mid] == 1 || wall[mid][nret] == 1){
                        // 벽이 있다면 pass
                        continue;
                    }

                    // 방문하지 않았고 벽이 없으면 갈수 있다.
                    q.add(new int[] {nret, cur[1]-1});
                    set.add(nret);
                    tempMap[nr][nc] = cur[1]-1;
                }
            }
        }

        addMap(tempMap, map);
    }
    static int[] dx ={0, 1, 0, -1};
    static int[] dy ={1, 0, -1, 0};
    static void mixTemputure() {

        int[][] tempMap = new int[R+1][C+1];
        copyMap(map, tempMap);
        boolean[][] visit = new boolean[R+1][C+1];
        for(int i=1; i<R+1; i++){
            for(int j=1; j<C+1; j++){
                if(map[i][j] >= 0){
                    visit[i][j] = true;
                    int cur = map[i][j];
                    int cret = i*100 + j;

                    for(int dir=0; dir<4; dir++){
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        int nret = nx*100 + ny;

                        if(nx <= 0 || ny <= 0 || nx > R || ny > C)
                            continue;
                        else if(wall[cret][nret] == 1 || visit[nx][ny]){
                            // 벽이 있다면 pass
                            continue;
                        }
                        int next = map[nx][ny];
                        int diff = (int) Math.floor(Math.abs((double)(cur - next)/4));

                        if(cur > next){
                            tempMap[i][j] -= diff;
                            tempMap[nx][ny] += diff;
                        }
                        else {
                            tempMap[i][j] += diff;
                            tempMap[nx][ny] -= diff;
                        }
                    }
                }
            }
        }

        copyMap(tempMap, map);
    }

    static void downTemputure(){
        int sx = 1;
        int sy = 1;

        for(int i=0; i<4; i++){

            while(sx+dx[i] > 0 && sx+dx[i] <= R && sy+dy[i] > 0 && sy+dy[i] <= C){
                sx += dx[i];
                sy += dy[i];

                map[sx][sy] = map[sx][sy]-1 < 0? 0 : map[sx][sy]-1;
            }

        }

    }


    static void copyMap(int[][] from, int[][] to){
        for(int i=0; i<R+1; i++){
            for(int j=0; j<C+1; j++){
                to[i][j] = from[i][j];
            }
        }
    }
    static void addMap(int[][] from, int[][] to){
        for(int i=0; i<R+1; i++){
            for(int j=0; j<C+1; j++){
                to[i][j] += from[i][j];
            }
        }
    }


    static boolean isDone() {
        for(int d : detection){
            int r = d /100;
            int c = d % 100;
            if(map[r][c] < K) return false;
        }
        return true;
    }
    static void input() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        detection = new ArrayList<>();
        heater = new ArrayList<>();

        // 인덱스 1부터
        for(int i=1; i<R+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=1; j<C+1; j++){
                int ret = Integer.parseInt(st.nextToken());

                // 히터가 있는 칸도 온도가 상승하므로 모든 칸에 초기 온도 0도로 셋팅
                if(ret == 5){
                    // 조사할 칸 위치 정보 저장
                    detection.add(i*100 + j);
                }
                else if(ret >= 1 && ret <= 4){
                    // 히터 위치 정보 저장
                    // h r c
                    heater.add(new int[] {i * 100 + j, ret});
                }
            }
        }

        st = new StringTokenizer(br.readLine().trim());
        W = Integer.parseInt(st.nextToken());
        int size = (R+1)*100 + (C+1);
        wall = new int[size][size];
        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());

            if(type == 0){
                int a = x * 100 + y;
                int b = (x-1) * 100 + y;

                wall[a][b] = 1;
                wall[b][a] = 1;
            }
            else {
                int a = x * 100 + y;
                int b = x * 100 + (y+1);

                wall[a][b] = 1;
                wall[b][a] = 1;
            }

        }


    }
}