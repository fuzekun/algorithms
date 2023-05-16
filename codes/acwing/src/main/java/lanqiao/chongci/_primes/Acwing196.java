package lanqiao.chongci._primes;

import utils.InAndOutUitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * 素数的距离
 *
 * 1. 素数的距离
 * 2. 如何对某一个区间的素数进行筛选
 *      1. 找到这个sqrt(r)的素数
 *      2. 使用这个素数，对[L, R]的区间素数进行筛选。
 *
 * 1. 循环中的r如果是2^31 - 1，那么i如果不用long，就可能会越界成为负数。
 * 2. mp中的j如果是int类型，mp.contained(j)一定是false;
 *
 * */
public class Acwing196 {

    /**
     *
     *  1. 素数筛选, 获取[1, n]中的所有素数
     *  2. 二分查找
     *
     *  有问题,素数筛选时间复杂度是O(nlogn)
     *
     *  考虑到[L, R]之间的距离很小，所以采用范围素数筛选。
     *
     *  1. 找到[1, sqrt(R）]的所有素数
     *  2. 用这些素数pi筛选[L,R]中的所有合数
     *
     * */

    private static HashMap<Integer, Integer> not_p = new HashMap<>();
    private static boolean[] not_prime;
    private static void get_primes(long L, long R) {
        int len = (int)Math.sqrt(R) + 1;
        not_prime = new boolean[len + 1];
        not_prime[1] = true;
        not_p.put(1, 1);
        for (int i = 2; i <= len; i++) {
            if (!not_prime[i]) {
                for (int j = i + i; j <= len; j += i) {
                    not_prime[j] = true;
                }
//                 从[L, R]中最小的倍数开始筛选, 最少是2倍
                int low = (int)(L / i);
                if (low <= 1) low = 2;
                for (long j = low * i; j <= R; j += i) {
                    if (!not_p.containsKey((int)j))
                        not_p.put((int)j, 1);
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{

        InAndOutUitl util = new InAndOutUitl();

        Integer L, R;
        while ((L = util.nextInt()) != null) {
            R = util.nextInt();
            get_primes(L, R);
            int pre = -1;
            int maxv = 0;
            int minv = Integer.MAX_VALUE;
            int[] maxp = new int[2], minp = new int[2];
            for (long i = L; i <= R; i++) {
                if (!not_p.containsKey((int)i)) {
                    if (pre != -1) {
                        int len = (int)i - pre;
                        if (maxv < len) {
                            maxv = len;
                            maxp[0] = pre;
                            maxp[1] = (int)i;
                        }
                        if (minv > len) {
                            minv = len;
                            minp[0] = pre;
                            minp[1] = (int)i;
                        }
                    }
                    pre = (int)i;
                }
            }
            if (maxv != 0)
                System.out.println(minp[0] + "," + minp[1] + " are closest, " + maxp[0] + "," + maxp[1] + " are most distant.");
            else System.out.println("There are no adjacent primes.");
        }
    }
}
