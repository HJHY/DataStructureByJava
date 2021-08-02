package Chapter2;

import java.util.Arrays;

/**
 * 顺序表的简单实现
 * @author HJHY
 * @date 2021/8/2 - 10:27
 */
public class SequenceList {
    /**
     * 数组最大长度
     */
    private int maxLength;
    /**
     * 数组
     */
    int[] elem;
    /**
     * 长度
     */
    int length;

    /**
     * 使用长度进行初始化
     *
     * @param maxLength 最大长度
     */
    public SequenceList(int maxLength) {
        this.maxLength = maxLength;
        elem = new int[maxLength];
        length = 0;
    }

    /**
     * 使用数组进行初始化
     *
     * @param elem 数组
     */
    public SequenceList(int maxLength, int[] elem) {
        this.length = elem.length;
        if (maxLength < length) {
            System.out.println("空间不足,不能复制数组");
            System.exit(-1);
        }
        this.elem = new int[length];
        //复制数组
        for (int i = 0; i < length; i++) {
            this.elem[i] = elem[i];
        }
    }

    /**
     * 插入数据
     *
     * @param index 位置,从1开始
     * @param e     元素
     */
    public void insert(int index, int e) {
        if (length == maxLength) {
            System.out.println("数组已满");
            return;
        }
        if (index <= 0 || index > length + 1) {
            System.out.println("插入位置不合适");
            return;
        }
        /*index-1为元素所在数组的下标，需要将下标为index-1以及以后的数据往后移动一位,然后将元素插入数组index-1的位置*/
        for (int i = length - 1; i >= index - 1; --i) {
            elem[i + 1] = elem[i];
        }
        /*将元素e存入对应位置*/
        elem[index - 1] = e;
        ++length;
    }

    /**
     * 插到数组最后
     *
     * @param e 元素
     */
    public void insert(int e) {
        if (length == maxLength) {
            System.out.println("数组已满");
            return;
        }
        elem[length] = e;
        ++length;
    }

    /**
     * 删除特定位置元素
     *
     * @param index 位置,从1开始
     */
    public void delete(int index) {
        if (index <= 0 || index > length) {
            System.out.println("删除位置不合适");
            return;
        }
        /*index-1为删除的数组元素下标值,需要将index-1之后的数据往前移动一位,要删除的数据在第一次循环就会被覆盖*/
        for (int i = index; i < length; i++) {
            elem[i - 1] = elem[i];
        }
        /*将位置为length-1的值设置为初始值0(对象为null)*/
        elem[length-1] = 0;
        --length;
    }

    /**
     * 设置特定位置的值
     *
     * @param index index of array,from 0 to length-1
     * @param e     element
     */
    public void setElem(int index, int e) {
        if (index < 0 || index > length - 1) {
            System.out.println("修改位置不合适");
            return;
        }
        elem[index] = e;
    }

    /**
     * 根据元素确定位置
     *
     * @param e 元素
     * @return index of data or -1 if element doesn't exist
     */
    public int locateElem(int e) {
        for (int i = 0; i < length; i++) {
            if (elem[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用索引获取元素
     *
     * @param index 索引值
     * @return 数据
     */
    public int getElem(int index) {
        if (index < 0 || index > length - 1) {
            System.out.println("元素不存在");
            System.exit(-1);
        }
        return elem[index];
    }

    @Override
    public String toString() {
        return "SequenceList{" +
                "maxLength=" + maxLength +
                ", elem=" + Arrays.toString(elem) +
                ", length=" + length +
                '}';
    }

    public static void main(String[] args) {
        /*测试用例*/
        SequenceList sequenceList = new SequenceList(5);
        sequenceList.insert(1, 1);
        sequenceList.insert(2, 2);
        sequenceList.insert(3, 3);
        sequenceList.insert(4);
        sequenceList.insert(5);
        sequenceList.insert(4);
        System.out.println(sequenceList);
        System.out.println(sequenceList.locateElem(5));
        System.out.println(sequenceList.getElem(4));
        sequenceList.setElem(0, 6);
        System.out.println(sequenceList.getElem(0));
        System.out.println(sequenceList);
        sequenceList.delete(1);
        System.out.println(sequenceList);
    }
}
