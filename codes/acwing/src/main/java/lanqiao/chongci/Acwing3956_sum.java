package lanqiao.chongci;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/29 11:15
 * @Description: 前缀和
 *
 * 前缀和可以递减。
 *
 */
public class Acwing3956_sum {
    private static int find(List<Integer>sum, int l, int r, int s) {
        while (l < r) {
            int mid = l + r >> 1;
            if (sum.get(mid) >= s) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;
        int[] sum = new int[n + 1];
        HashMap<Integer, List<Integer>>mp = new HashMap<>();
        int ss = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            sum[i + 1] = sum[i] + a;
            List<Integer> list = mp.getOrDefault(sum[i + 1], new ArrayList<>());
            list.add(i + 1);
            mp.put(sum[i + 1], list);
            ss += a;
        }
        if (ss % 3 != 0) {
            System.out.println(0);
            return ;
        }
        int s = ss / 3;
        if (!(mp.containsKey(s) && mp.containsKey(s * 2))) {
            System.out.println(0);
            return ;
        }
        List<Integer>listi = mp.get(s);
        List<Integer>listj = mp.get(s * 2);
        // 最后一个不能要
        if (listj.get(listj.size() - 1) == n) {
            listj.remove(listj.size() - 1);
        }
        for (int i : listi) {
            // 下标递增
            int j = find(listj, 0, listj.size(), i + 1);
            ans += listj.size() - j;
        }
        System.out.println(ans);
    }
}
