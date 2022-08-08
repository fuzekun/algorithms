package leetcode.categories.graph;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/8/6 16:00
 * @Description: leetcode的127题
 *
 * 思考几个问题：
 * 1. 为什么放在里面不行？
 * 2. 为什么一定要把beginWord添加到里面？防止建立dist的时候出现问题。如果使用vis进行判断那么就不会出现这种问题。
 * 3. 再进行hashmap访问的时候，一定首先判断的是是否含有key，不含有不可访问。
 */
public class leet127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int n = wordList.size();
        int inf = 0x3f3f3f3f;
        HashSet<String> set = new HashSet<>();
        for (String ss : wordList) set.add(ss);
        if (!wordList.contains(endWord)) return 0;


        Queue<String> que = new ArrayDeque<>();
        HashMap<String, Integer> dist = new HashMap<>();
        for (String s : wordList) dist.put(s, inf);
        dist.put(beginWord, 1);

        que.add(beginWord);
        while (!que.isEmpty()) {
            String u = que.poll();
            if (u.equals(endWord)) {
                return dist.get(u);
            }
            for (int i = 0; i < u.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == u.charAt(i)) continue;
                    String tmp = u.substring(0, i) + ch + u.substring(i + 1);
                    if (dist.containsKey(tmp) && dist.get(tmp) == inf) {
                        que.add(tmp);
                        dist.put(tmp, dist.get(u) + 1);
                    }
                }
            }
        }
        return 0;
    }
    int inf = 0x3f3f3f3f;
    public boolean check(String u, Map<String, Integer>dist1, Map<String, Integer>dist2, Queue<String>que) {
        if (dist2.containsKey(u) && dist2.get(u) != inf) {
            return true;
        }
        for (int i = 0; i < u.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == u.charAt(i)) continue;
                String tmp = u.substring(0, i) + ch + u.substring(i + 1);
                if (dist1.containsKey(tmp) && dist1.get(tmp) == inf) {
                    que.add(tmp);
                    dist1.put(tmp, dist1.get(u) + 1);
                }
            }
        }
        return false;
    }
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        int n = wordList.size();

        HashSet<String> set = new HashSet<>();
        for (String ss : wordList) set.add(ss);
        if (!wordList.contains(endWord)) return 0;


        Queue<String> que1 = new ArrayDeque<>();
        Queue<String> que2 = new ArrayDeque<>();
        HashMap<String, Integer> dist1 = new HashMap<>();
        HashMap<String, Integer> dist2 = new HashMap<>();
        for (String s : wordList) {
            dist1.put(s, inf);
            dist2.put(s, inf);
        };
        dist1.put(beginWord, 1);
        dist2.put(endWord, 1);
        que1.add(beginWord);
        que2.add((endWord));
        while (!que1.isEmpty() && !que2.isEmpty()) {
            String u1 = que1.poll();
            String u2 = que2.poll();
            if (check(u1, dist1, dist2, que1)) {
                return dist1.get(u1) + dist2.get(u1) - 1;
            }
            if (check(u2, dist2, dist1, que2)) {
                return dist1.get(u1) + dist2.get(u1) - 1;
            }
        }
        return 0;
    }

}
