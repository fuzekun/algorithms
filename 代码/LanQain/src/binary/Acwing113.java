package binary;

import java.util.Scanner;
import java.util.PrimitiveIterator.OfDouble;

/**
 * 
 * 
 *  二分
 *  1. 字符串匹配的时候，匹配到哪一个下标。
 * 
 * 
 * */
public class Acwing113 {
	public static void main(String[] args) {
		int n;
		Scanner scanner = new Scanner(System.in);
		n = Integer.parseInt(scanner.nextLine());
		char[] chars = scanner.nextLine().toCharArray();
		int l = 1, r = chars.length + 1;
		while (l < r) {
			int mid = l + r >> 1;
			if (check(chars, mid)) r = mid;
			else l = mid + 1;
		}
		System.out.println(l);
	}
	private static boolean check(char[] chars, int len) {

		char[] tmp = new char[len];
		// 这个应该有等于号，因为chars.length - len是最后一个子串的开始位置
		for (int i = 0; i <= chars.length - len; i++) {
			// 长度为len.
			for (int j = i; j < i + len; j++) {
				tmp[j - i] =  chars[j];
			}
			// 判断字符串在原串中出现的次数
			int cnt = 0;
			// 最后的子串是[chars.length - len, chars.len)。长度为len
			for (int p = 0; p <= chars.length - len; p++) {
				int tp = p, q;
				for (q = 0; q < len; tp++, q++) {
					if (chars[tp] != tmp[q]) break;
				}
				if (q == len) cnt++;
				if (cnt >= 2) return false;
			}
		}
		return true;
	}
}
