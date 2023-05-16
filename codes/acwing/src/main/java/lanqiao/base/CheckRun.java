package lanqiao.base;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 14:29
 * @Description: 检测是否是闰年
 */
public class CheckRun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0) System.out.println("yes");
        else System.out.println("no");
    }
}
