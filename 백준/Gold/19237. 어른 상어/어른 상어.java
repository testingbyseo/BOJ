import java.io.*;
import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;


public class Main {
	static class Shark{
		int num, x, y, dir;
		int[][] priority;
		
		Shark(int num, int x, int y, int dir, int[][] priority){
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.priority = priority;
		}
	}
	
	static int n;
	static int m;
	static int k;
	static int[][] map;
	static int[][] tempMap;
	static long[][] smellmap;
	static Shark[] sharks;
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
	static void print() {
		for(int i=1; i<m+1; i++) {
			Shark curShark = sharks[i];
			System.out.println(curShark.num + " " + curShark.x + " " + curShark.y + " " + curShark.dir);
		}
	}
	static void input() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		map = new int[n+1][n+1];
		smellmap = new long[n+1][n+1];
		sharks = new Shark[m+1];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=1; j<n+1; j++) {
				int e = Integer.parseInt(st.nextToken());
				if(e > 0) {
					// 상어면 에 넣어줄것임...
					sharks[e] = new Shark(e, i, j, 0, new int[5][4]);
				}
				map[i][j] = e;
			}
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for(int i=1; i<m+1; i++) {
			// 우선 순위 초기 저장
			
			int dir = Integer.parseInt(st.nextToken());
			
			sharks[i].dir = dir;
		}
		
		for(int i=1; i<m+1;i++) {
			// i 번째 상어...			
			
			// 위 아래 왼 오 순서 
			for(int j=1; j<5; j++) {
				
				st = new StringTokenizer(br.readLine().trim());
				int[] direction = new int[4];
				for(int d=0; d<4;d++) {
					// 방향 별 우선 순위  
					direction[d] = Integer.parseInt(st.nextToken());
				}
				// j번째 방향의 우선순위 배열 넣어주기 
				sharks[i].priority[j] = direction;
			}
			
		}
	}
	static boolean isOnly() {
		
		for(int i = 2; i < m+1; i++) {
			if(sharks[i].num != -1 ) 
				return false;
		}
		
		return true;
	}
	static void process() {
		answer = 0;
		
		while(true) {
			
			if(isOnly() || answer > 1000) {
				// 1번 상어만  남았다
				break;
			}
			
			removeSmell();			
			leaveSmell();
			
			
			moveSharks();			
			killSharks();		
			
			answer++;
			
			
		}
		
		answer = answer <= 1000? answer : -1;
	}
	static void removeSmell() {
		for(int i=1; i<n+1; i++) {
			for(int j =1; j<n+1; j++) {
				if(smellmap[i][j] > 0) {
					smellmap[i][j]--;
					
					if(smellmap[i][j] % 10000 == 0) {
						// k 시간이 지난 것이라면 아예 0으로 만들어 주자 
						smellmap[i][j] = 0;
					}
					
				}
					
			}
		}
	}
	static void leaveSmell() {
		for(int i=1; i<m+1; i++) {
			Shark shrk = sharks[i];
			
			if(shrk.num > 0) {
				// 누구의 냄새인지도 확인...
				smellmap[shrk.x][shrk.y] = shrk.num *10000 + k ;
			}
		}
	}
	
	static void moveSharks() {		
		tempMap = new int[n+1][n+1];
		
		for(int i=1; i<m+1; i++) {
						
			if(sharks[i].num != -1) {
				// 상어가 존재 한다면,,,
				int ndir = getDir(sharks[i]);
				if(ndir != 0) {
					// dir  을 찾았으면
					int nx = sharks[i].x + dx[ndir];
					int ny = sharks[i].y + dy[ndir];
					
					// 이동한정보로 수정해준다..
					sharks[i].x = nx;
					sharks[i].y = ny;
					sharks[i].dir= ndir;
					
					
					//이동할 자리에 상어가 없거나, 내가 그 상어보다 번호가 작으면... 더쎄니까 차지할 수 있따..
					if(tempMap[nx][ny] == 0) {
						tempMap[nx][ny] = sharks[i].num;
					}
					else {						
						tempMap[nx][ny] = Math.min(tempMap[nx][ny], sharks[i].num);
					}
				
				}
			}		
			
		}		
		// 이동완료 맵 저장 
		for(int i=0; i<n+1;i++) {
			map[i] = tempMap[i].clone();
		}
		
	}
	static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};	
	static int getDir(Shark cur) {
		
		int num = cur.num;
		int dir = cur.dir;
		// 현재 방향의 우선순위 표를 참고하자  
		int[] pri = cur.priority[cur.dir];
		
		// 다음 방향이 될 수 있는 후보군  
		ArrayList<Integer> ndir = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			
			int sx = cur.x + dx[pri[i]];
			int sy = cur.y + dy[pri[i]];
			
			if(sx < 1 || sy < 1 || sx > n || sy > n) {
				continue;
			}
			
			// ㅁ냄새 없거나 나의 냄새가 있는 칸으로 갈 수 있따...
			if(smellmap[sx][sy] == 0) {
				return pri[i];
			}
			else if(smellmap[sx][sy] / 10000 == num) {
				ndir.add(pri[i]);
			}
			
		}
		
		
		// 우선 순위가 높은 거 먼저 따른다... 
		for(int i=0; i<4; i++) {
			int direction = pri[i];
			for(Integer d  : ndir) {
				if(direction == d) {
					return direction;
				}
			}
		}
		
		return 0;
		
	}
	
	static void killSharks() {
				
		// 여러마리 걸러주기...
		for(int i = 1; i < m+1; i++) {
			if(sharks[i].num == -1) continue;
			
			
			int x = sharks[i].x;
			int y = sharks[i].y;
			if(map[x][y] != sharks[i].num) {
			
				sharks[i].num = -1;
				// 죽은 표ㅅㅣ 
			}
		}
		
	}
	
	
	
}