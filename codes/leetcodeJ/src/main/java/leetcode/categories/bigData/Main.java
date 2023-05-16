package leetcode.categories.bigData;

import javafx.util.Pair;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // 划分文件，得到划分好的文件
        Division div = new Division();
        HashMap<String, List<String>>files = div.divInM();

        // 为每个文件建立堆
        BigHeap bg = new BigHeap(files);
        // 在内存中建立每一个文件的heap
        HashMap<String, Queue> mp = new HashMap<>();
        for (String file : files.keySet()) {
            Queue que = bg.build(file);
            mp.put(file, que);
        }
        // 进行统计
        CountTopN cnt = new CountTopN(3);
        List<Pair<String, Integer>> ans = cnt.workInMemory(mp);
        for (Pair<String, Integer> p : ans) {
            System.out.println(p.getKey() + " " + p.getValue());
        }
    }
}
