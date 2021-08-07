package Chapter8;

import java.util.Arrays;

/**
 * @author HJHY
 * @date 2021/8/7 - 20:49
 */
public class HeapSort {
    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        int[] newArray = new int[array.length + 1];
        newArray[0] = -1;
        System.arraycopy(array, 0, newArray, 1, newArray.length - 1);
        /*思路,再捋捋*/
        createMaxHeap(newArray);
        //
        int tmp;
        for (int i = newArray.length - 1; i > 1; --i) {
            //将最大值放到后面然后再调整当前大根堆
            tmp = newArray[i];
            newArray[i] = newArray[1];
            newArray[1] = tmp;
            //这里堆调整的终点是变化的
            heapAdjust(newArray, 1, i - 1);
        }
        //将新数组的数据写回
        System.arraycopy(newArray, 1, array, 0, array.length);
    }

    private static void createMaxHeap(int[] array) {
        //从最后一个有左孩子的节点开始调整
        int size = array.length;
        for (int i = size / 2; i > 0; --i) {
            heapAdjust(array, i, array.length - 1);
        }
    }

    private static void heapAdjust(int[] array, int start, int end) {
        array[0] = array[start];
        for (int i = 2 * start; i <= end; i *= 2) {
            //i指向孩子节点,开始i为左孩子,如果右孩子比左孩子大,i设置为右孩子(先要判断右孩子存在)
            if (i < end && array[i] < array[i + 1]) {
                ++i;
            }
            //如果父节点大于左右孩子的最大值则不处理
            if (array[0] >= array[i]) {
                break;
            } else {
                //交换节点后往下调整
                array[start] = array[i];
                start = i;
            }
        }
        //将原父节点值写回
        array[start] = array[0];
    }


    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("排序前:" + Arrays.toString(array));
        HeapSort.sort(array);
        System.out.println("排序后:" + Arrays.toString(array));
    }
}
