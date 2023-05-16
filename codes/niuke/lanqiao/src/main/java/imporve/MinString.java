package lanqiao.imporve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 15:26
 * @Description:
 * 算法提高组第一题，最小字符串
 * 考察了重写比较器的方法。
 * 1. 使用策略模式传入接口
 * 2. 自定义类型，继承Comprable接口，从写compare方法
 *
 * String类型中存在toCompare对象方法按照字典序比较大小
 */
public class MinString {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        while (T-- != 0) {
            int n = Integer.parseInt(bf.readLine());
            PriorityQueue<String>que = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return (o1 + o2).compareTo(o2 + o1);
                }
            });
            for (int i = 0; i < n; i++) {
//                char ch = bf.readLine().charAt(0);
//                System.out.println(ch);
                que.add(bf.readLine());
            }
            while(!que.isEmpty()) {
                System.out.print(que.poll());
            }
            System.out.println();
        }
    }
}
