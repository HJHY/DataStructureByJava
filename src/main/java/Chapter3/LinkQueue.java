package Chapter3;

/**
 * 这里使用链表实现队列(带头节点方便统一处理)
 *
 * @author HJHY
 * @date 2021/8/3 - 15:05
 */
public class LinkQueue {
    /**
     * 头节点
     */
    LinkQueueNode front;

    /**
     * 尾节点
     */
    LinkQueueNode rear;

    /**
     * 空构造器
     */
    public LinkQueue() {
        LinkQueueNode tmp = new LinkQueueNode();
        tmp.next = null;
        front = rear = tmp;
    }

    /**
     * 判断是否为空
     *
     * @return true if Queue is empty
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 获取队头元素
     *
     * @return value of the front of the queue
     */
    public int getFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return front.next.data;
    }

    /**
     * 获取队尾元素
     *
     * @return value of the rear of the queue
     */
    public int getRear() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        return rear.data;
    }

    /**
     * 将元素入队(插入队尾)
     *
     * @param e data
     */
    public void enQueue(int e) {
        //申请新节点插入最后的位置
        LinkQueueNode tmp = new LinkQueueNode(e);
        tmp.next = null;
        //连接新节点
        rear.next = tmp;
        //新节点设置为新的尾指针
        rear = tmp;
    }

    /**
     * 出队(删除队头)
     */
    public void deQueue() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }
        //其他语言重需要手动释放第一个节点,Java中直接修改指向即可
        front.next = front.next.next;
        //删除了最后一个节点,应该对尾指针进行修改
        if (front.next == null) {
            rear = front;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        LinkQueueNode p = front.next;
        while (p != null) {
            stringBuilder.append(p.data).append(",");
            p = p.next;
        }
        if (!isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        /*测试用例*/
        LinkQueue linkQueue = new LinkQueue();
        System.out.println(linkQueue);
        linkQueue.enQueue(1);
        linkQueue.enQueue(2);
        linkQueue.enQueue(3);
        System.out.println(linkQueue);
        System.out.println(linkQueue.getFront());
        System.out.println(linkQueue.getRear());
        linkQueue.deQueue();
        linkQueue.deQueue();
        linkQueue.deQueue();
        System.out.println(linkQueue);
    }

}

class LinkQueueNode {
    /**
     * 数据
     */
    int data;

    /**
     * 指向下一个节点
     */
    LinkQueueNode next;

    /**
     * 空构造器
     */
    public LinkQueueNode() {
    }

    /**
     * 使用数据进行初始化
     *
     * @param data data
     */
    public LinkQueueNode(int data) {
        this.data = data;
    }
}