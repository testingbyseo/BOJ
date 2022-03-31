import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] < o2[0])
                    return -1;
                return 1;
                
            }
        });
        
        
        
        for(int[] job : jobs){
            pq.add(job);
        }
        
        PriorityQueue<int[]> wait = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1])
                    return -1;
                return 1;
                
            }
        });
        
        
        int spent = 0;
        
        while(true){
            if(pq.isEmpty() && wait.isEmpty()) break;
            
            Iterator<int[]> it = pq.iterator();            
            while(it.hasNext()){
                if(pq.peek()[0] <= spent){
                    wait.add(pq.poll());
                }
                else{
                    break;
                }
            }
            if(wait.isEmpty()){
                spent = pq.peek()[0];
            }
            else{
                int[] cur = wait.poll();
                spent += cur[1];
                answer += spent - cur[0];
            }
            
            
            
            //System.out.println(spent);
                
            
        }
        
        answer /= jobs.length;
        
        
        return answer;
    }
}