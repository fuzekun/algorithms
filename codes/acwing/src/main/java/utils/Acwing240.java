package utils;

/**
 * 
 * 
 * 食物链
 * */
public class Acwing240 {
	
	private static int[] fa;
	private static void init() {
		for (int i = 0; i < fa.length; i++) {
			fa[i] = i; 
		}
	}
	private static int getP(int x) {
		if (fa[x] == x) return x;
		int p = getP(fa[x]);
		fa[x] = p;
		return p;
	}
	private static void unite(int x, int y) {
		int fx = getP(x), fy = getP(y);
		if (fx == fy) return ;
		fa[fx] = fy;
	}
	private static boolean same(int x, int y) {
		return getP(x) == getP(y);
	}
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt(), m = util.nextInt();
		fa = new int[n * 4];
		init();
		int ans = 0;
		for (int i = 0; i < m; i++) {
			int rel = util.nextInt(), a = util.nextInt(), b = util.nextInt();
			if (a > n || b > n || rel == 2 && a == b) {
				ans++;
				continue;
			}
			if (rel == 1) {
				if ((same(a, b + n) || same(a, b + 2 * n))) ans++;
				else {
					unite(a, b);
					unite(a + n, b + n);
					unite(a + 2 * n, b + 2 * n);
				}
			}
			else {
				if(same(a, b) || same(a, b + n)) ans++;
				else {
					unite(a + n, b);
					unite(a + 2 * n, b + n);
					unite(a, b + 2 * n);
				}
			}
		}
		util.write(ans+"");
		util.flush();
	}
}
