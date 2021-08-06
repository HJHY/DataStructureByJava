package Chapter7;

import java.util.Random;

/**
 * @author HJHY
 * @date 2021/8/6 - 9:36
 */
public class SequenceSearch {
    /**
     * 数组
     */
    public static int[] array;

    /**
     * 关键字
     */
    public static int key;

    public static final int SIZE = 100000000;

    /**
     * 初始化
     */
    public static void init() {
        array = new int[SIZE + 1];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE);
        }
        //关键字设置为下标为0的值
        key = array[0];
    }

    /**
     * 顺序搜索
     */
    public static int sequenceSearch(int[] array, int key) {
        long startTime = System.currentTimeMillis();
        int i;
        for (i = SIZE; array[i] != key; --i) {
        }
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间:" + (endTime - startTime) + "ms");
        return i;
    }

    /**
     * 常规的判断搜索
     */
    public static int sequenceSearchNormal(int[] array, int key) {
        long startTime = System.currentTimeMillis();
        int i;
        for (i = SIZE; array[i] != key; --i) {
            if (array[i] == key) {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("消耗时间:" + (endTime - startTime) + "ms");
        return i;
    }

    public static void main(String[] args) {
        /*测试用例*/
        //采取设置哨兵的方法,将数组下标为0的位置设置为key,然后从后往前搜索,这样在数组中一定可以找到,减少if判断
        //根据找到的下标判断,如果为0则证明没有找到
        init();
        System.out.println("下标:" + sequenceSearch(array, key));
        System.out.println("下标:" + sequenceSearchNormal(array, key));
    }
}