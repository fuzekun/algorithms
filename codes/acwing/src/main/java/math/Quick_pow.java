package math;

import java.util.Queue;

/**
 * @author: Zekun Fu
 * @date: 2022/10/3 20:24
 * @Description:
 *
 * 快速幂取模
 */
public class Quick_pow {

    public static long process(long a, long x, final long mod) {
        return x == 1 ? a :
                process(a * a % mod, x >> 1, mod) * ((x & 1) == 0 ? 1 : a) % mod;
    }

    public static void main(String[] args) {
        System.out.println(process(3,5, 100));
        // 81 * 3 % 100 = 243 % 100 = 43
    }

}
