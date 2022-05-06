import java.util.*;

class Solution {
    
    static void getPrime() {
        long max = 1000000;
        numbers.add((long) 0);
        numbers.add((long) 1);
        
        for(long i = 2; i <= Math.sqrt(max); i++){
            if(numbers.contains(i)){
                continue;
            }
            
            for(long j=i*2; j < max; j = j+i){
                // 소수가 아닌값 셋에 넣어준다.
                numbers.add(j);
            }
        }
        
    }
    static HashSet<Long> numbers;
    static StringBuilder sb;
    public int solution(int n, int k) {
        int answer = 0;
        sb = new StringBuilder();
        numbers = new HashSet<>();
        getPrime();
        
        convertByK(n, k);
        String[] splited = sb.toString().split("0+");
        
        for(String candi : splited){
            
            long num = Long.parseLong(candi);
            //if(!numbers.contains(num)){
            if(isPrime(num)){
                // 소수이당.
                answer++;
            }
        }
        
        
        return answer;
    }
    
    public boolean isPrime(long num) {
        if(num == 1) {
            return false;
        } else if(num == 2) {
            return true;
        }

        int limit = (int) Math.sqrt(num);
        for(int i=2; i<=limit; ++i) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
    static void convertByK(int n, int k){
        
        
        String res = "";
        while(n > 0){
            int share = n / k;
            int remain = n % k;            
            
            sb.insert(0, remain);
            
            
            n = share;            
        }
    
    }
}