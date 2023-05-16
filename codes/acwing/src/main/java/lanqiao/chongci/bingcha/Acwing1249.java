package lanqiao.chongci.bingcha;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import utils.InOutUtil;

/**
 * 
 * 
 *	亲戚 
 *可能出现的问题
 *1. 变量没有相应的更新
 *2. 并查集的getFa写错
 *3. 输出不使用buffered而超时
 **/
public class Acwing1249 {
	
	private static int[] fa;
	private static int getP(int x) {
		if (fa[x] == x) return x;
		fa[x] = getP(fa[x]);			// 这里是找父亲的父亲，不是找自己的父亲
		return fa[x];
	}
	/**
	 * 
	 * 合并成功，就是不是一棵树，
	 * 否则就是一颗树
	 * */
	private static boolean unite(int x, int y) {
		int fx = getP(x), fy = getP(y);
		if (fx == fy) return false;
		fa[fx] = fy;
		return true;
	}
	private static void init() {
		for (int i = 0; i < fa.length; i++) {
			fa[i] = i; 
		}
	}
	private static boolean same(int x, int y) {
		if (getP(x) == getP(y)) return true;
		return false;
	}
	public static void main(String[] args) throws Exception{
		BufferedWriter out = InOutUtil.getBufferedWriter();
		BufferedReader in = InOutUtil.getBufferedReader();
		int[] input = InOutUtil.toNumArray(in.readLine());
		int n = input[0], m = input[1];
		fa = new int[n + 1];
		init();
		for (int i = 0; i < m; i++) {
			input = InOutUtil.toNumArray(in.readLine());
			unite(input[0], input[1]);
		}
		int q = Integer.parseInt(in.readLine());
		for (int i = 0; i < q; i++) {
			input = InOutUtil.toNumArray(in.readLine());
			if (same(input[0], input[1])) out.write("Yes\n");
			else out.write("No\n");;
		}
		out.flush();
	}
}
