package __bfs;


import java.util.ArrayDeque;
import java.util.Queue;

import utils.InAndOutUitl;

/**
 * 
 * 地雷与勇士，蓝桥杯算法训练
 * bfs求最短路
 * 
 * 1. 需要从0开始作为源点
 * 2. 如果使用单数组，不可达的表示
 * 
 * */
public class BfsShort {
	public static void main(String[] args) throws Exception {
		InAndOutUitl util = new InAndOutUitl();
		int n = util.nextInt();
		char[][] grid = new char[n][];
		
		for (int i = 0; i < n; i++) {
			grid[i] = util.nextLine().toCharArray();
		}
		// 到每一个点的距离， 0表示不可达
		int[][] dist = bfs(grid);
		Integer x;
		while ((x = util.nextInt()) != null) {
			int y = util.nextInt(), thread = util.nextInt() + 1; 	// 初始算作第一步
			if(dist[x][y] > 0 && dist[x][y] <= thread) util.write("1\n");
			else util.write("-1\n");
		}
		util.flush();
	}
	private static int[][] bfs(char[][] grid) {
		int[][] dirs = {{-1, 0}, {1, 0}, {0, 1},{0, -1}};
		int n = grid.length, m = grid[0].length;
		Queue<int[]>que = new ArrayDeque<>();
		que.add(new int[]{0, 0});
		int[][] step = new int[n][m];
		// 两个作用，步数、标志位。初始步数为1
		step[0][0] = 1;
		while (!que.isEmpty()) {
			int[] t = que.poll();
			int x = t[0], y = t[1];
			int cnt = step[x][y];
			for (int i = 0; i < 4; i++) {
				int nx = x + dirs[i][0], ny = y + dirs[i][1];
				if (nx >= n || ny >= m || nx < 0 || ny < 0 || grid[nx][ny] == '*' || step[nx][ny] > 0) continue;
				que.add(new int[] {nx, ny});
				step[nx][ny] = cnt + 1; 
			}
		}
		return step;
	}
}
