package lanqiao.chongci.graph;

import java.util.ArrayList;
import java.util.List;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 使用dfs进行一个拓扑排序
 * 
 * 
 * */
public class Tuopudfs {
	static List<Integer>[]g;
	static int[] c;
	static int[] nums;
	static int id;
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		g = new ArrayList[n + 1];
		c = new int[n + 1];
		nums = new int[n + 1];
		id = n - 1;
		for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			int a = util.nextInt(), b = util.nextInt();
			g[a].add(b);
		}
		
		int flag = 1;
		for (int i = 1; i <= n; i++) {
			if (c[i] == 0 && !dfs(i)) {
				flag = 0;
				break;
			}
		}
		if (flag == 0 || id != -1) util.write("1-\n");
		else {
			for (int i = 0; i < n; i++) {
				util.write(nums[i] + " ");
			}
			util.write("\n");
		}
		util.flush();
	}
	
	private static boolean dfs(int u) {
		c[u] = -1;
		for (int v : g[u]) {
			if (c[v] < 0) return false;
			if (c[v] == 0 && !dfs(v)) return false;
		}
		
		c[u] = 1;
		nums[id--] = u;
		
		return true;
	}

}
