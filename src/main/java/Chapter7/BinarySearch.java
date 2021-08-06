package Chapter7;

/**
 * @author HJHY
 * @date 2021/8/6 - 10:02
 */
public class BinarySearch {
    public static int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * 二分搜索
     *
     * @return 下标值, -1为找不到
     */
    public static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (array[middle] == key) {
                return middle;
            } else if (array[middle] < key) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        //找不到
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("下标:" + binarySearch(array, 5));
    }
}
