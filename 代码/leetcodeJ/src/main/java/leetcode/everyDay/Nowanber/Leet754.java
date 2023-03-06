package leetcode.everyDay.Nowanber;

import leetcode.everyDay.October.Leet927;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: Zekun Fu
 * @date: 2022/11/4 10:28
 * @Description: 不仅需要记录最小移动次数，需要记录到他的每一个次数。
 * -1 ：1， 3
 *
 * 0 1 3 6 10
 * 0 -1 -3 -6 -10
 * 0 1 -1 2 -2 3 -3 4 -4
 * 0 -1 1 -2 2 -3 2
 * 0 1 3 6 2 7
 * 0 1 3 0 4
 * 0 1 3 6 10 5
 * 0 -1 1 4
 *
 * 0
 * -1, 1 -> 1
 *
 *
 * 往前走一步，需要两步。
 * 1. 首先找到距离他最近并且大于等于他的
 * 2. 如果等于直接就返回结果，如果大于，
 *
 * 满足对称性：-1 和 1的次数相同，-2和2的次数相同
 * 如果恰好等于就是等于
 * 如果不恰好等于，向前走一步，需要退一步，然后进一步。
 *-1 1
 *
 * 首先转化成数字求和\sum_i^k == tareget，如果距离为偶数
 * 1 + 2 + 3 + 4 ==
 * 也就是每取一个负号，就会减少2 * x,可以减少2， 4， 6...
 * 1. 如果恰好走到,直接返回答案
 * 2. 如果越过的终点为偶数，直接取负号就行了
 * 3. 如果越过为奇数，首先多走一步，如果变成了偶数，就可以直接变成上一步的情况了
 * 4. 如果变成了奇数，多走一步仍旧是奇数，奇数 + 偶数 = 奇数，上一步走的是偶数，那么下一步一定走奇数，奇数 + 奇数 = 偶数。
 * 所以多走两步一定是偶数，就变成了上面的情况二了。
 */
public class Leet754 {
    public int reachNumber(int target) {
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);      // 保存最小的移动次数 + 1
        while (!que.isEmpty()) {
            int x = que.poll();
            int step = mp.get(x);
            if (x == target) return step - 1;
            int nx = x + step;
            // int nx2 = x - mp.get(x);
            if (!mp.containsKey(nx)) {
                que.add(nx);
                que.add(-nx);
                mp.put(nx, step + 1);
                mp.put(-nx, step + 1);
            }
            nx = x - step;
            if (!mp.containsKey(nx)) {
                que.add(nx);
                que.add(-nx);
                mp.put(nx, step + 1);
                mp.put(-nx, step + 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Leet754 l = new Leet754();
        System.out.println(l.reachNumber(2));
    }
}
