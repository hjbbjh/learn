#题目
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。  

示例 1:  

输入: s = "abcabcbb"  
输出: 3  
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。  
示例 2:  

输入: s = "bbbbb"  
输出: 1  
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。  
示例 3:  

输入: s = "pwwkew"  
输出: 3  
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。  
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。  
示例 4:  

输入: s = ""  
输出: 0  

#题解
##滑动窗口
###思路
所谓滑动窗口，即为两个左右指针，通过左右指针的单向滑动，来遍历元素，最终得到结果。  
1. 首先明确，需要遍历字符串，求子串（不能剔除元素），那么怎么遍历所有子串呢？  
```java
        int n = s.length();
        for (int left = 0; left < n; left++) {
            for (int right = 0; right < n; right++) {
                
            }
        }
```  
这是暴力解法，不是滑动窗口，那么怎么能简化暴力解到滑动窗口呢？
2. [left，right]范围内的滑动窗口，保存不重复的字符，如果字符不重复，right不断右移，直到遇到重复字符，left右移一位，right不动，left右移后，再次进行字符重复性判断和right的移动
3. left左指针遍历规则不变，left指针每次右移的时候，right指针不回缩到left+1位置，因为没有必要，为什么呢？  
    - [left,right]范围内是不重复子串，那么[left+1，right]范围肯定也是不重复的，right也就没有必要回缩，继续在原来位置进行新一轮的重复性判断和移动就可以了
4. 遇到重复字符，left右移后，如果left新位置字符和right处字符重复，则left继续右移，如果不重复，那么right处原本重复的字符变成了不重复，被添加到窗口中了，这样right就没有回缩，而是不断的往右移动
#代码
```java
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
```

[github](https://github.com/hjbbjh/learn/tree/main/Algorithm/algorithm-soft/src/main/java/com/hjb/learn/string/longest_substring_without_repeating_characters)
