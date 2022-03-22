import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        HashSet<Integer> set = new HashSet<>();
        for(int e : win_nums){
            set.add(e);
        }
        
        int unknown = 0;
        int match = 0;
        for(int e: lottos){
            if(set.contains(e)){
                match++;
            }
            else{
                if(e == 0) unknown++;
            }
        }
        
        answer[0] = rank[match + unknown];
        answer[1] = rank[match];
        
        return answer;
    }
}