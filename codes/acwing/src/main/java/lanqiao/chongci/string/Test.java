package lanqiao.chongci.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import utils.InAndOutUitl;

public class Test {
	
	static class PR implements Comparable<PR>{
		int x, y;
		public PR(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(PR o) {
			// TODO Auto-generated method stub
			return Integer.compare(x, o.x);
		}
	}
	public static void main(String[] args) throws Exception {
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		int m = util.nextInt();
		List<PR>[] g = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) g[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int x = util.nextInt(), y = util.nextInt(), w = util.nextInt();
			g[x].add(new PR(y, w));
		}
		
		PriorityQueue<PR>que = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		que.add(new PR(0, 1));
		Arrays.fill(dist, -1);
		dist[1] = 0;
		
		int flag = 0;
		while (!que.isEmpty()) {
			PR t = que.poll();
			int d = t.x, u = t.y;
			if (u == n) {
				flag = 1;
				util.write(d + "\n");
				break;
			}
			for (PR tmp: g[u]) {
				int v = tmp.x, w = tmp.y;
				if (dist[v] == -1 || w + d < dist[v]) {
					dist[v] = d + w;
					que.add(new PR(dist[v], v));
				}
			}
		}
		if (flag == 0) util.write("-1\n");
		util.flush();
	}
}
