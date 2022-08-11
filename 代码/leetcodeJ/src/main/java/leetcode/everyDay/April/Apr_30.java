package leetcode.everyDay.April;

public class Apr_30 {
    public int smallestRangeI(int[] nums, int k) {
        /**

         为了最小化
         最小值需要最大
         如果最小值加上大于最大值
         还可以减去最大值

         10 - 2 = 8
         8 - 50 = -42
         10 - 50 = -40
         2 + 40 = 42

         每一个都可以改变，所以最大值可以减少，最小值可以增大，就是2 * k。
         如果小于0，直接等于0就行了。
         */

        int maxv = 0, minv = Integer.MAX_VALUE;
        for (int x: nums) {
            maxv = Math.max(maxv, x);
            minv = Math.min(minv, x);
        }
        // System.out.println("maxv = " + maxv + " minv = " + minv);
        int ans = maxv - minv - k * 2;
        return ans < 0 ? 0 : ans;
    }

    public static void main(String[] args) {
        int []nums = {1,2,3};
        int ans = new Apr_30().smallestRangeI(nums,2);
        System.out.println("ans = " + ans);
    }
}
