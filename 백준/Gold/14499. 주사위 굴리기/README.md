# [Gold IV] 주사위 굴리기 - 14499 

[문제 링크](https://www.acmicpc.net/problem/14499) 

## 나의 풀이
### **초기 셋팅**
1. 주사위 값을 담을 클래스 Dice
2. 지도 저장 map
3. 주문 저장 배열 queries


### **기능 구현**
1. queries 배열을 돌면서 차례대로 실행한다.
2. [ 주사위 굴리기 ] rollDice(query)
	1. query 에 값에 따른 방향으로 좌표를 이동한다.
	 이때, map의 범위를 벗어나면 *false* 를 리턴한다.
	2. 주사위 이동이 가능하면 위치 좌표를 이동 후 좌표로 업데이트한다.
	2. 주사위 굴린 결과를 moved 주사위 객체에 저장한다
	3. dice 를 moved 의 값으로 복사한다.
	4. 주사위를 이동했으니 *true*를 리턴한다.
3. 주사위를 굴릴 수 없으면 바로 다음 루프를 실행한다.
4. [ 값 복사 ] getNum()
- 주사위 위치 (x, y)에 저장된 지도 값이 0 이면, 주사위 바닥값을 복사해준다.
- 주사위 위치 (x, y)에 저장된 지도 값이 0 이 아니라면, 주사위 바닥값으로 복사 후 지도 값을 0으로 만든다.
5. Stringbuilder 에 주사위 top (윗면) 값을 저장한다.
	
### **배운점**
1. 어렸을때 쌓기나무하던 기억이 새록새록 나고 좋았다. 👍
2. 기능만 제대로 구현하면 되는 문제였다.

### 성능 요약

메모리: 14504 KB, 시간: 136 ms

---

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명

<p>크기가 N×M인 지도가 존재한다. 지도의 오른쪽은 동쪽, 위쪽은 북쪽이다. 이 지도의 위에 주사위가 하나 놓여져 있으며, 주사위의 전개도는 아래와 같다. 지도의 좌표는 (r, c)로 나타내며, r는 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로부터 떨어진 칸의 개수이다. </p>

<pre>  2
4 1 3
  5
  6</pre>

<p>주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며, 놓여져 있는 곳의 좌표는 (x, y) 이다. 가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.</p>

<p>지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.</p>

<p>주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하는 프로그램을 작성하시오.</p>

<p>주사위는 지도의 바깥으로 이동시킬 수 없다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.</p>

### 입력 

 <p>첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x, y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.</p>

<p>둘째 줄부터 N개의 줄에 지도에 쓰여 있는 수가 북쪽부터 남쪽으로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다. 지도의 각 칸에 쓰여 있는 수는 10 미만의 자연수 또는 0이다.</p>

<p>마지막 줄에는 이동하는 명령이 순서대로 주어진다. 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.</p>

### 출력 

 <p>이동할 때마다 주사위의 윗 면에 쓰여 있는 수를 출력한다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.</p>

