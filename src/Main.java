import deadlock.DeadLockDemo;
import quicksort.QuickSort;
import segmenttree.ArraySegmentTree;

/**
 * @author: XuQihang
 * @date: 2025/2/26 13:27
 * @description:
 */
public class Main {
    public static void main(String[] args) {
         int[] arr = {1, 3, 5, 7, 9};
        // 示例，创建一棵求和线段树
        ArraySegmentTree st = new ArraySegmentTree(arr, (a, b) -> a + b);

        System.out.println(st.query(1, 3)); // 3 + 5 + 7 = 15
        st.update(2, 10);
        System.out.println(st.query(1, 3)); // 3 + 10 + 7 = 20
    }
}
