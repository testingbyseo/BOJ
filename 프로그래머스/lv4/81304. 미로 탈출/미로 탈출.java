import java.util.*;
class Solution {
    static class Pos implements Comparable<Pos> {
        int u, distance, state;
        Pos(int u, int distance, int state){
            this.u = u;
            this.distance = distance;
            this.state = state;
        }

        @Override
        public int compareTo(Pos o){
            if(this.distance < o.distance)
                return -1;
            return 1;
        }
    }

    static ArrayList<int[]>[] adj;
    static ArrayList<int[]>[] re_adj;
    static HashMap<Integer, Integer> trapList;
    static int[][] dist;
    static int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int answer = 0;
        adj = new ArrayList[n+1];
        re_adj = new ArrayList[n+1];
        init(adj, re_adj, n);
        setMap(roads);

        trapList = new HashMap<>();
        int idx = 0;
        for(int trap : traps){
            trapList.put(trap, 1 << idx++);
        }

        dist = new int[n+1][1 << traps.length];
        for(int i=1; i<n+1;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        answer = dijkstra(start, end);
        return answer;
    }
    static int dijkstra(int start, int end){
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.add(new Pos(start, dist[start][0], 0));

        while(!pq.isEmpty()){

            Pos cur = pq.poll();

            if(cur.u == end){
                return cur.distance;
            }

            int status = 0;
            if(trapList.containsKey(cur.u)){
                if((cur.state & trapList.get(cur.u)) != 0){
                    // 현재 위치가 트랩이고
                    // 켜져있으면 1로 초기화
                    status = 1;
                }
            }

            // 정방향
            int isStraight = status;
            for(int[] next : adj[cur.u]){

                isStraight = status;
                int nState = cur.state;
                if(trapList.containsKey(next[0])){
                    // 다음 방문지도 트랩이라면 두 비트 마스크를 비교해야 한다.
                    // 다음 방문지 트랩이 on 일 경우
                    if((cur.state & trapList.get(next[0])) != 0){
                        // 현재와 다음 트랩 동시에 켜져있으면 정방향 > 1^1 = 0
                        // 다음 on && 현재 off > 0^1 = 0
                        isStraight ^= 1;
                    }
                    nState ^= trapList.get(next[0]);
                }

                // 이 루프에서는 정방향만 처리한다.
                if(isStraight == 0){
                    if(dist[next[0]][cur.state] > cur.distance + next[1]){
                        dist[next[0]][cur.state] = cur.distance + next[1];
                        pq.add(new Pos(next[0], dist[next[0]][cur.state], nState));
                    }
                }
            }

            // 역방향
            for(int[] next : re_adj[cur.u]){

                isStraight = status;
                int nState = cur.state;
                if(trapList.containsKey(next[0])){
                    // 다음 방문지도 트랩이라면 두 비트 마스크를 비교해야 한다.
                    // 다음 방문지 트랩이 on 일 경우
                    if((cur.state & trapList.get(next[0])) != 0){
                        // 현재와 다음 트랩 동시에 켜져있으면 정방향 > 1^1 = 0
                        // 다음 on && 현재 off > 0^1 = 0
                        isStraight ^= 1;
                    }
                    nState ^= trapList.get(next[0]);
                }

                // 이 루프에서는 역방향만 처리한다.
                if(isStraight == 1){
                    if(dist[next[0]][cur.state] > cur.distance + next[1]){
                        dist[next[0]][cur.state] = cur.distance + next[1];
                        pq.add(new Pos(next[0], dist[next[0]][cur.state], nState));
                    }
                }
            }

        }
        return Integer.MAX_VALUE;
    }

    static void init(ArrayList<int[]>[] arrlist, ArrayList<int[]>[] re_arrlist, int n){
        for(int i=1 ;i<n+1; i++){
            arrlist[i] = new ArrayList<>();
            re_arrlist[i] = new ArrayList<>();
        }
    }
    static void setMap(int[][] roads){
        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int w = road[2];

            adj[u].add(new int[] {v, w});
            re_adj[v].add(new int[] {u, w});
        }
    }
}