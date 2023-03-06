package leetcode.contest.yiyue;

import org.omg.CORBA.NO_IMPLEMENT;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/2/25 10:54
 * @Description:
 *
 * TreeMap:二叉树
 * HashMap:链表 + 红黑树。大于8个值的时候，自动变成红黑树
 * LinkedHashMap 哈希表 + 链表， 链表记录了添加数据的顺序。
 */
public class Leet_contest_333 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> mp = new TreeMap<>();                         // TreeMap是递增排列的
        for (int[] num: nums1) {
            mp.put(num[0], num[1]);
        }
        for (int[] num:nums2) {
            mp.computeIfPresent(num[0], (key, val)->val+=num[1]);
            mp.putIfAbsent(num[0], num[1]);
        }
        int n = mp.size();
        int[][] ans = new int[n][];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            ans[i] = new int[]{entry.getKey(), entry.getValue()};
            i ++ ;
        }
        return ans;
    }
    public int minOperations(int n) {
        // 两种可能
        // 1. 直接减, 也就是1的次数
        // 2. 先变成大于它的第一个2的n次方,然后加上一次操作


        // 1. 如果有n个连续的1，那么两次就可以把他变成0.一次操作
        // 2. 如果没有，那么最少需要一次操作。
        // 碰见0，就加上一个1
        // 碰见1，就直接进位就行了

        int pre = 0;                                // 前边1的个数
        int ans = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                pre++;
            }
            else {
                 if (pre != 0) {
                     ans++;
                     pre = pre == 1 ? 0 : 1;            // 两个以上1，加上一个1，产生进位。一个1直接去掉
                 }
            }
            n /= 2;
        }
        ans++;                                      // 最后一位一定是1
        if (pre > 1) ans++;                         // 如果有两个以及以上的连续1，需要先加上1，然后在变成0
        return ans;
    }
    public int squareFreeSubsets(int[] nums) {
        // 1.先排除，含有平方因子的数字num
        // 2. 无相同因子的数字可以形成一个子集。假设有x个数字，那么给答案的贡献是2^x - 1，减去一个空集

        /*
        *
        *   2, 3, 5, 7, 11, 13, 17, 19, 23, 29  总共就10个素数
        *
        * 计数dp
        * 1. 定义问题：dp[2^n]：集合为这个组合的集合个数。
        * 2. 划分子问题：计算这个集合的所有的补集的个数。dp[S - 2^n]，枚举子集合。
        * 3. 初始化
        *
        * 时间复杂度3^n =
        *
        * 第一种方法：直接遍历数字，每一个数字计算一遍子集。
        * 第二种方法：统计每个数字的个数c，然后子集 * c
        * */
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] NSQ_TO_MASK = new int[31];
        Arrays.fill(NSQ_TO_MASK, 0);
        for (int i = 2; i < 31; i++) {
            for (int j = 0; j < primes.length; j++) {
                if (i % primes[j] != 0) continue;
                if (i % (primes[j] * primes[j]) == 0) {
                    NSQ_TO_MASK[i] = -1;
                    break;
                }
                NSQ_TO_MASK[i] |= 1 << j;
            }
        }

        int mod = (int)1e9 + 7;
        int M = 1 << primes.length;
        int[] f = new int[M];
        Arrays.fill(f, 0);
        f[0] = 1;
//        for (int x:nums) {
//            int mask = NSQ_TO_MASK[x];
//            if (mask >= 0) {
//                for (int j = M - 1; j >= mask; j--) {
//                    if ((mask | j) == j) {
//                        f[j] = (f[j] + f[mask ^ j]) % mod;
//                    }
//                }
//            }
//        }
//        int ans = 0;
//        for (int i = 0; i < f.length; i++) {
//            ans = (int)(((long)ans + f[i]) % mod);
//            System.out.println(f[i]);
//        }
//        return (ans - 1 + mod) % mod;
        for (int x : nums) {
            int mask = NSQ_TO_MASK[x];
            if (mask >= 0) // x 是 NSQ
                for (int j = M - 1; j >= mask; --j)
                    if ((j | mask) == j)  // mask 是 j 的子集
                        f[j] = (f[j] + f[j ^ mask]) % mod; // 不选 mask + 选 mask
        }
        long ans = 0L;
        for (int v : f) ans += v;
        return (int) ((ans - 1) % mod); // -1 去掉空集
    }
    public static void main(String[] args) {
        Leet_contest_333 t = new Leet_contest_333();
        int ans = t.minOperations(70);
        System.out.println(ans);
    }
}
