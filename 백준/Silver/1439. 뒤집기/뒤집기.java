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
    static int answer;
    static String num;
    static int pile_zero;
    static int pile_one;

    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine().trim());
        num = st.nextToken();

        pile_zero = 0;
        pile_one = 0;
        if(num.length() > 0){
            countPile();
        }
        answer = Math.min(pile_one, pile_zero);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
    static void countPile() {

        char cur = num.charAt(0);
        int left = 0;
        int right;
        for(right=0; right<num.length(); right++){
            char nxt = num.charAt(right);
            if(cur == nxt){
                continue;
            }
            else {
                if(cur == '1'){
                    pile_one++;
                }
                else{
                    pile_zero++;
                }
                cur = nxt;
                left = right;
            }
        }

        if(cur == '1'){
            pile_one++;
        }
        else{
            pile_zero++;
        }

    }
}