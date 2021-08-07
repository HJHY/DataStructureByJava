package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 10:10
 */
public class BinaryInsertionSort {
    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        /*思路与直接插入排序一样,只是搜索的方式从顺序搜索变成了二分搜索,这是因为前面的序列已经排序完成,适合使用二分搜索*/
        int tmp;
        int index;
        int low, high, mid;
        for (int i = 1; i < array.length; i++) {
            //保存当前值
            tmp = array[i];
            //二分搜索找循环位置
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                //要插入的值比当前的值小,往左搜索
                if (tmp < array[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            //记录后移;当二分搜索结束的时候,low>high,所以应该从已经排序的最后(i-1)到所找到的位置排序(low,使用high-1可能出现索引为-1的异常)
            for (index = i - 1; index >= low; --index) {
                array[index + 1] = array[index];
            }
            array[index + 1] = tmp;
        }
    }


    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        BinaryInsertionSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
