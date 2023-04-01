package _primes;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * 
 * O(pow(ln n, 2) * log lnn * T)
 * */
public class Acwing3792 {
	
	private static int[] notPrime = new int[1006];
	private static ArrayList<Integer>primes = new ArrayList<>();
	private static void init() {
		notPrime[1] = notPrime[0] = 1;
		for (int i = 2; i <= 1005; i++) {
			if (notPrime[i] == 0) {
				primes.add(i);
				for (int j = i + i; j <= 1005; j += i) {
					notPrime[j] = 1; 
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		init();
		int T = scanner.nextInt();
		while (T-- != 0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int ans = 0;
			for (int i = 0; i < primes.size() - 1; i++) {
				int x = primes.get(i), y = primes.get(i + 1);
				int sum = x + y + 1;
				if (sum > n) break;
				if (notPrime[sum] == 0) 
					ans++;
			}
			if (ans >= k) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}
