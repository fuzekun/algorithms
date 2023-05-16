package dataStructure.Map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Zekun Fu
 * @date: 2023/2/26 9:52
 * @Description:
 *
 * 测试
 * 1. hashmap不是有序的
 * 2. putIfAbsent(key, ++idx);如果没放入，idx会加吗?
 */
public class Test {
    public static void main(String[] args) {
        int[] a = {2, 2, 2};
        int idx = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int x : a) {
            mp.putIfAbsent(x, ++idx);
        }
        // 就算不放入,idx也会加
        System.out.println(idx);
        // 离散化的代码应该这么写
        Map<Integer, Integer>mp2 = new HashMap<>();
        Map<Integer, Integer>height = new HashMap<>();
        idx = 0;
        for (int x : a) {
            if (mp2.containsKey(x)) continue;
            mp2.put(x, ++idx);
            height.put(idx, x);
        }
        System.out.println(height.get(mp.get(2)));
        System.out.println(idx);
    }
}
