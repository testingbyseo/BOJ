import java.util.*;

class Solution {
    static int[] board;
    static int answer;
    public int solution(int n) {
        answer = 0;
        
        board = new int[n];
        
        nQueen(0, n);
        return answer;
    }
    
    static void nQueen(int cdx, int n){
        
        if(cdx == n) {
            answer++;
            return;
        }
        
        for(int i=0; i<n; i++){
            board[cdx] = i;
            
            if(promising(cdx)){
                nQueen(cdx+1, n);
            }
            
        }
        
        
    }
    
    static boolean promising(int cdx){
        
        for(int i=0; i<cdx; i++){
         if(board[cdx] == board[i] 
            || Math.abs(cdx - i) == Math.abs(board[cdx]-board[i])){
             return false;
         }      
        }
        
        return true;
    }
}