package leetcode.categories.search;


/*
*
*   bfs的模板题目
* https://leetcode-cn.com/problems/count-unguarded-cells-in-the-grid/
* */
public class BfsModel {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] vis = new int[m][n];
        int[][] isG = new int[m][n];
        int[][] isW = new int[m][n];
        for (int[] g : guards) {
            isG[g[0]][g[1]] = 1;
        }
        for (int[] w : walls) {
            isW[w[0]][w[1]] = 1;
        }
        for (int i = 0; i < m; i++) {
            int pre = 0;
            for (int j = 0; j < n; j++) {
                if (isG[i][j] == 1) {   // 是G的话
                    pre = 1;
                    vis[i][j] = 1;
                } else if (isW[i][j] == 1) {  // 是墙的话
                    pre = 0;
                    vis[i][j] = 1;
                }
                else vis[i][j] |= pre; // 前边是墙就行
            }
            for (int j = n - 1; j >= 0; j--) {
                if (isG[i][j] == 1) {   // 是G的话
                    pre = 1;
                    vis[i][j] = 1;
                } else if (isW[i][j] == 1) {  // 是墙的话
                    pre = 0;
                    vis[i][j] = 1;
                }
                else vis[i][j] |= pre; // 前边是墙就行
            }
        }

        for (int j = 0; j < n; j++) {
            int pre = 0;
            for (int i = 0; i < m; i++) {
                if (isG[i][j] == 1) {   // 是G的话
                    pre = 1;
                    vis[i][j] = 1;
                } else if (isW[i][j] == 1) {  // 是墙的话
                    pre = 0;
                    vis[i][j] = 1;
                }
                else vis[i][j] |= pre; // 前边是墙就行
            }
            for (int i = m - 1; i >= 0; i--) {
                if (isG[i][j] == 1) {   // 是G的话
                    pre = 1;
                    vis[i][j] = 1;
                } else if (isW[i][j] == 1) {  // 是墙的话
                    pre = 0;
                    vis[i][j] = 1;
                }
                else vis[i][j] |= pre; // 前边是墙就行
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == 0)
                    ans++;
            }
        }
        return ans;
    }
}
