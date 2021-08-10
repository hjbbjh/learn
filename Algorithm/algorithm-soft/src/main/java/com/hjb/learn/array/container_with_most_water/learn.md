#题目

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

**说明**：你不能倾斜容器。

#题解
##双指针法
###思路
假设有数组height[n]，左边界left初始为：0，右边界初始为：数组长度-1，最大面积maxArea初始为：0，假想每个数组的值是一个个竖起的板子  
1. 计算当前面积并求此时最大面积  
maxArea = max(maxArea,min(height[left],height[right])*(right-left))  
假想两块竖起的板子，中间能存的水的高度为最短的板子：min(height[left],height[right]，长度为左右指针之间的距离：right-left
2. 移动左右指针（向中间移动较矮的板子）  
为什么移动较高的板子不行？  
假设left处是较矮的板子，需要遵循的原则：左右板子之间能存的水的最大高度是较矮的板子的高度。那么我们来看下right处的板子向左移动会不会存更多的水：  
    - right-1处的板子高度比left处高或者相等   
    此时的最大存水高度还是height[left]，宽度则少了1：right-1-left，此时高度不变，宽度减少，因此存的水也减少了  
    - right-1处的板子高度比left处要矮  
    此时最大存水高度变成了height[right]，宽度同样减少了1：right-1-left，此时高度变矮，宽度减少，因此存水更少了  
由此可见，不管怎么移动较高的板子，所带来的存水量肯定是减少的，反之证明只能移动较矮的板子。  
为什么移动较矮的板子可行？  
    - left+1处的板子比left处要矮  
    此时能存水的高度以及宽度都减少了，因此存水量肯定减少
    - left+1处的板子比left处要高
    此时存水的高度可能比之前低也可能比之前高,是具有一定的不确定性的，正是由于此处的不确定所以才移动较矮的板子找寻最大存水面积  
#代码  
```java
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
```