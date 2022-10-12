package lanqiao;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/9 21:13
 * @Description:
 * Fibonacci 数列求和
 *
 * [fn, fn + 1, sn] [0, 1, 0]
 *                  [1, 1, 1]
 *                  [0, 0, 1]
 * [fn + 1, fn + 2, sn + 1]
 *
 *  [f1, f2, s1]
 *
 *  [fn, fn+1, sn]
 *
 *
 */
public class acwing_1303_fib {

    // 获取单位矩阵
    public static long[][] getE(int n, int m) {
        long[][] ans = new long[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ans[i],0);
            ans[i][i] = 1;
        }
        return ans;
    }
    private static final long [][] E = getE(3, 3);

    // 矩阵乘法
    public static long[][] mul(long[][] a, long[][] b, long mod) {
        int n = a.length, m = a[0].length, t = b[0].length;
        long[][] ans = new long[n][t];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < t; j++) {
                for (int k = 0; k < m; k++) {
                    ans[i][j] = (ans[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return ans;
    }

    // 矩阵快速幂， 输入必须是二维矩阵，如果不是二维的补上0，变成二维的，得到的结果在进行变化就行了。
    public static long[][] quick_pow(long[][] a, long b, long mod) {
        if (b == 0) return getE(a.length, a[0].length);
        // 因为a会被下面的改变，所以应该拷贝一份
        long [][] ans = quick_pow(mul(a, a, mod), b / 2, mod);
        if (b % 2 == 1)
            ans = mul(ans, a, mod);     // 这个a已经被改变了,因为是引用
        return ans;
    }
    public static void test() {

        long[][] a = {{0, 1, 0},{1, 1, 1},{0, 0, 1}};
        long[][] na = mul(a, a, 10001);
        long[][] ans = mul(na, na, 10001);
        ans = mul(ans, a, 100001);       // a^5
        for (int i = 0; i < 3; i++)
            System.out.println(Arrays.toString(ans[i]));
        ans = quick_pow(a, 5, 100001);

        for (int i = 0; i < 3; i++)
            System.out.println(Arrays.toString(ans[i]));
    }
    public static void main(String[] args) {

        long[][] a = {{0, 1, 0},{1, 1, 1},{0, 0, 1}};
        // 测试是否乘法正确
//        test();
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();
        a = quick_pow(a, n - 1, m);

        // 这里第一行有用，其他就没用了
        long[][] b = {{1, 1, 1}, {0, 0, 0}, {0, 0, 0}};
        long[][] ans = mul(b, a, m);
        System.out.println(ans[0][2]);
    }
}
