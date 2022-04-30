import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int n;
    static int[][] map;
    static int[][] temMap;
    static StringBuilder sb;
    static int answer;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        temMap = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        process();


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static void process() {
        makeTonado();

    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static void makeTonado() {

        int startX = (n-1)/2;
        int startY = (n-1)/2;

        int cnt = 1;
        while(true){

            // 좌 하
            for(int d=0; d<2; d++){

                for(int i=0; i<cnt; i++){
                    startX += dx[d];
                    startY += dy[d];
                    if(startY == -1) break;
                    if(map[startX][startY] != 0){
                        //모래 만나면 흩뿌리기
                        spread(startX, startY, d);
                    }
                }
            }
            cnt++;

            if(cnt == n+1){
                break;
            }

            // 우 상
            for(int d=2; d<4; d++){
                for(int i=0; i<cnt; i++){
                    startX += dx[d];
                    startY += dy[d];
                    if(map[startX][startY] != 0){
                        //모래 만나면 흩뿌리기
                        spread(startX, startY, d);
                    }

                }
            }
            cnt++;
        }

    }

    static void spread(int x, int y, int dir){
        int xx = x - dx[dir];
        int yy = y - dy[dir];
        int ax = x + dx[dir];
        int ay = y + dy[dir];
        /*System.out.println(x + " " + y + " ");
        System.out.println("yr " + xx + " yc" + yy);
        System.out.println("ar " + ax + " ac" + ay);
*/
        int ori = map[x][y];
        map[x][y] = 0;
        // 1% X
        int one = (int) (ori * 0.01);
        rangeOne(xx, yy, one, dir);

        // 7% Y
        int seven = (int) (ori * 0.07);
        rangeOne(x, y, seven, dir);
        // 2% Y
        int two = (int) (ori * 0.02);
        rangeTwo(x, y, two, dir);

        // 10% a
        int ten = (int) (ori * 0.1);
        rangeOne(ax, ay, ten, dir);
        // 5% a
        int five = (int) (ori * 0.05);
        getFive(ax, ay, five, dir);

        int a = ori - 2*(one + seven + two + ten) - five;
        //System.out.println(a);
        if(ax < 0 || ax >= n || ay < 0 || ay >= n){
            answer += a;
        }
        else
            map[ax][ay] += a;

        //System.out.println(answer);
    }
    //상하, 좌우
    static int[] sdx = {1, -1, 0, 0};
    static int[] sdy = {0, 0, 1, -1};
    static void rangeOne(int curX, int curY, int amount, int dir){

        if(dir == 0 || dir == 2){
            for(int d=0; d<2; d++){
                // 상 하
                int nx = curX + sdx[d];
                int ny = curY + sdy[d];

                if(nx < 0|| nx >= n || ny < 0 || ny >= n){
                    // 경계로 사라진 모래양 더하기
                    answer += amount;
                    continue;
                }
                map[nx][ny] += amount;
            }
        }
        else {
            for(int d=2; d<4; d++){
                // 좌 우
                int nx = curX + sdx[d];
                int ny = curY + sdy[d];

                if(nx < 0|| nx >= n || ny < 0 || ny >= n){
                    // 경계로 사라진 모래양 더하기
                    answer += amount;
                    continue;
                }
                map[nx][ny] += amount;
            }
        }

    }

    static void rangeTwo(int curX, int curY, int amount, int dir){

        if(dir == 0 || dir == 2){
            for(int d=0; d<2; d++){
                // 상 하
                int nx = curX + 2*sdx[d];
                int ny = curY + 2*sdy[d];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                    // 경계로 사라진 모래양 더하기
                    answer += amount;
                    continue;
                }
                map[nx][ny] += amount;
            }
        }
        else {
            for(int d=2; d<4; d++){
                // 좌 우
                int nx = curX + 2*sdx[d];
                int ny = curY + 2*sdy[d];

                if(nx < 0|| nx >= n || ny < 0 || ny >= n){
                    // 경계로 사라진 모래양 더하기
                    answer += amount;
                    continue;
                }
                map[nx][ny] += amount;
            }
        }

    }
    static void getFive(int curX, int curY, int amount, int dir){

        int nx = curX + dx[dir];
        int ny = curY + dy[dir];

        if(nx < 0|| nx >= n || ny < 0 || ny >= n){
            // 경계로 사라진 모래양 더하기
            answer += amount;
        }
        else
            map[nx][ny] += amount;
    }

}