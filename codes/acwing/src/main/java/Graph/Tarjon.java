package Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/8/3 9:36
 * @Description: tarjon算法, 顺便统计出度和入度
 *
 * 1. 点是从1开始编号，编号到n的。
 * 2. 可以顺便把无向图建立起来, 去重使用的是1e6的hash值
 * 3. 顺便统计出度和入度
 *
 */
@SuppressWarnings("unchecked")
public class Tarjon {
    // low: 最小循环入口，所以使用min(low[u], dfn[v])
    // dfn: 每个点的到达时间。
    private int[] dfn, low, id;
    private int[] sk;
    private boolean[] in_sk;
    private int[] ind, outd;
    private int tt, timestrap, sc_cnt;
    private List<Integer>[]G;           // 存储图
    private int n;

    Tarjon(int _n, List[] _G) {
        n = _n;
        G = _G;            // 泛型数组没法创建，但是可以这样创建并使用
        low = new int[n + 5];
        id = new int[n + 5];
        dfn = new int[n + 5];
        sk = new int[n + 5];
        in_sk = new boolean[n + 5];
        ind = new int[n + 5];
        outd = new int[n + 5];
        tt = timestrap = sc_cnt = 0;
    }
    private void tarjan(int u) {
        dfn[u] = low[u] = ++timestrap;
        sk[tt++] = u; in_sk[u] = true;
//        System.out.printf("in : %d %d %d\n", u, dfn[u], low[u]);
        for (int v : G[u]) {
            if (dfn[v] == 0) {
                tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            }
            else {
                if (in_sk[v])
                    low[u] = Math.min(low[u], dfn[v]);
            }
        }
//        System.out.printf("out :%d %d %d\n", u, dfn[u], low[u]);
        if (dfn[u] == low[u]) {
            int y = 0;
            ++sc_cnt;
            do {
                y = sk[--tt];
                id[y] = sc_cnt;
                in_sk[y] = false;
            } while (y != u);
        }
    }
    // 返回id和cnt，也就是连通分量的个数，以及每个点对应的来连通分量
    public int tarjan(int[] ans) {
        for (int i = 1; i <= n; i++) {
            if (dfn[i] == 0)
                tarjan(i);
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.printf("%d %d\n", i, id[i]);
//        }
        for (int i = 0; i <= n; i++) {
            ans[i] = id[i];
        }
        return sc_cnt;
    }
    public List<int[]> getD() {
        for (int i = 1; i <= n; i++) {
            for (int j : G[i]) {
                int a = id[i], b = id[j];
                if (a != b) {
                    ind[b]++;
                    outd[a]--;
                }
            }
        }
        ArrayList<int[]> ans = new ArrayList<>();
        ans.add(ind);
        ans.add(outd);
        return ans;
    }
    // 对新的强连通分量创建新图, 去掉重边
    public List<Integer>[] buildG() {
        List<Integer>[] ans = new List[n + 1];
        for (int i = 1; i <= n; i++)
            ans[i] = new ArrayList<>();
        HashSet<Integer>set = new HashSet<>();
        int base = (int)1e6 + 5;
        for (int i = 1; i <= n; i++) {
            int a = id[i];
            for (int j : G[i]) {
                int b = id[j];
                int hash = a * base + b;
                if (a != b && !set.contains(hash)) {
                    set.add(hash);
                    ans[a].add(b);
                }
            }
        }
        return ans;
    }

    // 计算每一个连通分量点的个数
    public int[] get_sc_size() {
        int[] ans = new int[n + 1];
        Arrays.fill(ans, 0);
        for (int i = 1; i <= n; i++) {
            ans[id[i]]++;
        }
        return ans;
    }

}
