import java.util.*;

class Solution {
    static int[][] a = { {1, 2, 3, 4, 5},
                       {2, 1, 2, 3, 2, 4, 2, 5},
                       {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    public int[] solution(int[] answers) {
        ArrayList<Integer> answer= new ArrayList<>();
        int max = 0;
        
        for(int i=0; i<3; i++){
            int t = count(a[i], answers);
            if(t >= max){
                if(t > max){
                    answer.clear();
                    max = t;
                }                   
                
                answer.add(i+1);
            }
        }      
        
        
        return answer.stream().mapToInt(i->i).toArray();
    }
    public static int count(int[] a, int[] answers){
        int total = 0;
        int size = 0;
        for(int i=0; i<answers.length; i++){
            if(a[size++] == answers[i]){
                total++;
            }
            size = size == a.length? 0 : size;
        }
        return total;
    }
}