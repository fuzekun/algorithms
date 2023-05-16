package lanqiao.chongci._primes;

import java.util.Scanner;

/**
 * 
 * 
 * 	分解质因数
 * 注意a > 1的情况。就是当最后一个
 * */
public class Acwing867 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n -- != 0) {
			int a = sc.nextInt();
			for (int i = 2; i <= a / i; i++) {
				int cnt = 0;
				if (a % i == 0) {
					while (a % i == 0) {
						a /= i;
						cnt++;
					}
					System.out.println(i + " " + cnt);
				}
			}
			if (a > 1) System.out.println(a + " " + 1);
			System.out.println();
		}
	}
}
