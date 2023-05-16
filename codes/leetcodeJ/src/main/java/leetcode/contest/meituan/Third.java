package leetcode.contest.meituan;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/18 10:45
 * @Description: 贪心
 *
 * 如果回文直接改第一个非a的,可以改一次
 * 如果两对不一样，取前后最小的那一个
 * 如果一对不一样，都改成a，还可以改中间的一个
 *
 *
 *
 */
public class Third {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.nextLine().toCharArray();
        int i = 0, j = chars.length - 1;
        Queue<int[]> que = new ArrayDeque<>();// 保存不一样的对
        while (i <= j) {
            if (chars[i] != chars[j]) {
                que.add(new int[]{i, j});
            }
            i++; j--;
        }
        i = 0;
        j = chars.length - 1;
        // 没有不一样的
        if (que.size() == 0) {
            while (i <= j) {
                if (chars[i] != 'a') {
                    chars[i] = chars[j] = 'a';
                    break;
                }
                i++; j--;
            }
        }
        // 一对
        else if (que.size() == 1) {
            int[] pos = que.poll();
            chars[pos[0]] = chars[pos[1]] = 'a';
            if (chars[pos[0]] == 'a' ||  chars[pos[1]] == 'a') {    // 一个
                if ((chars.length & 1) == 1) {
                    chars[chars.length / 2] = 'a';                      // 两个
                }
            }
        }
        // 两对 accbdd, acbbd
        else {
            while (!que.isEmpty()) {
                int[] pos = que.poll();
                int x = pos[0], y = pos[1];
                chars[pos[0]] = chars[pos[1]] = (char)Math.min(chars[pos[0]], chars[pos[1]]);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (char ch : chars) {
            ans.append(ch);
        }
        System.out.println(ans);
    }
}
