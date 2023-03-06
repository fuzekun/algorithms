package lanqiao.base;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 16:53
 * @Description: 数的潜能
 * 算法训练第8题
 * 采用直接暴力的方法来做
 *
 * 尽可能的分解成3
 * 比如5 = 2 * 3
 * 6 = 3 * 3
 * 4 = 3 * 1
 * 如果剩剩下一个1，那么就合并成(1 + 3) * 3
 * 如果剩下一个2，那么直接就是2 * 3
 * 如果剩下0，那么就直接是最大的
 *
 * 最后应该注意，long, 不可以使用int
 */
public class QianNengOfNumber {


    final static long mod = 5218L;
    static long dfs (int n) {
        if (n == 1 || n == 0) return 1;
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, (long) i * dfs(n - i) % mod);
        }
        return ans % mod;
    }
    public static long quickMod(long a, long x) {
        if (x == 1) return a;
        return quickMod(a * a % mod, x / 2) * (x % 2 == 1 ? a : 1) % mod;
    }
    public static long quick_mod(long n) { // 返回3的n次方
        long res = 1;
        long x = 3;
        while (n != 0) {
            if ((n & 1) == 1) res = res * x % mod;
            x = (x * x) % mod;
            n >>= 1;
        }
        return res % mod;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
//        System.out.println(dfs(n));
//        System.out.println(quick_mod(4));
//        System.out.println(quickMod(3, 3));
        if (n <= 4) {
            System.out.println(n);
            return ;
        }
        long yu = n % 3;
        long b = n / 3;
        if (yu == 1) {
            System.out.println(4 * quickMod(3,b - 1) % mod);
        } else if (yu == 2) {
            System.out.println(2 * quickMod(3, b) % mod);
        } else {
            System.out.println(quickMod(3, b) % mod);
        }
    }

}
