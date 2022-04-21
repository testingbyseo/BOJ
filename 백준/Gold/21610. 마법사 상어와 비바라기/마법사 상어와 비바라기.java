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
    static int[][] arr;
    static StringBuilder sb;
    static ArrayList<Pos>[] cloud;
    static ArrayList<Pos>[] preCloud;
    static int[][] queries;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer. parseInt(st.nextToken());
        m = Integer. parseInt(st.nextToken());
        arr = new int[n+1][n+1];
        queries = new int[m][2];

        cloud = new ArrayList[n+1];
        preCloud = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) {
            cloud[i] = new ArrayList<>();
            preCloud[i] = new ArrayList<>();
            if(i >= n-1){
                // 구름 시작 위치
                cloud[i].add(new Pos(i, 1));
                cloud[i].add(new Pos(i, 2));
            }
        }


        for(int i=1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<n+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            queries[i][0] = d;
            queries[i][1] = s;
        }


        process();
        count();


        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }
    static void count() {
        int sum = 0;
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++){
                sum += arr[i][j];
            }
        }
        sb.append(sum);
    }

    static void process() {

        for(int i=0; i<m; i++) {
            moveCloud(queries[i]);

            increaseWater();

            // with vanish last cloud
            createCloud();
            // System.out.println("i " + i);
            // print();
        }
    }
    static void print() {
        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }


    static void moveCloud(int[] query) {

        ArrayList<Pos>[] copy = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) {
            copy[i] = new ArrayList<>();
        }

        for(int i=1; i<n+1; i++){
            if(cloud[i].size() > 0) { // 구름이 있는 곳
                for(Pos c : cloud[i]) {
                    Pos next = move(c, query);
                    copy[next.x].add(next);
                    /*System.out.println(" orix oriy " + c.x + " " + c.y);
                    System.out.println(" nx ny " + next.x + " " + next.y);*/
                    // 구름 이동한 바구니 물 증가
                    arr[next.x][next.y]++;
                }
            }
        }

        copyCloud(copy, cloud); // 클라우드 위치 갱신 끝
        // 구름 사라지기전 저장
        copyCloud(cloud, preCloud);
    }
    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1};
    static Pos move(Pos cur, int[] query) {

        int nx = cur.x;
        int ny = cur.y;
        int d = query[0];
        int s = query[1];

        for(int i=0; i < s; i++){
            nx += dx[d];
            ny += dy[d];

            // map 연결
            if(nx < 1 || nx > n || ny < 1 || ny > n){
                nx = rotate(nx);
                ny = rotate(ny);
            }
        }
        Pos next = new Pos(nx, ny);
        return next;
    }
    static int rotate(int idx){
        if(idx == 0){
            return n;
        }
        else if( idx == n+1){
            return 1;
        }
        return idx;
    }
    static void copyCloud(ArrayList<Pos>[] from, ArrayList<Pos>[] to) {

        for(int i=1; i<n+1; i++){
            to[i] = (ArrayList<Pos>) from[i].clone();
        }

    }

    static int[] bdx = {1, 1, -1, -1};
    static int[] bdy = {1, -1, 1, -1};
    static void increaseWater() {

        for(int i = 1; i < n+1; i++){
            if(cloud[i].size() > 0){
                for(Pos cur : cloud[i]){

                    int water = 0;
                    for(int k=0; k<4; k++){
                        int nx = cur.x + bdx[k];
                        int ny = cur.y + bdy[k];

                        if(nx < 1 || nx > n || ny < 1 || ny > n) continue;
                        if(arr[nx][ny] != 0){
                            water++;
                        }
                    }

                    arr[cur.x][cur.y] += water;
                }
            }
        }

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){

            }
        }

    }

    static void createCloud() {
        ArrayList<Pos>[] copy = new ArrayList[n+1];
        for(int i=1; i<n+1; i++) {
            copy[i] = new ArrayList<>();
        }

        for(int i=1; i<n+1; i++){
            for(int j=1; j<n+1; j++){
                if(arr[i][j] >= 2){
                    if(!hasCloud(i, j)){
                        arr[i][j] -= 2;
                        copy[i].add(new Pos(i, j));
                    }
                }
            }
        }

        //새 구름 정보 저장
        copyCloud(copy, cloud);

    }
    static boolean hasCloud(int x, int y){
        if(preCloud[x].size() > 0){
            for(Pos c : preCloud[x]){
                if(c.y == y){
                    return true;
                }
            }
        }
        return false;

    }


}