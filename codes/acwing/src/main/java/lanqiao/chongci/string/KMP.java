package lanqiao.chongci.string;

import utils.InAndOutUitl;

/**
 * 
 * 
 * kmp算法
 * */
public class KMP {
	public static void main(String[] args) throws Exception {
		InAndOutUitl utils = new InAndOutUitl();
		int n = utils.nextInt();
		char[] t = utils.nextLine().toCharArray();
		int m = utils.nextInt();
		char[] s = utils.nextLine().toCharArray();
		int[] next = new int[n + 1];
		int i = 0, j = -1;
		// 求t的每一个[0:i]的最大前后缀
		next[0] = -1;
		while (i < n) {
			if (j == -1 || t[i] == t[j]) {
				i++; j++;
				// 注意这个 i != n
				if (i != n && t[i] == t[j]) next[i] = next[j];
				else next[i] = j;
			}
			else j = next[j];
		}
		
		i = 0; j = 0;
		while (i < m) {
			if (j == -1 || s[i] == t[j]) {
				i++; j++;
			} else j = next[j];
			
			if (j == n) {
				utils.write((i - j) + " ");
				j = next[j];
			}
		}
		utils.flush();
	}
}
