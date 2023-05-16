package bingcha;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 银河英雄传说
 * 1. 前缀和 + 并查集
 * 
 * 带上路径长度的并查集
 * */
public class Acwing238 {
	private static final int N = 30005;
	private static int[] rank;
	private static int[] fa;
	private static int[] sz;
	private static void init() {
		for (int i = 0; i < fa.length; i++) {
			fa[i] = i;
			sz[i] = 1; 
			// 根的rank = 0，这样才能使用路径压缩，要不然每一次查询都加上1
		}
	}
	private static int getP(int x) {
		if (fa[x]== x) return x;
		int p = getP(fa[x]);
		rank[x] += rank[fa[x]]; 
		fa[x] = p;
		return p;
	}
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		fa = new int[N];
		rank = new int[N];
		sz = new int[N];
		init();
		for (int i = 0; i < n; i++) {
			String[] input = util.nextLine().split(" ");
			int x = Integer.parseInt(input[1]), y = Integer.parseInt(input[2]);
			int fx = getP(x), fy = getP(y);
			if ("M".equals(input[0])) {
				if (fx == fy) continue;
				fa[fx] = fy;
				rank[fx] = sz[fy];
				sz[fy] += sz[fx];
			}
			else {
				if (fx == fy) util.write(Math.max(0, Math.abs(rank[x] - rank[y]) - 1) + "\n");
				else util.write("-1\n");
			}
		}
		util.flush();
	}
}
