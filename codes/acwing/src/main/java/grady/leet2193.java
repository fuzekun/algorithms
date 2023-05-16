package grady;

import dataStructure.TreeArray;
import org.junit.Test;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/8/9 9:27
 * @Description: 得到会问串的最少操作次数
 *
 * 1, 出现奇数次的最多有一种。
 * 2. 出现偶数次的回文字母，相对顺序一定保持不变。
 *
 *
 * 有一个问题，会不会，一旦一个字母交换完成，另一个字母就受到影响。
 * 会不会和字母的交换顺序有什么关系呢？
 *
 * 所以就有了下面的构造方法：
 * 一、对于偶数次的字母
 * 1. 对于任意一个字母， 采用双指针。
 * 2. 遍历左半部分i，计算右半部分对应的位置p = n - i - 1 。
 *3. 右指针从右向左进行，计算需要交换的次数. ans += abs(p - k)
 *
 * 二、对于奇数的字母
 * 直接删掉，就变成了子问题。最后把它加进去需要n / 2 - i次交换
 *
 * aaabb aabba abba ababa
 *
 *
 *
 * 问题
 *1. 为什么要贪心
 *2. 代码中为什么右边的序列里面要和左边的逆序匹配
 *
 *
 *
 *
 */
public class leet2193 {
    public int minMovesToMakePalindrome(String ss) {
        char[] s = ss.toCharArray();
        int n = s.length, ans = 0;
        for (int i = 0, j = n - 1; i < j; i++) {
            boolean flag = false;
            for (int k = j; i != k; k--) {
                if (s[i] == s[k]) {
                    for (; k < j; k++) {
                        char tmp = s[k];
                        s[k] = s[k + 1];
                        s[k + 1] = tmp;
                        ans++;
                    }
                    j--;
                    flag = true;
                    break;
                }
            }
            if (!flag) ans += n / 2 - i;
        }
        return ans;
    }


    public int m2(String ss) {
        char[] s = ss.toCharArray();
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch :s) {
            if (freq.containsKey(ch)) freq.put(ch, freq.get(ch) + 1);
            else freq.put(ch, 1);
        }

        int ans = 0;
        // 存储每一个字符对应的编码, 从1开始编码
        HashMap<Character, List<Integer>> left = new HashMap<>();
        Map<Character, List<Integer>>right = new HashMap<>();
        int lcnt = 0, rcnt = 0;
        int n = s.length;
        for (int i = 0; i < n; i++) {
            char ch = s[i];
            left.putIfAbsent(ch, new ArrayList<>());
            right.putIfAbsent(ch, new ArrayList<>());
            List<Integer>llist = left.get(ch);
            List<Integer> rlist = right.get(ch);
            if (llist.size() < freq.get(ch) / 2) {
                ++lcnt;
                llist.add(lcnt);
                ans += (i - lcnt + 1);  // 计算组间间距
            } else {
                ++rcnt;
                rlist.add(rcnt);
            }
        }

        // 处理奇数的情况
        if (n % 2 == 1) {
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                char c = entry.getKey();
                int occ = entry.getValue();
                if (occ % 2 == 1) {
                    ++lcnt;
                    left.get(c).add(lcnt);
                    break;
                }
            }
        }
        for (Map.Entry<Character, List<Integer>>entry :left.entrySet()) {
            System.out.println("ch = " + entry.getKey());
            System.out.println(entry.getValue());
        }
        for (Map.Entry<Character, List<Integer>>entry :right.entrySet()) {
            System.out.println("ch = " + entry.getKey());
            System.out.println(entry.getValue());
        }

        // 生成右边的排列
        int[] perm = new int[(n + 1) / 2];
        for (Map.Entry<Character, List<Integer>> entry : right.entrySet()) {
            List<Integer>rlist = entry.getValue();
            List<Integer>llist = left.get(entry.getKey());
            int m = rlist.size();
            for (int i = 0; i < m; i++) {
                perm[rlist.get(m - i - 1) - 1] = llist.get(i);
            }
        }
        for (int x :perm) {
            System.out.print( x + " ");
        }
        System.out.println();
        for (int i = 0, j = perm.length - 1; i < j; i++, j--) {
            int tmp = perm[i];
            perm[i] = perm[j];
            perm[j] = tmp;
        }

        //  使用树状数组计算逆序对
        int maxv = 1005;
        TreeArray ta = new TreeArray(maxv); // 最多1000
        for (int x : perm) {

            ans += ta.sum(maxv) - ta.sum(x); // (x, maxv]
            ta.add(x, 1);
        }
        System.out.println();
        return ans;
    }

    @Test
    public void testM2() {
        String s = "aaabbbbcc";
        int ans = m2(s);
        System.out.println("ans = " + ans);
    }
}
