package leetcode.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 12:29
 * @Description:
 */

public class ReadDataTest {

    @Test
    public void testGetArray() throws Exception{
        System.out.println(Arrays.toString(ReadData.getArray()));
    }

    @Test
    public void testGet2Darray() throws Exception{
        PrintArrays.print2DIntArray(ReadData.get2DIntArray());
    }
    @Test
    public void testGet2DStringarray() throws Exception{
        PrintArrays.print2DObjArray(ReadData.get2DStringArray());
    }

    @Test
    public void testGet1DStringArray() throws Exception {
        PrintArrays.print1DObjArray(ReadData.get1DStringArray());
    }

}
