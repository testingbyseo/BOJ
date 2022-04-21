import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int x, y, dir;
        Pos(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int n;
    static int s;
    static Pos shark;
    static ArrayList<Pos>[][] map;
    static int[][] fishSmellMap;
    static int[] path;
    static int[] arr;
    static int maxKill;
    static int answer;


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        map = new ArrayList[5][5];
        fishSmellMap = new int[5][5];
        init(map);

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            Pos fish = new Pos(x, y, dir);
            map[x][y].add(fish);
        }
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        shark = new Pos(sx, sy, 0);

        process();
        count();


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static void print() {
        for(int i=1; i<5; i++){
            for(int j =1; j<5; j++){
                int size = map[i][j].size();
                if( size > 0){
                    for(Pos fi : map[i][j]){
                        System.out.println(i + " " + j + " " + fi.dir);
                    }

                }
            }
        }
        System.out.println();
    }
    static void init(ArrayList<Pos>[][] M){
        for(int i=1; i<5; i++){
            for(int j =1; j<5; j++){
                M[i][j] = new ArrayList<>();
            }
        }
    }
    static void count () {

        for(int i=1; i<5; i++){
            for(int j =1; j<5; j++){
                int size = map[i][j].size();
                if( size > 0){
                    answer += size;
                }
            }
        }
    }

    static void process(){
        while(s-- > 0){
            ArrayList<Pos>[][] copyMap = new ArrayList[5][5];
            init(copyMap);

            copyMagic(map, copyMap);
            moveFish();
            // System.out.println("after move fish");
            // print();
            removeSmell();
            path = new int[3];
            arr = new int[3];
            moveShark();

            // removeSmell();
            afterMagic(copyMap);
            // System.out.println(s);
            // print();

        }
    }

    static void copyMagic(ArrayList<Pos>[][] from, ArrayList<Pos>[][] to) {
        for(int i=1; i<5; i++){
            for(int j =1; j<5; j++){
                to[i][j] = (ArrayList<Pos>) from[i][j].clone();
            }
        }
    }
    static int[] fdx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fdy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Pos move(Pos fish){

        int dir = fish.dir;
        for(int d=0; d<8; d++){
            int nx = fish.x + fdx[dir];
            int ny = fish.y + fdy[dir];

            if(isOkay(nx, ny)){
                Pos next = new Pos(nx, ny, dir);
                return next;
            }
            dir = rotate(dir);
        }
        return fish;
    }

    static boolean isOkay(int nx, int ny){
        if(nx < 1 || nx > 4 || ny < 1 || ny > 4) return false;
        if ((nx == shark.x && ny == shark.y) || fishSmellMap[nx][ny] != 0) return false;

        return true;
    }
    static int rotate(int dir){
        dir--;
        if( dir == 0 ) {
            return 8;
        }
        return dir;
    }

    static void moveFish() {
        ArrayList<Pos>[][] copy = new ArrayList[5][5];
        init(copy);

        for(int i = 1; i <5; i++){
            for(int j=1; j<5; j++){
                if(map[i][j].size() > 0){
                    for(Pos f : map[i][j]){
                        Pos fish = move(f);
                        copy[fish.x][fish.y].add(fish);
                    }
                }
            }
        }
        copyMagic(copy, map);
    }

    static int[] sdx = {0, -1, 0, 1, 0};
    static int[] sdy = {0, 0, -1, 0, 1};
    static int killFish(){

        Pos cur = new Pos(shark.x, shark.y, 0);
        int num = 0;
        boolean[][] visit = new boolean[5][5];

        for(int i=0; i<3; i++){
            int dir = path[i];
            int nx = cur.x + sdx[dir];
            int ny = cur.y + sdy[dir];

            if(nx < 1 || nx > 4 || ny < 1 || ny > 4)
                return -1;
            if(!visit[nx][ny]){
                visit[nx][ny] = true;
                num += map[nx][ny].size();
            }
            cur.x = nx;
            cur.y = ny;
        }
        return num;

    }
    static void dfs(int idx){

        if(idx == 3){
            int kill = killFish();
            if(maxKill < kill){
                for(int i=0 ;i<3; i++){
                    arr[i] = path[i];
                }
                maxKill = kill;
            }
            return;
        }

        for(int i = 1; i<=4; i++){
            path[idx] = i;
            dfs(idx + 1);
        }

    }
    static void moveShark() {

        maxKill = -1;
        dfs(0);

        int x = shark.x;
        int y = shark.y;
        for(int i=0; i<3; i++){
            int dir = arr[i];
            int nextX = x + sdx[dir];
            int nextY = y + sdy[dir];

            if(map[nextX][nextY].size() > 0){
                fishSmellMap[nextX][nextY] = 2;
                map[nextX][nextY].clear();
            }
            x = nextX;
            y = nextY;
            shark.x = x;
            shark.y = y;

        }

    }

    static void removeSmell() {
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){

                if(fishSmellMap[i][j] > 0){
                    fishSmellMap[i][j]--;
                }
            }
        }

    }

    static void afterMagic(ArrayList<Pos>[][] copy) {
        for(int i=1; i<5; i++){
            for(int j =1; j<5; j++){

                if(copy[i][j].size() > 0){
                    for(Pos fish : copy[i][j]){
                        map[fish.x][fish.y].add(fish);
                    }
                }
            }
        }
    }
}