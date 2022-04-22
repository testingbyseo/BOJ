import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Pos{
        int x, y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    static int n;
    static int m;
    static int[][] map;
    static StringBuilder sb;
    static int noVirus;
    static Pos[] path;
    static int minVirus;
    static int answer;
    static ArrayList<Pos> virus;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        noVirus = 0;
        virus = new ArrayList<>();
        answer = 0;
        minVirus = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                // 초기 0의 갯수 저장
                if(map[i][j] == 0) noVirus++;

                // 초기 바이러스 위치 저장
                if(map[i][j] == 2) {
                    virus.add(new Pos(i, j));
                }
            }
        }


        path = new Pos[3];
        buildWall();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }


    static void buildWall() {
        boolean[][] visit = new boolean[n][m];
        dfs(0, visit);

    }
    static void dfs(int idx, boolean[][] visit) {

        if(idx == 3){
            // 안전영역 갯수
            // 1. 감염된 곳 구하기
            // 2. maxSafeZone
            //      = noVirus(초기 0 갯수) - 3(새 벽) - minVirus (새로 감염된 곳)
            int num = numVirusZone();
            if(minVirus > num){
                minVirus = num;
                answer = noVirus - 3 - num;

            }
            return;
        }


        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){

                //방문하지 않고, 빈칸인 곳에 벽을 세울 수 있다.
                if(!visit[i][j] && map[i][j] == 0){
                    path[idx] = new Pos(i, j);
                    visit[i][j] = true;
                    dfs(idx+1, visit);
                    visit[i][j] = false;
                }
            }
        }


    }
    static int numVirusZone(){

        int[][] tempMap = new int[n][m];
        copyMap(map, tempMap);

        // 벽 세움
        for(Pos w : path){
            tempMap[w.x][w.y] = 1;
        }

        int num = 0;
        for(Pos v : virus){
            num += getContagious(v, tempMap);
        }

        return num;
    }
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int getContagious(Pos v, int[][] tempMap){

        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[n][m];
        q.add(new Pos(v.x, v.y));
        visit[v.x][v.y] = true;

        // 처음 바이러스 시작 non count
        int cnum = -1;
        while (!q.isEmpty()){
            Pos cur = q.poll();
            cnum++;

            for(int i=0 ; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visit[nx][ny] && tempMap[nx][ny] == 0){
                    tempMap[nx][ny] = 2;
                    q.add(new Pos(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }

        return cnum;

    }
    static void copyMap(int[][] from, int[][] to){
        for(int i=0; i<n; i++){
            to[i] = from[i].clone();
        }
    }

}