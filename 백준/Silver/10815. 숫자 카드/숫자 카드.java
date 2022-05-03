import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		int i = 0;
		int c;
		Integer num;
		//Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> set = new HashSet();
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(i=0; i < N; i++) {
			num = Integer.parseInt(stk.nextToken());
			set.add(num);
		}
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		stk = new StringTokenizer(br.readLine());
		for(i=0;i<M; i++) {
			c = Integer.parseInt(stk.nextToken());
			if(set.contains(c)) {
				sb.append("1 ");
			}else
			{
				sb.append("0 ");
			}
			
		}
		System.out.print(sb);
	
	}
	

	
}
