import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int n;
    static int l;
    static int r;

    static int[][] map;
    static boolean[][] visit;
    static ArrayList<int[]> list;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];


        for(int i=0; i<n ; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0 ;j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isUpdated = false;
        int count = 0;
        // move
        while(true){

            visit = new boolean[n][n];
            for(int i=0; i<n ;i++){
                for(int j=0; j<n; j++){
                    if(!visit[i][j]){
                        list = new ArrayList<>();
                        int sum = move(i, j);
                        
                        if(list.size() > 1){
                            int avg = sum / list.size();
                            for(int[] p : list){
                                map[p[0]][p[1]] = avg;
                            }
                            isUpdated = true;
                        }
                    }
                }
            }

            if(!isUpdated) break;
            count++;
            isUpdated = false;

        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    static int move(int x, int y){
        visit[x][y] = true;

        list.add(new int[] {x, y});
        int sum = map[x][y];

        for(int i=0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            int diff = Math.abs(map[x][y] - map[nx][ny]);
            if(diff >= l && diff <= r && !visit[nx][ny]){
                sum += move(nx, ny);
            }
        }

        return sum;
    }

    static boolean check(){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];


        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()){
            int[] cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if(!visited[nx][ny]){
                    int diff = Math.abs(map[cur[0]][cur[1]] - map[nx][ny]);
                    if(diff >= l && diff <= r){
                        // 인구이동 실행
                        return false;
                    }
                    else{
                        q.add(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return true;

    }



}