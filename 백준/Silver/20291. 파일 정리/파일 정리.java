import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {


    static int n;
    static int answer;
    static HashMap<String, Integer> hm;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        hm = new HashMap<>();

        for(int i=0; i<n; i++){
            String file = br.readLine();
            String[] str = file.split("\\.");

            if(str.length > 0){
                hm.put(str[1], hm.getOrDefault(str[1], 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> entries = new LinkedList<>(hm.entrySet());

        Collections.sort(entries, Comparator.comparing(Map.Entry::getKey));

        for(Map.Entry<String, Integer> e : entries){
            sb.append(e.getKey()).append(" ").append(e.getValue());
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}