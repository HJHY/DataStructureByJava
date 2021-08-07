package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 20:07
 */
public class SimpleSelectSort {
    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        /*循环n-1次,每一次找出最小的放在前面*/
        int index;
        int min;
        //循环n-1次
        for (int i = 0; i < array.length - 1; ++i) {
            index = i;
            min = array[i];
            //挑出最小值
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < min) {
                    index = j;
                    min = array[j];
                }
            }
            //交换
            if (index != i) {
                int tmp = array[i];
                array[i] = array[index];
                array[index] = tmp;
            }
        }
    }


    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        SimpleSelectSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
