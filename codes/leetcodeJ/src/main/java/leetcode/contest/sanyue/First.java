package leetcode.contest.sanyue;

import leetcode.utils.ChangeToArrayOrList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/12 10:35
 * @Description: 第一场双周赛
 */
public class First {
    public int vowelStrings(String[] words, int left, int right) {
        HashMap<Character, Integer>mp = new HashMap<>();
        Character[] chars = new Character[]{'a','e','i','o','u'};
        for (char ch : chars) mp.put(ch, 1);
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (mp.containsKey(words[i].charAt(words[i].length() - 1)) && mp.containsKey(words[i].charAt(0))) {
                ans++;
//                System.out.println(words[i]);
            }
        }
        return ans;
    }
    public int maxScore(int[] nums) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int x : nums) {
            if (x >= 0) pos.add(x);
            else neg.add(x);
        }
        // 逆序排序
        pos.sort((o1, o2)->Integer.compare(o2, o1));
        neg.sort((o1, o2)->Integer.compare(o2, o1));
        // 特殊情况 000 ，没有正数
        if (pos.size() == 0 || pos.get(0) == 0) return 0;
        int ans = pos.size();
        long sum = 0;
        for (int i = 0; i < pos.size(); i++) sum += pos.get(i);
        for (int i = 0; i < neg.size(); i++) {
            sum += neg.get(i);
            if (sum > 0) ans++;
        }
        return ans;
    }
    public long beautifulSubarrays(int[] nums) {
        // 子数组异或和为0
        HashMap<Integer, Integer>cnts = new HashMap<>();
        cnts.put(0, 1);
        int sum = 0;
        long ans = 0;
        for (int x : nums) {
            sum ^= x;
            int precnt = cnts.getOrDefault(sum, 0);
            ans += precnt;
            cnts.put(sum, precnt + 1);
        }
        return ans;
    }


    public int findMinimumTime(int[][] tasks) {
        return 0;
    }
    public static void main(String[] args) {
        String[] ss = {"hey","aeo","mu","ooo","artro"};
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray("[[2,3,1],[4,5,1],[1,5,2]]");
        int []nums = ChangeToArrayOrList.changTo1DIntArray("[0, 0, 0]");
        First f = new First();
//        System.out.println(f.vowelStrings(ss, 1, 4));
//        System.out.println(f.maxScore(nums));
//        System.out.println(f.beautifulSubarrays(nums));
        System.out.println(f.findMinimumTime(numss));
    }
}
