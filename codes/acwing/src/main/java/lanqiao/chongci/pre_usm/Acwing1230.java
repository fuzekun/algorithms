package lanqiao.chongci.pre_usm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Acwing1230 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		Map<Integer, Integer>cnt = new HashMap<>();
		int sum = 0;
		cnt.put(0, 1);
		long ans = 0;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			sum += a;
			sum %= k;
			ans += cnt.getOrDefault(sum, 0);
			cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
		}
		System.out.println(ans);
	}
}
