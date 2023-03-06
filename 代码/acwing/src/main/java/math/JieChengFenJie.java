package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/11/5 11:59
 * @Description: 阶乘分解
 * 方法一；
 * 对于每一个数字分解质因数，时间复杂度O(N^sqrt(N))
 * 方法2:
 * 有n / i个数字包含一个i
 * 有n / i * i个数字包含两个i, 然后加上n / i是因为上面一个i的已经全部包含了, 在加上包含两个i的就行了。
 * ...
 */
public class JieChengFenJie {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    private static List<Integer> primes;

    public static void init1(int n) {
        int[] st = new int[n + 1];
        primes = new ArrayList<>();
        Arrays.fill(st, 0);
        for (int i = 2; i <= n; i++) {
            if (st[i] == 0) {           // 只有素数的时候才进行筛选
                primes.add(i);
                // 使用素数筛掉合数
                for (int j = i + i; j <= n; j += i) st[j] = 1;
            }
        }
    }
    public static void init(int n) {
        int[] st = new int[n + 1];
        Arrays.fill(st, 0);
        primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (st[i] == 0) primes.add(i);
            for (int j = 0; primes.get(j) <= n / i; j++) {
                st[primes.get(j) * i] = 1;
                if (i % primes.get(j) == 0) break; // 等于是最小, 不等于是更小
            }
        }
    }
    public static void main(String[] args) throws Exception{
        int n = Integer.valueOf(in.readLine());
        init1(n);
        for (int i : primes) {
            long x = i;
            int ans = 0;
            while (x <= n) {
                ans += n / x;
                x *= i;
            }
            out.write(i + " " + ans + "\n");
        }
        out.flush();
        out.close();
    }
}
