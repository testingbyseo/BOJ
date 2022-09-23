import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int x = 0;
        int y = 0;
        
        for(int[] a:sizes){
            Arrays.sort(a);
            if(a[0]>x){
                x = a[0];
            }
            if(a[1]>y){
                y=a[1];
            }
        }
        answer = x*y;
        
        
        return answer;
    }
}