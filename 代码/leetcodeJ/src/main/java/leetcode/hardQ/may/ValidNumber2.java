package leetcode.hardQ.may;

import java.util.HashMap;
import java.util.Map;

/*
*
*   使用数字代替状态
* */
public class ValidNumber2 {


    private int getType(char ch) {
        if (ch == '+' || ch == '-') return 0;
        else if (Character.isDigit(ch)) return 1;
        else if (ch == 'e' || ch == 'E') return 2;
        else if (ch == '.') return 3;
        else return 4;
    }
    public boolean isNumber(String s) {
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<Integer, Map<Integer, Integer>>() {
            {
                put(0, new HashMap<Integer, Integer>() {
                    {
                        put(0, 1);
                        put(1, 2);
                        put(3, 8);
                    }
                });
                put(1, new HashMap<Integer, Integer>() {
                    {
                        put(1, 2);
                        put(3, 8);
                    }
                });
                put(2, new HashMap<Integer, Integer>() {
                    {
                        put(3, 3);
                        put(1, 2);
                        put(2, 5);
                    }
                });
                put(3, new HashMap<Integer, Integer>() {
                    {

                        put(2, 5);
                        put(1, 4);
                    }
                });
                put(4, new HashMap<Integer, Integer>() { // 小数数字
                    {
                        put(1,4);
                        put(2,5);
                    }
                });
                put(5, new HashMap<Integer, Integer>() {    // 字符e
                    {
                        put(1, 7);
                        put(0, 6);
                    }
                });
                put(6, new HashMap<Integer, Integer>() {
                    {
                        put(1, 7);
                    }
                });
                put(7, new HashMap<Integer, Integer>() {
                    {
                        put(1, 7);
                    }
                });
                put(8, new HashMap<Integer, Integer>() {
                    {
                        put(1, 4);
                    }
                });
            }
        };

        char[] chars = s.toCharArray();
        int state = 0;
        for (char ch : chars) {
            int type = getType(ch);
            if (!mp.get(state).containsKey(type)) return false;
            else state = mp.get(state).get(type);
        }
        return state == 2 || state == 3 || state == 4 || state == 7;
    }


}
