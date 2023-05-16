package lanqiao.chongci.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import utils.InAndOutUitl;

/**

bellman-ford的思想：
初始距离都为正无穷
每次加入一个点，如果可以缩小其他点的距离，说明其他点的距离仍旧能缩小，所以需要继续加入队列，否则不需要加入。
因为加入了也是白加入。
*/

public class SPFA2 {
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
		int n = util.nextInt(), m = util.nextInt(), k = util.nextInt();
		List<PR>[] g = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			int a = util.nextInt(), b = util.nextInt(), w = util.nextInt();
			g[a].add(new PR(b, w));
		}
		
		Deque<Integer>que = new ArrayDeque<>();
		int[] dist = new int[n + 1];
		int[] cnt = new int[n + 1];
		int[] st = new int[n + 1];
		Arrays.fill(dist, 0x3f3f3f3f);
		
		dist[1] = 0;
		st[1] = 1;
		cnt[1] = 0;
		que.add(1);
		
//		int flag = 1;
		
		// 使用bellman求有边数限制的最短路
		
//		for (int i = 0; i < k; i++) {
//			int[] copy = Arrays.copyOf(dist, n + 1);
//			// 对所有边进行松弛操作
//			for (int u = 1; u <= n; u++) {
//				for (PR nx : g[u]) {
//					int v = nx.x, w = nx.y;
//					dist[v] = Math.min(dist[v], copy[u] + w);
//				}
//			}
//		}
		int flag = 1;
		while (!que.isEmpty()) {
			int u = que.pollFirst();
			st[u] = 0;
			if (cnt[u] == k) continue;
			
			for (PR nx : g[u]) {
				int v = nx.x, w = nx.y;
				if (dist[v] > dist[u] + w) {
					dist[v] = dist[u] + w;
					if (st[v] == 0) {
						st[v] = 1;
						cnt[v] = cnt[u] + 1;
						// 队列中点经过的边，小于等于n
				// 		if (cnt[v] >= n) {
				// 			flag = 0;
				// 			break;
				// 		}
						if (que.isEmpty() || dist[que.peekFirst()] > dist[v])
							que.addFirst(v);
						else que.addLast(v);
					}
				}
			}
			if (flag == 0) break;
		}
		if (flag == 1 && dist[n] != 0x3f3f3f3f) util.write("" + dist[n]);
		else util.write("impossible\n");
		
		if (dist[n] < 0x3f3f3f3f / 2) util.write("" + dist[n]);
		else util.write("impossible\n");
		util.flush();
		
	}
}
