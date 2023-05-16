package __dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 	约数之和取模
 * 1. 试除法求约数
 * 2. 二分法，求sum(p, k)
 * 
 * */
public class Acwing97 {
	
	private final static int mod = 9901;
	private static long rx, ry;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b;
		a = sc.nextInt();
		b = sc.nextInt();
		
		
		
		// 求素数因子
		int[][] primes = div(a);
		
		// 测试快速幂
//		System.out.println(quick_pow(2,  0));;
		
		// 求约数之和, sum(p0, k + 1) * sum(p1, k + 1)...，除法不能取模，所以需要求逆元
		long res = 1;
		for (int[] p : primes) {
			long prime = p[0], cnt = p[1] * b + 1;
//			exgcd(prime - 1, mod);
			res = res * sum(prime, cnt)% mod;
//			res = res * ((quick_pow(prime, cnt) - 1) *rx % mod) % mod;
		}
		if (a == 0) res = 0;
		while (res < 0) res = (res + mod) % mod;
		System.out.println(res);
	}
	private static long exgcd(long a,long b)
	{
		if(b==0) {rx=1;ry=0;return a;}
		long ans=exgcd(b, a%b);
		long team=rx;
		rx=ry;
		ry=team-a/b*ry;
		return ans;
	}
	// pow(p, 0) + pow(p, 1) + pow(p, 2) + ... pow(p, k - 1); k >= 1
	private static long sum(long p, long k) {
		if (k == 1) return 1;
		long res = 1;
		res = (sum(p, k / 2) * (quick_pow(p, k / 2) + 1)) % mod;
		if (k % 2 == 1) res = res + quick_pow(p, k - 1) % mod;
		return res;
	}
	
	private static long quick_pow(long a, long b) {
		long res = 1;
		while (b != 0) {
			if (b % 2 == 1) res = res * a % mod;
			a = a * a % mod;
			b >>= 1;
		}
		return res;
	}
	/**
	 * 
	 * 求素数因子
	 * */
	private static int[][] div(int a) {
		ArrayList<int[]>ans = new ArrayList<>();
		for (int i = 2; i <= a / i; i++) {
			if (a % i == 0) {
				int cnt = 0;
				while (a % i == 0) {
					cnt++; 
					a /= i;
				}
//				System.out.print(i + " " + cnt);
				ans.add(new int[] {i, cnt});
			}
		}
		if (a > 1) {
			ans.add(new int[] {a, 1});
//			System.out.println(a + " " + 1);
		}
//		System.out.println();
//		return ans.stream().toArray(int[][]::new);
		return ans.toArray(int[][]::new);
	}
}
