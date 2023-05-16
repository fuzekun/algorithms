package lanqiao.chongci._primes;

import utils.InAndOutUitl;

/**
 * @author: Zekun Fu
 * @date: 2023/4/17 17:04
 * @Description: 哥德巴赫的猜想
 *
 * 大于4的偶数可以写成两个奇素数
 */
public class Acwing1292 {

    static int[] primes;
    static int cnt = 0;
    static boolean[] not_primes;
    private static void init(int n) {
        not_primes = new boolean[n + 1];
        primes = new int[n + 1];
        not_primes[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!not_primes[i]){
                for (int j = i + i; j <= n; j += i) {
                    not_primes[j] = true;
                }
                primes[cnt++] = i;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        InAndOutUitl uitl = new InAndOutUitl();
        Integer n;
        final int maxn = (int)1e6 + 5;
        init(maxn);
        while ((n = uitl.nextInt()) != 0) {
            for (int i = 0; i <= cnt; i++) {
                int x = primes[i], y = n - primes[i];
                if (x % 2 == 1 && y % 2 == 1 && !not_primes[y]) {
                    uitl.write(n + " = " + x + " + " + y + "\n");
                    break;
                }
            }
        }
        uitl.flush();
    }
}
