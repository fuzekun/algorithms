package lanqiao.chongci.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import utils.InAndOutUitl;

public class Spfa {
	static class PR implements Comparable<PR> {
		int x, y;
		@Override
		public int compareTo(PR o) {
			// TODO Auto-generated method stub
			return Integer.compare(x, o.x);
		}
		public PR(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		List<PR>[] g = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) g[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int a = util.nextInt(), b = util.nextInt(), w = util.nextInt();
			g[a].add(new PR(b, w));
		}
		PriorityQueue<PR>que = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		int[] vis = new int[n + 1];
		Arrays.fill(dist, 0x3f3f3f3f);
		que.add(new PR(0, 1));
		dist[1] = 0;
		
		while (!que.isEmpty()) {
			PR t = que.poll();
			int d = t.x, u = t.y;
			
			vis[u] = 0;
			for (PR tmp : g[u]) {
				 int v = tmp.x, w = tmp.y;
				 if (dist[u] + w < dist[v]) {			// 松弛
					 dist[v] = d + w;		
					 if (vis[v] == 0) {
						 vis[v] = 1;
						 que.add(new PR(dist[v], v));
					 }
				 }
			}
		}
		
		if (dist[n] == 0x3f3f3f3f) util.write("-1\n");
		else util.write(dist[n] + "\n");
		util.flush();
	}
}
