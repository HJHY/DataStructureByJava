package Chapter2;

/**
 * 单链表,为了便于首元节点的处理与空表和非空表的统一处理，增加了空的头节点
 *
 * @author HJHY
 * @date 2021/8/2 - 13:30
 */
public class SingleLinkList {
    private final Node head;

    /**
     * 空构造器
     */
    public SingleLinkList() {
        //初始化申请空的头节点
        head = new Node();
        head.next = null;
    }

    /**
     * 尾插法
     *
     * @param e data
     */
    public void insert(int e) {
        Node p = head;
        //找到最后一个节点
        while (p.next != null) {
            p = p.next;
        }
        //申请一个新节点链接到尾部
        p.next = new Node(e);
        p.next.next = null;
    }

    /**
     * 插到特定位置(插入位置有n+1个)
     *
     * @param index 插到特定位置
     * @param e     data
     */
    public void insert(int index, int e) {
        if (index < 1) {
            throw new IndexOutOfBoundsException("插入位置小与1");
        }
        //循环index-1次,找到插入位置的前一个节点
        int count = 0;
        Node p = head;
        while (p != null && count < index - 1) {
            p = p.next;
            ++count;
        }
        //判断插入位置是否越界
        if (p == null) {
            throw new IndexOutOfBoundsException("插入位置越界");
        }
        Node tmp = new Node(e);
        //为了防止指针丢失先操作新节点指向下一个节点
        tmp.next = p.next;
        p.next = tmp;
    }

    /**
     * 删除最后一个元素
     */
    public void deleteLast() {
        //链表为空
        if (head.next == null) {
            return;
        }
        Node p = head;
        while (p.next.next != null) {
            p = p.next;
        }
        //其他语言可能需要临时变量指向待删除节点再手动释放
        p.next = null;
    }

    /**
     * 删除特定位置元素(删除位置只有n个)
     *
     * @param index index from 1 to length
     */
    public void delete(int index) {
        if (index < 1) {
            throw new IndexOutOfBoundsException("插入位置小与1");
        }
        //循环n-1次找到删除位置的前一个节点,为了避免index过大造成的空指针异常,循环条件与插入有一点点差距
        Node p = head;
        int count = 0;
        while (p.next != null && count < index - 1) {
            p = p.next;
            ++count;
        }
        //循环位置终止位置的节点要么下一个是空指针,要么是循环次数够了,如果下一个位置是空指针(即当前节点是最后一个节点)则插入位置过大
        //删除的最大位置是尾节点,如果循环终止的节点正常是尾节点的前一个位置,如果是尾节点,证明index大于链表长度
        if (p.next == null) {
            throw new IndexOutOfBoundsException("插入位置越界");
        }
        /*tmp用来保存要删除的节点,在Java中没有指向的时候会自动释放内存,所以直接指向即可,否则需要使用一个临时变量指向待删除节点再手动释放内存*/
        //为了防止丢失应该先链接将删除节点的后续节点,其他语言可能需要手动释放内存
        p.next = p.next.next;
    }

    /**
     * 修改特定位置的节点值
     *
     * @param index index from 1 to length
     * @param e     data
     */
    public void set(int index, int e) {
        //空链表
        if (head.next == null) {
            throw new IndexOutOfBoundsException("链表为空");
        }
        if (index < 1) {
            throw new IndexOutOfBoundsException("修改位置小与1");
        }
        //循环index次
        int count = 0;
        Node p = head;
        while (p != null && count < index) {
            p = p.next;
            ++count;
        }
        //越界判断
        if (p == null) {
            throw new IndexOutOfBoundsException("修改位置越界");
        }
        //修改当前节点数据
        p.data = e;
    }

    /**
     * 查找元素
     *
     * @param e data
     * @return location from 0 to length-1 or -1 if not found
     */
    public int locate(int e) {
        //空链表
        if (head.next == null) {
            return -1;
        }
        int count = 0;
        Node p = head.next;
        while (p != null) {
            //判断不要写在p=p.next后面,因为p=p.next之后可能有空指针
            if (p.data == e) {
                return count;
            }
            ++count;
            p = p.next;
        }
        //找不到
        return -1;
    }

    /**
     * 根据索引获取值
     *
     * @param index index from 1 to length
     * @return value of data
     */
    public int get(int index) {
        //空链表
        if (head.next == null) {
            throw new IndexOutOfBoundsException("链表为空");
        }
        if (index < 1) {
            throw new IndexOutOfBoundsException("修改位置小与1");
        }
        Node p = head.next;
        int count = 0;
        //从第1个值开始搜索循环index-1次
        while (p != null && count < index - 1) {
            p = p.next;
            ++count;
        }
        //越界
        if (p == null) {
            throw new IndexOutOfBoundsException("获取位置越界");
        }
        return p.data;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        Node p = head.next;
        while (p != null) {
            str.append(p.data).append(",");
            p = p.next;
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        return str.toString();
    }

    public static void main(String[] args) {
        /*测试用例*/
        SingleLinkList list = new SingleLinkList();
        list.insert(1);
        list.insert(3);
        list.insert(2, 2);
        list.insert(4, 4);
        System.out.println(list);
        list.deleteLast();
        System.out.println(list);
        list.delete(1);
        System.out.println(list);
        list.set(1, 0);
        System.out.println(list);
        System.out.println(list.locate(3));
        System.out.println(list.get(1));
    }

}

class Node {
    int data;
    Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }
}
