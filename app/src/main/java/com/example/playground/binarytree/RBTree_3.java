package com.example.playground.binarytree;

import java.util.Random;

public class RBTree_3 {
    public static class Node {
        public Node left, right, parent;
        public boolean black = false;
        public int value;

        public Node grandparent() {
            return this.parent == null ? null : this.parent.parent;
        }

        public Node uncle() {
            if (grandparent() == null) {
                return null;
            } else if (parent == grandparent().left) {
                return grandparent().right;
            } else if (parent == grandparent().right) {
                return grandparent().left;
            } else {
                return null;
            }
        }

        public Node sibling() {
            if (parent == null) {
                return null;
            }
            if (parent.left == this) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

        @Override
        public String toString() {
            return show(this);
        }
    }

    private Node root;

    public void insert(int v) {

        final Node node = new Node();
        node.left = node.right = node.parent = null;
        node.black = false;
        node.value = v;

        // case 1: 插入节点是根
        if (root == null) {
            root = node;
            root.black = true;
            return;
        } else {
            Node p = findParent(v);
            // case 2: 插入值重复
            if (p == null) {
                return;
            }

            setParent(node, p);
            if (v > p.value) {
                p.right = node;
            } else {
                p.left = node;
            }

            fixInsert(node);
        }
    }

    // 找到属于v的parent准备插入
    private Node findParent(int v) {
        Node pre = root;
        Node index = root;
        while (index != null) {

            // 找到相同值直接返回
            if (index.value == v) {
                return null;
            }

            pre = index;
            index = v < index.value ? index.left : index.right;
        }
        return pre;
    }

    // 平衡红黑树
    private void fixInsert(Node node) {


        final Node parent = node.parent;
        final Node uncle = node.uncle();
        final Node grandparent = node.grandparent();
        // case 1: 插入节点是根
        if (parent == null) {
            root = node;
            root.black = true;
        }
        // case 3: 插入节点的 父亲 是黑
        else if (parent != null && parent.black) {
            // do nothing
        }
        // case 4: 插入节点的 父亲 是红
        else if (parent != null && !parent.black) {

            // case 4.1: 叔叔 也是红
            if (uncle != null && !uncle.black) {
                parent.black = true;
                uncle.black = true;
                // 有叔叔必定有祖父
                grandparent.black = false;
                // 祖父的父亲是 红, 和祖父冲突了
                fixInsert(grandparent);
            }
            // case 4.2: 叔叔 是黑/空
            else if (uncle == null || uncle.black) {
                // case 4.2.1: `左左`
                if (parent == grandparent.left && node == parent.left) {
                    parent.black = true;
                    grandparent.black = false;
                    rotateRight(grandparent);
                }
                // case 4.2.2: `左右`
                else if (parent == grandparent.left && node == parent.right) {
                    rotateLeft(parent);
                    fixInsert(parent);
                }
                // case 4.2.3: `右右`
                else if (parent == grandparent.right && node == parent.right) {
                    parent.black = true;
                    grandparent.black = false;
                    rotateLeft(grandparent);
                }
                // case 4.2.4: `右左`
                else if (parent == grandparent.right && node == parent.left) {
                    rotateRight(parent);
                    fixInsert(parent);
                }
            }
        }

    }

    public Node remove(int num) {
        // case 1: 树空, 删除失败
        if (root == null) {
            return null;
        } else {
            final Node numNode = findNum(root, num);

            // case 2: 无匹配项, 删除失败
            if (numNode == null) {
                return null;
            }

            // 此时, replaceNode 一定是个叶子节点
            final Node replaceNode = findReplaceNode(numNode);

            // 所有的删除操作最后都被转换成一种情况, 删除一个叶子节点
            fixRemove(replaceNode);

            replaceNode.value = num;
            replaceNode.black = numNode.black;
            return replaceNode;
        }
    }

    // 平衡红黑树
    private void fixRemove(Node replaceNode) {

        // case 3: 删除的是 红
        if (!replaceNode.black) {
            // do nothing
            replaceNode.parent = null;
        }
        // case 3: 删除的是 黑
        else if (replaceNode.black) {

        }

    }

    // 递归查找所有替换的点
    private Node findReplaceNode(Node numNode) {
        Node replaced;
        if (numNode.left != null) {
            replaced = findMax(numNode.left);
            numNode.value = replaced.value;
            return findReplaceNode(replaced);
        } else if (numNode.right != null) {
            replaced = findMin(numNode.right);
            numNode.value = replaced.value;
            return findReplaceNode(replaced);
        } else {
            return numNode;
        }
    }

    // 找到树最小节点
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 找到树最大节点
    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 找到 num 节点
    private Node findNum(Node node, int num) {
        if (node != null) {
            if (num > node.value) {
                return findNum(node.left, num);
            } else if (num > node.value) {
                return findNum(node.right, num);
            } else {
                return node;
            }
        }
        return null;
    }

    private void rotateLeft(Node root) {

        Node p = root.right;

        root.right = p.left;
        setParent(p.left, root);

        setParent(p, root.parent);
        if (root.parent != null) {
            if (root.parent.left == root) {
                root.parent.left = p;
            } else {
                root.parent.right = p;
            }
        }

        p.left = root;
        setParent(root, p);
    }

    private void rotateRight(Node root) {

        Node p = root.left;

        root.left = p.right;
        setParent(p.right, root);

        setParent(p, root.parent);
        if (root.parent != null) {
            if (root.parent.left == root) {
                root.parent.left = p;
            } else {
                root.parent.right = p;
            }
        }

        p.right = root;
        setParent(root, p);
    }

    private void setParent(Node node, Node parent) {
        if (node != null) {
            node.parent = parent;
            // 没有父亲,那么你就是根节点了
            if (parent == null) {
                root = node;
            }
        }
    }

    @Override
    public String toString() {
        return root == null ? "空" : root.toString();
    }


    public static void main(String[] args) {
        final RBTree_3 t = new RBTree_3();
//        final Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            final int v = random.nextInt(100);
//            System.out.println("add: " + v);
//            t.insert(v);
//            System.out.println(t);
//        }

        System.out.println("add: " + 2);
        t.insert(2);
        System.out.println(t);

        System.out.println("add: " + 45);
        t.insert(45);
        System.out.println(t);

        System.out.println("add: " + 0);
        t.insert(0);
        System.out.println(t);

        System.out.println("add: " + 86);
        t.insert(86);
        System.out.println(t);

        System.out.println("add: " + 50);
        t.insert(50);
        System.out.println(t);

        System.out.println("add: " + 32);
        t.insert(32);
        System.out.println(t);

        System.out.println("add: " + 65);
        t.insert(65);
        System.out.println(t);

        System.out.println("add: " + 43);
        t.insert(43);
        System.out.println(t);

        System.out.println("add: " + 37);
        t.insert(37);
        System.out.println(t);

        System.out.println("add: " + 36);
        t.insert(36);
        System.out.println(t);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // print-utils

    private static int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf((currNode.black ? "B" : "R") + currNode.value);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static String show(Node root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        StringBuilder sb = new StringBuilder();
        for (String[] line : res) {
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
