import java.util.*;

class Solution {
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visit;
    static boolean flag;
    public int[] solution(String[][] places) {
        
        int[] answer = new int[5];
        int index = 0;
        
        for(String[] p : places){
            
            int num =0;
            flag = true;            
            
            for(int u=0; u<5 && flag; u++){
                for(int v=0; v<5 && flag; v++){                    
                    
                    char c = p[u].charAt(v); 
                    if(c == 'P'){
                        visit = new boolean[5][5];
                        check(p, u, v, 0);
                                               
                    }
                }
            }
            
            answer[index] = flag? 1 : 0;
            
            index++;
            
        }
        
        
        return answer;
    }
    
    
    
    static void check(String[] map, int u, int v, int dis){
        
        if(dis >= 2) return;
        visit[u][v] = true;
        
        
        for(int i=0; i<4; i++){
            int nx = u + dx[i];
            int ny = v + dy[i];
            
            if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visit[nx][ny]) continue;
            
            char next = map[nx].charAt(ny);
            
            if(next == 'O'){                
                check(map, nx, ny, dis+1);
            }
            else if(next == 'P'){
                flag = false;
                return;                                
            }
            
        }
        
        
    }
    
}