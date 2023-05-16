package lanqiao.chongci._primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 
 * 	素数筛选
 * */
public class Acwing868 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		List<Integer>primes = new ArrayList<>();
		int[] not_prime = new int[n + 1];
		for (int i = 2; i <= n; i++) {
			if (not_prime[i] == 0) {
				primes.add(i);
				for (int j = i + i; j <= n; j += i) not_prime[j] = 1; 
			}
		}
		System.out.println(primes.size());
	}
}
