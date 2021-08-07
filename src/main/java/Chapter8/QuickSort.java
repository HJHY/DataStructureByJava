package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 15:48
 */
public class QuickSort {
    /**
     * 分区
     *
     * @param array 数组
     * @param low   低指针
     * @param high  高指针
     * @return 枢纽位置
     */
    public static int partition(int[] array, int low, int high) {
        //保存枢纽值
        int key = array[low];
        while (low < high) {
            //从后搜索,比枢纽值小,与覆盖low指针指向的位置;否则high指针前移
            while (low < high && key <= array[high]) {
                --high;
            }
            //覆盖数据
            array[low] = array[high];
            while (low < high && key >= array[low]) {
                ++low;
            }
            //覆盖数据
            array[high] = array[low];
        }
        //退出循环的时候low==high,将枢纽值写到对应位置,子表一分为二
        array[low] = key;
        return low;
    }

    /**
     * 对于每一个分区循环调用排序
     *
     * @param array 数组
     * @param low   低指针
     * @param high  高指针
     */
    public static void recurseSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int keyLocation = partition(array, low, high);
        recurseSort(array, low, keyLocation - 1);
        recurseSort(array, keyLocation + 1, high);
    }

    /**
     * 提供快排
     *
     * @param array 数组
     */
    public static void sort(int[] array) {
        recurseSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        QuickSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
