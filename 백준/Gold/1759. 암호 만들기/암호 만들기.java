
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
/*

 */


public class Main {

    static StringBuilder sb;
    static int l;
    static int c;
    static String[] arr;
    static String[] vow = {"a", "e", "i", "o", "u"};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new String[c];
        for(int i=0; i<c ; i++){
            arr[i] = st.nextToken();
         }
        Arrays.sort(arr);

        dfs(0, 0, 0, "");


        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
    }

    static void dfs(int idx, int len, int vowel, String word){

        if(len == l){
            if(vowel >= 1 && (len - vowel) >= 2){
                sb.append(word).append("\n");
                //System.out.println(sb);
            }
            return;
        }

        if(idx == c) return;

        int next = vowel;
        if(isVowel(arr[idx])){
            next++;
        }
        dfs(idx+1, len+1, next, word.concat(arr[idx]));

        dfs(idx+1, len, vowel, word);
    }

    static boolean isVowel(String w){
        for(int i=0; i<5; i++){
            if(w.equals(vow[i])){
                return true;
            }
        }
        return false;
    }



}