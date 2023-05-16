package lanqiao.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/4 10:14
 * @Description:
 * http://lx.lanqiao.cn/problem.page?gpid=T47
 * 判断回文数
 */

public class HuiwenJudge {

    public static boolean check(int x) {

        List<Integer> nums = new ArrayList<>();
        int n = x;
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
//        System.out.println(Arrays.toString(nums.toArray()));
        for (int i = 0, j = nums.size() - 1; i < j; i++, j--) {
            if (nums.get(i) != nums.get(j)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        for (int i = 1000; i <= 9999; i++) {
            if (check(i)) System.out.println(i);
        }
    }
}
