package lanqiao.chongci.bingcha;

import utils.InAndOutUitl;

public class Acwing240_2 {
	
	private static int[]fa;
	private static int[]d;
	private static void init() {
		for (int i = 0; i < fa.length; i++) {
			fa[i] = i; 
		}
	}
	private static int getP(int x) {
		if (fa[x] ==  x) return x;
		int p = getP(fa[x]);
		d[x] += d[fa[x]];
		d[x] %= 3; 
		fa[x] = p;
		return p;
	}
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		fa = new int[n + 1];
		d = new int[n + 1];
		init();
		int ans = 0;
		int k = util.nextInt();
		for (int i = 0; i < k; i++) {
			int op = util.nextInt(), a = util.nextInt(), b = util.nextInt();
			if (a > n || b > n) {
				ans++;
				continue;
			}
			int pa = getP(a), pb = getP(b);
			if (op == 2) {
				// a吃b，a如果是0， b应该是1
				if ((pa == pb) && (d[b] - d[a] - 1) % 3 != 0) ans++;
				else if (pa != pb) {
					fa[pb] = pa;
					d[pb] = d[a] - d[b] + 1;
				}
			} else {
				if ((pa == pb) && (d[b] - d[a]) % 3 != 0) ans++;
				else if (pa != pb) {
					fa[pb]= pa;
					d[pb] = d[a] - d[b];
				}
			}
		}
		util.write(ans+"");
		util.flush();
	}

}
