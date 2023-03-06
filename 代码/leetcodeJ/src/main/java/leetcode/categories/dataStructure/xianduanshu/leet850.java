package leetcode.categories.dataStructure.xianduanshu;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/2/27 8:36
 * @Description:
 *
 * 1. 为什么需要r - 1
 * 2.
 *
 *
 * 1. 为什么不需要Push_down?
 *
 * 每次只需要访问 根区间，所以询问的时候不用Push_down
 * 每次更新都是先加后减，并且加和减是成对出现的。
 *          所以区间要么等于0，要么等于线段的长度。
 *          等于线段长度的时候，直接可以计算出来。
 *
 *
 */
public class leet850 {
    class Segment implements Comparable<Segment>{                                  // 扫面线，保存线段
        public int x, y1, y2;
        public int diff;                            // 入加，出减

        public Segment(int x, int y1, int y2, int diff) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.diff = diff;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.x, o.x);
        }
    }
    private Segment[] seg;
    private int[] ys, cover;
    HashMap<Integer, Integer>mp;                // 离散化
    HashMap<Integer, Integer>height;            // 双向映射，从下标到高度
    private long[] len;
    private void push_up(int u, int l, int r) {
        if (cover[u] >= 1) {
            len[u] = height.get(r + 1) - height.get(l);     // 这里应该是r + 1
        }
        else if (l == r) len[u] = 0;            // 没有被覆盖，且只有一个线段， 因为要当push_down使用,所以要加上这句话和上面的那句话
        else len[u] = len[u << 1] + len[u << 1 | 1];
    }
    private void update(int u, int l, int r, int L, int R, int diff)
    {
        if (l > r) throw new IllegalArgumentException(String.format("l = %d 应该小于 r = %d", l, r));
        if (L > R) throw new IllegalArgumentException(String.format("L = %d 应该小于 R = %d", L, R));
        if (L > r || R < l) throw new IllegalArgumentException(String.format("[L, R] = [%d %d]不应该在[l, r] = [%d %d]之外", L, R, l, r));
        if (L <= l && r <= R) {
            cover[u] += diff;
            push_up(u, l, r);
            return ;
        }
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R, diff);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R, diff);
        push_up(u, l, r);
    }
    private void init(int n) {
        int m = n * 2;
        this.seg = new Segment[m];
        this.ys = new int[m];
        this.cover = new int[m * 4];
        this.len = new long[m * 4];
        this.mp = new HashMap<>();
        this.height = new HashMap<>();
    }
    public int rectangleArea(int[][] rectangles) {

        int n = rectangles.length;
        int m = n * 2;
        init(n);
        for (int i = 0, j = 0, k = 0; i < n; i++) {
            int[] rec = rectangles[i];
            int x0 = rec[0], y0 = rec[1], x1 = rec[2], y1 = rec[3];
            ys[j++] = y0;
            ys[j++] = y1;
            seg[k++] = new Segment(x0, y0, y1, 1);
            seg[k++] = new Segment(x1, y0, y1, -1);
        }

        Arrays.sort(seg);                                                  // x坐标，类似离散化
        Arrays.sort(ys);
        int idx = 0;
        for (int i = 0; i < m; i++) {
            if (mp.containsKey(ys[i])) continue;
            mp.put(ys[i], ++idx);                                       // 线段下标从1开始
            height.put(idx, ys[i]);
        }
        long ans = 0;
        int mod = (int)1e9 + 7;
        for (int i = 0; i < m; i++) {
            if (i != 0) ans = (ans + len[1] * (seg[i].x - seg[i - 1].x)) % mod;
            update(1, 1, idx - 1, mp.get(seg[i].y1), mp.get(seg[i].y2) - 1, seg[i].diff);
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{

                {0,0,2,2},{1,0,2,3},{1,0,3,1}
        };
        System.out.println(new leet850().rectangleArea(a));
    }
}
