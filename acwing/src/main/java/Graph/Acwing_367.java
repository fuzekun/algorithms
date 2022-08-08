package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/8/3 10:44
 * @Description: acwing 367题目，学校网络
 *
 * 1. 使用tarjan算法得到对应的连通图的数量并统计出度和入读
 * 2.
 */
public class Acwing_367 {
    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        List<Integer>[]G = new List[n + 1];
        for (int a = 1; a <= n; a++) {
            G[a] = new ArrayList<>();
            String[] cur = in.readLine().split(" ");
            for (int j = 0; j < cur.length - 1; j++)
            {
                int b = Integer.parseInt(cur[j]);
                G[a].add(b);
            }
        }
        Tarjon tarjon = new Tarjon(n, G);
        int sc_cnt = tarjon.tarjan(new int[n + 1]);
//        System.out.println(sc_cnt);
        List<int[]> p = tarjon.getD();
        int[] ind = p.get(0);
        int[] outd = p.get(1);
        if (sc_cnt == 1) {
            System.out.println(1);
            System.out.println(0);
        }
        else {
            int a = 0, b = 0;
            for (int i = 1; i <= sc_cnt; i++) {
                if (ind[i] == 0) a++;
                if (outd[i] == 0) b++;
            }
            System.out.println(a);
            System.out.println(Math.max(a, b));
        }
    }
}
