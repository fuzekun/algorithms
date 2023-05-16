package contest.liangzi;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/4/5 10:19
 * @Description:
 * 遇事不决，量子力学。
 * 写个随机数判断应该选择什么
 */
public class Depend {

    // 今天需要重点学习什么。
    // 结合自己的兴趣90% + 结合实际情况100% + 结合上天注定
    // 如果有兴趣，兴趣优先，如果不是特别感兴趣，结合实际情况
    // 如果两者都没有直接量子力学
    private static String[] learn = new String[]{"mysql", "redis", "spring",
            "rabbimq", "jvm", "多线程", "操作系统", "网络"};
    private static int maxn = (int)1e5;
    public static void main(String[] args) {
        int[] nums = new int[learn.length];
        for (int i = 0; i < maxn; i++) {
            int id = (int)(Math.random() * learn.length);
            nums[id]++;
        }
        int max_id = Arrays.stream(nums).max().orElse(-1);
        if (max_id == -1) throw new IllegalArgumentException("出错了!");
        System.out.println("接下来应该学习" + learn[(int)(Math.random() * learn.length)]);
    }
}
