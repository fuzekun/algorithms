package lanqiao.imporve;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/15 15:42
 * @Description: 强力党
 *
 *
 *
 * 每一个技能都有一个且只有一个前提技能，且只有一个没有前提技能的，
 * 所以一定有且只有一个树根， 且每一个点有且只有一个入度。
 * 所以构成一个有向树。
 *
 * 还需要计算出每一层的最大值
 * 总和为m,分配个k个结点，每一个结点如果有t个值，会有不同的结果。
 * 然后需要求出将m分配给k个结点的最大值。
 *
 * 典型的分组背包问题，每一个结点是一个组，每个组中有体积不同的值，每个组中的
 * 物品最多选择一个。
 *
 * 分成n个组，dp[i][j]表示第j个物品的价值，j表示第j个物品的体积。
 * 背包的容量是变化的
 *
 *
 *
 */
public class QiangLiDang {

    private static int n, m;
    private static List[]g;
    private static int[]arr;
    public static int maxv = 0;
    public static int[][] dp;
    public static void dfs(int u) { // 对于结点u为根的树，有m个可以选择的点的最大值, 并且一定要选择u
        // 分成了g[u].size()组
        for (Object tmp: g[u].toArray()) {
            int v = (Integer)tmp;
            dfs(v);
            for (int i = m - 1; i >= 0; i--) {      // 枚举体积
                for (int j = 0; j <= i; j++) {       // 枚举物品种类,有j种体积，就有j种物品
                    dp[u][i] = Math.max(dp[u][i], dp[u][i - j] + dp[v][j]);
                }
            }
        }
        // 当前结点必选，所以dp[u][i]应该是子节点中选择i - 1个，然后加上自己
        // 注意是逆序，正序就成了求前缀和了。
        for (int i = m; i >= 1; i--) dp[u][i] = dp[u][i - 1] + arr[u];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dp = new int[n][m + 1];
        int[] d = new int[n];
        Arrays.fill(d, 0);
        g = new List[n];
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
            Arrays.fill(dp[i], 0);
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            g[a].add(b);
            d[b]++;
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) {
                root = i;
                break;
            }
        }
        dfs(root);
        System.out.println(dp[root][m]);
    }
}
