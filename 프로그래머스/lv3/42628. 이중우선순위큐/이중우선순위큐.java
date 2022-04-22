import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq;
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        pq = new PriorityQueue<>();
        
        for(String op : operations){
            StringTokenizer st = new StringTokenizer(op);
            
            String o = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            
            operation(o, val);
        }
        
        
        if(!pq.isEmpty()){
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
           
            Iterator it = pq.iterator();
            int index = 0;
            while(it.hasNext()){                
                int e = (int) it.next();
                min = Math.min(min, e);
                max = Math.max(max, e);
            }
            answer[0] = max;
            answer[1] = min;
        }
        
        return answer;
    }
    
    static void operation(String o, int val){
        
        switch(o) {
                
            case "I" :
                pq.add(val);
                break;
            case "D" :
                if(!pq.isEmpty()){
                    if(val == -1){
                        pq.poll();
                    }
                    else{
                        // 최댓값 삭제
                        int[] arr = new int[pq.size()-1];
                        int index = 0;
                        while(index < arr.length) {
                            arr[index++] = (int) pq.poll();
                        }
                        pq.clear();
                        
                        for(int i=0; i<arr.length; i++){
                            pq.add(arr[i]);
                        }
                        
                    }
                }
                break;   
                
        }
        
        
        
    }
    
}