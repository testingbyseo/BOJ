import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
    static class Customer{
        int sx, sy, dx, dy;
        Customer(int sx, int sy, int dx, int dy){
            this.sx = sx;
            this.sy = sy;

            this.dx = dx;
            this.dy = dy;
        }
    }

    static class Pos{
        int x, y, dist;
        Pos(int x, int y, int dist){
            this.x = x;
            this.y = y;

            this.dist = dist;

        }
    }

    static int n;
    static int m;
    static int f;
    static int[][] map;
    static int[] taxi;
    static Customer[] customers;
    static StringBuilder sb;
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        // 배열 채우기
        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<n+1; j++){
                int p = Integer.parseInt(st.nextToken());
                // 벽이랑 손님 번호 구분
                map[i][j] = (p == 1? -1: p);

            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new int[2];
        for(int i=0; i<2; i++){
            taxi[i] = Integer.parseInt(st.nextToken());
        }

        customers = new Customer[m+1];
        for(int i=1; i<m+1; i++){
            st = new StringTokenizer(br.readLine());
            // 1 <= <= N
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            customers[i] = new Customer(sx, sy, dx, dy);
            // 승객 위치
            map[sx][sy] = i;

        }

        answer = -1;
        process();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }

    static void process() {

        for(int i=1; i<=m ;i++){

            int pick = pickCustomer();
            if(f < 0 || pick == -1) return;
            // 목적지까지 필요 연료
            // 최단거리
            // 연료 없으면 종료
            // 도착하면 위치 갱신
            getDestination(pick);
            if(f < 0) return;

        }

        answer = f;

    }

    static int pickCustomer() {

        int min = Integer.MAX_VALUE;
        int num = -1;
        Customer picked = new Customer(-1, -1, -1, -1);

        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][n+1];
        // 현재 택시 위치부터 각각의 승객 or 목적지 위치까지 최단 거리 구하기
        q.add(new Pos(taxi[0], taxi[1], 0));
        visit[taxi[0]][taxi[1]] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            // 현재 위치가 손님이 있는 곳이라면 거리를 구하고 가장 가까운지 판단한다
            if(map[cur.x][cur.y] > 0){
                if(min > cur.dist){
                    min = cur.dist;
                    num = map[cur.x][cur.y];
                    picked = customers[num];
                }
                else if(min == cur.dist){
                    if(picked.sx == cur.x){
                        if(picked.sy > cur.y){
                            num = map[cur.x][cur.y];
                            picked = customers[num];
                        }
                    }
                    else{
                        if(picked.sx > cur.x){
                            num = map[cur.x][cur.y];
                            picked = customers[num];
                        }
                    }
                }
            }


            for(int i=0; i<4; i++){
                int nx = cur.x + bdx[i];
                int ny = cur.y + bdy[i];
                int ndist = cur.dist + 1;

                if(nx <= 0 || ny <= 0 || nx > n || ny > n) continue;
                if(map[nx][ny] == -1) continue;

                if(!visit[nx][ny]){
                    q.add(new Pos(nx, ny, ndist));
                    visit[nx][ny] = true;
                }

            }
        }


        // 승객 정해지면 택시가 갑니다.
        taxi[0] = picked.sx;
        taxi[1] = picked.sy;
        f -= min;


        return num;

    }
    static int[] bdx = {0, 0, 1, -1};
    static int[] bdy = {1, -1, 0, 0};
    static int bfs(int desX, int desY){

        Queue<Pos> q = new LinkedList<>();
        boolean[][] visit = new boolean[n+1][n+1];
        // 현재 택시 위치부터 승객 or 목적지 위치까지 최단 거리 구하기
        q.add(new Pos(taxi[0], taxi[1], 0));
        visit[taxi[0]][taxi[1]] = true;
        
        int min = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Pos cur = q.poll();
            // 목적지 구하기
            if(cur.x == desX && cur.y == desY){
                min = cur.dist;
                break;
            }

            for(int i=0; i<4; i++){
                int nx = cur.x + bdx[i];
                int ny = cur.y + bdy[i];
                int ndist = cur.dist + 1;

                if(nx <= 0 || ny <= 0 || nx > n || ny > n) continue;
                if(map[nx][ny] == -1) continue;

                if(!visit[nx][ny]){
                    q.add(new Pos(nx, ny, ndist));
                    visit[nx][ny] = true;
                }

            }
        }

        return min;
    }

    static void getDestination(int pick){

        Customer cur = customers[pick];

        // 탑승 승객 목적지 위치 구하기
        int distance = bfs(cur.dx, cur.dy);

        // 목적지에 도착 후 연료 거리만틈 소진
        f -= distance;
        // 목적지 가다가 연료부족하면
        if(f < 0) return;
        // 목적지까지 운행 가능하면 운행 시작
        // 현재 위치 빈칸 만들기
        map[cur.sx][cur.sy] = 0;

        // 목적지 정상 도착 후 택시 위치 갱신
        taxi[0] = cur.dx;
        taxi[1] = cur.dy;

        // 손님 완료 목록
        customers[pick].sx = -1;
        customers[pick].sy = -1;
        customers[pick].dx = -1;
        customers[pick].dy = -1;

        // 연료 채우기
        f += distance*2;
    }



}