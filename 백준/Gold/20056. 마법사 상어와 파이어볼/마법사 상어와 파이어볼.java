import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Ball {
        int r, c, m, s, d;
        Ball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }


    static int n;
    static int m;
    static int k;
    static ArrayList<Ball>[][] map;
    static StringBuilder sb;
    static int answer;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n+1][n+1];
        init(map);

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new Ball(r, c, m, s, d));
        }

        process();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static void print() {
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(map[i][j].size() > 0){
                    for(Ball b : map[i][j]){
                        System.out.println(b.r + " " + b.c + " " + b.m + " " + b.s + " " + b.d);
                    }
                }
            }
        }
    }

    static void init(ArrayList<Ball>[][] list){
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                list[i][j] = new ArrayList<>();
            }
        }
    }
    static void process(){

        while(k-- > 0){
            move();
            divide();
            // System.out.println("k " + k);
            // print();
        }
        countMass();
    }
    static void countMass() {
        answer = 0;
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(map[i][j].size() > 0){
                    for(Ball b : map[i][j]){
                        answer += b.m;
                    }
                }
            }
        }
    }
    static void copyMap(ArrayList<Ball>[][] from, ArrayList<Ball>[][] to){
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                to[i][j] = from[i][j];
            }
        }
    }

    static void move() {
        ArrayList<Ball>[][] tempMap = new ArrayList[n+1][n+1];
        init(tempMap);

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(map[i][j].size() > 0){
                    for(Ball b : map[i][j]){
                        Ball moved = moveBall(b);
                        tempMap[moved.r][moved.c].add(moved);
                    }
                }
            }
        }
        copyMap(tempMap, map);

    }
    static int[] fdx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] fdy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Ball moveBall(Ball b){
        int bx = b.r;
        int by = b.c;
        int dir = b.d;
        int time = b.s;

        while(time-- > 0){
            bx += fdx[dir];
            by += fdy[dir];

            if(bx <= 0 || bx > n || by <= 0 || by > n){
                bx = rotate(bx);
                by = rotate(by);
            }
        }

        Ball moved = new Ball(bx, by, b.m, b.s, dir);
        return moved;
    }
    static int rotate(int idx){
        if(idx == 0) return n;
        if(idx == n+1) return 1;

        return idx;
    }

    static void divide() {
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){

                if(map[i][j].size() >= 2){
                    int dm = getMass(i, j);
                    int ds = getSpeed(i, j);
                    int dir = getDir(i, j);

                    map[i][j].clear();

                    if(dm > 0){
                        for(int k=0; k<=6; k+=2){
                            Ball next = new Ball(i, j, dm, ds, dir + k);
                            map[i][j].add(next);
                        }
                    }

                }
            }
        }
    }
    static int getMass(int r, int c){
        int sum = 0;

        for(Ball b : map[r][c]){
            sum += b.m;
        }

        sum = (int) Math.floor((double) sum / 5);

        return sum;
    }
    static int getSpeed(int r, int c){
        int sum = 0;

        for(Ball b : map[r][c]){
            sum += b.s;
        }

        sum = (int) Math.floor((double) sum / map[r][c].size());
        return sum;
    }
    static int getDir(int r, int c){

        int sDir = map[r][c].get(0).d;
        boolean first = isEven(sDir);

        boolean isSame = true;
        for(Ball b : map[r][c]){
            int dir = b.d;
            if(first != isEven(dir)){
                isSame = false;
                break;
            }
        }

        if(isSame) return 0;

        return 1;
    }
    static boolean isEven(int dir){
        if(dir % 2 == 0){
            return true;
        }
        return false;
    }

}