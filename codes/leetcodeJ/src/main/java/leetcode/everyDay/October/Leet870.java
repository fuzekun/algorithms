package leetcode.everyDay.October;

import leetcode.utils.PrintArrays;
import leetcode.utils.ReadData;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/8 10:54
 * @Description: 每日一题870，优势洗牌
 *贪心题目，优势最大化， 索引的数目。
 * 找到比他大的个数，用最小的来放就行了。
 * 排序 + 双指针
 *
 *
 *
 */
public class Leet870 {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int n = nums1.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        List<int[]>pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new int[]{nums2[i], i});
        }
        pairs.sort((a, b)->Integer.compare(a[0], b[0]));
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        for (int i = 0, j = 0; j < n && i < n; j++, i++) {
            // 找到第一个大于nums2的数
            while (i < n && nums1[i] <= pairs.get(j)[0]) i++;
            if (i == n) break;
            // 使用过了
            mp.put(i, 1);
            // 对应的下标应该放上nums1对应的数字
            ans[pairs.get(j)[1]] = nums1[i];
        }
        for (int i = 0, j = 0; i < n; i++) {
            if (ans[i] == -1) {
                // 找到第一个没有使用过的数字，也就是比他小的数字
                while (mp.containsKey(j)) j++;
                // 肯定会有的，因为是1，1对应的
                ans[i] = nums1[j];
                j++;
            }
        }
        return ans;
    }
    public static void main(String[] args)throws Exception {
        int[] nums1 = ReadData.getArray();
        int[] nums2 = ReadData.getArray("int1d_");
        PrintArrays.print1DIntArray(new Leet870().advantageCount(nums1, nums2));
    }
}
