package chapter9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Zekun Fu
 * @date: 2022/11/20 9:59
 * @Description: 巴比伦塔
 * Uva437 简单的dp问题
 * 本题和leetcode中的 943最短超级串很相似
 * 前者有约束条件，后者没有约束条件。
 * 所以前者DAG，后者排列->选择。
 *
 * 30个立方体，不是正方体，从大到小落到一起。选择最高的。
 * 1. 维度：(正方体的边，正方体的种类)
 * 2. 找子问题：已经知道了上面两个。
 *
 * 只有顶面的尺寸会影响决策。因此可以使用(a, b)来表示
 * 顶面尺寸为a * b的状态。
 *
 * (idx, k):idx是正方体的
 * 枚举在上面的正方形，枚举在上面的面。
 *
 * 典型的DAG上的动态规划，只取决于顶面，由大向小的进行转移
 * 1. 将所有的6种情况放入队列种
 * 2. 转化成了完全背包问题,高是val. 底是w1和w2。
 */
public class TowerOfBabylon_Uva437 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args)throws Exception {
        int n = Integer.valueOf(in.readLine());
        int T = 0;
        while (n != 0) {
            int[][] a = new int[n][3];
            int[][] arr = new int[n * 3][3];
            int idx = 0;
            for (int i = 0; i < n; i++) {
                String[] input = in.readLine().split(" ");
                for (int j = 0, t = 0; j < input.length; j++) {
                    if (input[j].compareTo("") == 0) continue;
                    a[i][t++] = Integer.valueOf(input[j]);
                }
                for (int j = 0; j < 3; j++) {
                    arr[idx++] = get(j, a[i]);
                }
            }
            Arrays.sort(arr, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                    if (o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                    return Integer.compare(o1[2], o2[2]);
                }
            });
            /*
            *   a * b * c
            *   0 :  高是 a, 底面是b * c
            *   1 : 高是b, 底面是a * c
            *   2 : 高是c, 底面是a * b
            * */
            int ans = 0;
            int[] dp = new int[n * 3];
            for (int i = 0; i < n * 3; i++) {
                int h = arr[i][2], w1 = arr[i][0], w2 = arr[i][1];
                dp[i] = 0;
                for (int j = 0; j < i; j++) {
                    int wn1 = arr[j][0], wn2 = arr[j][1];
                    if (wn1 < w1 && wn2 < w2)
                        dp[i] = Math.max(dp[j], dp[i]);
                }
                dp[i] += h;
                ans = Math.max(ans, dp[i]);
            }
            out.write("Case " + (++T) + ": maximum height = " + ans + "\n");
//            System.out.printf("Case %d: maximum height = %d\n", ++T, ans);
            n = Integer.valueOf(in.readLine());
        }
        out.flush();
        out.close();
    }
    private static int[] get(int k, int[] a) {
        int w1 = a[(k + 1) % 3], w2 = a[(k + 2) % 3];
        if (w1 > w2) {
            int tmp = w1;
            w1 = w2;
            w2 = tmp;
        }
        return new int[]{w1, w2, a[k]};
    }
}
