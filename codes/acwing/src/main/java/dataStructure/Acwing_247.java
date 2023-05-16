package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/2/25 10:01
 * @Description: 扫描线
 * 1. 扫描线很难扩展
 * 2.
 * 扫描线：左边加上，右边减去。
 * x 排序
 *
 * 区间修改，区间查询
 * 性质：
 * 1. 永远只考虑根节点的信息 -> 查询不用push_down了。
 * 2. 操作都是成对出现， 且先加后减-> 修改的时候不用push_down了
 *              减法的时候
 *                  1. 恰好成对出现
 *
 *
 *              加法操作：
 *                  1. cnt > 0： 不push_down也不用影响结果
 *                  2. cnt == 0: 懒标记不用下推
 *
 * 由于有double，所以离散化，用unique来做
 *
 *  线段树
 *  1. 存储线段， yl-1 表示 yl-1到y, 所以需要把yl到yr-1进行 + diff就行了
 *  1.1 修改区间，就是修改纵坐标的区间。
 *  1.2 求和，只求头的和
 *  2. cover和
 *  3. segment：离散化 + 扫描线的产物
 *  4. ys, y坐标离散化的产物，保留了。
 *  5. push_up操作
 *      1. 如果cover等于1，说明完全被覆盖，直接就是当前区间的代表现段总长。
 *      2. 如果
 *
 */
public class Acwing_247 {
    private static double[] ys, len;
    private static int[] cover;
    private static Segment[] seg;
    private static int idx;
    private static void init(int n) {
        int m = n * 2;
        ys = new double[m];
        len = new double[m * 4];
        cover = new int[m * 4];
        seg = new Segment[m];
        idx = 0;
    }
    // 使用double进行离散化，很困难啊。
    // 如果单单使用mp进行离散化，那么离散化之后的高度，就不知道等于多少了
    // 这里使用双向映射，就可以知道idx对应的高度是多少了。
    private static void push_up (int u, int l, int r) {
        if (cover[u] == 1) {
            len[u] = ys[r + 1] - ys[l];
        } else if (l == r) len[u] = 0;      // 因为没有push_down,所以碰见叶子结点，从push_up中修改成0
        else len[u] = len[u << 1] + len[u << 1 | 1];
    }
    private static void update(int u, int l, int r, int L, int R, int d) {
        if (L > R) throw new NoSuchElementException(String.format("输入应该保证L <= R但是给定[L, R] = [%d, %d]", L, R));
        if (L > r || R < l) throw new NoSuchElementException(String.format("区间[L, R]应该在区间[l, r]之内但是给定[L, R] = [%d %d], [l, r] = [%d, %d]", L, R, l, r));
        if (L <= l && r <= R) {
            cover[u] += d;
            push_up(u, l, r);           // push_up代替了push_down
            return ;
        }
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R, d);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R, d);
        push_up(u, l, r);
    }
    private static class Segment implements Comparable<Segment>{
        public double x, y1, y2;
        public int diff;

        @Override
        public int compareTo(Segment o) {
            return Double.compare(x, o.x);
        }

        public Segment(double x, double y1, double y2, int diff) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.diff = diff;
        }
    }
    private static double[] unique(double []sortNums) {
        double eps = 1e-9;
        int n = sortNums.length;
        int j = 0;
        for(int i = 0;i < n;i++) {
            if (i == 0 || Math.abs(sortNums[i] - sortNums[i - 1]) > eps) {
                sortNums[j] = sortNums[i];
                j++;
            }
        }
        double[] ans = new double[j];
        for (int i = 0; i < j; i++) {
            ans[i] = sortNums[i];
        }
        return ans;
    }
    public static int find(double aIndex){
        double eps = 1e-9;
        int l = 0;
        int r = ys.length;
        while(l < r){
            int mid = l + r >>1;
            if(ys[mid] - aIndex >= eps) r = mid;
            else l = mid + 1;
        }
        return l - 1;
    }

    public static void main(String[] args) throws Exception{
        int T = 1;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(in.readLine().split(" ")[0]);
        while (n != 0) {
            init(n);
            String[] input;
            for (int i = 0, j = 0, k = 0; i < n; i++) {
                input = in.readLine().split(" ");
                double x1 = Double.parseDouble(input[0]);
                double y1 = Double.parseDouble(input[1]);
                double x2 = Double.parseDouble(input[2]);
                double y2 = Double.parseDouble(input[3]);
                seg[j ++] = new Segment(x1, y1, y2, 1);
                seg[j ++] = new Segment(x2, y1, y2, -1);
                ys[k++] = y1;
                ys[k++] = y2;
            }
            // 离散化, 为了获取idx对应的高度，双向映射
            Arrays.sort(ys);
            Arrays.sort(seg);
            ys = unique(ys);
            idx = ys.length;
            int m = 2 * n;
            double res = 0 ;
            for (int i = 0; i < m; i++) {
                if (i != 0) res += len[1] * (seg[i].x - seg[i - 1].x);
                int l = find(seg[i].y1), r = find(seg[i].y2);
                update(1,0, idx - 1, l, r - 1, seg[i].diff);
            }
            out.write(String.format("Test case #%d\n", T++));
            out.write(String.format("Total explored area: %.2f\n\n", res));
            n = Integer.parseInt(in.readLine().split(" ")[0]);
        }
        out.flush();
        out.close();
        in.close();
    }
}
