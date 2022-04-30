package leetcode.contest.April;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/*
*
*月的但周赛，第一场
*
* */
public class CApr1_1 {


    static String preHadle(String s) {
        return s + "!";
    }
    static String test1() {
        String paragraph = "Bob hit a ball, the hit BALL! flew far after it was hit.";
        String[] banned = {"hit"};
        Map<String, Integer> mp = new HashMap<>();
        for (String s : banned) {
            s = s.toLowerCase();        // 转换成小写字母
            mp.put(s, -1);
        }
        char []chs = {',', '?', '!', '.', ';', '\'', ' '};

        paragraph = preHadle(paragraph);
        char [] chars = paragraph.toCharArray();

        int maxv = 0;
        String ans = "";
        StringBuilder tmp = new StringBuilder();
        for (char ch : chars) {
            int flag = 0;
            for (char character : chs) {
                if (character == ch) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {        // 标点符号
                String ts = tmp.toString();
                if (ts.equals("")) continue;        // 注意是否是空字符串
                tmp .setLength(0);  // 清空
                int cnt = mp.containsKey(ts) ? mp.get(ts) : 0;
                System.out.println("s = " + ts + " cnt = " + (cnt + 1));
                if (cnt == -1) continue;
                mp.put(ts, cnt + 1);
                if (cnt + 1 > maxv) {
                    maxv = cnt + 1;
                    ans = ts;
                }
            } else {            // 字符
                tmp.append(Character.toLowerCase(ch)); // 转小写
            }
        }


        return ans;
    }

    void test2() {
        String paragraph = "Bob hit a ball, the hit BALL! flew far after it was hit.";
        String[] banned = {"hit"};
        String []chs = {",", "?", "!", ".", ";", "\'", " "};
        String[] words = paragraph.split(" ");
        Map<String, Integer>mp = new HashMap<>();

        for (String s : banned) {
            s = s.toLowerCase();        // 转换成小写字母
            mp.put(s, -1);
        }
        String ans = "";
        int maxv = 0;
        for (String s: words) {         // 把字符船里掉
            for (String ch : chs)
                if (s.endsWith(ch)) {
                    s = s.substring(0, s.length() - 1);
                    break;
                }
            s = s.toLowerCase();        // 都转换成小写字符
            int cnt = mp.containsKey(s) ? mp.get(s) : 0;
            System.out.println("s = " + s + " cnt = " + (cnt + 1));
            if (cnt == -1) continue;
            mp.put(s, cnt + 1);
            if (cnt + 1 > maxv) {
                ans = s;
                maxv = cnt + 1;
            }
        }
        System.out.println("ans = " + ans);
    }
    public static void main(String[] args) {
       String ans = test1();
        System.out.println("ans = " + ans);
    }
}
