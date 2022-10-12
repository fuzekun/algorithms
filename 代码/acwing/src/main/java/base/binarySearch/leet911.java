package base.binarySearch;

import grady.Leet1383;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zekun Fu
 * @date: 2022/9/24 11:29
 * @Description:在线选举，区间二分
 *
 *  1. 区间问题
 *  2. 二分
 *  3. 对于
 */
public class leet911 {
    Map<Integer, Integer> mp;
    int[] interval;
    int[] nums;
    int n;

    public leet911(int[] persons, int[] times) {
        n = times.length;
        interval = new int[n];
        nums = new int[n];
        mp = new HashMap<>();
        int[] cnt = new int[6001];
        int pre = 0;
        for (int i = 0; i < n; i++) {
            int p = persons[i];
            int t = times[i];
            cnt[p]++;
            if (cnt[p] >= cnt[pre]) {
                pre = p;
            }
            if (i != n - 1) interval[i] = times[i + 1];
            else interval[i] = Integer.MAX_VALUE;
            nums[i] = pre;
        }
    }

    public int query(int t) {
        int l = 0, r = n;
        // System.out.println(interval[n - 1]);
        while (l < r) {
            int mid = (l + r) >> 1;
            // System.out.println(mid + " " + interval[mid]);
            if (interval[mid] <= t) {
                // System.out.println("in");
                l = mid + 1;
            } else r = mid;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        int[] persons = {0, 0, 0, 0, 1};
        int[] times = {0, 6, 39, 52, 75};
        leet911 le = new leet911(persons, times);
        le.query(68);
    }
}
//}["TopVotedCandidate","q","q","q","q","q","q","q","q","q","q"]
//        [[[0,0,0,0,1],[0,6,39,52,75]],[45],[49],[59],[68],[42],[37],[99],[26],[78],[43]]
