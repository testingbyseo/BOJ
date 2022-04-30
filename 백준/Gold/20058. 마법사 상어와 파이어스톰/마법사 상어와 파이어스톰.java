import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int size;
	static int q;
	static int max;
	static int numIce;
	static int[][] map;
	static int[] query;
	
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
	
	static void input()throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, n);
		
		map = new int[size][size];
		
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0; j<size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		query = new int[q];		
		st = new StringTokenizer(br.readLine().trim());
		for(int i=0; i < q; i++) {
			query[i] = Integer.parseInt(st.nextToken());
		}
	}
	static void process(){
		
		for(int i=0; i < q; i++) {
			// 레벨 결정  
			int l = query[i];
			
			fireStorm(l);
			
			downIce();
			
		}
		
		// 남은 얼음 합 그리고 가장 큰 덩어리 갯수 
		count();
		sb.append(numIce).append("\n");
		sb.append(max).append("\n");
		
	}
	static void print() {
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void copyMap(int[][] from, int[][] to) {
		for(int i=0; i<size; i++) {
			to[i] = from[i].clone();
		}
	}
	static void fireStorm(int l) {
		int[][] temp = new int[size][size];
		
		int lsize = (int) Math.pow(2, l);
		
		for(int i=0; i <= size - lsize; i += lsize) {
			for(int j=0; j <= size - lsize; j += lsize) {
				int startX = i;
				int startY = j;
				
				//rotate;
				rotate(startX, startY, lsize, temp);
			}
		}
		copyMap(temp, map);
		
	}
	static void rotate(int startX, int startY, int lsize, int[][] temp) {
		// 마지막 열 - 행 : 새로운 열 ( 행 같으면 열이 같아)
		
		int row = startX;
		int col = startY + lsize - 1;
		
		for(int i = 0; i < lsize; i++) {
			for(int j = 0; j < lsize; j++) {
				// 열이 행을 결정하고 행이 열을 결정한다 >> 같은 행이었으면 같은 열이
				temp[row + j][col - i] = map[startX + i][startY + j];
				
			}
		}
	}
	
	static void downIce() {
		int[][] temp = new int[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] > 0) {
					int ret = getIce(i, j);
					int ice = map[i][j];
					// 인점 얼음 칸  < 3 
					if(ret < 3) {
						ice--;
					}
					// ice 음수 될수 없다.
					temp[i][j] = ice < 0? 0 : ice;
				}
				
			}
		}
		copyMap(temp, map);
		
	}
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int getIce(int x, int y) {
		int num = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
			
			
			// 얼음 존재하면 카운트 
			if(map[nx][ny] > 0) {
				num++;
			}
			
		}
		
		return num;
	}
	static void count() {
		// 최소 맥스 값은 0
		// 갱신 안돼도 0
		max = 0;
		numIce = 0;
		boolean[][] visit = new boolean[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(map[i][j] > 0 && !visit[i][j]) {
					// 얼음이 있고 방문하지 않았으면 dfs로 덩어리 구한다.
					int num = dfs(i, j, visit);
					max = Math.max(max, num);
				}
			}
		}
	}
	static int dfs(int x, int y, boolean[][] visit) {
		
		int sum = 1; // 끝에선 자기자신   
		visit[x][y] = true;
		
		// 얼음양의 합
		numIce += map[x][y];
		
		for(int i=0; i<4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
			
			// 방문하지 않았고 얼음이 있는곳이면 간다 연결 
			if(!visit[nx][ny] && map[nx][ny] > 0) {
				
				sum += dfs(nx, ny, visit);
				
				
			}
			
		}
		
		return sum;
		
	}
	
}
