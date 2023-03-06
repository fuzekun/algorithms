package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/11 10:04
 * @Description: 蓝桥杯算法训练-同余方程
 */
public class Tongyu {
    // 方法一：直接使用扩展欧几里得
    private static long x, y;
    public static long exgcd(long a, long b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        long d = exgcd(b, a % b);
        long tmp = x; x = y; y = tmp;
        y -= a / b * x;
        return d;
    }
    // 方法二：使用欧拉函数 + 快速幂
    public static long getEuler(long x) {
        long phi = x;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                phi = phi / i * (i - 1);
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) phi = phi / x * (x - 1);
        return phi;
    }
    public static long quick_mod(long x, long n, long mod) {
        long res = 1;
        while (n != 0) {
            if (n % 2 == 1) res = res * x % mod;
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        long phi = getEuler(b);
        // 方法一：
//        long d = exgcd(a, b);
//        long ans = (a % b + b) % b;
//        long mod = Math.abs(b / d); // d == 1
        // 方法二：
        long ans = quick_mod(a, phi - 1, b);
        System.out.println(ans);
    }
}
