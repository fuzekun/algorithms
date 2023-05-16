package leetcode.categories.base.sortAlgorithm;


import java.util.Random;

// 寻找第k大数字
public class FindK {

    Random random = new Random();
    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void random_Partition(int[] nums, int l, int r) {
        //[l, r] 长度最长为 r - l + 1, 得到的random[0, r - 1]所以加上l
        int p = random.nextInt(r - l + 1) + l;
        swap(p, l, nums);
    }
    private void findk(int[] nums, int l, int r, int k) {
        if (l >= r) return ;
        random_Partition(nums, l, r);
        int a = nums[l];
        int i = l, j = r;
        while(i < j) {
            while(i < j && nums[j] >= a) j--;
            while(i < j && nums[i] <= a) i++;
            swap(i, j, nums);
        }
        swap(i, l, nums);
        if (i == k) return ;
        else if (i > k) findk(nums, l, i, k);
        else findk(nums, i + 1, r, k);
    }

    public static void main(String[] args) {

    }
}
