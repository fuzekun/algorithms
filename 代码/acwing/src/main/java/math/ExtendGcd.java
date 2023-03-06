
package math;
        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;
        import java.util.Scanner;


/**
 * @author: Zekun Fu
 * @date: 2022/11/8 11:33
 * @Description: 扩展欧几里得算法
 * 线性同余方程的解
 * 1
 * 35 25
 */
public class ExtendGcd {

    private static long x, y;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int INF = Integer.MAX_VALUE;

    public static long exgcd(long a, long b, long[] x, long[] y) {
        if (b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }
        long d = exgcd(b, a % b, y, x);
        y[0] -= a / b * x[0];
        return d;
    }
    public static int exgcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        int d = exgcd(b, a % b);
        // 注意下面的交换
        long tmp = x;
        x = y;
        y = tmp;
        y -= a / b * x;
        return d;
    }

    public static int getX(int a, int b, int c) throws Exception {
        int d = exgcd(a, b);
        if (c % d != 0) {
            return INF;
        }
//        out.write((x / d * c  % b + b) % b + " " + y / d * c + "\n");
        return (int)((long)c / d * x % b);
    }

    public static long exgcd(long a, long b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        long d = exgcd(b, a % b);
        long tmp = x;
        x = y;
        y = tmp;
        y -= a / b * x;
        return d;
    }
    // 获取最小的解
    public static long getMin(long a, long b) {
        exgcd(a, b);        // d == 1
        long mod = Math.abs(b);     // 如果不是正整数的话，需要abs
        return (x % b + b) % b;
    }
    // 如何使用泛型开发出更加通用的程序呢？, Integer和Long重写了equals方法
//    public static<T extends Number> T exgcd(Long a, T b, T x, T y) {
//
//    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    // 最幸运的数字
    // 为了防止爆long，需要使用龟速乘法
    public static long slow_mod(long x, long n, long c) {
        long ans = 0;
        while (n != 0) {
            if (n % 2 == 1) ans = (ans + x) % c;
            x = (x + x) % c;
            n >>= 1;
        }
        return ans;
    }
    public static long quick_mod(long x, long n, long c) {
        long ans = 1;
        while (n != 0) {
            if (n % 2 == 1) ans = slow_mod(ans, x, c);
            x = slow_mod(x, x, c);
            n >>= 1;
        }
        return ans;
    }
    public static boolean check(int x, long c) {
        long a = 1;
        for (int i = 0; i < x; i++) a *= 10;
        if (a % c == 1) return true;
        return false;
    }
    public static long getEuler(long x) {
        long res = x;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                res = res / i * (i - 1);
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) res = res / x * (x - 1);
        return res;
    }
    public static long luckiestNum(long L) {
        long d1 = gcd(L, 8);
        long c = L * 9L / d1;     // d1是L和8的最大公约数
        // 特殊判断是否与c互质，如果不互素，约数不可能为1
        if (c % 2 == 0 || c % 5 == 0) return 0;
        // 首先求phi[c],使用质因数分解的方法
        long phi = getEuler(c);
        // 之后再对phi[c]进行试除法求约数，求10^(i) = 1 (mod c)
        long res = (long)1e18;
        for (long i = 1; i <= phi / i; i++) {
            if (phi % i == 0) {     // 1. 只有等于他约数的时候才有必要，否则没有必要
                if (quick_mod(10, i, c) == 1) res = Math.min(res, i);
                if (quick_mod(10, phi / i, c) == 1) res = Math.min(res, phi / i);
            }
        }
        return res == (long)1e18 ? 0 : res;
    }



    // 青蛙约会
    public static long guGua(long p1, long p2, long v1, long v2, long b) throws Exception{
        long a = v1 - v2, c = p2 - p1;
        long[] xx = new long[1];
        long[] yy = new long[1];
        long d = exgcd(a, b, xx, yy);
        x = xx[0];
        y = yy[0];
//        System.out.println(a + " " + b + " " + c + " " + d);
        if (c % d != 0) {
            return Long.MAX_VALUE;
        } else {
            long mod = Math.abs(b / d);    // 如果不是正整数的话，需要abs
            long mul = c / d;
            long ans = ((x * mul % mod) + mod) % mod;
            return ans;
        }
    }

    // 中国剩余定理
    public static long shengyu(long[][]nums) {
        int n = nums.length;
        long a1 = nums[0][0], m1 = nums[0][1];
        for (int i = 1; i < n; i++) {
            long a2 = nums[i][0], m2 = nums[i][1];
            long d = exgcd(a1, a2);
            long delta = m2 - m1;
            if (delta % d != 0) return -1;
            // 这里需要求最小正整数解
            long mod = a2 / d;
            long k1 = (x * (delta / d) % mod + mod) % mod;
            m1 += k1 * a1;
            a1 = a1 * a2 / d;
        }
        return (m1 % a1 + a1) % a1;
    }

// 曹冲养猪，裸的中国剩余定理
    public static long caoChong(long[][] nums) {
        long n = nums.length;
        long a1 = nums[0][0], m1 = nums[0][1];
        for (int i = 1; i < n; i++) {
            long a2 = nums[i][0], m2 = nums[i][1];
            long delta = m2 - m1;
            long d = exgcd(a1, a2);
            if (delta % d != 0) return -1;
            long mod = Math.abs(a2 / d);
            long k1 = (x * (delta / d) % mod + mod) % mod;
            m1 += a1 * k1;          // 加上a1 * k
            a1 = a1 * a2 / d;       // 最小公倍数
        }
        return (m1 % a1 + a1) % a1;
    }


    public static void main(String[] args) throws Exception{
//        Scanner sc = new Scanner(System.in);
        // 中国剩余定理

        int n = Integer.valueOf(in.readLine());
        long[][] nums = new long[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = in.readLine().split(" ");
            nums[i][0] = Long.valueOf(input[0]);
            nums[i][1] = Long.valueOf(input[1]);
        }
        long ans = caoChong(nums);
        out.write(ans + "\n");

        // 最幸运的数字
//        long L = Long.valueOf(in.readLine());
//        int cnt = 1;
//        while (L != 0) {
//            long ans = luckiestNum(L);
//            out.write("Case " + cnt + ": " +ans + "\n");
//            L = Integer.valueOf(in.readLine());
//            cnt++;
//        }


        // 获取最小的解
//        String[] s = in.readLine().split(" ");
//        long ans = getMin(Integer.valueOf(s[0]), Integer.parseInt(s[1]));
//        out.write(ans + "\n");

        // 青蛙约会
//        String[] s = in.readLine().split(" ");
//        long ans = guGua(Integer.valueOf(s[0]), Integer.valueOf(s[1])
//                ,Integer.valueOf(s[2]), Integer.valueOf(s[3]), Integer.valueOf(s[4]));
//        if (ans == Long.MAX_VALUE) {
//            System.out.println("Impossible");
//        } else System.out.println(ans);

        // 获取同于方程的解
//        int n = Integer.valueOf(in.readLine());
//        for (int i = 0; i < n; i++) {
//            String[] nums = in.readLine().split(" ");
//            int a = Integer.valueOf(nums[0]);
//            int b = Integer.valueOf(nums[1]);
//            int c = Integer.valueOf(nums[2]);
//            int d = getX(a, c, b);
//            if (d == INF) {
//                out.write("impossible\n");
//            }
//            else out.write( d  + "\n");
//        }
        out.flush();
        out.close();
    }
}
