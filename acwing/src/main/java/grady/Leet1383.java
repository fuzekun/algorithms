package grady;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/8/10 16:58
 * @Description:
 *             1. 按照e进行逆序排序，当前的以及前面的e都是大于等于它的，一旦选择，那么e必是它。
 *             2. 直接得到当前speed的最大值对应的下标i
 *             3. e[cur] * (speed[i] + speed[cur])
 */
public class Leet1383 {

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        List<int[]>nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(new int[2]);
        }
        for (int i = 0; i < n; i++) {
            int[] p = nums.get(i);
            p[0] = efficiency[i];
            p[1] = speed[i];
        }
        nums.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);  // 逆序排序
            }
        });
//        for (int i = 0; i < n; i++) {
//            int[] t = nums.get(i);
//            for (int j = 0; j < 2; j++) {
//                System.out.print(t[j] + " ");
//            }
//            System.out.println();
//        }
        long ans = 0;
        long mod = (long)1e9 + 7;
        PriorityQueue<Integer>que = new PriorityQueue<>();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (i < k) {
                que.add(nums.get(i)[1]);
                sum += nums.get(i)[1];
            } else {
                if (!que.isEmpty() && que.peek() < nums.get(i)[1]) {
                    sum -= que.poll();
                    sum += nums.get(i)[1];
                    que.add(nums.get(i)[1]);
                }
            }
            // 这个可不敢放在外面，因为是限定的最多k个，不是一定k个。
            ans = Math.max(ans, sum * nums.get(i)[0]);
        }
        int res = (int)(ans % mod);
        return res;
    }

    public static void main(String[] args) {
        int[] speed = {10,10,7,9,8};
        int[] eff = {9,8,3,6,9};
        int n = 5;
        int k = 1;

        Leet1383 l = new Leet1383();
        int ans = l.maxPerformance(n, speed, eff, k);
        System.out.println(ans);
    }
}
