import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos>{
        int x, y, num, dir;
        Pos(int x, int y, int num, int dir){
            this.x = x;
            this.y = y;
            this.num = num;
            this.dir = dir;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.num < o.num) return -1;
            return 1;
        }
    }

    static int n;
    static StringBuilder sb;
    static int answer;
    static Pos[][] map;
    static Pos shark;
    static Pos[] fish;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        map = new Pos[4][4];
        fish = new Pos[17];
        for(int i=0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                int fnum = Integer.parseInt(st.nextToken());
                int fdir = Integer.parseInt(st.nextToken());

                map[i][j] = new Pos(i, j, fnum, fdir);
                if(fnum > 0) fish[fnum] = map[i][j];
            }
        }


        // 처음 상어는 0,0 위치, 물고기 방향 흡수
        Pos first = map[0][0];
        answer = first.num;
        fish[first.num] = new Pos(0, 0, 0, first.dir);

        shark = new Pos(0, 0, -1, map[0][0].dir);
        map[0][0] = shark; // num == -1


        process(map, fish, shark, answer);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();

    }
    static void print(Pos[][] m){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(m[i][j].num + " ");
            }
            System.out.println();
        }
    }
    static void copyMap(Pos[][] from, Pos[][] to, Pos[] fromFish, Pos[] toFish){
        for(int i=0; i<4; i++){
            to[i] = from[i].clone();
        }

        for(int i=1; i<17; i++){
            toFish[i] = fromFish[i];
        }
    }
    static int[] sdx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] sdy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static void process(Pos[][] curMap, Pos[] curFish, Pos curShark, int cnt) {

        if(answer < cnt){
            answer = cnt;
        }

        Pos[][] moved = new Pos[4][4];
        Pos[] movedFish = new Pos[17];
        copyMap(curMap, moved, curFish, movedFish);
        // 물고기 이동
        moveFish(moved, movedFish);
        

        // 상어 이동
        int nx = curShark.x;
        int ny = curShark.y;
        int dir = curShark.dir;

        Pos empty = new Pos(curShark.x, curShark.y, 0, 0);
        while(true){ // 상어 여러번 이동 가능!!@

            nx += sdx[dir];
            ny += sdy[dir];

            // 더 이상 이동할 수 없거나 주변이 물고기가 없다면
            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4){
                break;
            }
            else if(moved[nx][ny].num == 0){
                continue;
            }

            if(moved[nx][ny].num != 0){
                Pos[][] temp = new Pos[4][4];
                Pos[] tempFish = new Pos[17];
                copyMap(moved, temp, movedFish, tempFish);

                // 상어 자리 이동
                // 원래 위치 빈자리 만들고
                temp[curShark.x][curShark.y] = empty;
                // fish 죽이기
                Pos fish = temp[nx][ny];
                tempFish[fish.num] = new Pos(nx, nx, 0, 0);
                // 물고기 먹었으니까 상어 방향 업데이트
                int ndir = fish.dir;
                int ncnt = cnt + fish.num;
                Pos nShark = new Pos(nx, ny, -1, ndir);
                temp[nx][ny] = nShark;

                process(temp, tempFish, nShark, ncnt);
            }

        }

    }

    static void moveFish(Pos[][] curMap, Pos[] curFish){

        for(int i=1; i<17; i++){
            // fish live
            if(curFish[i].num > 0){
                Pos cur = curFish[i];
                int dir = cur.dir;

                for(int d=0; d<8; d++){
                    int nx = cur.x + sdx[dir];
                    int ny = cur.y + sdy[dir];

                    if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4){
                        // 상어 없는 칸
                        // 물고기 있거나 빈칸
                        if(curMap[nx][ny].num != -1){

                            Pos tmp = curMap[nx][ny];
                            // 지금 물고기 다음자리에 넣어줌 - 방향성 유지
                            curMap[nx][ny] = new Pos(nx, ny, cur.num, dir);
                            // 원래 자리에 다음 자리 객체 넣어줌
                            curMap[cur.x][cur.y] = new Pos(cur.x, cur.y, tmp.num, tmp.dir);

                            //curFish 배열 업뎃
                            curFish[cur.num] = curMap[nx][ny];
                            curFish[tmp.num] = curMap[cur.x][cur.y];
                            // 한번 이동후 종료
                            break;
                        }
                    }

                    // 반시계 방향 45도 회전
                    dir = (dir+1 == 9? 1 : dir+1);
                }
            }
        }

    }




}