package Graph;

import jdk.internal.util.xml.impl.Pair;
import sun.java2d.pipe.BufferedRenderPipe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/8/7 17:03
 * @Description: 最大半连通子图
 */

@SuppressWarnings("unchecked") // 抑制没有进行类型检查的警告
public class Acwing_1175 {
    static int N = (int)1e5 + 5, M = (int)2e6 + 5;

    private static List<Integer>[] G1 = new List[N]; // 原图
    private static int[] id = new int[N];           // 强连通分量信息，由于作为参数，所以需要定义一下
    private static int[] f = new int[N];            // dp
    private static int[] g = new int[N];



    public static void main(String[] args) throws Exception{
        // 1. 读取数据并建立图
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String []p = in.readLine().split(" ");
        int n = Integer.parseInt(p[0]);
        int m = Integer.parseInt(p[1]);
        int mod = Integer.parseInt(p[2]);
        for (int i = 0; i <= n; i++)
            G1[i] = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            p = in.readLine().split(" ");
            int a = Integer.parseInt(p[0]);
            int b = Integer.parseInt(p[1]);
            G1[a].add(b);
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.printf("u = %d v:\n", i);
//            for (int j : G1[i]) {
//                System.out.println(j);
//            }
//        }



        // 2. 使用tarjan算法计算强连通分量，并创建图，并且统计每个分量中点的个数
        Tarjon tarjon = new Tarjon(n, G1);
        int sc_cnt = tarjon.tarjan(id);      // 得到每一个点所属于的请连通分量，以及分量的个数
        int[] scCnt = tarjon.get_sc_size();
        List<Integer>[]G = tarjon.buildG();
//        for (int i = 1; i <= n; i++) {
//           System.out.printf("%d in %d, num = %d\n", i, id[i], scCnt[id[i]]);
//        }
//        for (int i = 1; i <= sc_cnt; i++) {
//            System.out.printf("u = %d v:\n", i);
//            for (int j : G[i]) {
//                System.out.println(j);
//            }
//        }

        // 3. 计算有向无环图的dp路径最大值问题, 逆序就是拓扑序
        Arrays.fill(f, 0);
        Arrays.fill(g, 0);
        for (int i = sc_cnt; i >= 1; i--) {
            if (f[i] == 0) {
                f[i] = scCnt[i];
                g[i] = 1;
            }
            for (int j : G[i]) {
                if (f[j] < f[i] + scCnt[j]) {
                    f[j] = f[i] + scCnt[j];
                    g[j] = g[i];
                } else if (f[j] == f[i] + scCnt[j]) {
                    g[j] = (g[i] + g[j]) % mod;
                }
            }
        }
        int maxv = 0;
        int cnt = 0;
        for (int i = 1; i <= sc_cnt; i++) {
//            System.out.printf("i = %d f[i] = %d\n", i, f[i]);
            if (f[i] > maxv) {
                maxv = f[i];
                cnt = g[i];
            } else if (f[i] == maxv) {
                cnt = (cnt + g[i]) % mod;
            }
        }
        System.out.println(maxv);
        System.out.println(cnt);
    }
}
