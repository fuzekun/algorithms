package leetcode.everyDay.May;


import javafx.util.Pair;
import leetcode.utils.PrintArrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
*
*   5.3号每日一题
* */
public class May_E_3 {
    public String[] reorderLogFiles(String[] logs) {
        List<Pair<String, String>> letters = new ArrayList<>();
        List<String> digs = new ArrayList<>();
        for (String s : logs) {
            int firstSpace = s.indexOf(" ");
            if (Character.isLetter(s.charAt(firstSpace + 1))) {
                System.out.println("s = " + s + " id = " + s.substring(0, firstSpace + 1) + "log = " + s.substring(firstSpace));
                letters.add(new Pair<>(s.substring(firstSpace), s.substring(0, firstSpace)));
            } else digs.add(s);
        }
        letters.sort((a, b)->{
            if (a.getKey().equals(b.getKey()))
                return a.getValue().compareTo(b.getValue());
            return a.getKey().compareTo(b.getKey());
        });
//        Collections.sort(digs);
        int m = letters.size(), n = digs.size();
        String [] ans = new String[m + n];
        for (int i = 0; i < m; i++) {
            Pair<String, String> p = letters.get(i);
            String s = p.getValue() + p.getKey();
            ans[i] = s;
        }
        for (int i = m; i < n + m; i++) {
            ans[i] = digs.get(i - m);
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] ss = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        String[] ans = new May_E_3().reorderLogFiles(ss);
        PrintArrays.print1DObjArray(ans);


    }
}
