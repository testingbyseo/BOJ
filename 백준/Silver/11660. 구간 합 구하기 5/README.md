# [Silver I] 구간 합 구하기 5 - 11660 

[문제 링크](https://www.acmicpc.net/problem/11660) 

## 나의 풀이
<ul>
 <li> [2167]이차원배열의 합 구하기와 동일한 문제이다. </li>
 <li> 배열의 크기 범위가 더 커져서 점화식으로 풀어야 했던 문제이다. </li>
 <li> dp[x][y] 의 구간합은 가장 처음부터 자기 자신을 더한 값이다. </li>
 <li> 따라서, [ 위쪽까지 구간합 ] + [ 왼쪽까지 구간합 ] - [ 교집합 ] + [ ( x,y )의 값 ] 으로 구할 수 있다. </li>
 <li> 
	 동일한 로직으로 (x1, y2) ~ (x2, y2) 구간의 합도 구할 수 있다. <br>
	<ul>
   		<li> 끝 지점 합에서 경계 범위를 제하고 구한다. </li>
    		<li> [끝지점 구간합] - [경계 범위] = <br> 	   
    			[끝지점 구간합] - ( [ (시작지점 행, 끝지점 열 )의 위쪽 합 ] + [ (끝지점 행, 시작지점 열)의 왼쪽 합 ] - [ 교집합 ] )  </li>
    		<li> dp[x2][y2] - dp[x2][y1-1] - dp[x-1][y2] + dp[x1-1][y1-1] </li>
	</ul>
 </li>
</ul> 
 
### 초기 셋팅
<ol>
 <li> 값들을 저장할 이차원 배열 arr </li>
 <li> 구간합을 저장할 dp </li> 
 <li> 확인 범위를 저장할 queries </li> 	
</ol>

### 기능 구현 
<ol>
	<li> 1행부터 n행까지의 구간합을 누적하여 답을 구한다. </li>
	<li> 저장된 확인 범위의 구간합을 구한다. </li>
</ol>
	
### 배운점

어제 배운 거 복습으로 금방 끝났다. <br>
dp 점화식을 세우는 연습은 계속해서 해야 할 것 같다. <br>

### 성능 요약

메모리: 122360 KB, 시간: 872 ms

### 분류

다이나믹 프로그래밍(dp), 누적 합(prefix_sum)

### 문제 설명

<p>N×N개의 수가 N×N 크기의 표에 채워져 있다. (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오. (x, y)는 x행 y열을 의미한다.</p>

<p>예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.</p>

<table class="table table-bordered" style="line-height:20.8px; width:158px">
	<tbody>
		<tr>
			<td style="text-align:center">1</td>
			<td style="text-align:center">2</td>
			<td style="text-align:center">3</td>
			<td style="text-align:center">4</td>
		</tr>
		<tr>
			<td style="text-align:center">2</td>
			<td style="text-align:center">3</td>
			<td style="text-align:center">4</td>
			<td style="text-align:center">5</td>
		</tr>
		<tr>
			<td style="text-align:center">3</td>
			<td style="text-align:center">4</td>
			<td style="text-align:center">5</td>
			<td style="text-align:center">6</td>
		</tr>
		<tr>
			<td style="text-align:center">4</td>
			<td style="text-align:center">5</td>
			<td style="text-align:center">6</td>
			<td style="text-align:center">7</td>
		</tr>
	</tbody>
</table>

<p>여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.</p>

<p>표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다. 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다. 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)</p>

### 출력 

 <p>총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.</p>

