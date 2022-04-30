import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Main {
	static class Fireball {
		int x, y, m, s, d;
		Fireball(int x,int y, int m, int s, int d){
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int n;
	static int m;
	static int k;
	static Queue<Fireball> q;
	static ArrayList<Fireball>[][] map;
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
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
				
	}
	static void init(ArrayList<Fireball>[][] arrlist) {
		for(int i=0; i<n+1; i++) {
			for(int j=0; j<n+1; j++) {
				arrlist[i][j] = new ArrayList<>();
			}
		}
	}
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[n+1][n+1];
		init(map);
		
		q= new LinkedList<>();		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());			
			
			
			// 이동 처리해야할 파이어볼을 큐에 저장한다...			
			q.add(new Fireball(x, y, m, s, d));			
		}
	}
	
	
	static void process() {
				
		// K 번 명령후 결과 출력..
		for(int i=0; i<k; i++) {
			
			// 큐가 비었으면 바로 결과 출력  
			if(q.isEmpty()) break;
			
			while(!q.isEmpty()) {
				Fireball cur = q.poll();
				// 파이어 볼 하나씩 꺼내서 이동한다..
				// 맵에 저장된다...
				moveFireball(cur);				
			}
			
			// 합치고 나누는 작업 시행... 다하고 q에 넎는다.
			controlFireball();				
			
		}
		
		answer = 0;
		answer = sumMass();
		
		sb.append(answer);		
		
	}
	
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};	
	static void moveFireball(Fireball cur){
		int cx = cur.x;
		int cy = cur.y;
		int cm = cur.m;
		int cs = cur.s;
		int cd = cur.d;
		
		
		int nx = cx;
		int ny = cy;
		for(int i=0; i<cs; i++) {
			nx += dx[cd];
			ny += dy[cd];
			
			// 경계 처리
			if(nx < 1 || ny < 1 || nx > n || ny > n) {
				// 0 이된거면 or n+1 이된거면...
				// 먼저 범위 넘는 애를 찾
				nx = loop(nx);
				ny = loop(ny);
			}
		}
		
		// 이동 후 좌표를 맵 에 저장한다..
		map[nx][ny].add(new Fireball(nx, ny, cm, cs, cd));		
		
	}
	static int loop(int pos) {
		if(pos == 0) return n;
		if(pos == n+1) return 1;
		
		return pos;
	}
	static void controlFireball() {
		
		// 맵에서 찾아서 나눠주고 처리 큐에 넣어준다....
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				
				int size = map[i][j].size();
				
				// 파이어볼 존재하는 위치라면   
				if(size > 0) {
					
					// 파이어볼 나누기 실행   
					if(size >= 2) {
						
						int sumM = 0;
						int sumS = 0;
						int isEven = 0;
						
						for(Fireball f : map[i][j]) {
							sumM += f.m;
							sumS += f.s;
							if(f.d % 2 == 0) {
								// 짝수 인거 카운트
								// 나중에 이 변수의 갯수가 0 이거나 size랑 같으면 이거는 0246 
								isEven++;
							}
						}
						// 맵 저장 정보 정리... 
						map[i][j].clear();
						
						
						sumM = (int) Math.floor((double) (sumM / 5));
						sumS = (int) Math.floor((double) (sumS / size));
						
						if(sumM > 0) {
							// 0 이상일때만...
							
							if(isEven == 0 || isEven == size) {
								q.add(new Fireball(i, j, sumM, sumS, 0));
								q.add(new Fireball(i, j, sumM, sumS, 2));
								q.add(new Fireball(i, j, sumM, sumS, 4));
								q.add(new Fireball(i, j, sumM, sumS, 6));
							}
							else {
								q.add(new Fireball(i, j, sumM, sumS, 1));
								q.add(new Fireball(i, j, sumM, sumS, 3));
								q.add(new Fireball(i, j, sumM, sumS, 5));
								q.add(new Fireball(i, j, sumM, sumS, 7));
							}
						}
						
					}
					else {
						// 있는 파이어볼 몽땅 큐에 넣기   
						q.add(map[i][j].get(0));
						
						// 맵 저장 정보 정리... 
						map[i][j].clear();
					}
					
				}
				
			}
		}
		
		
		
	}
	static int sumMass() {
		int sum = 0;		
		if(q.isEmpty()) {
			// 남아있는거 하나도 없으면  0 리턴  
			return 0;
		}
		
		while(!q.isEmpty()) {
			Fireball curFireball = q.poll();
			sum += curFireball.m;		
			
		}
		
		
		return sum;
	}
}