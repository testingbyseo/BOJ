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
    static Student[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        int size = n*n + 1;
        arr = new Student[size];

        for(int i=1; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());

            ArrayList<Integer> flist = new ArrayList<>();
            for(int j=0; j<4; j++){
                flist.add(Integer.parseInt(st.nextToken()));
            }


            arr[number] = new Student(number, flist);
            findSeat(arr[number]);
        }


        answer = 0;
        isGood();


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    static void isGood(){

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int now = board[i][j];
                int numFav = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    int next = board[nx][ny];
                    if (arr[now].fav.contains(next)) {
                        numFav++;
                    }
                }

                switch (numFav){
                    case 1 :
                        answer += 1;
                        break;
                    case 2 :
                        answer += 10;
                        break;
                    case 3 :
                        answer += 100;
                        break;
                    case 4 :
                        answer += 1000;
                        break;
                }

            }
        }


    }

    static void findSeat(Student current){
        int maxFav = -1;
        int maxEmpty = -1;
        int X = 0, Y = 0;

        for(int i=0; i<n;i++){
            for(int j=0; j<n; j++){
                if(board[i][j] != 0) continue;

                int numFav = 0;
                int numEmpty = 0;

                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                    if (board[nx][ny] == 0) numEmpty++;
                    else {
                        if (current.fav.contains(board[nx][ny])) numFav++;
                    }
                }

                if(maxFav < numFav){
                    X = i;
                    Y = j;
                    maxFav = numFav;
                    maxEmpty = numEmpty;
                }
                else if(maxFav == numFav){
                    if(maxEmpty < numEmpty){
                        X = i;
                        Y = j;
                        maxEmpty = numEmpty;
                    }
                    else{
                        if(X == i){
                            if(Y > j){
                                X = i;
                                Y = j;
                            }
                        }
                        else if(X > i){
                            X = i;
                            Y = j;
                        }
                    }
                }
            }
        }


        board[X][Y] = current.num;

    }




    static class Student{

        int num;
        ArrayList<Integer> fav;

        Student(int num,  ArrayList<Integer> fav){
            this.num = num;
            this.fav = fav;
        }
    }

}