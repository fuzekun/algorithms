package lanqiao.constest13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/10/8 15:13
 * @Description: 最长不下降子序列
 */
public class longestSubSequence {

    static final int maxn = 100005;
    private static int[] a =  new int[maxn];
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = bf.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int k =Integer.parseInt(s1[1]);
        String[] inputs = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(inputs[i]);
//        如果修改都修改成0，那么就可以了。
//        有n个位置可以修改，可以修改，如果可以修改成最大值，直接就赚了

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = k;
        for (int i = 0; i < n; i++) {
            int maxv = k + 1;
            for (int j = 0; j < i; j++) {
                // 找(i, j)之间小于a[i]的子数组的最大长度。

                if (a[j] <= a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    // 如果中间有k个数字，可以直接都变成a[i]了
                    // 如果中间没有k个数字，那么可以直接从这后面的数字都变成a[i]
                    if (i - j - 1 >= k) maxv = Math.max(maxv, dp[j] + 1 + k);
                    else maxv = Math.max(maxv, dp[j] + i - j);
                }
            }
            ans = Math.max(maxv, ans);
        }
        System.out.println(ans);
    }
}
//极端情况, ans = k + 1
//5 4 3 2 1
//1 1 1 1 1
//
//1 4 2 8 5
//1 2 2 3 3
//1 2 3 4 4

//i, j之间连续小于a[i]的数字的个数，如果小于了k，那么可以直接加上j - i
//而不是说，(i, j)

// 如果子数组的最长长度小于k，那么就应该加上
