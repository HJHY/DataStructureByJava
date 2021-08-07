package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 10:55
 */
public class ShellSort {
    public static void sort(int[] array, int size) {
        if (array == null) {
            return;
        }
        /*希尔排序就是采用逼近的方式划分子表进行排序,其过程就是对各个子表进行直接掺入排序*/
        for (int space = size / 2; space >= 1; --space) {
            //找到第一个子表的第二个元素开始处理(第一个元素看作已经排好序的最小子表)
            int tmp;
            int j;
            for (int i = space; i < size; i++) {
                //当前要插入的值比所在子表的最大值小,则要移动
                if (array[i] < array[i - space]) {
                    //保存要插入的值
                    tmp = array[i];
                    //从已经排完序的子表中开始后移
                    for (j = i - space; j >= 0 && tmp < array[j]; j -= space) {
                        //已排序子表中的元素后移
                        array[j + space] = array[j];
                    }
                    //将值插入到位置中
                    array[j + space] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        ShellSort.sort(array, array.length);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
