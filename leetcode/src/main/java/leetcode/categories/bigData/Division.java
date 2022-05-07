package leetcode.categories.bigData;


import java.lang.reflect.Field;
import java.util.*;

/*
*
*   进行文件的划分
*  1. 所有的url相同的应该划分到同一个文件中去
*  2. 使用字符串hash对一个数字取模做到这一点
*
*
*   需要保证同一个Url不超过1e7次，否则每一个排序会很慢
*
*   考虑到到亿级别的点击量很少，所以可以进行如上的划分。
*
*    最后需要得到划分好的文件列表。
* */
public class Division {

    private String fileName;
    //
    private List<String> read() {
        List<String>list = new ArrayList();
        for (int i = 0; i < 19; i++) {
            list.add("fuzekun");
        }
        for (int i = 0; i < 10; i++) {
            list.add("b");
        }
        for (int j = 0 ; j < 18; j++) {
            list.add("sha");
        }
        return list;
    }

    // 划分，然后返回文件的路径和名称。
    public List<String> div() {
        List<String>list = new ArrayList<>();
        return list;
    }
    // 通过hash函数进行过滤,最后返回“内存文件”
    public HashMap<String, List<String>> divInM() {
        int base = 257;
        int mod = 10;                // 就两个文件
        // 创建Mod个文件
        String[] files = new String[10];       // 建立好的文件名列表
        for (int i = 0; i  < 10; i++) {
            files[i] = "file" + i + ".txt";
        }
        List<String> list = read(); // 应该是一条一条从文件中读取
        HashMap<String, List<String>>mp = new HashMap<>();
        for (String s : list) {
            int hash = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i);
                hash = ((hash * base) % mod + c) % mod;
            }
            String outFile = files[hash];
            /*
            *
            *   这里应该直接写入到文件中，
            * 如果文件没有就创建一个文件。
            * 否则直接写入就行了。
            * */
            if (!mp.containsKey(outFile)) {
                List<String>m = new ArrayList<>();
                m.add(s);
                mp.put(outFile, m);
            } else {
                // 修改的是内部存储的对象，所以直接放进去就行了。
                mp.get(outFile).add(s);
            }
        }
        return mp;
    }



    public static void main(String[] args) {
        Division div = new Division();

        HashMap<String, List<String>>ans = div.divInM();
        for (Map.Entry<String, List<String>> entry : ans.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (String s : entry.getValue()) {
                System.out.println(s);
            }
            System.out.println();
        }
    }

}
