package lanqiao.chongci.string;

import java.util.Arrays;

import utils.InAndOutUitl;

/**
 * 
 * 周期
 * kmp的前后缀问题
 * i % (i - next[i]) == 0说明两者相等
 * */
public class Acwing141 {
	public static void main(String[] args) throws Exception {
		InAndOutUitl util = new InAndOutUitl();
		int n, T = 1;
		while ((n = util.nextInt()) != 0) {
			char[] s = util.nextLine().toCharArray();
			int[] next = new int[n + 1];
			int i = 0, j = -1;
			// 计算最大[0:i]的最大前后缀
			next[0] = -1;
			while (i < n) {
				if (j == -1 || s[i] == s[j]) {
					i++;
					j++;
					next[i] = j;
				}
				else j = next[j];
			}
			
			// 求解
			util.write("Test case #" + T++ + "\n");
			for ( i = 2; i <= n; i++) {
				if (next[i] != -1 && next[i] != 0 && i % (i - next[i]) == 0) {
					util.write(i + " " + (i / (i - next[i])) + "\n");
				}
			}
			util.write("\n");
		}
		util.flush();
	}
}
