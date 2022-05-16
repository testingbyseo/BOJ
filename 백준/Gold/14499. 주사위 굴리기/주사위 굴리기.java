import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static class Dice {
        int top, bottom, front, rear, left, right;
        public Dice() { }

        public Dice(int top, int bottom, int front, int rear, int left, int right) {
            this.top = top;
            this.bottom = bottom;
            this.front = front;
            this.rear = rear;
            this.left = left;
            this.right = right;
        }
    }

    static StringBuilder sb;
    static BufferedWriter bw;
    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int m;
    static int x;
    static int y;
    static int q;
    static int[][] map;
    static int[] queries;
    static Dice dice;
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        queries = new int[q];
        map = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine().trim());
        for (int i=0; i<q; i++){
            queries[i] = Integer.parseInt(st.nextToken());
        }

        dice = new Dice(0,0, 0, 0, 0, 0);
        for (int i=0; i<q; i++){

            if(!rollDice(queries[i])){
                continue;
            }
            getNumber();
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static boolean rollDice(int dir){
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if(nx < 0 || ny < 0 || nx >= n || ny >=m)
            return false;

        // 이동이 가능하면 이동해준다.
        x = nx;
        y = ny;
        Dice moved = new Dice();
        switch (dir) {
            case 1:
                moved.bottom = dice.right;
                moved.top = dice.left;
                moved.front = dice.front;
                moved.rear = dice.rear;
                moved.right = dice.top;
                moved.left = dice.bottom;
                break;
            case 2:
                moved.bottom = dice.left;
                moved.top = dice.right;
                moved.front = dice.front;
                moved.rear = dice.rear;
                moved.left = dice.top;
                moved.right = dice.bottom;
                break;
            case 3:
                moved.bottom = dice.front;
                moved.top = dice.rear;
                moved.front = dice.top;
                moved.rear = dice.bottom;
                moved.right = dice.right;
                moved.left = dice.left;
                break;
            case 4:
                moved.bottom = dice.rear;
                moved.top = dice.front;
                moved.front = dice.bottom;
                moved.rear = dice.top;
                moved.right = dice.right;
                moved.left = dice.left;
                break;
        }

        // update dice
        dice = moved;
        return true;
    }
    static void getNumber() {
        int mapVal = map[x][y];
        if(mapVal == 0){
            map[x][y] = dice.bottom;
        }
        else {
            dice.bottom = mapVal;
            map[x][y] = 0;
        }
        sb.append(dice.top).append("\n");
    }
}