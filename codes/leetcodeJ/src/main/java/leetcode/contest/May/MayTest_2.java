package leetcode.contest.May;


import javafx.util.Pair;
import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.PrintArrays;

import java.util.ArrayDeque;
import java.util.Queue;

/*
*   周赛地址
*   https://leetcode-cn.com/contest/biweekly-contest-77/
*
*
* 1. 注意s.length < word.length的情况
* 2. 注意前缀和可能会超过int,所以使用long进行保存
* 3. 注意超时
*
* 每一行的找第一个墙
* 每一列找第一个墙
*
*
* 1. 首先看每一个格子在什么时候起火 ht
* 2. 二分查看每一个时间
* 3. 如果t + st <= ht说明可以到达这个格子。否则就到不了
*
* */
public class MayTest_2 {
    /*
    *
    *   统计前缀单词数目
    * */
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {

            if (s.length() < word.length()) continue;
            int i;
            for (i = 0; i < word.length(); i++) {
                if (word.charAt(i) != s.charAt(i))
                    break;
            }
            if (i == word.length()) {
                System.out.println(word);
                ans++;
            }
        }
        return ans;
    }
    public int minimumAverageDifference(int[] nums) {
        long sum = 0, pre = 0;
        int n = nums.length;
        long ans = Long.MAX_VALUE;
        int pos = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < n; i++) {
            pre += nums[i];
            long after = (sum - pre);
            if (i == n - 1) after = 0;
            else after /= (n - i - 1);
            long t = Math.abs(pre / (i + 1) - after);
            System.out.println("nums = " + nums[i] + " pre = " + pre + " after = " + (sum - pre) + " t = " + t);

            if (ans > t) {
                ans = t;
                pos = i;
            }
        }
        return pos;
    }
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        boolean[][] vis = new boolean[m][n];
        boolean[][] isWall = new boolean[m][n];
        Queue<Pair<Integer, Integer>>que =new ArrayDeque<>();
        for (int[] wall : walls) {
            vis[wall[0]][wall[1]] = true;
            isWall[wall[0]][wall[1]] = true;
        }
        int[][]dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] guard : guards) {
            int x = guard[0], y = guard[1];
            System.out.println("u = (" + x + "," + y + "):");
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {   // 四个方向,走到头，或者到墙
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                while(nx >= 0 && ny >= 0 && nx < m && ny < n && !isWall[nx][ny]) {
                    vis[nx][ny] = true;
                    nx += dirs[i][0];
                    ny += dirs[i][1];
                }
                System.out.println("v = (" + nx + "," + ny + ")");
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!vis[i][j]) ans++;
            }
        }
        return ans;
    }
    public int countUnguarded2(int m, int n, int[][] guards, int[][] walls) {
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
    final int INF = 0x3f3f3f3f;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean check(int[][]grid, int t) { // 格子着火的最小时间

        int base = 301;
        int n = grid.length, m = grid[0].length;
        Queue<Pair<Integer, Integer>>que = new ArrayDeque<>();
        que.add(new Pair<>(0, 1));
        boolean[][] vis = new boolean[n][m];
        vis[0][0] = true;
        while(!que.isEmpty()) {
            Pair<Integer, Integer> p = que.poll();
            int x = p.getKey() / base;
            int y = p.getKey() % base;
            int st = p.getValue();
//            System.out.println("u = (" + x + "," + y + "," + st + ")");

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx == n - 1 && ny == m - 1 && grid[nx][ny] >= st + t + 1) return true;
                if (nx >= n || ny >= m || nx < 0 || ny < 0 || vis[nx][ny] || grid[nx][ny] <= st + t + 1)
                    continue;
//                System.out.print("(" + nx + "," + ny + "," + (st + 1) + ") ");
                vis[nx][ny] = true;
                que.add(new Pair<>(nx * base + ny, st + 1));
            }
//            System.out.println("");
        }
        return false;
    }

    public int maximumMinutes(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<Pair<Integer, Integer>>que = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    que.add(new Pair<>(i, j));
                }
                if (grid[i][j] == 2) {  // 都不可以通过
                    grid[i][j] = -1;
                    vis[i][j] = true;
                }
            }
        }
       while(!que.isEmpty()) {
            Pair<Integer, Integer>p = que.poll();
            int x = p.getKey();
            int y = p.getValue();
            int st = grid[x][y];
            vis[x][y] = true;
//            System.out.println("u = (" + x + "," + y + "," + st + ")");
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= n || ny >= m || nx < 0 || ny < 0 || vis[nx][ny]) continue;
                grid[nx][ny] = st + 1;
                que.add(new Pair<>(nx, ny));
//                System.out.print("(" + nx + "," + ny + "," + (st + 1) + ") ");
            }
//            System.out.println();
        }

       for (int i = 0; i < n; i++) {
           for (int j = 0; j < m; j++) {
               if (grid[i][j] == 0) grid[i][j] = INF;
           }
       }
        PrintArrays.print2DIntArray(grid);

        int l = 0, r = m * n + 1;
        while(l < r) {
            int mid = (l + r) >> 1;
            if (check(grid, mid)) l = mid + 1;
            else r = mid;
        }
        return Math.min(grid[0][0], l - 1 == m * n ? (int)1e9 : l - 1);
    }


        public static void main(String[] args) {
        MayTest_2 mayTest_2 = new MayTest_2();
//        String[] words = {"a","b","c","ab","bc","abc"};
//        String s = "abc";
//        PrintArrays.print1DObjArray(words);
//        System.out.println(mayTest_2.countPrefixes(words, s));

//        String s = " [0]";
//        int [] nums = ChangeToArrayOrList.changTo1DIntArray(s);
//        System.out.println(mayTest_2.minimumAverageDifference(nums));

//        String g = "  [[1,1]]";
//        String w = "[[0,1],[1,0],[2,1],[1,2]]";
//        int[][] guards = ChangeToArrayOrList.changeTo2DIntArray(g);
//        int[][] walls = ChangeToArrayOrList.changeTo2DIntArray(w);
//
//        System.out.println(mayTest_2.countUnguarded2(3, 3, guards, walls));

        String g = "[[0,2,0,0,1],[0,2,0,2,2],[0,2,0,0,0],[0,0,2,2,0],[0,0,0,0,0]]";
        int[][] grids = ChangeToArrayOrList.changeTo2DIntArray(g);
        System.out.println(mayTest_2.maximumMinutes(grids));
    }
}
