package lanqiao.imporve;

import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/12 18:09
 * @Description: 蓝桥杯粘木棍
 * m, n都很小，所以可以直接暴力
 *
 * 典型的组合问题，使用枚举子集的方式进行
 *
 * 就是说，m个桶，放n个球，求最小桶和的差值
 *
 * 从球的角度来说，每一个球可以有m种选择
 *
 * 1.使用状态压缩S表示当前选择的木棍的集合, x表示粘贴成x个
 * 求最小值
 * 2. 怎么转移呢？
 *      1. 多加上一根木棍，可以粘贴到某一根上
 *      2. 如果没有超过x的话可以独立一根。
 *
 *
 * 考虑如果集合中有m根木棍，那么应该为maxv - minv
 * 否则，集合的最小值等于
 *
 *
 */
public class ZhanMuGun {

    private static int n, m;
    private static int[] a;
//    int dfs(int S, int x) {
//        int ans = Integer.MAX_VALUE;
//        List<Integer>nums = new ArrayList<>();
//        int minv = ans, maxv = Integer.MIN_VALUE;
//        for (int j = 0; j < n; j++) {
//            if ((S >> j  & 1) == 1) {
//                nums.add(a[j]);
//                minv = Math.min(minv, a[j]);
//                maxv = Math.max(maxv, a[j]);
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            // 1. 独立成一根
//            if (x + 1 <= m) {
//                minv = Math.min(minv, a[i]);
//                maxv = Math.max(maxv, a[i]);
//                int tmp = dfs(S | j, x + 1) +
//            }
//            if ((S >> i & 1)== 0) {
//                for (int j = 0; j < n; j++) {
//
//                    if ((S >> j & 1) == 1) {
//                        int tmp = dfs(S | i, x) + 1;
//                        ans = Math.min(ans, dfs())
//                    }
//                }
//            }
//        }
//    }
    public static int dfs(int[] bucket, int cur) {
        if (cur == n) {
            int maxv = Integer.MIN_VALUE, minv = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (bucket[i] == 0) return Integer.MAX_VALUE;
                minv = Math.min(minv, bucket[i]);
                maxv = Math.max(maxv, bucket[i]);
            }
            return maxv - minv;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            // 把当前的球放进i号桶中
            bucket[i] += a[cur];
            ans = Math.min(ans, dfs(bucket, cur + 1));
            bucket[i] -= a[cur];
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[] bucket = new int[m];
        System.out.println(dfs(bucket, 0));
        Map<Integer, Integer>mp = new HashMap<>();
        mp.put(1, 1);
        if (mp.containsKey(1));
    }
}
