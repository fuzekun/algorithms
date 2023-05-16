package lanqiao.chongci.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import utils.InAndOutUitl;

/**
 * 
 * 作物杂交
 * 
 * */
public class Acwing3305 {
	
	static class PR implements Comparable<PR>{
		int x, y;
		public PR(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(PR o) {
			return Integer.compare(x, o.x);
		};
	}
	
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt(), m = util.nextInt(), k = util.nextInt(), target = util.nextInt();
		List<PR>[] g = new ArrayList[n + 1];
		int[] nums = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			nums[i] = util.nextInt();
		}
		int[]source = new int[m];
		for (int i = 0; i < m; i++) {
			source[i] = util.nextInt();
		}
		for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			int a = util.nextInt(), b = util.nextInt(), c = util.nextInt();
			g[a].add(new PR(c, b));
			g[b].add(new PR(c, a));
		}
		
		PriorityQueue<PR>que = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, 0x3f3f3f3f);
		for (int i = 0; i < m; i++) {
			que.add(new PR(0, source[i]));
			dist[source[i]] = 0;
		}
		
		while (!que.isEmpty()) {
			PR f = que.poll();
			int d = f.x, u = f.y;				// 当前的最小距离是d，对应的点是u
			
			// 找到了对应的结点
			if (u == target) {
				util.write(dist[u] + "");
				break;
			}
			
			List<Integer>backup = new ArrayList<>();
			for (PR nx : g[u]) {
				// 到达这个结点的距离，等于相邻结点的距离和自己距离的最大值
				int v = nx.x, pr = nx.y;
				if (dist[pr] == 0x3f3f3f3f) continue;	// 相邻的结点无法到达
				// 首先等待两个结点都生成，然后，生成新的结点，需要的时间为两个的最大值。
				int dist_v = Math.max(dist[u], dist[pr]) + Math.max(nums[u], nums[pr]);
				if (dist_v < dist[v]) {
					dist[v] = dist_v;
					que.add(new PR(dist[v], v));
				}
			}
			
		}
		
		util.flush();
	}
}
