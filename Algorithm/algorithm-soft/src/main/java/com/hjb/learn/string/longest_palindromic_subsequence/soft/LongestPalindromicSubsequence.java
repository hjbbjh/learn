package com.hjb.learn.string.longest_palindromic_subsequence.soft;

/**
 * ClassName: LongestPalindromicSubsequence
 * Description: 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by haojingbin on 2021/8/12 8:27
 *
 * @author haojingbin
 */
public class LongestPalindromicSubsequence {

    private static int longestPalindromeSubseq(String s) {
        int n = s.length();
        // 要点1：初始化为0（0<=i<j<n时subLen[i][j] > 0，否则等于0
        int[][] subLen = new int[n][n];
        // 要点2：从右边界开始遍历，动态规划的状态转移是从短子串向长子串转移
        for (int i = n - 1; i >= 0; i--) {
            // 要点3：一个字符的子串的回文长度是1，动态规划的边界初始值
            subLen[i][i] = 1;
            char charAti = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                if (charAti == s.charAt(j)) {
                    // 要点4：如果i和j位置字符相同，那不管其中间区域最长回文子串长度是几，假设为m，[i,j]范围最长回文子串长度为m+2
                    subLen[i][j] = subLen[i + 1][j - 1] + 2;
                } else {
                    // 要点5：如果i和j位置字符不相同，[i,j]范围的最长子串枚举为：[i+1,j]、[i,j-1],[i,j]范围最长回文子串长度为max([i+1,j]最长回文,[i,j-1]最长回文)
                    subLen[i][j] = Math.max(subLen[i + 1][j], subLen[i][j - 1]);
                }
            }
        }
        // 要点6：最终的结果是[0,n-1]范围的最长回文子串长度，也就是字符串s的最长回文子串长度，值为subLen[0][n - 1]的值
        return subLen[0][n - 1];
    }

    public static void main(String[] args) {
        String test = "bbbab";
        System.out.println("longest palindromic subsequence of \"" + test + "\" is: " + longestPalindromeSubseq(test));
    }
}
