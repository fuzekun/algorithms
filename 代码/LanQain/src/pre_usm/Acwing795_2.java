package pre_usm;

import java.util.Scanner;

/**
 * 
 * 裸前缀和
 * */
public class Acwing795_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] sum = new int[n + 1];
		// 求前缀和
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			sum[i + 1] = sum[i] + a;
		}
		
		for (int i = 0; i < m; i++) {
			int l, r;
			l = sc.nextInt();
			r = sc.nextInt();
			
			System.out.println(sum[r]- sum[l - 1]);
		}
		
	}
}
