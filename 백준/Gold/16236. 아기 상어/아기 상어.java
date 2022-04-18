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
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer;
    static int mindist, minX, minY;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());

                if(board[i][j] == 9) {
                    minX = i;
                    minY = j;
                    board[i][j] = 0;
                }
            }
        }


        answer = 0;
        int[] shark = {2, 0};
        int dist = 0;
        while(true){

            int preX = minX;
            int preY = minY;
            // System.out.print(minX + " "+ minY + " -> ");
            Pos start = new Pos(minX, minY, 0);
            bfs(start, shark[0]);
            // System.out.println(minX + " "+ minY + " ");

            if(preX != minX || preY != minY){
                // num of eaten fish
                shark[1]++;

                if(shark[0] == shark[1]){
                    shark[0]++;
                    shark[1] = 0;
                }
                board[minX][minY] = 0;
                dist += mindist;

            }
            else{
                answer = dist;
                break;
            }

        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void bfs(Pos start, int sharkSize){

        Queue<Pos> q = new LinkedList<>();
        visit = new boolean[n][n];
        mindist = Integer.MAX_VALUE;


        q.add(start);
        visit[start.x][start.y] = true;

        while(!q.isEmpty()){
            Pos cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;

                if(!visit[nx][ny] && board[nx][ny] <= sharkSize){
                    int nextDist = cur.dis + 1;
                    q.add(new Pos(nx, ny, nextDist));
                    visit[nx][ny] = true;

                    if(board[nx][ny] < sharkSize && board[nx][ny] != 0){
                        if(nextDist <= mindist){
                            if(nextDist == mindist){
                                if(minX == nx){
                                    if(minY > ny){
                                        minX = nx;
                                        minY = ny;
                                        mindist = nextDist;
                                    }
                                }
                                else if(minX > nx){
                                    minX = nx;
                                    minY = ny;
                                    mindist = nextDist;
                                }
                            }
                            else{
                                minX = nx;
                                minY = ny;
                                mindist = nextDist;
                            }



                        }

                    }
                }
            }
        }

    }


    static class Pos{

        int x, y, dis;

        Pos(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;

        }
    }

}