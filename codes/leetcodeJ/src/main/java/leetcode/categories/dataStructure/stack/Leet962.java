package leetcode.categories.dataStructure.stack;

import leetcode.utils.ChangeToArrayOrList;

import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 16:04
 * @Description: 最大宽度坡
 * 直接单调栈。找小于它的最左边的一个
 */
public class Leet962 {

    public int maxWidthRamp(int[] nums) {
        Stack<Integer>stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            // 单调栈经典模型2，先构造
            if (stack.empty() || nums[stack.peek()] > nums[i]) {
                // 等于的时候，左边的会更小，没必要放进去了
                stack.add(i);
            }
        }
        int ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i]) {
                ans = Math.max(ans, i - stack.pop());
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = ChangeToArrayOrList.changTo1DIntArray("[6,0,8,2,1,5]");
        Leet962 tmp = new Leet962();
        int ans = tmp.maxWidthRamp(nums);
        System.out.println(ans);
    }
}
