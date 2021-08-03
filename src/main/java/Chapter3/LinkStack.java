package Chapter3;

/**
 * 这里链栈的实现以链表头为栈顶,链表尾为栈底
 * 入栈出栈只需要在第一个节点进行操作,所以不需要使用头节点
 *
 * @author HJHY
 * @date 2021/8/3 - 10:43
 */
public class LinkStack {
    /**
     * 头节点
     */
    LinkStackNode head;

    /**
     * 初始化空链栈
     */
    LinkStack() {
        head = null;
    }

    /**
     * 判断是否为栈空
     *
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * 进栈(没有头节点的头插法)
     *
     * @param e data
     */
    public void push(int e) {
        //申请一个新的节点并将数据进行初始化
        LinkStackNode tmp = new LinkStackNode(e);
        //为了防止数据丢失先指向head
        tmp.next = head;
        //将新节点设置为头节点
        head = tmp;
    }

    /**
     * 将元素出栈
     */
    public void pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        //在Java中不用手动释放内存,在其他语言中可能需要指向删除的节点再手动释放
        head = head.next;
    }

    /**
     * 获取栈顶元素
     *
     * @return data of the stack
     */
    public int getTop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        return head.data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        LinkStackNode p = head;
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
        LinkStack linkStack = new LinkStack();
        linkStack.push(1);
        linkStack.push(2);
        linkStack.push(3);
        linkStack.push(4);
        linkStack.push(5);
        System.out.println(linkStack);
        System.out.println(linkStack.getTop());
        linkStack.pop();
        System.out.println(linkStack);
        linkStack.pop();
        System.out.println(linkStack);
        linkStack.pop();
        System.out.println(linkStack);
        linkStack.pop();
        System.out.println(linkStack);
        linkStack.pop();
        System.out.println(linkStack);
//        linkStack.pop();
    }
}

class LinkStackNode {
    /**
     * 元素部分
     */
    int data;
    /**
     * 下一个节点指向
     */
    LinkStackNode next;

    /**
     * 数据初始化
     *
     * @param data data
     */
    public LinkStackNode(int data) {
        this.data = data;
    }
}