package Chapter3;

/**
 * 顺序(循环)队列,采用一个空位进行队满的判断
 * 解决假溢出和无法判断队空队满的情况,故最大容量为maxSize-1
 *
 * @author HJHY
 * @date 2021/8/3 - 11:31
 */
public class SequenceQueue {
    /**
     * 元素数组
     */
    int[] elem;

    /**
     * 队头
     */
    int front;

    /**
     * 队尾
     */
    int rear;

    /**
     * 最大容量
     */
    int maxSize;

    /**
     * 以最大容量进行初始化
     *
     * @param maxSize 最大容量
     */
    public SequenceQueue(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("初始容量不能小与1");
        }
        this.maxSize = maxSize;
        front = rear = 0;
        elem = new int[maxSize];
    }

    /**
     * 求队长
     *
     * @return length of the queue
     */
    public int getLength() {
        //队尾在后,rear-front为正数;队尾在前,rear-front为负数,绝对值为差距,所以加上maxSize再求模maxSize
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * 判断队列是否为空
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断队列是否为满
     *
     * @return true if the queue is full
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 入队
     *
     * @param e data
     */
    public void enQueue(int e) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("队列已满");
        }
        elem[rear] = e;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 出队
     */
    public void deQueue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        front = (front + 1) % maxSize;
    }

    /**
     * 获取队头
     *
     * @return value of front
     */
    public int getHead() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return elem[front];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = front; i < front + getLength(); i++) {
            stringBuilder.append(elem[i]).append(",");
        }
        if (!isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        /*测试用例*/
        SequenceQueue sequenceQueue = new SequenceQueue(5);
        sequenceQueue.enQueue(1);
        sequenceQueue.enQueue(2);
        sequenceQueue.enQueue(3);
        sequenceQueue.enQueue(4);
        System.out.println(sequenceQueue);
//        sequenceQueue.enQueue(5);
        System.out.println(sequenceQueue.getHead());
        sequenceQueue.deQueue();
        System.out.println(sequenceQueue);
        sequenceQueue.deQueue();
        sequenceQueue.deQueue();
        sequenceQueue.deQueue();
//        sequenceQueue.deQueue();
        System.out.println(sequenceQueue);
    }
}
