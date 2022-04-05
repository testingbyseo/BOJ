
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st;
        String str;
        while((str = br.readLine()) != null){
            st = new StringTokenizer(str);
            
            String s = st.nextToken();
            String t = st.nextToken();
            if(s.length() > t.length()){
                sb.append("No").append("\n");
            }
            else{
                check(s, t);
            }


        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static void check(String s, String t){
        int idx = 0;
        int size = s.length();
        for(int i=0; i<t.length();i++){
            if(s.charAt(idx) == t.charAt(i)){
                idx++;
            }
            if(idx == size) {
                sb.append("Yes").append("\n");
                return;
            }
        }

        sb.append("No").append("\n");

    }


}