package com.hjb.learn.string.longest_substring_without_repeating_characters.soft;

import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: LongestSubstringWithoutRepeatingCharacters
 * Description: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by haojingbin on 2021/8/12 8:28
 *
 * @author haojingbin
 */
public class LongestSubstringWithoutRepeatingCharacters {

    private static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> diffrent = new HashSet<>();
        // 右指针
        int right = -1;
        int max = 0;
        // 左指针
        for (int left = 0; left < n; left++) {
            // 不重复的右指针不断右移，同时加到set中
            while (right + 1 < n && !diffrent.contains(s.charAt(right + 1))) {
                diffrent.add(s.charAt(right + 1));
                right++;
            }
            // 出现重复字符，此时可求得一个不重复字符的子串，和之前最大长度比较，求最大，注意此时重复的字符还没有加到set中
            max = Math.max(max, diffrent.size());
            // 左指针右移一位，因为此时left位置到right位置不重复，那么left+1位置到right位置肯定也不会重复
            // 如果left位置是重复字符，那么移除后新一轮就变成了不重复字符加到了set中，如果left位置不重复，那新一轮还是重复字符，left继续右移一位
            diffrent.remove(s.charAt(left));
        }
        return max;
    }


    public static void main(String[] args) {
        String test = "abcabcbb";
        System.out.println("the longest substring without repeating characters of \"" + test + "\" is " + lengthOfLongestSubstring(test));
    }
}
