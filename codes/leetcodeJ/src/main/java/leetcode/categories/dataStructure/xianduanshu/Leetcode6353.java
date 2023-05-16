package leetcode.categories.dataStructure.xianduanshu;

import leetcode.contest.sanyue.Leet_6318;
import leetcode.utils.ChangeToArrayOrList;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/4/9 16:49
 * @Description: leetcode 340场单周赛
 *
 * 行列线段树, 区间修改单点求和
 * dp 更新
 *
 * 用每一个点的最小距离更新右边和下边的其他点，是最朴素的想法，也就是dp。
 * 但是(i, j)状态有grid[i][j]个转移，时间复杂度太高。
 *
 * 对于每个坐标，只能横向或者纵向更新，更新的距离为dist[i][j] + 1; dist[i][j]表示到i, j的最小距离。
 * 所以对于每一行和每一列可以使用线段树进行维护。
 * 对于每一个点，可以将本行的区间[j, j + grid[i][j]]更新为dist[i][j] + 1;
 * 可以将本列的(i, i + grid[i][j])更新为dist[i][j] + 1;
 * 那么到当前点的最小距离就是min(行区间中当前点的最小值，列区间中当前点的最小值);
 *
 * 所以就转化成了区间求和，单点访问的问题。
 */
public class Leetcode6353 {

    void push_up(int u, int[] tr) {
        tr[u] = Math.min(tr[u << 1], tr[u << 1 | 1]);
    }
    void push_down(int u, int x, int[]tr, int[] lazy) {
        tr[u] = Math.min(tr[u], x);
        lazy[u] = Math.min(lazy[u], x);
    }
    void push_down(int u, int[] tr, int[] lazy) {
        if (lazy[u] == 0x3f3f3f3f) return ;
        push_down(u << 1, lazy[u], tr, lazy);
        push_down(u << 1 | 1, lazy[u], tr, lazy);
        lazy[u] = 0x3f3f3f3f;
    }
    void modify(int u, int l, int r, int L, int R, int x, int[] tr, int[] lazy) {
        if (L <= l && R >= r) {
            push_down(u, x, tr, lazy);
            return ;
        }
        push_down(u, tr, lazy);
        int mid = l + r >> 1;
        if (L <= mid) modify(u << 1, l, mid, L, R, x, tr, lazy);
        if (R > mid) modify(u << 1 | 1, mid + 1, r, L, R, x, tr, lazy);
        push_up(u, tr);
    }

    int query(int u, int l, int r, int L, int R, int[] tr, int[] lazy) {
        if (L <= l && R >= r) {
            return tr[u];
        }
        push_down(u, tr, lazy);
        int mid = l + r >> 1;
        int ans = 0x3f3f3f3f;
        if (L <= mid) ans = Math.min(query(u << 1, l, mid, L, R, tr, lazy), ans);
        if (R > mid) ans = Math.min(query(u << 1 | 1, mid + 1, r, L, R, tr, lazy), ans);
        return ans;
    }


    public int minimumVisitedCells(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] rtr = new int[n][m * 4];
        int[][] ltr = new int[m][n * 4];
        int[][] rlazy = new int[n][m * 4];
        int[][] llazy = new int[m][n * 4];

        for (int i = 0; i < n; i++) {
            Arrays.fill(rtr[i], 0x3f3f3f3f);
            Arrays.fill(rlazy[i], 0x3f3f3f3f);
        }
        for (int i = 0; i < m; i++) {
            Arrays.fill(ltr[i], 0x3f3f3f3f);
            Arrays.fill(llazy[i], 0x3f3f3f3f);
        }
        modify(1, 0, m, 0, 0, 1, rtr[0], rlazy[0]);
        modify(1, 0, n, 0, 0, 1, ltr[0], llazy[0]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = grid[i][j];
                int dist = Math.min(query(1, 0, m, j, j, rtr[i], rlazy[i]), query(1, 0, n, i, i, ltr[j], llazy[j]));
                if (x == 0 || dist == 0x3f3f3f3f) continue;
                modify(1, 0, m, j, Math.min(j + x, m), dist + 1, rtr[i], rlazy[i]);
                modify(1, 0, n, i, Math.min(i + x, n), dist + 1, ltr[j], llazy[j]);
            }
        }
        int dist = Math.min(query(1, 0, m, m - 1 , m - 1, rtr[n - 1], rlazy[n - 1]), query(1, 0, n, n - 1, n - 1, ltr[m - 1], llazy[m - 1]));
        if (dist == 0x3f3f3f3f) return -1;
        return dist;
    }

    public static void main(String[] args) {
        Leetcode6353 l = new Leetcode6353();
        int ans = l.minimumVisitedCells(ChangeToArrayOrList.changeTo2DIntArray("[[6,4,8],[7,3,2],[2,1,11],[8,13,12],[4,3,0]]"));
        System.out.println(ans);
    }

}
