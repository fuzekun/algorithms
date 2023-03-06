package leetcode.categories.dp;

import javax.jws.Oneway;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Zekun Fu
 * @date: 2023/3/1 20:11
 * @Description: 无平方子集数
 *
 * 1. nums很小，所以
 * 2. 素数 or 因子 = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
 * 3. 每个数字进行分解，得到因子的集合。
 * 比如：7 = [000100000]
 *
 * 然后，统计每一个数字的个数
 *
 * 之后，使用dp[mask] 进行求解。
 * 枚举子集，如果sub_mask | cur_num == mask && sub_mask & cur_num == 0
 * 那么f[mask] = f[sub_mask] * cur_num;
 * 最后的和就是f[mask]的总和。
 *
 *
 * 如果使用01背包问题呢？
 * 对于每一mask
 * 从这些物品中选择重量恰好是j的选法数目。
 * 每一种的物品重量是mask。
 * f[j] = f[j] + f[j - mask]; 选择，或者不选择。
 *
 * 对于1这种情况怎么处理呢？
 *
 * 快速幂取模
 * int 转化成long，a * a回爆int
 *
 * 计数： getOrDefault(key, 0) + 1
 */
public class Leet2572 {
    public int squareFreeSubsets(int[] nums) {
        // 计算每一个数字对应的集合
        int[] primes = {2,3,5,7,11,13,17,19,23,29};
        int[] masks = new int[31];
        for (int i = 2; i <= 30; i++) {
            int mask = 0;
            for (int j = 0; j < 10; j++) {
                int x = primes[j];
                if ((i % (x * x)) == 0) {
                    mask = -1;
                    break;
                }
                if (i % x == 0) mask |= 1 << j;
            }
            masks[i] = mask;
        }
        // 使用dp
        int high = 1 << 10;
        int[] f = new int[high];
        int n = nums.length;
        int mod = (int) 1e9 + 7;
        f[0] = 1;
        for (int i = 0; i < n; i++) {
            int mask = masks[nums[i]];
            if (mask == -1) continue;
            for (int j = high - 1; j >= mask; j--) {
                if ((j & mask) == mask)                                 // mask是j的子集
                    f[j] = (int)(((long)f[j] + f[j ^ mask]) % mod);
            }
        }
        long ans = 0;
        for (int i = 0; i < high; i++) {
            ans = (ans + f[i]) % mod;
        }
        return (int)((ans - 1 + mod) % mod);
    }
    public int solve2(int[] nums) {
        // 计算Mask
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] masks = new int[31];
        masks[1] = -1;                                      // 1不是c(n, 1)而是2^n
        for (int i = 2; i <= 30; i++) {
            int mask = 0;
            for (int j = 0; j < 10; j++) {
                int x = primes[j];
                if (i % (x * x) == 0) {
                    mask = -1;
                    break;
                }
                if (i % x == 0) mask |= 1 << j;
            }
            masks[i] = mask;
        }

        // 计算cnt
        HashMap<Integer, Integer>cnt = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }

        // 使用dp
        int high = 1 << 10;
        int[] f = new int[high];
        int mod = (int)1e9 + 7;
        f[0] = 1;
        for (Map.Entry<Integer, Integer>entry : cnt.entrySet()) {
            int mask = masks[entry.getKey()];
            if (mask == -1) continue;
            int other = (high - 1) ^ mask;              // 补集
            int i = other;
            while (true) {                            // 枚举other的子集，包括空集, 逆序。使用小子集得到大子集。
                f[i | mask] = (int)(((long)f[i | mask] + (long)f[i] * entry.getValue() % mod) % mod);        // 每一个补集的子集都可以加上mask，形成新的集合。数量就是mask的数量
                i = (i - 1) & other;
                if (i == other) break;          // 不是等于0的时候终止，空集也包含进去
            }
        }

        // 统计答案
        long ans = 0;
        for (int i = 0; i < high; i++) {
            ans = (ans + f[i]) % mod;
        }
        // 处理最后为1的特殊情况
        ans = ans * pow_mod(2L, cnt.getOrDefault(1, 0));
//        System.out.println(pow_mod(2, 10));

        // 减去空集
        return (int)((ans - 1 + mod) % mod);
    }
    private int pow_mod(long a, long b) {
        long res = 1;
        int mod = (int)1e9 + 7;
        while (b != 0) {
            if ((b & 1) == 1) res = (res * a) % mod;
            b >>= 1;
            a = (a * a) % mod;
        }
        return (int)(res % mod);
    }
//    public int squareFreeSubsets(int[] nums) {
//        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
//        int[] masks = new int[31];
//        masks[1] = -1;
//        for (int i = 2; i <= 30; i++) {
//            int x = i;
//            for (int j = 0; j < 10; j++) {
//                int cnt = 0;
//                while (x % primes[j] == 0) {
//                    cnt++;
//                    x /= primes[j];
//                }
//                if (cnt >= 2) {
//                    masks[i] = -1;
//                    break;
//                }
//                if (cnt == 1)
//                    masks[i] |= 1 << j;
//            }
//        }
//        int high = (1 << 10) - 1;
//        int n = nums.length;
//        int mod = (int)1e9 + 7;
//        int[] f = new int[high + 1];
//        Arrays.fill(f, 0);
//        f[0] = 1;
//        int cnt1 = 0;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 1) cnt1++;
//            int mask = masks[nums[i]];
//            if (mask < 0) continue;
//            for (int j = high; j >= mask; j--) {
//                if ((j | mask) == j)
//                    f[j] = (int)(((long)f[j] + f[j - mask]) % mod);
//            }
//        }
//        long ans = 0;
//        for (int i = 0; i <= high; i++) {
//            ans = (ans + f[i]) % mod;
//        }
////        System.out.println(cnt1);
////        System.out.println(pow_mod(2, cnt1, mod));
//        ans = (ans * pow_mod(2, cnt1, mod) % mod) % mod;                   // (cnt1, 1), (cnt1, 2)....(cnt1, cnt1)
//        ans--;
//        return (int)(ans + mod) % mod;
//    }
//    public int solve2(int[] nums) {
//        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
//        int[] masks = new int[31];
//        masks[1] = 0;
//        for (int i = 2; i <= 30; i++) {
//            int x = i;
//            for (int j = 0; j < 10; j++) {
//                int cnt = 0;
//                while (x % primes[j] == 0) {
//                    cnt++;
//                    x /= primes[j];
//                }
//                if (cnt >= 2) {
//                    masks[i] = -1;
//                    break;
//                }
//                if (cnt == 1)
//                    masks[i] |= 1 << j;
//            }
//        }
//        HashMap<Integer, Integer>cnt = new HashMap<>();
//        for (Integer x : nums) {
//            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
//        }
//        int high = (1 << 10) - 1;
//        int[] f = new int[high + 1];
//        int mod = (int)1e9 + 7;
//        f[0] = 1;
//        for(Map.Entry<Integer, Integer>entry : cnt.entrySet()) {
//            int mask = masks[entry.getKey()];
//            if (mask == -1) continue;
//            int other = high ^ mask;                    // 补集
//            int j = other;                              // 子集
//            while (true) {
//                f[j | mask] = (int)(((long)f[j | mask] + (long)f[j] * entry.getValue() % mod) % mod);
//                j = (j - 1) & other;
//                if (j == other) break;
//            }
//        }
//        long ans = 0;
//        for (int i = 0; i <= high; i++) {
//            ans = (ans + f[i]) % mod;
//        }
//        ans = ans * pow_mod(2, cnt.getOrDefault(1, 0), mod) % mod;
//        ans = (ans - 1 + mod) % mod;
//        return (int)ans;
//    }
//    private long pow_mod(int a, int b, int mod) {
//        long res = 1;
//        while (b != 0) {
//            if ((b & 1) == 1) res = res * a % mod;
//            a = (int)(((long)a * a) % mod);
//            b >>= 1;
//        }
//        return res;
//    }

    public static void main(String[] args) {
        Leet2572 leet2572 = new Leet2572();
        int[] nums = {15,5,7,10,21,11,21,8,20,27,4,13,22,29,24,24,8,1,27,29,30,29,27,19,18,29,25,8,23,9,13,18,11,15,21,1,9,27,17,14,25,16,22,9,30,20,24,5,15,13,29,21,12,4,27,20,3,16,26,30,20,26,13,27,26,9,21,19,14,14,3,3,11,15,13,17,19,11,15,17,8,16,26,27,5,20,27,25,21,25,5,4,22,9,11,11,5,2,28,5,9,28,21,19,10,22,16,23,23,28,15,1,28,19,29,12,8,5,17,6,16,15,11,6,9,16,21,18,2,3,26,13,16,1,29,19,30,16,28,2,27,7,13,4,27,8,26,7,23,16,16,5,24,26,24,23,6,6,30,6,24,20,17,24,13,2,5,3,17,21,23,28,14,6,15,4,10,29,16,28,24,12,22,4,5,14,20,13,4,20,23,10,4,13,20,27,20,5,15,5,28,5,15,8,5,19,19,19,8,24,19,7,23,22,23,6,15,18,28,17,9,14,10,24,30,15,3,27,22,23,16,26,24,13,6,5,16,17,14,15,24,24,22,8,3,14,30,11,6,29,19,3,28,25,26,18,23,13,4,29,9,20,5,5,26,19,23,11,12,19,20,8,18,19,20,13,13,18,29,20,11,18,6,26,8,30,17,29,17,10,13,1,28,6,7,21,16,3,22,16,15,30,27,17,24,4,6,25,1,2,4,4,26,9,4,11,18,22,5,26,14,27,25,17,23,9,15,6,22,20,10,28,29,4,26,21,22,15,24,22,10,16,30,27,20,15,20,30,1,25,1,4,1,26,25,21,14,15,20,16,7,15,10,10,19,4,13,1,19,6,12,28,30,2,22,23,18,3,7,17,15,24,24,25,7,20,6,23,1,27,27,23,28,19,21,10,4,24,11,10,3,28,14,1,11,23,15,3,22,7,25,16,6,26,16,11,1,3,14,14,8,8,30,27,1,22,26,23,17,29,21,11,25,5,16,20,14,30,11,6,9,5,7,21,24,4,15,19,17,21,6,15,1,28,26,26,10,9,8,2,27,22,14,30,13,11,6,20,17,15,2,20,7,15,24,18,28,7,11,28,21,26,4,5,30,5,17,30,12,24,10,3,5,19,12,15,26,20,28,1,5,23,21,27,7,16,14,12,3,13,20,2,3,14,16,7,17,10,26,18,25,18,8,15,19,19,5,1,10,15,7,8,15,12,5,22,10,29,14,3,25,24,16,3,14,5,28,18,10,18,7,12,14,3,6,7,22,6,5,5,11,4,1,6,29,13,20,14,5,28,27,3,27,25,12,22,18,13,25,9,14,6,26,20,24,2,23,1,13,19,12,27,18,11,12,18,15,26,21,20,15,28,4,2,12,25,15,6,26,15,12,22,11,10,1,6,9,2,21,24,21,3,6,1,16,26,4,11,23,1,15,15,5,3,2,5,13,7,9,13,4,16,6,27,14,21,10,25,10,17,28,1,10,19,8,1,29,21,4,21,7,11,17,16,9,10,15,29,9,30,11,23,4,20,9,22,12,23,10,30,9,11,6,1,23,21,28,9,8,25,14,10,11,6,30,3,7,19,18,25,22,3,23,24,30,24,12,23,26,15,11,4,6,12,29,15,30,8,17,21,2,15,28,14,27,23,12,12,10,21,11,15,27,18,23,2,2,26,6,24,20,2,26,4,5,5,1,23,29,12,30,20,13,11,27,13,8,13,17,12,6,1,13,3,24,27,21,28,30,24,20,1,8,2,3,8,4,28,16,13,25,26,9,14,1,17,5,11,9,11,10,10,11,4,6,10,20,15,10,27,19,3,10,7,13,24,26,21,23,29,18,23,28,28,27,2,14,19,15,3,21,6,22,7,4,2,11,17,3,22,1,14,17,8,25,24,2,16,28,10,12,18,24,11,21,19,13,29,28,6,19,2,20,1,28,4,8,20,24,12,20,27,16,5,17,25,8,1,4,21,8,22,16,24,9,24,8,27,7,27,7,3,9,7,13,27,22,2,3,23,7,26,11,28,28,26,3,7,13,23,29,15,24,18,6,12,26,30,28,21,30,29,16,11,26,13,12,29,27,2,20,24,11,15,19,2,29,5,20,24,15,9,12,1,29,1,24,16,23,17,5,28,23,21,6,10,17,24,15,23,3,30,27,18,18,15,27,18,22,7,14,29,10,15,25,8,20,26,6,14,2,7,24,1,13,4,23,7,18,20,28,29,13,30,27,2,7,11,14,13,22,9,21,6,21,6,22,30,23,22};
//        int[] nums = {3,4,4,5};
        int ans = leet2572.solve2(nums);
        System.out.println(ans);
    }
}
