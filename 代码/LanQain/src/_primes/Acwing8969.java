package _primes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 	试除法求约数
 * 1. a / i != i的时候放入
 * 2. 一半一半
 * */
public class Acwing8969 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		while (n -- != 0) {
			int a = scanner.nextInt();
			int sz = (int)Math.sqrt(a + 1);
			List<Integer>list = new ArrayList<>();
			for (int i = 1; i <= sz; i++) {
				if (a % i == 0) {
					System.out.print(i + " ");
					if(a / i != i) list.add(a / i);
				}
			}
			Collections.reverse(list);
			for (int x: list) System.out.print(x + " ");
			System.out.println();
		}
	}
}
