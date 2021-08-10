package com.hjb.learn.array.sum3.soft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: Sum3
 * Description:给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by haojingbin on 2021/8/4 8:59
 *
 * @author haojingbin
 */
public class Sum3 {

    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == nums || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int left = 0;
        while (left < nums.length && nums[left] <= 0) {
            int right = nums.length - 1;
            int middle = left + 1;
            while (middle < right) {
                int sum = nums[left] + nums[middle] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    middle++;
                } else {
                    List<Integer> target = new ArrayList<>();
                    target.add(nums[left]);
                    target.add(nums[middle]);
                    target.add(nums[right]);
                    result.add(target);

                    while (middle < right && nums[middle] == nums[middle + 1]) {
                        middle++;
                    }
                    middle++;
                    while (middle < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                }
            }
            while (left < nums.length - 2 && nums[left] == nums[left + 1]) {
                left++;
            }
            if (left >= nums.length - 2) {
                break;
            }
            left++;
        }
        return result;
    }

    /**
     * 官方解法
     * create by: haojingbin
     * create time: 21-08-10 09:02:03
     *
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 优化官方解法，a如果大于0就没必要再进行下去了
            if (nums[first] > 0) {
                break;
            }
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] test = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println("sum 3 is: " + threeSum1(test));
    }
}
