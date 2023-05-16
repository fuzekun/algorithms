package leetcode.contest.siyue;

import leetcode.utils.ChangeToArrayOrList;
import org.jetbrains.annotations.NotNull;

import java.util.*;
/**
 * @author: Zekun Fu
 * @date: 2023/4/9 11:21
 * @Description:
 */
public class First {

    boolean check(int x) {
        int sz = (int)Math.sqrt(x);
        int flag = 1;
        for (int k = 2; k <= sz; k++) {
            if (x % k == 0) {
                flag = 0;
                break;
            }
        }
        if (flag == 1 && x > 1) return true;
        return false;
    }
    public int diagonalPrime(int[][] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (check(nums[i][i])) ans = Math.max(ans, nums[i][i]);
            if (check(nums[i][n - i - 1])) ans = Math.max(ans, nums[i][n - i - 1]);
        }
        return ans;
    }
    public long[] distance(int[] nums) {
        Map<Integer, long[]>mp = new HashMap<>();
        long[] ans = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long[] value = mp.getOrDefault(nums[i], new long[]{0L, 0L});
            long cnt = value[0];
            long sum = value[1];
            ans[i] += i * cnt - sum;
            value[0]++;
            value[1] += i;
            mp.put(nums[i], value);
        }
        mp.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            long[] value = mp.getOrDefault(nums[i], new long[]{0L, 0L});
            long cnt = value[0];
            long sum = value[1];
            ans[i] += sum - i * cnt;
            value[0]++;
            value[1] += i;
            mp.put(nums[i], value);
        }
        return ans;
    }
    boolean check(int[] nums, int x, int p) {
        // nums[i] - nums[j] <= x 找第一个满足的使用就行了
        int ans = 0 ;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++){
            if (nums[i + 1] - nums[i] <= x) {
                ans++;
                i++;
            }
        }
        return ans >= p;
    }
    public int minifmizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int l = 0, r = Arrays.stream(nums).max().getAsInt() + 3;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, mid, p)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    class PR implements Comparable<PR>{
        int x, y;

        public PR(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(@NotNull PR o) {
            return Integer.compare(x, o.x);
        }
    }
    public int minimumVisitedCells(int[][] grid) {
        // 1. 找联通的点,直接使用并查集
        // 2. 找每次能走到的最大点进行跳
        Deque<Integer>que = new ArrayDeque<>();
        int n = grid.length, m = grid[0].length;
        int[] dist = new int[n * m];
        int target = (n - 1) * m + m - 1;
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[0] = 1;
        que.add(0);
        while (!que.isEmpty()) {
            int u = que.poll();
            int x = u / m, y = u % m;
            if (u == target) {
                return dist[u];
            }
            // 最少走一步
            for (int a = 1; a <= grid[x][y]; a++) {
                int nx = x + a;
                int ny = y + a;
                int v1 = nx * m + y;
                int v2 = x * m + ny;
                // 横着走
                if (nx < n && nx >= 0 && dist[v1] > dist[u] + 1) {
                    dist[v1] = dist[u] + 1;
                    que.addLast(v1);
                }
                // 竖着走
                if (ny < m && ny >= 0 && dist[v2] > dist[u] + 1) {
                    dist[v2] = dist[u] + 1;
                    que.addLast(v2);
                }
            }
        }
        // 不可达
        return -1;
    }
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


    public int minimumVisitedCells2(int[][] grid) {
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
        First first = new First();
//        int ans = first.diagonalPrime(ChangeToArrayOrList.changeTo2DIntArray(" [[1,2,3],[5,17,7],[9,11,10]]"));
//        long[] ans = first.distance(new int[]{1,3,1,1,2});
//        int ans = first.minifmizeMax(ChangeToArrayOrList.changTo1DIntArray("[10,1,2,7,1,3]"), 2);
        int ans = first.minimumVisitedCells2(ChangeToArrayOrList.changeTo2DIntArray("[[2,1,0],[1,0,0]]"));
        System.out.println(ans);

    }

}
