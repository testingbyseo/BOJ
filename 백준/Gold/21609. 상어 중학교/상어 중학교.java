import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {


    static int n;
    static int m;
    static int[][] map;
    static StringBuilder sb;
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                // 초기에는 모든 격자에 블럭이 들어있다.
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         process();


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    static void process() {
        answer = 0;
        while(true){

            int[] sblock = new int[2];
            int max = findBiggest(sblock);

            // 더이상 블럭 그룹이 없으면
            // 블럭그룹은 항상 2이상
            if(max == 1){
                return;
            }

            // 블럭 없애고 점수 얻는다.
            answer += max * max;

            popBlock(sblock);

            gravity();


            rotate();

            gravity();


        }

    }
    static int findBiggest(int[] sblock){
        boolean[][] visit = new boolean[n][n];
        int max = 0;
        int ret = 0;
        int maxRainbow = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                // black doesnt consist a group
                // rainbow will be counted at dfs
                // 일반블럭 적어도 하나니까 일반 블럭에서 시작
                if(map[i][j] > 0){

                    if(visit[i][j]) continue;

                    ArrayList<Integer>[] path = new ArrayList[2];
                    path[0] = new ArrayList<>();
                    path[1] = new ArrayList<>();
                    path[1].add(0);
                    boolean[][] ballVisit = new boolean[n][n];
                    // 같은 색만 가능하다
                    int color = map[i][j];
                    int num = dfs(i,  j, color, ballVisit, path, 0);

                    // 만났던 "일반"공들 방문처리
                    for(int idx = 0; idx<path[0].size(); idx++){
                        int r = path[0].get(idx) / 100;
                        int c = path[0].get(idx) % 100;

                        visit[r][c] = true;
                    }

                    // 두개 이상부터 그룹이다.
                    if(num < 2) continue;

                    int curRainbow = path[1].get(0);
                    if(max < num){
                        max = num;
                        //기준 블럭 위치
                        ret = (i * 100) + j;
                        maxRainbow = curRainbow;
                    }
                    else if(max == num){ // 오름차순으로 하고 있기때문에 max 동점 해결 가능
                        // 무지개수가 가장 커야하므로 작으면 통과
                        if(maxRainbow > curRainbow) continue;


                        //기준 블럭 위치
                        ret = (i * 100) + j;
                        maxRainbow = curRainbow;

                    }

                }
            }
        }

        // 아무것도 없으면
        if(max == 0) return 1;


        // 최대 크기 블럭 그룹의
        // 기준 블럭 좌표 저장
        sblock[0] = ret / 100;
        sblock[1] = ret % 100;

        return max;
    }
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int dfs(int x, int y, int color, boolean[][] visit, ArrayList<Integer>[] path, int size){
        visit[x][y] = true;

        int cnt = 1;
        if(map[x][y] == color){
            // 일반 색 공들은 자리를 표시해 줌
            path[0].add(size++, x*100 + y);
        }
        else {
            int rainbow = path[1].get(0) + 1;
            path[1].add(0, rainbow);// 무지개공
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 경계선, 방문한거, 다른색, 검은색
            if(nx < 0 || ny < 0 || nx >=n || ny >=n){
                continue;
            }
            // 검은공 or 방문한 곳 pass
            if(map[nx][ny] == -1 || visit[nx][ny]) continue;

            // 방문하지 않은 같은 색 or 그냥 무지개
            if(map[nx][ny] == color || map[nx][ny] == 0){
                cnt += dfs(nx, ny, color, visit, path, size);
            }
        }

        return cnt;
    }
    static void popBlock(int[] sblock){
        int[][] temp = new int[n][n];
        copyMap(map, temp);

        boolean[][] visit = new boolean[n][n];

        // 기준 블럭부터 dfs 로 없앤다.
        // empty == -2
        pop(sblock[0], sblock[1], map[sblock[0]][sblock[1]], visit, temp);

        copyMap(temp, map);
    }
    static void pop(int x, int y, int color, boolean[][] visit, int[][] temp){
        visit[x][y] = true;

        // 빈칸으로 만들어줌
        temp[x][y] = -2;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 경계선, 방문한거, 다른색, 검은색
            if(nx < 0 || ny < 0 || nx >=n || ny >=n){
                continue;
            }
            if(visit[nx][ny]) continue;

            // 방문하지 않은 무지개 or 같은 색
            if(map[nx][ny] == color || map[nx][ny] == 0){
                pop(nx, ny, color, visit, temp);
            }
        }
    }
    static void copyMap(int[][] from, int[][] to){

        for(int i=0; i<n; i++){
            to[i] = from[i].clone();
        }
    }
    static void gravity() {

        for(int j=0; j<n; j++){
            // 가장 아래 행부터 훑는다.
            for(int i=n-1; i >= 0; i--){
                // 무지개거나 일반 색깔 공만 움직일것
                if(map[i][j] == 0 || map[i][j] > 0){
                    int r = i;

                    // 다음이 경계를 넘거나 검은 공이면 진행하지 않는다.
                    while(r+1 < n){
                        if(map[r+1][j] != -2) break;
                        r++;
                    }
                    if(i==r) continue;

                    // 내려줌
                    map[r][j] = map[i][j];

                    // empty
                    map[i][j] = -2;
                }
            }
        }
    }
    static void rotate() {
        int[][] temp = new int[n][n];
        copyMap(map, temp);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                temp[n-1-j][i] = map[i][j];
            }
        }

        copyMap(temp, map);
    }


}