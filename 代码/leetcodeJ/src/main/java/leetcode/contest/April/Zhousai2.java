package leetcode.contest.April;

import java.util.*;
import java.util.List;

public class Zhousai2 {
    static Zhousai2 t = new Zhousai2();
    public List<Integer> intersection(int[][] nums) {
        List<Integer>ans = new ArrayList<>();
        int n = nums.length;
        HashMap<Integer, Integer>cnt = new HashMap<>();
        for (int[] num:nums) {
            for (int x : num) {
                if (cnt.containsKey(x)) {
                    int t = cnt.get(x);
                    if (++t == n) ans.add(x);
                    cnt.put(x, t);
                } else {
                    cnt.put(x, 1);
                    if (n == 1)
                        ans.add(x);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
    int maxv = 101;
    int[] tr = new int[maxv + 5];
    private int lowbit(int x) {
        return x & (-x);
    }
    private void add(int x) {
        for (int i = x; i <= maxv; i += lowbit(i)) tr[i]++;
    }
    private int query(int n) {

        int res = 0;
        for (int i = n; i != 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
    public int[] countRectangles(int[][] rectangles, int[][] points) {

        int n = rectangles.length;
        int m = points.length;
        class Point{
            public int id;
            public int x, y;
        }
        Point[] ps = new Point[m];
        for (int i = 0; i < m; i++) {
            ps[i] = new Point();
            ps[i].id = i;
            ps[i].x = points[i][0];
            ps[i].y = points[i][1];
        }

        Arrays.sort(rectangles, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {    // 逆序
                return Integer.compare(o2[0], o1[0]);
            }
        });
        Arrays.sort(ps, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o2.x, o1.x);     // 逆序
            }
        });
        System.out.print("[");
        for (int[] r : rectangles) {
            System.out.print("[" + r[0] + "," + r[1] + "]");
        }
        System.out.println("]");
        System.out.print("[");
        for (Point r : ps) {
            System.out.print("[" + r.x + "," + r.y + "]");
        }
        System.out.println("]");

        int[] ans = new int[m];
        int cur = 0;
        for (int i = 0; i < m; i++) {
            int x = ps[i].x, y = ps[i].y, id = ps[i].id;
            int l = 0, r = n, k = 0;
            while(l < r) {
                int mid = (l + r) >> 1;
                if (x > rectangles[mid][0]) r = mid;
                else l = mid + 1;
            }
            System.out.println("id = " + id + " l = " + l);
            for (;cur < l; cur++) add(rectangles[cur][1]);
            ans[id] = query(100) - query(y - 1);// 第二维大于他的有多少个
        }
        return ans;
    }
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        /**


         开放的花的个数 - 凋谢的花的个数
         1. 每个人的id记录下来
         2. 排序
         3. 截止到person[i]，有多少花开了
         upper_bound() - 1
         截止到i，有多少花谢了upper_bound() - 1
         */


        int n = flowers.length, m = persons.length;

        int[] f0 = new int[n];
        int[] f1 = new int[n];
        for (int i = 0; i < n; i++) {
            f0[i] = flowers[i][0];
            f1[i] = flowers[i][1];
        }
        Arrays.sort(f0);
        Arrays.sort(f1);
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int t = persons[i];
            int l = 0, r = n, k = 0;
            while(l < r) {
                int mid = (l + r) >> 1;
                if (f0[mid] <= t) l = mid + 1;
                else r = mid;
            }
            k = l; l = 0; r = n;
            while(l < r) {
                int mid = (l + r) >> 1;
                if (f1[mid] < t) l = mid + 1;
                else r = mid;
            }
//            System.out.println("id =  " + id + " start = " + k + " end = " + l);
            ans[i] = k - l;
        }
        return ans;
    }
    public int countLatticePoints(int[][] circles) {
/**
 方法1：直接进行优化
 1. 遍历圆
 2. 对于每一个y计算两个交点
 pow(x1 - c[0], 2) + pow(y - c[1], 2) <= c[2] * c[2]继续
 方法2：直接进行暴力
 */
        int[][] d = new int[205][205];
        int maxx = 0, maxy = 0;
        for (int[] c : circles) {
            maxx = Math.max(maxx, c[0] + c[2]);
            maxy = Math.max(maxy, c[1] + c[2]);
        }
        for (int[] c : circles) {
            for (int y1 = c[1] + c[2], x1 = c[0]; y1 >= c[1]; y1--) {
                while((x1 - c[0]) * (x1 - c[0]) + (y1 - c[1]) * (y1 - c[1]) <= c[2] * c[2]) x1--;
                int y2 = 2 * c[1] - y1, x2 = 2 * c[0] - x1;
                d[y1][x1 + 1]++;
                d[y1][x2]--;
                d[y2][x1 + 1]++;
                d[y2][x2]--;
            }
        }

        int ans = 0;
        for (int y = 0; y <= maxy; y++) {
            int sum = 0;
            for (int x = 0; x <= maxx; x++) {
                sum += d[y][x];
                if (sum > 0) ans++;
            }
        }
        return ans;
    }
    public int countLatticePoints2(int[][] circles) {
/**
 * 方法2：暴力解法
 *  1. 对于每一个圆   O(200)
 *  2. 给圆内的每一个点做一个标记O(200 * 200)
 *  3. 最后遍历一遍
 *  时间复杂度0(8e6)
 */
        boolean[][] isin = new boolean[205][205];
        int maxx = 0, maxy = 0;
        for (int[] c: circles) {
            maxx = Math.max(maxx, c[0] + c[2]);
            maxy = Math.max(maxy, c[1] + c[2]);
            for (int x = c[0] - c[2]; x <= c[0] + c[2]; x++) {
                for (int y = c[1] - c[2]; y <= c[1] + c[2]; y++) {
                    if ((x - c[0]) * (x - c[0]) + (y - c[1]) * (y - c[1]) <= c[2] * c[2])
                        isin[x][y] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= maxx;i++) {
            for (int j = 0; j <= maxy; j++) {
                if (isin[i][j])
                    ans++;
            }
        }
        return ans;
    }




    static void test1() {

        int[][] nums = {{3,1,2,4,5}};
        List<Integer> ans = t.intersection(nums);
        for (int x: ans) System.out.print(x + " ");
        System.out.println();
    }
    static void test2() {
        int[][] rec = {{2,2},{1,1},{3,3}};
        int[][]points = {{1,3},{1,1}};
        int[] ans2 = t.countRectangles(rec, points);
        for (int x : ans2) System.out.println(x);
    }

    static void test3() {
        String s = "[[1,10],[3,3]]";
        s = s.replace("[", "{");
        s = s.replace("]", "}");
        System.out.println(s);
        int[][] flowers = {{1,10},{3,3}};
        int[] persons = {3,3,2};
        int[] ans = t.fullBloomFlowers(flowers, persons);
        for (int x : ans) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
    static void test4() {
        String s = "[[2,2,1]]";
        s = s.replace("[", "{");
        s = s.replace("]", "}");
        System.out.println(s);
        int[][] circles = {{2,2,1}};
        int ans = t.countLatticePoints2(circles);
        System.out.println(ans);
    }




    public static void main(String[] args) {
//        test2();
//        test3();
        test4();
    }
}
