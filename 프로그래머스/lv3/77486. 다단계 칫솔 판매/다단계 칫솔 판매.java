import java.util.*;

class Solution {
    static int[] answer;
    static HashMap<String, Integer> hm;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        answer = new int[enroll.length];
        hm = new HashMap<>();
       
        for(int i=0; i<enroll.length; i++){
            hm.put(enroll[i], i);
            
        }
        
        for(int i=0; i< seller.length; i++){
           dfs(hm.get(seller[i]), amount[i]*100, referral);
        }
        
        return answer;
    }
    
    static void dfs(int idx, int profit, String[] referral){
        
        answer[idx] += profit - (profit/10);
        profit/= 10;
        //if(referral[idx].equals("-")){
        if(hm.containsKey(referral[idx]) && profit > 0){
            dfs(hm.get(referral[idx]), profit, referral);
        }        
        
        
        return;
        
    }
}