package offer;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: Zekun Fu
 * @date: 2023/4/17 19:27
 * @Description:
 */
public class Second {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[]ss = sc.nextLine().split(",");
        int maxv = Integer.MIN_VALUE, sec = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (String s : ss) {
            int x = Integer.valueOf(s);
            if (set.contains(x)) continue;
            set.add(x);
            if (maxv <= x) {
                sec = maxv;
                maxv = x;
            }
            else if (x > sec) {
                sec = x;
            }
        }
        System.out.println((long)maxv * sec);
    }
}
