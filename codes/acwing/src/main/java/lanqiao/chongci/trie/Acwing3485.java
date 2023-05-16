package lanqiao.chongci.trie;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 子数组的最大异或和
 * 前缀和 + 带有删除的trie树
 * 1. trie树进行一个前缀和的维护
 * 2. 如果有相反的，像相反的走，采用贪心的思想，没有只能向通向走了。
 * 
 * 
 * */
public class Acwing3485 {
	
	private static int idx;
	private static int[][] nx;
	private static int[] cnt;
	private static final int HIGH = 32;
	private static void op(int x, int flag) {
		int cur = 0;
		for (int i = HIGH; i >= 0; i--) {
			int c = (x >> i) & 1;
			// 创建一个新的结点
			if (nx[cur][c] == 0) nx[cur][c] = ++idx;
			// 统计当前分支中有几个数字
			cnt[cur] += flag;
			cur = nx[cur][c];
		}
		// 1，增加结点， -1，删除结点
		cnt[cur] += flag;
	}
	
	private static int getMax(int x) {
		int cur = 0;
		int ans = 0;
		for (int i = HIGH; i >= 0; i--) {
			int c = (x >> i) & 1;
			ans <<= 1;
			// 如果有相反的结点，贪心反向走，本位放1；否则只能同向走，本位放0;如果通向没有，说明是第一个数字
			if (nx[cur][c ^ 1] != 0 && cnt[nx[cur][c ^ 1]] != 0) {
				cur = nx[cur][c ^ 1];
				ans |= 1;
			}
			else if (nx[cur][c] != 0 && cnt[nx[cur][c]] != 0) cur = nx[cur][c];
			else break;	
		}
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		nx = new int[n * 50][2];
		cnt = new int[n * 50];
		
		int ans = 0;
		int[] sum = new int[n + 1];
		op(0, 1);
		for (int i = 1; i <= n; i++) {
			int x = util.nextInt();
			sum[i] = sum[i - 1] ^ x;
			// 这个需要注意，什么时候删除，需要删除哪一个
			if (i > m) {
				op(sum[i - m - 1], -1);
			}
			ans = Math.max(ans, getMax(sum[i]));
			op(sum[i], 1);
		}
		util.write(ans + "");
		util.flush();
		
	}

}
