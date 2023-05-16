package leetcode.everyDay.siyue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: Zekun Fu
 * @date: 2023/4/18 9:08
 * @Description:
 *
 *
 * 一次性尽量做清所有的题目
 */
public class Summary {

    /**
     *
     * 给你一条个人信息字符串 s ，可能表示一个 邮箱地址 ，也可能表示一串 电话号码 。返回按如下规则 隐藏 个人信息后的结果：
     *
     * 电子邮件地址：
     *
     * 一个电子邮件地址由以下部分组成：
     *
     * 一个 名字 ，由大小写英文字母组成，后面跟着
     * 一个 '@' 字符，后面跟着
     * 一个 域名 ，由大小写英文字母和一个位于中间的 '.' 字符组成。'.' 不会是域名的第一个或者最后一个字符。
     * 要想隐藏电子邮件地址中的个人信息：
     *
     * 名字 和 域名 部分的大写英文字母应当转换成小写英文字母。
     * 名字 中间的字母（即，除第一个和最后一个字母外）必须用 5 个 "*****" 替换。
     * 电话号码：
     *
     * 一个电话号码应当按下述格式组成：
     *
     * 电话号码可以由 10-13 位数字组成
     * 后 10 位构成 本地号码
     * 前面剩下的 0-3 位，构成 国家代码
     * 利用 {'+', '-', '(', ')', ' '} 这些 分隔字符 按某种形式对上述数字进行分隔
     * 要想隐藏电话号码中的个人信息：
     *
     * 移除所有 分隔字符
     * 隐藏个人信息后的电话号码应该遵从这种格式：
     * "***-***-XXXX" 如果国家代码为 0 位数字
     * "+*-***-***-XXXX" 如果国家代码为 1 位数字
     * "+**-***-***-XXXX" 如果国家代码为 2 位数字
     * "+***-***-***-XXXX" 如果国家代码为 3 位数字
     * "XXXX" 是最后 4 位 本地号码
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/masking-personal-information
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 题目没有规定对于邮箱名字为1的时候，怎么进行添加*，是否有最后一个字符
     *
     * */
    public String maskPII(String s) {
        if (s.indexOf('@') != -1) {       // 邮箱
            s = s.toLowerCase();
            String[] ss = s.split("@");
            char first = ss[0].charAt(0);
            char last = ss[0].charAt(ss[0].length() - 1);
            StringBuilder ans = new StringBuilder(String.valueOf(first));
            ans.append("*****");
            ans.append(last);
            ans.append("@");
            ans.append(ss[1]);
            return ans.toString();
        }
        else {                      // 电话号码
            List<Character> nums = new LinkedList<>();
            for (char ch : s.toCharArray()) {
                if (Character.isDigit(ch)) nums.add(ch);
            }
            int type = nums.size() - 10;
            StringBuilder sb = new StringBuilder("***-***-");
            for (int i = nums.size() - 4; i < nums.size(); i++) {
                sb.append(nums.get(i));
            }
            StringBuilder sb2 = new StringBuilder("+");
            for (int i = 0; i < type; i++) sb2.append("*");
            sb2.append("-").append(sb);
            if (type == 0) return sb.toString();
            return sb2.toString();
        }
    }

    /**
     *
     * 1. 如何判断某一个数字的次数: 保存下标，二分查找
     * 2. 如何判断是否大于threadhold, 使用二进制每一位的方式，找0和1大于threadhold的。
     *
     * 1. getOrDefault之后需要进行put，否则就等于没有放入。
     * 2. 找左边界使用lower, 找右边界使用upper。[大于等于所以一定在区间中且是第一个，大于一定不在区间中且是第一个)
     *
     * */
    private Map<Integer, List<Integer>> mp = new HashMap<>();
    int[][]cnt0, cnt1;
    final int HIGH_BIT = 15;
    public void MajorityChecker(int[] arr) {
        int n = arr.length;
        cnt0 = new int[n + 1][HIGH_BIT + 1];
        cnt1 = new int[n + 1][HIGH_BIT + 1];
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            List<Integer> list = mp.getOrDefault(x, new ArrayList<>());
            list.add(i);
            mp.put(x, list);

            for (int j = 0; j <= HIGH_BIT; j++) {
                int bit = x >> j & 1;
                cnt1[i + 1][j] = cnt1[i][j] + bit;
                cnt0[i + 1][j] = cnt0[i][j] + (1 - bit);
            }
        }
        // for debug
//        System.out.println();
    }

    public int query(int left, int right, int threshold) {
        int val = 0;
        for (int i = HIGH_BIT; i >= 0; i--) {
            if (cnt0[right + 1][i] - cnt0[left][i] < threshold && cnt1[right + 1][i] - cnt1[left][i] < threshold) return -1;
            if (cnt0[right + 1][i] - cnt0[left][i] >= threshold) {
                val <<= 1;
            } else {
                val = val << 1 | 1;
            }
        }

        System.out.println(val);
        if (upper_bound(mp.getOrDefault(val, new ArrayList<>()), right) - lower_bound(mp.getOrDefault(val, new ArrayList<>()), left) >= threshold)
            return val;
        return -1;
    }
    private int upper_bound(List<Integer>nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = l + r >> 1;
            if (nums.get(mid) > x) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    private int lower_bound(List<Integer> nums, int x) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = l + r >> 1;
            if (nums.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    /**
     *
     * https://leetcode.cn/problems/partition-array-for-maximum-sum/
     * 相邻取k，最大值求和
     * 线性dp问题。
     * */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1, cnt = 1, maxv = arr[i - 1]; j >= 0 && cnt <= k; j--, cnt++) {
                dp[i] = Math.max(dp[i], dp[j] + maxv * cnt);
                if (j - 1 >= 0) maxv = Math.max(maxv, arr[j - 1]);
            }
        }
        return dp[n];
    }
    /**
     *
     * 1000.合并石头的最大成本
     *
     * dp[l][r][k] =
     * 枚举划分点i，枚举划分的堆数j
     *
     * dp[l][r][k] = dp[l][i][j] + dp[i + 1][r][k - j]
     *
     *
     * */
    private int dfs_merge(int l, int r, int k, int[] sum) {
        if (r - l + 1 <= k) {
            // 如果恰好等于
            if (r - l + 1 == k) return 0;
            // 如果小于是不可能的
            return 0x3f3f3f3f;
        }
        int ans = 0x3f3f3f3f;
        for (int i = l; i < r; i++) {
//            for (int j = Math.max(1, k - r + i); j <= Math.min(k - 1, (i - l + 1)); j++) {       //左最少一堆， （最多k - 1堆，或者长度堆[l, i])
            for (int j = 0; j <= k; j++) {
                int left = dfs_merge(l, i, j, sum);
                int right = dfs_merge(i + 1, r, k - j, sum);                 // 右边最少一堆 , 最多 k -1, 或者长度堆 k - j >= 1 && k - j <= Math.min(k - 1, r - i);
                ans = Math.min(left + right + sum[r + 1] - sum[l], ans);
            }
        }
        return ans;
    }
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        int[][][] dp = new int[n + 1][n + 1][k];            // [l, r]合并成为k堆的最大值
        int[] sum = new int[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }
        int ans = dfs_merge(0, n - 1, k, sum);
        if (ans >= 0x3f3f3f3f) return -1;
        return ans;
    }

    public static void main(String[] args) {
        Summary summary = new Summary();
//        System.out.println(summary.maskPII("LeetCode@LeetCode.com"));
//        summary.MajorityChecker(new int[]{1, 1, 2, 2, 1, 1});
//        System.out.println(summary.query(2, 3, 2));
        System.out.println(summary.mergeStones(new int[]{3,2,4,1}, 2));
}
}
