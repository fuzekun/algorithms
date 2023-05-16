package leetcode.categories.bigData;


import java.util.*;

public class BigHeap {
    /*
        1. 读取文件中的内容
    *   2. 使用hashMap统计词频
    *   3. 按照词频进行堆的建立
    *   4. 将建立好的堆输出到临时文件中。

        构造函数是对应的输入和输出文件名。
    *
    * */

    class Pair<T, K> {
        T key;
        K value;

        public T getKey() {
            return key;
        }

        public K getValue() {
            return value;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public void setValue(K value) {
            this.value = value;
        }
        public Pair(T key, K value) {
            this.key = key;
            this.value = value;
        }
    }
    private List<String> list;
    private Map<String, Integer>mp;
    private Queue<Pair<String, Integer>>que;
    private HashMap<String, List<String>>files;


    // 从“内存文件中读取”
    private void read(String inFile) {
        this.list = files.get(inFile);
    }
    private void write(String outFile) {
        while(!que.isEmpty()) {
            Pair p = que.poll();
            System.out.println(p.getKey() + " " + p.getValue());
        }
    }

    private void readInFile() {

    }
    private void writeToFile() {

    }

    private void buildHeap() { // 建立大根堆
        que = new PriorityQueue<>(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for (Map.Entry<String, Integer> s: mp.entrySet()) {
            que.add(new Pair<>(s.getKey(), s.getValue()));
        }
    }
    private void buildMap() {    // 统计点击频率
        mp = new HashMap<>();
        int n = list.size();
        for (String s:list) {
            if (mp.containsKey(s)) {
                mp.put(s, (Integer) mp.get(s) + 1);
            } else {
                mp.put(s, 1);
            }
        }
    }

    // 使用模板方法的设计模式, 工作在文件中
    public void work(String in, String out) {
        read(in);             // 读取
        buildMap();         // 统计
        buildHeap();        // 排序
        write(out);            // 写回
    }
    public Queue build(String in) {  // 或者输出建立好的堆，在内存足够大的情况下直接存储就行了。
        read(in);
        buildMap();
        buildHeap();
        return que;
    }


    // 使用内存文件进行读取，需要给它一个文件的地址
    BigHeap(HashMap<String, List<String>>files) {
        this.files = files;
    }
    BigHeap() {

    }


    public static void main(String[] args) {

       BigHeap bg =new BigHeap();
       bg.work("in", "out");
    }

}
