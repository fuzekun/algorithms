package leetcode.everyDay.sanyue;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/3/21 11:45
 * @Description:
 */
public class Leet1487 {

    public String[] getFolderNames(String[] names) {
        HashMap<String, Integer> mp = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (String name : names) {
            // 不包含直接放入
            if (!mp.containsKey(name)) {
                mp.put(name, 1);
                ans.add(name);
                continue;
            }

            // 包含，直接第k个就是没有的
            String tmp = name;
            int k = mp.get(tmp);
            while (mp.containsKey(tmp)) {
                tmp = (name + '(' + k + ')');
                k++;
            }
            mp.put(tmp, 1);                 // 最新的为1
            mp.put(name, k);            // 更新下标
            ans.add(tmp);
        }
        return ans.toArray(new String[ans.size()]);
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"gta","gta(1)","gta(1)","avalon"};
        System.out.println(Arrays.toString(new Leet1487().getFolderNames(ss)));
    }
}
