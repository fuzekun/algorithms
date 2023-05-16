package leetcode.categories.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/4/22 15:12
 * @Description:
 */
public class bfs2 {
    int maxv = 0;
    int minv = Integer.MAX_VALUE;
    int k;
    int n;
    List<Integer>sum = new ArrayList<>();
    void dfs(int[] price, int id, int cur, List<Integer>[] bag) {
        if (id == n) {
            if (cur != k) return ;
        }
        if (k == cur) {
            if (id != n) return;

            int s = 0;
            for (int i = 0; i < k; i++) {
                s += bag[i].get(0) + bag[i].get(bag[i].size() - 1);
            }
            sum.add(s);
            return;
        }
        // 放在前一个背包中
        bag[cur].add(price[id]);
        dfs(price, id + 1, cur, bag);
        // 放在后一个背包中
        dfs(price, id + 1, cur + 1, bag);
        bag[cur].remove(bag[cur].size() - 1);
    }
    public int putGems (int[] price, int k) {
        // write code here
        // k个分组
        this.n = price.length;
        this.k = k;
        List<Integer>[] bag = new ArrayList[k];
        for (int i = 0; i < k; i++) bag[i] = new ArrayList<>();
        dfs(price, 0, 0, bag);
        sum.sort(Integer::compareTo);
        return sum.get(sum.size() - 1) - sum.get(0);
    }

    public static void main(String[] args) {
        int ans = new bfs2().putGems(new int[]{2,3}, 2);
        System.out.println(ans);
    }
}
