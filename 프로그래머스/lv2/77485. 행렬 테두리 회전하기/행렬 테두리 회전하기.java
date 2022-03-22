class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int value = 1;
        map = new int[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = value;
                value++;
            }
        }
        int n = 0;
        for(int[] query : queries){
            answer[n] = swirl(query);
            n++;
        }
        
        
        return answer;
    }
    static int swirl(int[] q){
        
        int x1 = q[0] - 1;
        int y1 = q[1] - 1;
        int x2 = q[2] - 1;
        int y2 = q[3] - 1;
        int posx = x1;
        int posy = y1;
        
        int tmp = 0;
        int min = 10000;
        int pre = 0;
        int next = 0;
        pre = map[posx][posy];
        for(int i=0;i<4;i++){
            
            while((posx+dx[i]) >= x1 && (posx+dx[i]) <= x2 && (posy+dy[i]) >= y1 && (posy+dy[i]) <= y2 ){
                posx+=dx[i];
                posy+=dy[i];
                
                next = map[posx][posy];
                map[posx][posy] = pre;
                min = Math.min(min, pre);
                pre = next;
                
                
            }
        }
        return min;     
        
        
    }
}