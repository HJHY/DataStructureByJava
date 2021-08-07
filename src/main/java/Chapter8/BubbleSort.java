package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 15:06
 */
public class BubbleSort {
    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        /*每次消除一个逆序对思想*/
        int tmp;
        //标记是否发生交换,如果从前往后都没有交换则证明后面已经是升序的,无需再次排列
        boolean flag;
        for (int i = 0; i < array.length - 1; i++) {
            flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                //前面大于后面则交换
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        BubbleSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
