package leetcode.contest.May;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class M_T_1 {
    public String removeDigit(String number, char digit) {
        char[] chars = number.toCharArray();
        StringBuilder b = new StringBuilder();
        int n = number.length();
        PriorityQueue<String>que = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < n; i++) {
            if (chars[i] == digit){
                StringBuilder tmp = new StringBuilder(b);
                tmp.append(number.substring(i + 1));
                System.out.println("tmp = " + tmp);
                que.add(tmp.toString());
            }
            b.append(chars[i]);
        }
        return que.peek();
    }
    public int solve2(int[] nums) {
        HashMap<Integer, Integer>mp = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (mp.containsKey(nums[i])) {
                System.out.println("i = " + i + " p = " + mp.get(nums[i]));
                ans = Math.min(i - mp.get(nums[i]) + 1, ans);
            }
            mp.put(nums[i], i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int countDistinct(int[] nums, int k, int p) {
        /*
        *
        *   使用字符串hash就行了,需要注意应该使用
        * */

        int n = nums.length;
        int ans = 0;
        HashMap<String, Boolean>mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for (int j = i; j < n; j++) {
                sb.append(String.valueOf(i));
                sb.append('a');
                cnt += nums[i] % p == 0 ? 1 : 0;
                if (!mp.containsKey(sb.toString())) {
                    mp.put(sb.toString(), true);
                    if (cnt <= k) ans++;
                }
            }
        }
        return ans;
    }


    public int lastSolve(String s) {
        int sum = 0, ans = 0;
        int[] p = new int[s.length()];
        Arrays.fill(p, -1);
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            sum += i - p[c];
            p[c] = i;
            ans += sum;
            System.out.println("sum = " + sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        M_T_1 t = new M_T_1();
        int[] nums = {2,3,3,2,2};
        int[] nums2 = {1,2,3,4};
//        System.out.println("ans1 = " + t.removeDigit("513214", '1'));
        System.out.println("ans2 = " + t.solve2(nums2));
//        System.out.println("ans3 = " + t.countDistinct(nums, 2, 2));
//        System.out.println("ans4 = " + t.lastSolve("abbca"));
    }
    /*
    *
    *   1, 3, 4, 8, 12
    *   以c结尾的提供了4个，为什么要加上以前的4个呢,以前的4个
    *   以前的每一个子串都在加上一个c, 另外
    * */
}
