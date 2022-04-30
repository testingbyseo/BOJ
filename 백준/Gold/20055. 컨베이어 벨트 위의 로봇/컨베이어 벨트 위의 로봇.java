import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;


public class Main {
	
	
	static int n;
	static int maxN;
	static int k;
	static int[] line;
	static int[] strength;
	static int answer;
	
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		
		input();
		process();
	
		sb.append(answer);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
				
	}
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		maxN = n*2;
		
		strength = new int[maxN+1];
		line = new int[maxN+1];
		
		st = new StringTokenizer(br.readLine().trim());		
		for(int i=1; i < maxN + 1 ; i++) {
			strength[i] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	static void process() {
		
		answer = 0;
		int lift = 1;
		int down = n;
		// 로못이 있는 인덱
		Queue<Integer> q = new LinkedList<>();
		
		while(true) {
			
			// 한칸 회전 
			lift = lift - 1 == 0? maxN : lift - 1;
			down = down - 1 == 0? maxN : down - 1;
			//가장앞 로봇 내리는위치에 도달했으면 내려준다...
			if(!q.isEmpty()) {
				int idx = q.peek();
				
				if(down == idx) {					
					// 로봇 내림  
					q.poll();
					line[down] = 0;
				}
					
			}
			
			
			// 로봇 이동
			if(!q.isEmpty()) {
				int[] robot = new int[q.size()];
				
				for(int i=0; i<robot.length; i++) {					
					int cur = q.poll();
					int next = cur + 1 == maxN + 1? 1 : cur + 1;
					
					if(line[next] == 0 && strength[next] >= 1) {
						
						if(next == down) {
							// 내리는 위치가 되면 즉시 내린다. 
							robot[i] = 0;
							line[cur] = 0;
							strength[next]--;
							
						}
						else {
							// 로못이 없고 내구성이 1 이상 남아있으면 
							robot[i] = next;

							line[next] = 1;
							line[cur] = 0;
							strength[next]--;
						}
						
					}
					else {
						// 갈수 없으면 그냥 있는다.
						robot[i] = cur;
					}
					
				}
				
				// 로봇 이동 끝나면 q에 넣어준다.
				for(int i=0; i<robot.length;i++) {
					if(robot[i] != 0) {
						q.add(robot[i]);
					}
				}
			}			
			
			
			
			// 올리는 위치에 로봇 올린다..
			if(strength[lift] > 0) {
				line[lift] = 1;
				strength[lift]--;
				q.add(lift);
			}				
							
			answer++;
			
			if(isDone()) {
				return;
			}
		}
		
	}
	static boolean isDone() {
		int sum =0;
		
		for(int i=1; i<maxN+1;i++) {
			if(strength[i] == 0)
				sum++;			
		}
		
		if(sum >= k) return true;
		
		return false;
	}
	
}