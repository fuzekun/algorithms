package leetcode.contest.October;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/29 23:02
 * @Description: 第四场双周赛
 */
public class ThirdS {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        HashMap<char[], Integer>mp = new HashMap<>();
        for (String s: dictionary) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    mp.put(chars, 1);
                }
                chars[i] = ch;
            }
        }
        List<String> ans = new ArrayList<>();
        for (String s : queries) {
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char ch = chars[i];
                int flag = 0;
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    if (mp.containsKey(chars)) {
                        flag = 1;
                        break;
                    };
                }
                chars[i] = ch;
                if (flag == 1) {
                    ans.add(s);
                    break;
                }
            }
        }
        return ans;
    }

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums[i];
            a[i][1] = i;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) {
                    return 1;
                } else if (o1[0] == o2[0]) {
                    if (o1[1] < o2[1]) return -1;
                    else if (o1[1] == o2[1]) return 0;
                    else return 1;
                } else return -1;
            }
        } );
        PriorityQueue que = new PriorityQueue();
        Set<Integer>st = new HashSet<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int id = a[i][1];
            st.add(a[i][1]);
        }
        return new int[]{};
    }
    public static void main(String[] args) {

    }
}
