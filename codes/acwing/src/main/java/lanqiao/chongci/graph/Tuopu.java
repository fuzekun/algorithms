package lanqiao.chongci.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.InAndOutUitl;

/**
 * 
 * 
 * 
 * 本题目中如果初始不存在环的话，就一定可以构造一个没有环的。因为没有重边和自环的情况。
 * 无方向的边所在的点，如果它入度为0，直接做成边的起始点就行了。
 * 
 * 对于入度为0的点，将所有的无向边做成以他为起点的有向边, 那么去掉它后，别的点的度最起码不会增加，
 * 所以原来没有环的肯定仍旧没有环。
 * 
 * 最后看能否做成一个拓扑序就行了。
 * 
 * bfs过程
 * 0. 将所有度数为0的边加入到队列中。
 * 1. 将所有的有向边连接点的度数减去1，如果度为0，放入队列
 * 2. 将所有无向边改成有向边，如果边的汇点没有vis过，边指向汇点; 否则这个边一定被访问过了, 就不用管了。
 * 3. 使用一个nums记录已经访问过的点，形成拓扑序
 * 4. 最后nums中点的数量如果 == n说明true，输出所有的有向边，否则false
 * */
public class Tuopu {
	
	public static void main(String[] args) throws Exception{
		InAndOutUitl util = new InAndOutUitl();
		int T = util.nextInt();
		while (T-- != 0) {
			int n = util.nextInt(), m = util.nextInt();
			int[] d = new int[n + 1];
			List<Integer>[] gv = new ArrayList[n + 1];
			List<Integer>[] gn = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				gv[i] = new ArrayList<>();
				gn[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; i++) {
				int t = util.nextInt(), a = util.nextInt(), b = util.nextInt();
				if (t == 1) {
					d[b]++;
					gv[a].add(b);
				}
				else {
					gn[a].add(b);
					gn[b].add(a);
				}
			}
			
			Queue<Integer>que = new ArrayDeque<>();
			
			for (int i = 1; i <= n; i++) {
				if (d[i] == 0) que.add(i);
			}
			
			List<Integer>list = new LinkedList<>();
			int[] vis = new int[n + 1];
			
			while (!que.isEmpty()) {
				int u = que.poll();
				vis[u] = 1;
				list.add(u);
				
				for (int v: gv[u]) {
					d[v]--;
					if (d[v] == 0) que.add(v);
				}
				for (int v : gn[u]) {
					if (vis[v] == 0) {
						gv[u].add(v);
					}
				}
			}
			if (list.size() == n) {
				util.write("YES\n");
				for (int i = 1; i <= n; i++) {
					for (int v : gv[i]) {
						util.write(i + " " + v + "\n");
					}
				}
			} else {
				util.write("NO\n");
			}
			util.flush();
		}
	}
	
}
