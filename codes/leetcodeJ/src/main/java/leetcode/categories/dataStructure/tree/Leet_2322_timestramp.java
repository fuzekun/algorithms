package leetcode.categories.dataStructure.tree;

import com.sun.javaws.IconUtil;
import leetcode.categories.dp.Leet2572;
import leetcode.utils.ChangeToArrayOrList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/7 15:24
 * @Description: 时间戳 / 枚举
 */
public class Leet_2322_timestramp {

    int[] nums;
    List<Integer>[]g;
//    int sb;
//    int s;
    private int ans;
//    // 得到u为根，ban为分割点的异或和。也就是Sb
//    int dfs(int u, int fa, int ban) {
//        int ret = nums[u];
//        for (Integer v: g[u]) {
//            if (v == fa || v == ban) continue;
//            int son = dfs(v, u, ban);
//            ret ^= son;
//        }
//        return ret;
//    }
//    // 对于每一个结点来说，两种选择，删除，或者不删除。如果删除，就得到Sd 也就是x。 sa = sm2 ^ x
//    int dfs2(int u, int fa, int ban) {
//        int ret = nums[u];
//        // 枚举待删除的边, 每一个根节点都删除一次就行了。
//        for (Integer v : g[u]) {
//            if (v != fa && v != ban) {
//                int sd = dfs2(v, u, ban);          // Sd，
//                ret ^= sd;
//                int sc = sb ^ sd;                   // sc
//                int sa = s ^ sb;
//                int maxv = max(max(sa, sc), sd);    // sa
//                int minv = min(min(sa, sc), sd);
//                ans = Math.min(ans, maxv - minv);
//            }
//        }
//        return ret;
//    }
    private int min(int a, int b) {
        return Math.min(a, b);
    }
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private int cur;
    private int[] in, out;
    private int[] xor;                          // 存储一下就少了一次遍历啊。
    private void dfs(int u, int fa) {
        in[u] = cur++;
        xor[u] = nums[u];
        for (Integer v : g[u]) {
            if (v == fa) continue;
            dfs(v, u);
            xor[u] ^= xor[v];
        }
        out[u] = cur++;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new ArrayList[n];
        this.nums = nums;
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        ans = (int)2e9;
        // 方法一：计算前缀和
//        s = 0; sb = 0;
//        for (int x : nums) s ^= x;

        // 方法一：枚举第一条需要删除的边。和根相连的第一条边作为被删除的边。
//        for (int i = 0; i < n; i++) {
//            for (int j : g[i]) {
//                sb = dfs(i, -1, j);                // Sb
//                dfs2(i, -1, j);
//            }
//        }
        // 方法二：时间戳
        in = new int[n];
        out = new int[n];
        xor = new int[n];
        dfs(0, -1);
        for (int i = 2; i < n; i++) {                       // 枚举待删除的第一个点
            for (int j = 1; j < i; j++) {                     // 枚举带删除的第二个点
                int x, y, z;
                if (in[i] < in[j] && out[j] < out[i]) {     // i是j的祖先
                    x = xor[0] ^ xor[i]; y = xor[i] ^ xor[j]; z = xor[j];
                }
                else if (in[j] < in[i] && out[i] < out[j]) { // j 是i的祖先
                    x = xor[0] ^ xor[j]; y = xor[j] ^ xor[i]; z = xor[i];
                }
                else {
                    x = xor[0] ^ xor[i] ^ xor[j];
                    y = xor[i];
                    z = xor[j];
                }
                int maxv = max(max(x, y), z);
                int minv = min(min(x, y), z);
                ans = min(ans, maxv - minv);
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        String s1 =  "[5,5,2,4,4,2]";
        String s2 = " [[0,1],[1,2],[5,2],[4,3]";
//        String s1 = " [1,5,5,4,11]";
//        String s2 = "[[0,1],[1,2],[1,3],[3,4]]";
        int[] nums = ChangeToArrayOrList.changTo1DIntArray(s1);
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray(s2);
        Leet_2322_timestramp t = new Leet_2322_timestramp();
        int ans = t.minimumScore(nums, numss);
        System.out.println(ans);
    }
}
