package quicksort;

/**
 * @author: XuQihang
 * @date: 2025/3/12 20:28
 * @description:
 */
public class QuickSort {
    public static void quickSort(int[] arr,int left,int right) {
        if(left >= right) {
            return;
        }
        int p = partition(arr,left,right);
        quickSort(arr,left,p-1);
        quickSort(arr,p+1,right);
    }
    public static int partition(int[] arr,int left,int right) {
        int pivot = arr[left];
        int i = left+1,j = right;
        while(i<=j) {
            while(i<arr.length&&arr[i]<=pivot) {
                i++;
            }
            while(j>=0&&arr[j]>pivot) {
                j--;
            }
            if(i>=j) break;
            swap(arr,i,j);
        }
        swap(arr,left,j);
        return j;
    }
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
