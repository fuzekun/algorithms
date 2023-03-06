package leetcode.everyDay.Nowanber;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.ReadData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Zekun Fu
 * @date: 2022/11/18 9:55
 * @Description: 11/13- 11_18号
 */
public class E11_18 {
    // leet 1775
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        /*
         *   1. 第二维排序
         *   2. 直接装，
         * */
        int n = boxTypes.length;
        Arrays.sort(boxTypes, (x1, x2) -> Integer.compare(x2[0], x1[0]));
        int ans = 0;
        for (int i = 0; i < n && truckSize > 0; i++) {
            int num = Math.min(truckSize, boxTypes[i][0]);
            ans += num * boxTypes[i][1];
        }
        return ans;
    }
    private final int maxn = (int)1e5 + 5;
    private int[] c = new int[maxn];
    public int lowbit(int x) {return  x & -x;}
    public int query(int x) {
        int res = 0;
        for (int i = x; i != 0; i -= lowbit(i)) {
            res += c[i];
        }
        return res;
    }
    public void add(int x, int num) {
        for (int i = x; i < maxn; i += lowbit(i)) {
            c[i] += num;
        }
    }
    // leet 775
    public boolean isIdealPermutation(int[] nums) {
        // 1. 求逆序对， 2. 求局部倒置， 3. 进行比较
        int n = nums.length;
        int cntT = 0, cntP = 0;
        add(nums[0] + 1, 1);
        for (int i = 1; i < n; i++) {
            cntT += query(maxn - 1) - query(nums[i]);
            cntP += (nums[i] < nums[i - 1]) ? 1 : 0;
            add(nums[i] + 1, 1);
        }
        return cntT == cntP;
    }
    // leet 792 子序列
    // 判断s是否为t的子序列
    public boolean isSubsequence(String s, String t, int[][] f) {
        int n = s.length(), m = t.length();

        /*
        *
        *   已知，t[i]的字符距离j最近的是哪一个
        *   求解是否是子序列。
        *
        *   双指针的思想：
        *   如果匹配两个同时后移一个位置
        *   如果不匹配，直接return false
        *   这个时候，就应该注意让f[n] 进行填充了, 否则应该特殊判断cur == n ?
        *
        *   这个题目还是有点不懂的地方。
        *   不知道怎么迭代匹配过程。
        * */
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (f[cur][s.charAt(i)] == m) return false;
            // 双指针思想，f[cur][s.charAt(i)] == s[i]，两者同时加上1
            cur = f[cur][s.charAt(i)] + 1;
        }
        return true;
//        int add = 0;
//        for (int i = 0; i < n; i++) {
//            if (f[add][s.charAt(i) - 'a'] == m) {
//                return false;
//            }
//            add = f[add][s.charAt(i) - 'a'] + 1;
//        }
    }
    public int numMatchingSubseq(String s, String[] words) {
        /*
        *
        *   为什么这个题不会？
        * 1. 考察的子序列，不知道子序列有什么性质
        * 2. word很短，但是不知道怎么从word的角度来解决问题
        * 3. 子序列问题，见392判断子序列。这个问题可以使用双指针->二分进行解决
        * 当然可以使用动态规划在O(m)的时间复杂度内解决这个问题。
        * 也就是说匹配的时间会缩短。
        * dp[i][c]: 表示前i的下一个字符c所在的最近位置
        * dp[i][c] = dp[i + 1][c]
        * dp[i][s[i + 1] - 'a'] = i + 1;
        * if (s == n) dp[i][c] = INF;
        *
        * 为什么感觉难写，还是那个简单题目写的不熟练。
        * 如果熟练掌握了那个动态规划写法，直接就可以AC了
        * */
        int n = s.length();
        char[] chars = s.toCharArray();
        int[][] f = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            f[n][i] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) f[i][j] = f[i + 1][j];
            f[i][chars[i] - 'a'] = i;
        }
        int ans = 0;
        for (String ss : words) {
            if (isSubsequence(ss, s, f))
                ans++;
        }
        return ans;
    }

    public int pow_mod(long a, long b, int mod) {
        long res = 1;
        while (b != 0) {
            if (b % 2 == 1) res = (res * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return (int)res;
    }

    // 子序列的宽度之和
    public int sumSubseqWidths(int[] nums) {
        // 用O(longn)的时间复杂度换O(n)的空间复杂度
//        System.out.println(pow_mod(2, 3, 1000));
        /*
        *   单调栈 + 前缀和
        *
        *  求得在[l, r]中nums[i]是最大的, s[1...n]表示前缀和
        *  那么和应该加上s[i] * (r - l + 1) - s[r + 1] - s[l]
        *
        * 比如[2, 1, 3]
        *    [0, 1]
        *   [1, 1]
        *   [0, 2]
        *
        *   上面是错误的，原因但单调栈适用于子串，并不适用于子序列
        *
        * 可以考虑使用单调队列，滑动窗口最大值。
        * 队头是最大值，队尾是最小值
        *
        *   数学问题，不过思路是正确的。
        *
        * 数学的选择问题。
        * 子序列可以转成无序的。
        *
        * */
        int n = nums.length;
        Arrays.sort(nums);
        long ans = 0;
        int mod = (int)1e9 + 7;
        long ppre = 1;
        long paf = 1;       // pow(2, n - 1)
        int rev = pow_mod(2, mod - 2, mod); // 2的乘法逆元
//        System.out.println(rev);
        for (int i = 0; i < n - 1; i++) ppre = (ppre * 2) % mod;
        for (int i = n - 1; i >= 0; i--) {
            ans = (ans + nums[i] * (ppre - paf)) % mod;
            ppre = (ppre * rev) % mod;        // pre *= 2
            paf = (paf * 2) % mod;        // paf /= 2
        }
        return (int)ans;
    }
    public static void main(String[] args) throws Exception{
        E11_18 e = new E11_18();
        int[] nums = ChangeToArrayOrList.changTo1DIntArray("[1,2,3]");
//        boolean ans = e.isIdealPermutation(nums);
//        System.out.println(ans);
//        String[] words = {"a","bb","acd","ace"};
//        int ans = e.numMatchingSubseq("abcde", words);
//        System.out.println(ans);
        int ans = e.sumSubseqWidths(nums);
        System.out.println(ans);
    }

}
