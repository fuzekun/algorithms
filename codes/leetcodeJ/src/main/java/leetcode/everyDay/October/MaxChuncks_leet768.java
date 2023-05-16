package leetcode.everyDay.October;

import leetcode.utils.ChangeToArrayOrList;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zekun Fu
 * @date: 2022/10/13 15:05
 * @Description: leetcode的一个简单的贪心题
 */
public class MaxChuncks_leet768 {
    // 遍历的时间复杂度是O(n)，所以总的时间复杂度是O(n^2)
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] cp = Arrays.copyOf(arr, n);
        Arrays.sort(cp);
        HashMap<Integer, Integer>mp1 = new HashMap<>();
        Map<Integer, Integer>mp2 = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            mp1.put(arr[i], mp1.getOrDefault(arr[i], 0) + 1);
            mp2.put(cp[i], mp2.getOrDefault(cp[i], 0) + 1);
            int flag = 1;
            for (Map.Entry<Integer, Integer>entry: mp1.entrySet()) {
                if (Integer.compare(entry.getValue(), mp2.getOrDefault(entry.getKey(), 0)) != 0) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1) {
                mp1.clear();
                mp2.clear();
                ans++;
            }
        }
        return ans;
    }

    // 增加和删除的时间复杂度都是O(logn)
    public static int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] cp = Arrays.copyOf(arr, n);
        Arrays.sort(cp);
        int ans =  0;
        Map<Integer, Integer>mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(arr[i], mp.getOrDefault(arr[i], 0) + 1);
            mp.put(cp[i], mp.getOrDefault(cp[i], 0) - 1);
            if (mp.getOrDefault(arr[i], 0) == 0)
                mp.remove(arr[i]);
            if (mp.getOrDefault(cp[i], 0) == 0)
                mp.remove(cp[i]);
            if (mp.isEmpty()) ans++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = ChangeToArrayOrList.changTo1DIntArray("[5,4,3,2,1]");
        System.out.println(new MaxChuncks_leet768().maxChunksToSorted(arr));
        System.out.println(maxChunksToSorted2(arr));
    }
}
