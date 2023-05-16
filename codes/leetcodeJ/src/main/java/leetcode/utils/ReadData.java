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
 *
 * 方便leetcode测试，直接将数据放到data/int1d中，然后调用getArray()就可以获取到数组了
 * 同理，2维和三维也一样。
 * 还有提供文件名的方法，如果需要两个一维数组，可以使用getArray()读取，然后使用getArray("int1d_")读取
 */
public class ReadData {

    public static int[] getArray() throws Exception {
        return getArray("int1d");
    }
    public static int[][] get2DIntArray() throws Exception {
        return get2DIntArray("int2d");
    }
    public static int[][][] get3DIntArray() throws Exception {
        return get3DIntArray("int3d");
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

    public static int[][] get2DIntArray(String name) throws Exception {
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changeTo2DIntArray(data);
    }
    public static int[][][]get3DIntArray(String name) throws Exception {
        String path = "data/" + name;
        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
        String data = bf.readLine();
        return ChangeToArrayOrList.changeTo3DIntArray(data);
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
