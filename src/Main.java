import deadlock.DeadLockDemo;
import quicksort.QuickSort;

/**
 * @author: XuQihang
 * @date: 2025/2/26 13:27
 * @description:
 */
public class Main {
    public static void main(String[] args) {
//        DeadLockDemo deadLockDemo = new DeadLockDemo();
//        Thread t1 = deadLockDemo.getT1();
//        Thread t2 = deadLockDemo.getT2();
//        t1.start();
//        t2.start();
        int[] nums = new int[]{34,2,4,56,31,9221,1,0};
        QuickSort.quickSort(nums, 0, nums.length - 1);
        for(int i:nums){
            System.out.println(i);
        }
    }
}
