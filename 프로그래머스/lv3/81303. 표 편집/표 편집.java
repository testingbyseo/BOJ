import java.util.*;

class Solution {
    static class Node {
        int current;
        Node prev, next;
        
        Node() {    }
        
        Node(int current){
            this.current = current;
            this.prev = null;
            this.next = null;
        }
        
        public Node remove() {
            // 현재를 삭제하고 이전과 다음을 연결해준다.
            prev.next = next;
            next.prev = prev;
            
            if(next.current == -1){
                return prev;
            }
            
            return next;
            
        }
        
        public void restore() {
            // 삭제한거 다시 연결해준다.
            prev.next = this;
            next.prev = this;
        }
        
        
    }
    // 링크드 리스트 생성
    public static Node init(int size){
    
        // 완전 처음        
        Node initNode = new Node(-1);        
        Node prevNode = initNode;        
        Node curNode = null;            
        
        for(int i=0; i<size; i++){        
           
            curNode = new Node(i);
            curNode.prev = prevNode;            
            prevNode.next = curNode;
            
            prevNode = curNode;            
        }
        
        // 마지막 노드 설정        
        Node endNode = new Node(-1);        
        curNode.next = endNode;
        
        return initNode.next;
    }
        
    static StringBuilder sb;
    static Stack<Node> stack;
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        stack = new Stack<>();
        sb = new StringBuilder("O".repeat(n));        
        
        Node cursor = init(n);
        for(int i=0; i < k; i++){
            cursor = cursor.next;
        }
        
        
        for(String c : cmd){
            String[] query = c.split(" ");
            
            switch(query[0]){
                case "D" :
                    int downNum = Integer.parseInt(query[1]);                   
                    cursor = down(cursor, downNum);
                    break;
                    
                case "U" :
                    int upNum = Integer.parseInt(query[1]);                   
                    cursor = up(cursor, upNum);
                    break;
                    
                case "C" :
                    cursor = delete(cursor);
                    break;
                    
                case "Z" :
                    restore();                
                    break;
            }
            
            
            
        }
        answer = sb.toString();
        
        return answer;
    }
    
    static Node down(Node cur, int num){
        
        for(int i=0; i<num; i++){
            cur = cur.next;    
        }
        return cur;
    }
    static Node up(Node cur, int num){
        for(int i=0; i<num; i++){
            cur = cur.prev;    
        }
        return cur;
    }
    
    static Node delete(Node cur){
        stack.push(cur);
        sb.setCharAt(cur.current, 'X');
        cur = cur.remove();
        
        return cur;
    }
    static void restore() {
        Node re = stack.pop();
        re.restore();
        
        sb.setCharAt(re.current, 'O'); 
    }
}