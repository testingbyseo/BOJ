import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {


    static StringBuilder sb;
    static BufferedWriter bw;
    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int m;
    static int marvel;
    static ArrayList<Integer> mlist;
    static int[][] map;
    static int[][] query;
    static int one;
    static int two;
    static int three;
    static int shark;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();
        process();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void process(){
        one = 0;
        two = 0;
        three = 0;

        for(int i=0; i<m; i++){


            blizard(query[i]);

            // 구슬 리스트 저장
            getList();
            removeSeries();

            if(mlist.size() <= 0) break;


            // 구슬 변화
            transition();

        }

        int answer = one*1 + two*2 + three*3;
        sb.append(answer);


    }

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static void blizard(int[] q){
        int dir = q[0];
        int slen = q[1];

        int nx = shark;
        int ny = shark;

        // s 만큼 파괴
        for(int i=0; i<slen; i++){

            nx += dx[dir];
            ny += dy[dir];


            // 블리자드에서는 구슬 갯수만 사라짐
            // answer 카운트 되지 않는다
            marvel--;
            map[nx][ny] = 0;
        }

    }
    static int[] rx = {0, 1, 0, -1};
    static int[] ry = {-1, 0, 1, 0};
    static void getList() {
        // 0은 상어
        // 마지막은 하나 덜이동!!
        mlist = new ArrayList<>();

        int x = shark;
        int y = shark;

        int move = 1;
        while(true){

            for(int d=0; d<2; d++){
                int cnt = 0;
                while(cnt++ < move){
                    x += rx[d];
                    y += ry[d];

                    if(y == 0) return;
                    if(map[x][y] == 0) continue;
                    // 구슬의 위치 넣어줌
                    mlist.add(map[x][y]);

                }
            }
            move++;

            for(int d=2; d<4; d++){
                int cnt = 0;
                while(cnt++ < move){
                    x += rx[d];
                    y += ry[d];

                    if(map[x][y] == 0) continue;
                    mlist.add(map[x][y]);
                }
            }
            move++;

        }


    }

    static void countRemove(int curNum){
        // 구슬 수 계속 동기화
        marvel--;

        switch (curNum){
            case 1 : one++; break;
            case 2 : two++; break;
            case 3 : three++; break;
        }
    }

    static void removeSeries() {

        while(mlist.size() > 0){
            // 4개 이상 연속번호 지우기

            int left = 0;
            int right = 0;

            int cur = mlist.get(0);
            while(right <= mlist.size()){

                if(right == mlist.size()){
                    if(right- left >= 4){
                        int color = mlist.get(left);
                        while(left != right){
                            // 몇번 구슬 지웠나?
                            countRemove(color);
                            // 비워줌
                            mlist.set(left, -1);
                            left++;
                        }
                    }
                    break;
                }

                int nxtNum = mlist.get(right);
                if(cur == nxtNum){
                    right++;
                    continue;
                }

                if(right- left >= 4){

                    while(left != right){
                        // 몇번 구슬 지웠나?
                        countRemove(cur);

                        // 비워줌
                        mlist.set(left, -1);
                        left++;
                    }
                }
                else{
                    // 4개 이상 이루지 못하면 pass
                    left = right;
                }
                // left 바뀌면 현재 넘버도 변해야함
                cur = mlist.get(left);
            }


            ArrayList<Integer> copy = new ArrayList<>();

            for(int i=0; i < mlist.size(); i++){
                // -1 된거 빼고 copy에 넣어줌
                if(mlist.get(i) != -1){
                    copy.add(mlist.get(i));
                }
            }

            // 삭제된거 없으면
            if(mlist.size() == copy.size()) return;

            // 갱신된거 복사
            mlist.clear();
            mlist = copy;
        }
        marvel = mlist.size();
    }

    static void transition() {


        // 그룹 세분화
        ArrayList<Integer> temp = new ArrayList<>();

        int left = 0;
        int right = 0;
        int curNum = mlist.get(0);

        while(right <= mlist.size()){

            if(right == mlist.size()){
                // A 그룹 내 구슬 갯수
                int numOf = right - left;
                temp.add(numOf);
                //B 는 구슬 번호
                temp.add(mlist.get(left));
                marvel -= numOf - 2;
                break;
            }

            int nxtNum = mlist.get(right);
            if(curNum == nxtNum){
                right++;
            }
            else{
                // A 그룹 내 구슬 갯수
                int numOf = right - left;
                temp.add(numOf);
                //B 는 구슬 번호
                temp.add(mlist.get(left));

                // 구슬 갯수 계속 동기화
                marvel -= numOf - 2;

                left = right;

                // left 바뀌면 현재 넘버도 변해야함
                curNum = mlist.get(left);
            }
        }


        marvel = inputMap(temp);

    }
    static int inputMap(ArrayList<Integer> temp) {
        // 2가지 경우의 수
        // 구슬의 갯수가 맵크기보다 큰 경우
        // 맵의 크기가 구슬 갯수보다 큰 경우
        // 맵 초기화!!!!!
        map = new int[n+1][n+1];
        int idx = 0;
        int x = shark;
        int y = shark;
        int move = 1;

        while(true){

            for(int d=0; d<2; d++){
                int cnt = 0;
                while(cnt++ < move){
                    x += rx[d];
                    y += ry[d];

                    if(y == 0) return idx;

                    // 구슬의 넣어줌
                    map[x][y] = temp.get(idx++);
                    if(idx == temp.size()) return idx;
                }
            }
            move++;


            for(int d=2; d<4; d++){
                int cnt = 0;
                while(cnt++ < move){
                    x += rx[d];
                    y += ry[d];

                    map[x][y] = temp.get(idx++);
                    if(idx == temp.size()) return idx;
                }
            }
            move++;

        }

    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];

        shark = (n+1)/2;

        for(int i=1; i<n+1; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j=1; j<n+1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    marvel++;
                }
            }
        }

        query = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine().trim());
            // 0 d direction
            // 1 s street
            query[i][0] = Integer.parseInt(st.nextToken());
            query[i][1] = Integer.parseInt(st.nextToken());
        }

    }
}