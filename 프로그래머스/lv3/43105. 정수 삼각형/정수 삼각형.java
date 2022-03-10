class Solution {
    static int[][] dp;
    static int n, m;
    static int answer;
    public int solution(int[][] triangle) {
        answer = 0;
        n = triangle.length;
        m = triangle[n-1].length;
        dp = new int[n][m];       
        
        answer = traversal(0, 0, triangle);
        
                
        return answer;
    }
    
    private static int traversal(int x, int y, int[][] triangle){       
        
        if(x == n-1){
            return triangle[x][y];
        }       
        
        
        for(int i=0; i< 2; i++){
            int nx = x+1;
            int ny = y + i;
            if(nx >= n || ny >= m){
                continue;
            }
            
            if(dp[nx][ny] != 0){
                dp[x][y] = dp[nx][ny] + triangle[nx][ny];
                continue;
            }
            
            dp[x][y] = Math.max(dp[x][y], traversal(nx, ny, triangle)); 
        }
        
        return dp[x][y] + triangle[x][y];
        
    }
    
}