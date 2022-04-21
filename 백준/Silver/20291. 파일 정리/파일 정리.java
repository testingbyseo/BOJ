import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.*;

public class Main {

    static int n;
    static int answer;
    
    static Map<String, Integer> treeMap;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());        
        treeMap = new TreeMap<>();

        for(int i=0; i<n; i++){
            String file = br.readLine();
            String[] str = file.split("\\.");
            if(str.length > 0){                
                treeMap.put(str[1], treeMap.getOrDefault(str[1], 0) + 1);
            }
        }



        // treeMap
        for(Map.Entry<String, Integer> e : treeMap.entrySet()){
            sb.append(e.getKey()).append(" ").append(e.getValue());
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}