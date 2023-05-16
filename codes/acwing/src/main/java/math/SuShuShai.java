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
 * @date: 2022/11/5 10:33
 * @Description: 素数筛选
 */
public class SuShuShai {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    // 使用埃氏筛法进行筛选
    public static List<Integer> divF(int n) throws Exception {
        int[] st = new int[n + 1];
        Arrays.fill(st, 0);
        List<Integer>ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (st[i] == 0) {
                ans.add(i);
                for (int j = i + i; j <= n; j += i) st[j] = 1;
            }
        }
        return ans;
    }
    // 线性筛法
    // 1. pj是i的最小的质因子，也是i * pj的最小质因子
    // 1.2 pj 小于i的最小质因子，也是i * pj的最小质因子
    // 2. 所有的合数一定可以被其最小质因子筛掉。
    public static List<Integer>div2(int n) {
        List<Integer>ans = new ArrayList<>();
        int[] primes = new int[n + 1];
        int[] st = new int[n + 1];
        Arrays.fill(st, 0);
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (st[i] == 0) {
                ans.add(i);
                primes[cnt++] = i;
            }
            for (int j = 0; primes[j] <= n / i; j++) {
                // pj是i的最小质数因子，也就是pj * i的最小质因子
                // pj小于i的最小质数因子，pj是pj * i的最小质因子

                // 第一句话可以使用i的质因数分解来证明，pj
                st[primes[j] * i] = 1;
                if (i % primes[j] == 0) break;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception{
        int n = Integer.valueOf(in.readLine());
        List<Integer>ans = div2(n);
        out.write(ans.size() + "\n");
        out.flush();
        out.close();
    }
}
