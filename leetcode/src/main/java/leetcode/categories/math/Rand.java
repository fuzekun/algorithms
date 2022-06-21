package leetcode.categories.math;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/6/9 17:16
 * @Description:
 * 随机数，蓄水池抽样算法
 */
public class Rand {

    LinkedList<Integer>backup = new LinkedList<>();
    LinkedList<Integer>list = new LinkedList<>();
    int n, m;
    int total;
    Random random = new Random();
    public Rand(int m, int n) {
        this.m = m;
        this.n = n;
        total = n * m;
        for (int i = 0; i < total; i++) {
            list.add(i);
            backup.add(i);
        }
    }

    public int[] flip() {
        int k = random.nextInt(--total);
        int p = list.remove(k);
        return new int[]{p / n, p % n};
    }

    public void reset() {
        list = (LinkedList<Integer>) backup.clone();
    }
}
