package leetcode.everyDay.October;

import com.beust.jcommander.Parameter;
import leetcode.utils.ReadData;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 14:18
 * @Description:
 * 升序子数组最大和
 */
public class leet1800 {

    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int ans = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(new leet1800().maxAscendingSum(ReadData.getArray()));
    }
}
