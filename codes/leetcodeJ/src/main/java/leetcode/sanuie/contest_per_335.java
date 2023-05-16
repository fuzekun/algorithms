package leetcode.sanuie;

import leetcode.utils.ChangeToArrayOrList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author: Zekun Fu
 * @date: 2023/3/5 10:45
 * @Description:
 * 335场单周赛
 */
public class contest_per_335 {
    public int passThePillow(int n, int time) {
        int dir = time / (n - 1) % 2;
        time = time % (n - 1);
        if (dir == 1) return n - time;
        else return 1 + time;
    }

    // 多重背包问题，统计数量，无法使用拆分分组的方法。因为会出现重复
    public int p4(int target, int[][]nums) {
        int n = nums.length;
        int[] f = new int[target + 1];
        f[0] = 1;
        int mod = (int)1e9 + 7;
        for (int i = 0; i < n; i++) {
            int s = nums[i][0], w = nums[i][1];         // 优化之后，这个就必须在外面了，不优化，k可以在外层循环
            for (int j = target; j >= 0; j--) {
                for (int k = 1; k <= s; k++) {           // 最少选择一个，因为是计数，选择0个会直接加倍
                    if (j >= k * w)
                        f[j] += f[j - k * w];
                    if (f[j] >= mod) f[j] -= mod;
                }
            }
        }
        int[][] g = new int[n + 1][target + 1];
        g[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int s = nums[i - 1][0], w = nums[i - 1][1];
//            for (int j = 0; j <= target; j++) g[i][j] = g[i - 1][j];
            // 首先复制过去
            g[i] = Arrays.copyOf(g[i - 1], target + 1);
            for (int k = 1; k <= s; k++) {
                for (int j = target; j >= 0; j--) {
                    if (j >= k * w) {
                        g[i][j] += g[i - 1][j - k * w];             // 默认的选和不选就都包含了
                        if (g[i][j] > mod) g[i][j] -= mod;
                    }
                }
            }
        }
        return g[n][target];
        /*
            为什么需要最后枚举k
        *   1. 从实际意义角度，
        *   2. 从状态和转移角度。k是对应着转移。i， j对应着状态
            3. 从划分角度，划分成了k个转移,对于每一个状态，有k中转移方式。
        * */
    }

    public int findValidSplit(int[] nums) {
        HashMap<Integer, Integer>mp = new HashMap<>();
        for (int x : nums) {
            int t = x;
            for (int i = 2; i * i <= t; i++)      //最多含一个大于sqrt(n)的质因子 对应的含一个小于的sqrt的质因子
            {
                if (t % i == 0) {
                    int s = 0;
                    while (t % i == 0) {
                        s += 1;
                        t /= i;
                    }
                    mp.put(i, mp.getOrDefault(i, 0) + s);       // 质因数i增加s个
                }
            }
            if (t > 1) mp.put(t, mp.getOrDefault(t, 0) + 1);    // 质因数t，增加1个
        }
        HashSet<Integer>set = new HashSet<>();
        for (int ans = 0; ans < nums.length - 1; ans++) {
            int t = nums[ans];
            for (int i = 2; i * i <= t; i++)      //最多含一个大于sqrt(n)的质因子 对应的含一个小于的sqrt的质因子
            {
                if (t % i == 0) {
                    int s = 0;
                    while (t % i == 0) {
                        s += 1;
                        t /= i;
                    }
                    set.add(i);
                    mp.put(i, mp.get(i) - s);       // 质因数i减少s个
                }
            }
            if (t > 1) {
                set.add(t);
                mp.put(t, mp.get(t) - 1);
            }
            int flag = 1;
            for (Integer y : set) {             // 如果set和mp没有交集的情况下
                if (mp.get(y) != 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) return ans;
        }
        return -1;
    }

    public static void main(String[] args) {
        contest_per_335 con = new contest_per_335();
        int[] nums = {557663,280817,472963,156253,273349,884803,756869,763183,557663,964357,821411,887849,891133,453379,464279,574373,852749,15031,156253,360169,526159,410203,6101,954851,860599,802573,971693,279173,134243,187367,896953,665011,277747,439441,225683,555143,496303,290317,652033,713311,230107,770047,308323,319607,772907,627217,119311,922463,119311,641131,922463,404773,728417,948281,612373,857707,990589,12739,9127,857963,53113,956003,363397,768613,47981,466027,981569,41597,87149,55021,600883,111953,119083,471871,125641,922463,562577,269069,806999,981073,857707,831587,149351,996461,432457,10903,921091,119083,72671,843289,567323,317003,802129,612373};
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray("[[6,1],[3,2],[2,3]]");
//        int[] nums = {4,7,15,8,3,5};
//        System.out.println(con.passThePillow(3, 2));
//        System.out.println(con.p4(7, numss));
        System.out.println(con.findValidSplit(nums));
    }
}
