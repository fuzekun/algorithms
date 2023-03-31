package leetcode.everyDay.sanyue;

import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
/**
 * @author: Zekun Fu
 * @date: 2023/3/21 20:55
 * @Description: Trie树
 *
 * map的可行性：
 * 1. 相与一定会减小
 * 2. 数字最大不超过2^16
 * 3. 所以最多有2^16个数字
 * 4. 时间复杂度2 ^ 16 * 1000 < 1e8
 * 所以一定可以完成。
 */
public class Leet982 {

    // Trie的数据结构
    private int cur;
    private int idx = 0;
    private int[][] nx;
    private int[] cnt;
    private final int HIGH = 16;
    // trie树增加结点
    private void add(int x) {
        cur = 0;

        for (int i = HIGH; i >= 0; i--) {
            int c = x >> i & 1;
            // 创建新结点
            if (nx[cur][c] == 0) nx[cur][c] = ++idx;
            cur = nx[cur][c];
        }
        cnt[cur]++;
    }
    private int sum = 0;
    // &的性质 + trie树进行优化
    private int dfs(int d, int x, int cur) {
        if (d == -1) {
            return cnt[cur];
        }
        // 此位等于1，直接向0走
        int res = 0;
        if ((x >> d & 1) == 1) {
            if (nx[cur][0] != 0)
                res = dfs(d - 1, x, nx[cur][0]);
        }
        // 否则，向0和向1都要走
        else {
            if (nx[cur][0] != 0) res += dfs(d - 1, x, nx[cur][0]);
            if (nx[cur][1] != 0) res += dfs(d - 1, x, nx[cur][1]);
        }
        return res;
    }
    public int countTriplets(int[] nums) {
//        nx = new int[100000][2];
//        cnt = new int[100000];
        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : nums) {
//            mp.put(x, mp.getOrDefault(x, 0) + 1);
            for (int y : nums) {
//                add(x & y);
                int t = x & y;
                mp.put(t, mp.getOrDefault(t, 0) + 1);
            }
        }
        // 方法一：使用trie树进行优化, 1的数量越多，优化越明显，1越靠顶，优化越明显
//        int ans = 0;
//        for (Map.Entry<Integer, Integer>entry : mp.entrySet()) {
//            ans += dfs(HIGH, entry.getKey(), 0) * entry.getValue();
//        }
//        return ans;
        // 方法二：直接写
//        for (int x : nums) {
//            ans += dfs(HIGH, x, 0);
////            for (Entry<Integer, Integer> entry : mp.entrySet()) {
////                if ((x & entry.getKey()) == 0) ans += entry.getValue();
////            }
//        }
        // 方法三：二进制枚举优化, 只需要1的数量多就可以优化明显。
        int ans= 0;
        for (int x : nums) {
            // x的补集
            x = x ^ 0xffff;
            int sub = x;
            while (true) {
                ans += mp.getOrDefault(sub, 0);
                sub = (sub - 1) & x;
                if (sub == x) break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 3};
        System.out.println(new Leet982().countTriplets(nums));
    }
}
