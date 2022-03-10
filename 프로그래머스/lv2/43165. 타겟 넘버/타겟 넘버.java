//20220310
import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        for(int i=0; i < (1 << numbers.length); i++){
            int sum = 0;
            
            for(int j=0; j<numbers.length; j++){
                if((i & (1 << j)) != 0){
                    sum -= numbers[j];
                }
                else
                    sum += numbers[j];
            }
            
            if(sum == target){
                answer++;
            }
        }
        
        return answer;
        
    }
}


/*
//20220126
import java.util.*;

class Solution {
    private void dfs(int[] numbers, int target, int idx, int sum){
        if(idx == numbers.length){
            if(sum == target){
                answer++;
                return;
            }
        }
        else{
            dfs(numbers, target, idx+1, sum + numbers[idx]);
            dfs(numbers, target, idx+1, sum - numbers[idx]);
        }
        
        
        
    }
    
    private int answer = 0;
    public int solution(int[] numbers, int target) {
        
        dfs(numbers, target, 0, 0);
             
        
        return answer;
    }
}
*/
/*
//2021
import java.util.*;

class Solution {
    private void dfs(int idx, int sum, int target, int[] numbers){
        
        if(idx == numbers.length){
            
            if(sum == target){
                answer+= 1;
                System.out.println(sum);
            }
                
            return;
        }
        
        dfs(idx+1, sum + numbers[idx], target, numbers);
        dfs(idx+1, sum - 2*numbers[idx], target, numbers);
        
    }
    private static int answer = 0;
    
    public int solution(int[] numbers, int target) {
                
        dfs(0, 0, target, numbers);
        return answer;
        
    }
}
*/