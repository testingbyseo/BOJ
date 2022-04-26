import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Pos{
        int x, y, dir;
        int[][] priority;

        Pos(int x, int y, int dir, int[][] priority){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.priority = priority;
        }
    }
    static class Smell{
        int num, t;

        Smell(int num, int t){
            this.num = num;
            this.t = t;

        }
    }

    static int n;
    static int m;
    static int k;
    static StringBuilder sb;
    static int answer;
    static ArrayList<Smell>[][] map;
    static Pos[] shark;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        shark = new Pos[m+1];
        map = new ArrayList[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int num = Integer.parseInt(st.nextToken());

                if(num != 0){
                    // 상어 정보 저장
                    shark[num] = new Pos(i, j, 0, new int[5][4]);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        //상어 방향 저장
        for(int i=1; i<m+1; i++){
            shark[i].dir = Integer.parseInt(st.nextToken());
        }

        //상어 우선순위 저장
        for(int i=1; i<m+1; i++){
            // 각 마리당 우선순위 배열 생성
            int[][] pri = new int[5][4];

            for(int j=1; j<=4; j++){
                st = new StringTokenizer(br.readLine());
                for(int d=0; d<4; d++){
                    pri[j][d] = Integer.parseInt(st.nextToken());
                }
            }
            // 상어한테 저장!!!!!
            shark[i].priority = pri;
        }

        answer = 0;
        process();



        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }


    static void process() {

        int time = 0;
        while(true){


            if(isOnly() || time > 1000){
                break;
            }

            removeSmell();
            leaveSmell();

            moveShark();
            removeShark();

            time++;
        }

        answer = time <= 1000? time : -1;

    }

    static boolean isOnly(){
        // 1 을 제외하고 상어 남아 있는가?
        for(int i=2; i<m+1; i++){
            if(shark[i].x != -1){
                // 살아 있다
                return false;
            }
        }
        return true;
    }


    static void leaveSmell(){

        for(int i=1; i<m+1; i++){
            // 상어 살아 있으면
            if(shark[i].x != -1){
                int x = shark[i].x;
                int y = shark[i].y;

                // 냄새 남김 (넘버, k)
                map[x][y].add(new Smell(i, k));
            }
        }
    }

    static void moveShark() {

        for(int i=1; i<m+1; i++){
            // 상어 살아 있으면
            if(shark[i].x != -1){
                shark[i] = move(shark[i], i);
            }
        }

    }
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static Pos move(Pos cShark, int sharkNum){

        Pos cur = cShark;
        HashSet<Integer> candi = new HashSet<>();

        // 우선순위 저장되어 있다 ex) 1 2 3 4
        // 현재 초기 방향의 우선순위 정보 참고!!
        int[] idx = cur.priority[cur.dir];

        for(int i=0; i<4; i++){
            int nx = cur.x + dx[idx[i]];
            int ny = cur.y + dy[idx[i]];

            if(nx < 0 || ny < 0 || nx >=n || ny >=n) {
                continue;
            }

            if(map[nx][ny].size() == 0){
                // 아무냄새 없는칸 최우선
                cur.x = nx;
                cur.y = ny;
                cur.dir = idx[i];
                return cur;
            }
            else{
                for(Smell s : map[nx][ny]){
                    // 자신의 냄새
                    if(s.num == sharkNum){
                        // 방향 넣기
                        candi.add(idx[i]);
                    }
                }
            }

        }

        // 아무냄새없칸 없고, 후보 여러개라면
        // -->  현재 상어의 현재 방향 우선순위 확인
        if(candi.size() > 0){

            int nd = -1;

            for(int i=0; i<4; i++){

                for(Integer c : candi){
                    if(idx[i] == c){
                        nd = idx[i];
                        break;
                    }
                }
                // nd 찾으면
                if(nd != -1) break;
            }


            // 찾은 걸로 업뎃
            cur.x = cur.x + dx[nd];
            cur.y = cur.y + dy[nd];
            cur.dir = nd;
        }
        
        return cur;

    }

    static void removeShark() {

        for(int i=1; i<m+1; i++){
            Pos cur = shark[i];
            // 죽었으면 through
            if(cur.x == -1) continue;

            for(int j=i+1; j<m+1; j++){
                Pos next = shark[j];
                if(next.x == -1) continue;

                // 같은 칸에 상어 2마리 이상있을때
                if(next.x == cur.x && next.y == cur.y){
                    // 작은 번호가 승리
                    // 오름차순으로 증가하므로
                    // 다음 상어가 죽는다
                    next.x = -1;
                    next.y = -1;
                    next.dir = -1;
                }
            }
        }
    }
    static void removeSmell(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                ArrayList<Smell> removed = new ArrayList<>();
                if(map[i][j].size() > 0){
                    for(Smell s : map[i][j]){
                        s.t--;
                        // 아직 남아 있으면
                        if(s.t > 0){
                            removed.add(s);
                        }
                    }
                    // copy
                    map[i][j] = removed;
                }
            }
        }
    }
    static void print() {
        int[][] board = new int[4][4];
        for(int i=1; i<m+1; i++){
            board[shark[i].x][shark[i].y] = i;
        }

        for(int i=0; i<n; i++){
            System.out.println();
            for(int j=0; j<n; j++){
                System.out.print(board[i][j]);
            }
        }
    }
}