package leetcode.contest.May;

import java.util.*;

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

    /*
    *
    *   最后一道题目的暴力解法
    * 1. 首先枚举所有的子串
    * 2.
    * */
    public long appealSum(String s) {
        long ans = 0L;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            long cnt = 0L; // 之前的子串中有多少个不同的字符
            boolean[] vis = new boolean[26];
            Arrays.fill(vis, false);
            for (int j = i; j < n; j++) {
                sb.append(s.charAt(j));
                if (!vis[s.charAt(j) - 'a']) {
                    cnt++;
                    vis[s.charAt(j) - 'a'] = true;
                }
                System.out.println(sb.toString() + " " + cnt);
                ans += cnt;
            }
        }
        return ans;
    }

    /*
            第二种暴力解法
            fxfz
            f
            x
            fx

     */

    public long solve2(String s) {
        long ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            long sum = 0;
            boolean[] vis = new boolean[26];
            for (int j = i; j >= 0; j--) {
                int c = s.charAt(j) - 'a';
                sb.append(s.charAt(j));
                if (!vis[c]) {
                    vis[c] = true;
                    sum++;
                }
                System.out.println(sb.toString() + " " + sum);
                ans += sum;
            }
        }
        return ans;
    }

    public long appealSum2(String s) {
        long ans = 0;
        int n = s.length();
        int[] f = new int[26];
        Arrays.fill(f, -1);
        for (int i = 0; i < n; i++) {
            f[s.charAt(i) - 'a'] = i;
            for (int j = 0; j < 26; j++) {
                ans += f[j] + 1;
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
//        System.out.println("ans2 = " + t.solve2(nums2));
//        System.out.println("ans3 = " + t.countDistinct(nums, 2, 2));
//        System.out.println("ans4 = " + t.appealSum2("abbca"));
//        System.out.println("rightAns4 = " + t.lastSolve("abbca"));
        String s = "fxfz";
        System.out.println("ans = " + t.solve2(s));
        System.out.println("ans4 = " + t.appealSum(s));

    }
    /*
    *
    *   1, 3, 4, 8, 12
    *   以c结尾的提供了4个，为什么要加上以前的4个呢,以前的4个
    *   以前的每一个子串都在加上一个c, 另外
    * */
}
