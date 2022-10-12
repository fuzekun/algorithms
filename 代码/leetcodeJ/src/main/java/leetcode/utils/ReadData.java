package leetcode.utils;

import com.sun.istack.internal.Nullable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 11:24
 * @Description:
 */
public class ReadData {

    /*
    *
    *
    *   leetcode的输入基本上都是数组。
    * 不管是复杂还是简单的数据结构，大都可以使用数组来描述。
    * 比如图、树等等。
    * 所以如果想要快速读取数据，直接写好读取数组，就很方便。
    *
    * 需求2：怎么进行多行测试呢?
    *
    * */



//    @DataProvider(name = "1darray")
//    public static Object[] getArray() throws Exception {
//        int[] o= getArray("int1d");
//        Object[] ans = new Object[o.length];
//        for (int i = 0; i < ans.length; i++) {
//            ans[i] = o[i];
//        }
//        return ans;
////        return getArray("int1d");
//    }
    public static int[] getArray() throws Exception {
        return getArray("int1d");
    }
    public static int[][] get2DArray() throws Exception {
        return get2DArray("int2d");
    }
    public static String[][] get2DStringArray() throws Exception {
        return get2DStringArray("string2d");
    }
    public static String[] get1DStringArray() throws Exception {
        return get1DStringArray("string1d");
    }

    // 获取一维int类型的数组。
    // 如果想要获取String类型的数组呢？
    public static int[] getArray(String name) throws Exception{
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changTo1DIntArray(data);
    }

    public static int[][] get2DArray(String name) throws Exception {
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changeTo2DIntArray(data);
    }

    public static String[][] get2DStringArray(String name) throws Exception {
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changTo2DString(data);
    }

    public static String[] get1DStringArray( String name) throws Exception {
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changeTo1DString(data);
    }
}
