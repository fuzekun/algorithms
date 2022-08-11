package leetcode.categories.bigData;


import javafx.util.Pair;

import java.util.*;

/*
*
*   把拍好序的进行统计
* 1. 每条的第一个放入内存中，并删除。
* 2. 使用Pair，保存数据，key是文件的来源，value是文件的第一条数据。。
* 3. 使用value的value进行大根堆的创建。
* 4, 创建完成，去除堆顶
* 5.
*
* 构造函数传入临时文件的名字列表。
* work,返回热门的url100的列表。
* */
public class CountTopN {
    List<String> inFiles;
    Queue<Pair<String, Pair<String, Integer>>>que;
    HashMap<String, Queue>mp;
    private int n;  // 前几名

    // 建立初始大根堆
    private void buildHeapInMemory(){
        que = new PriorityQueue<>(new Comparator<Pair<String, Pair<String, Integer>>>() {
            @Override
            public int compare(Pair<String, Pair<String, Integer>> o1, Pair<String, Pair<String, Integer>> o2) {
                return o2.getValue().getValue().compareTo(o1.getValue().getValue());
            }
        });
        for (Map.Entry entry : mp.entrySet()) {
            Queue file = (Queue)entry.getValue(); // 获取文件的堆
            if (file.isEmpty()) continue; // 文件不空
            // 获取第一条内容
            Pair<String, Integer>p = (Pair<String, Integer>)file.poll();
            que.add(new Pair<>((String)entry.getKey(), p)); // 加入到que中
        }
    }



    private List<Pair<String, Integer>> countInMemory() {
        /*
        *
        *   1.堆顶出堆，加入答案
        *   2.如果对应文件的不空的时候，读取一条内容，重新入堆
        *
        * */
        List<Pair<String, Integer>>ans = new ArrayList<>();
        int cnt = 0;
        while(cnt < n && !que.isEmpty()) { // 文件不空时候
            Pair<String, Pair<String, Integer>>top = que.poll();
            ans.add(top.getValue());
            cnt++;
            Queue file = mp.get(top.getKey()); // 文件内容
            if (file.isEmpty()) continue;     //  文件不空
            // 取出第一条内容
            Pair<String, Integer>p = (Pair<String, Integer>)file.poll();
            que.add(new Pair<>(top.getKey(), p));   // 加入que中
        }
        return ans;
    }

    // 内存中进行工作的模式，输入建立好的hashMap
    public List workInMemory(HashMap mp) {
        this.mp = mp;
        buildHeapInMemory();
        List ans = countInMemory();
        return ans;
    }


    // 在文件中进行工作的模式，临时文件的名称
    public void workInFile(String name) {

    }

    CountTopN(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        // 划分好的文件，取名称
        List<String> files = new ArrayList<>();
        files.add("1.txt");
        files.add("2.txt");

        BigHeap bg = new BigHeap();
        // 在内存中建立每一个文件的heap
        HashMap<String, Queue> mymp = new HashMap<>();
        for (String file : files) {
            Queue que = bg.build(file);
            mymp.put(file, que);
        }

        CountTopN ct = new CountTopN(2);
        List<Pair<String, Integer>> ans = ct.workInMemory(mymp);

        for (Pair<String, Integer>p : ans) {
            System.out.println(p.getKey() + " " + p.getValue());
        }
    }
}
