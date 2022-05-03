import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		int i;
		Scanner scanner = new Scanner(System.in);
		
		
		int N = scanner.nextInt();
		int[] a = new int[N];
		
		int M = scanner.nextInt();
		int left = 0 ;
		int right = 0;
		for(i=0; i<N; i++) {
			a[i] = scanner.nextInt();
			right = Math.max(right, a[i]);
		}
		
		int ans = 0 ;
		while(left<=right) {
			int mid = (left + right)/2;
			long sum = 0 ;
			for(i = 0 ; i< N ; i++) {
				if( a[i] > mid ) {
					sum += a[i] - mid;
				}
			}
			if( sum >= M ) {
				left = mid+1;
				ans = Math.max(ans, mid);
			}
			else {
				right = mid-1;
			}
		}
		
		System.out.println(ans);
	}
	

	
}
