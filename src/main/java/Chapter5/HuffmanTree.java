package Chapter5;

import java.util.HashMap;
import java.util.Set;

/**
 * 实现对一段文章进行哈夫曼的编码与译码
 *
 * @author HJHY
 * @date 2021/8/5 - 10:17
 */
public class HuffmanTree {
    /**
     * 编码内容
     */
    String message;

    /**
     * 统计映射
     */
    HashMap<Character, Integer> statistic;

    /**
     * 数组信息,用来构建哈夫曼树
     */
    HtNode[] hT;

    /**
     * 叶子节点数量
     */
    int nodeCount;

    /**
     * 哈夫曼编码后的0,1字符串
     */
    HashMap<Character, String> charAndCodeMap;

    /**
     * 编码结果
     */
    String encodeResult = "";

    /**
     * 译码结果
     */
    String decodeResult = "";

    /**
     * 打印字符串需要换行的字符数量
     */
    public static final int PRINT_SIZE = 15;

    /**
     * 使用参数进行初始化
     *
     * @param message 初始化内容
     */
    public HuffmanTree(String message) {
        this.message = message;
        //统计
        count();
    }

    /**
     * 统计字符信息
     */
    private void count() {
        statistic = new HashMap<>(0);
        char[] chars = message.toCharArray();
        for (char aChar : chars) {
            if (statistic.containsKey(aChar)) {
                Integer integer = statistic.get(aChar);
                statistic.put(aChar, integer + 1);
            } else {
                statistic.put(aChar, 1);
            }
        }
        //设置叶子节点数量
        nodeCount = statistic.size();
    }

    /**
     * 构建哈夫曼树
     */
    public void createTree() {
        //总节点数量
        int totalNodeCount = 2 * nodeCount - 1;
        //申请空间
        hT = new HtNode[totalNodeCount];
        //初始化下标数据
        for (int i = 0; i < totalNodeCount; i++) {
            hT[i] = new HtNode();
            hT[i].parent = hT[i].lChild = hT[i].rChild = -1;
        }
        //初始化内容数据
        int index = 0;
        Set<Character> keySet = statistic.keySet();
        for (Character character : keySet) {
            hT[index].weight = statistic.get(character);
            hT[index].character = character;
            ++index;
        }
        /*开始创建哈夫曼树*/
        //通过n-1次的循环找到最小的两个节点,将其删除并合并
        for (int i = nodeCount; i < totalNodeCount; i++) {
            //找到最小的两个节点
            int[] nodes = getTwoSmallestNode(i);
            int smallestIndex = nodes[0];
            int secondSmallestIndex = nodes[1];
            //设置双亲
            hT[smallestIndex].parent = i;
            hT[secondSmallestIndex].parent = i;
            hT[i].weight = hT[secondSmallestIndex].weight + hT[secondSmallestIndex].weight;
            hT[i].lChild = smallestIndex;
            hT[i].rChild = secondSmallestIndex;
        }
    }

    /**
     * 创建哈夫曼编码
     * 对每一个叶子节点从下往上进行构建
     */
    public void enCode() {
        //字符串数量等于叶子节点数量
        charAndCodeMap = new HashMap<>(0);
        StringBuilder code = new StringBuilder();
        /*对所有叶子节点进行编码*/
        //当前节点所在下标
        int current;
        //双亲节点所在下标
        int parent;
        for (int i = 0; i < nodeCount; i++) {
            current = i;
            parent = hT[i].parent;
            while (parent != -1) {
                //当前节点为左孩子
                if (hT[parent].lChild == current) {
                    code.append("0");
                }
                //当前节点为右孩子
                else {
                    code.append("1");
                }
                //继续往上
                current = parent;
                parent = hT[current].parent;
            }
            //由于自下而上,所以编码出来的是逆序的编码,需要进行翻转
            charAndCodeMap.put(hT[i].character, code.reverse().toString());
            //code要置空否则会一直叠加下去
            code = new StringBuilder();
        }
        //将文章内容转化为01序列
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            String s = charAndCodeMap.get(ch);
            stringBuilder.append(s);
        }
        encodeResult = stringBuilder.toString();
    }

    /**
     * 解码
     */
    public void deCode() {
        /*将01序列转化为文章内容*/
        StringBuilder stringBuilder = new StringBuilder();
        //从根节点考试往下搜,0找左儿子,1找右儿子直到叶子节点
        HtNode p = hT[2 * nodeCount - 1 - 1];
        int index;
        for (int i = 0; i < encodeResult.length(); i++) {
            //左儿子
            if (encodeResult.charAt(i) == '0') {
                index = p.lChild;
            }
            //右儿子
            else {
                index = p.rChild;
            }
            p = hT[index];
            //对叶子节点进行译码
            if (p.lChild == -1 && p.rChild == -1) {
                stringBuilder.append(p.character);
                //重新设置为根节点
                p = hT[2 * nodeCount - 1 - 1];
            }
        }
        decodeResult = stringBuilder.toString();
    }

    /**
     * 打印每个字符的哈夫曼编码结果
     */
    public void printEnCodeResult() {
        StringBuilder result = new StringBuilder();
        Set<Character> keySet = charAndCodeMap.keySet();
        for (Character key : keySet) {
            String value = charAndCodeMap.get(key);
            System.out.println(key + "--" + value);
            result.append(value);
        }
        System.out.println("译码结果如下:");
        String str = result.toString();
        for (int i = 1; i <= str.length() / PRINT_SIZE; i++) {
            System.out.println(str.substring(PRINT_SIZE * (i - 1), PRINT_SIZE * i));
        }
        System.out.println(str.substring(str.length() / PRINT_SIZE * PRINT_SIZE));
    }

    /**
     * 打印译码结果
     */
    public void printDeCodeResult() {
        System.out.println(decodeResult);
    }

    /**
     * 找到权值最小的两个节点下标
     *
     * @return 节点下标数组, 第零个最小，第一个第二小
     */
    private int[] getTwoSmallestNode(int current) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int index1 = -1;
        int index2 = -1;
        //搜索范围应该是开始到当前的节点而不是hT.length
        for (int i = 0; i < current; i++) {
            //在所有没有双亲节点的节点找出两个最小的
            if (hT[i].parent == -1) {
                if (hT[i].weight < min1) {
                    min2 = min1;
                    index2 = index1;
                    min1 = hT[i].weight;
                    index1 = i;
                } else if (hT[i].weight < min2) {
                    min2 = hT[i].weight;
                    index2 = i;
                }
            }
        }
        return new int[]{index1, index2};
    }


    public static void main(String[] args) {
        String message = "The Chinese official said he viewed the Trump Presidency not as\n" +
                "an aberration but as the product of a failing political system.\n" +
                "This jibes with other accounts. The Chinese leadership believes that the United\n" +
                "States, and Western democracies in general, haven’t risen to the\n" +
                "challenge of a globalized economy, which necessitates big changes in\n" +
                "production patterns, as well as major upgrades in education and public\n" +
                "infrastructure. In Trump and Trumpism, the Chinese see an inevitable\n" +
                "backlash to this failure.";
        HuffmanTree huffmanTree = new HuffmanTree(message);
        huffmanTree.createTree();
        huffmanTree.enCode();
        huffmanTree.printEnCodeResult();
        huffmanTree.deCode();
        huffmanTree.printDeCodeResult();
    }
}

class HtNode {
    /**
     * 字符
     */
    Character character;
    /**
     * 权重
     */
    int weight;
    /**
     * 双亲节点位置
     */
    int parent;
    /**
     * 左孩子位置
     */
    int lChild;
    /**
     * 右孩子位置
     */
    int rChild;

    public HtNode() {
    }
}