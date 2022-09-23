import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        
        int w = 0, h = 0;
        for(int[] size : sizes){
            if(size[0] < size[1]){
                int tmp = size[1];
                size[1] = size[0];
                size[0] = tmp;
            }
            
            // max 구하기
            if(w < size[0]){
                w = size[0];
            }
            h = h < size[1]? size[1] : h;
        }
        answer = w*h;
        
        return answer;
    }
}