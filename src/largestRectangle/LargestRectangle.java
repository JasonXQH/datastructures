package largestRectangle;

import java.util.Stack;

/**
 * @author: XuQihang
 * @date: 2025/3/24 16:25
 * @description:  用到了单调栈的性质
 */
public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stk = new Stack<>();
        int maxArea = 0;
        int[] extendsArray = new int[heights.length+2];

        for(int i = 0;i<heights.length;i++){
            extendsArray[i+1] = heights[i];
        }
        for(int i = 0;i<extendsArray.length;i++){
            while(!stk.isEmpty()&&extendsArray[stk.peek()]<extendsArray[i]){
                int height = extendsArray[stk.pop()];
                int width = i-stk.peek()-1;
                maxArea = Math.max(maxArea,height*width);
            }
            stk.push(i);

        }
        return maxArea;
    }
}
