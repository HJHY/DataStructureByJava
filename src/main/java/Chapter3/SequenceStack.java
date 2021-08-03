package Chapter3;

/**
 * @author HJHY
 * @date 2021/8/3 - 10:18
 */
public class SequenceStack {
    /**
     * 元素数组
     */
    int[] elem;
    /**
     * 栈顶
     */
    int top;
    /**
     * 栈底
     */
    int base;
    /**
     * 最大容量
     */
    int maxSize;

    /**
     * 初始化空栈
     *
     * @param maxSize 最大容量
     */
    SequenceStack(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("初始容量不能小与1");
        }
        this.maxSize = maxSize;
        top = base = 0;
        elem = new int[maxSize];
    }

    /**
     * 判断是否为空
     *
     * @return true if stack is empty
     */
    public boolean isEmpty() {
        return top == base;
    }

    /**
     * 判断栈满
     *
     * @return true if stack is full
     */
    public boolean isFull() {
        return top - base == maxSize;
    }

    /**
     * 将元素压栈
     *
     * @param e data
     */
    public void push(int e) {
        //空间不足
        if (isFull()) {
            throw new IndexOutOfBoundsException("栈满");
        }
        elem[top++] = e;
    }

    /**
     * 将元素出栈
     */
    public void pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        --top;
    }

    /**
     * 获取栈顶元素
     *
     * @return top element of the stack
     */
    public int getTop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈空");
        }
        return elem[top - 1];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = base; i < top; i++) {
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
        SequenceStack sequenceStack = new SequenceStack(5);
        sequenceStack.push(1);
        sequenceStack.push(2);
        sequenceStack.push(3);
        sequenceStack.push(4);
        sequenceStack.push(5);
//        sequenceStack.push(6);
        System.out.println(sequenceStack);
        System.out.println(sequenceStack.getTop());
        sequenceStack.pop();
        System.out.println(sequenceStack);
        sequenceStack.pop();
        System.out.println(sequenceStack);
        sequenceStack.pop();
        System.out.println(sequenceStack);
        sequenceStack.pop();
        System.out.println(sequenceStack);
        sequenceStack.pop();
        System.out.println(sequenceStack);
//        sequenceStack.pop();
    }
}
