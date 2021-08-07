package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 9:54
 */
public class StraightInsertionSort {
    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        /*思路,第一个元素不排序,后面n-1个元素一个一个插入到正确的序列中,使得每一次插入后序列都是有序的*/
        int tmp;
        int index;
        //从第二个元素,也就是下标为1的元素开始循环
        for (int i = 1; i < array.length; i++) {
            tmp = array[i];
            //寻找插入位置
            for (index = i - 1; index >= 0 && array[index] > tmp; --index) {
                //所有大的元素后移
                array[index + 1] = array[index];
            }
            //将元素插入到合适位置
            array[index + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        StraightInsertionSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
