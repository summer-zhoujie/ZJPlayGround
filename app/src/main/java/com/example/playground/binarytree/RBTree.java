package com.example.playground.binarytree;

public class RBTree {

    private Node root;

    public static class Node {
        public int value;
        // 默认插入一个红节点
        public boolean isBlack = false;
        public Node left, right, parent;

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
    }


    public boolean doRemove(int delValue) {
        if (root == null) {
            return false;
        }
        return delete_child(root, delValue);
    }

    Boolean delete_child(Node p, int data) {
        if (p.value > data) {
            if (p.left == null) {
                return false;
            }
            return delete_child(p.left, data);
        } else if (p.value < data) {
            if (p.right == null) {
                return false;
            }
            return delete_child(p.right, data);
        } else if (p.value == data) {
            if (p.right == null) {
                delete_one_child(p);
                return true;
            }
            Node smallest = findMin(p.right);
            p.value = smallest.value;
            delete_one_child(smallest);

            return true;
        } else {
            return false;
        }
    }

    // 删除只有一个孩子的节点
    void delete_one_child(Node p) {
        Node child = p.left == null ? p.right : p.left;

        // case 1: p 是根节点且没有孩子, 直接删除
        if (p.parent == null && p.left == null && p.right == null) {
            p = null;
            root = p;
            return;
        }

        // case 2: p 是根节点, 删除p, 直接把孩子作为 root
        if (p.parent == null) {
            child.parent = null;
            root = child;
            root.isBlack = true;
            return;
        }

        // 孩子提升到p的位置
        if (p.parent.left == p) {
            p.parent.left = child;
        } else {
            p.parent.right = child;
        }
        child.parent = p.parent;

        // case 3: p 是红色节点(儿子一定是黑色), 直接将儿子替换p
        if (!p.isBlack) {
            // do nothing
        }
        // case 4: p 是黑色, 儿子是红色, 直接将儿子改为黑色替换p
        else if (p.isBlack && !child.isBlack) {
            child.isBlack = true;
        }
        // case 5: p 是黑色, 儿子也是黑色
        else if (p.isBlack && child.isBlack) {
            delete_case(child);
        }

    }

    // 此时的p是被child替换之后的
    void delete_case(Node p) {

        // case 5.1: p 是根节点, 直接把自己置黑
        if (p.parent == null) {
            p.isBlack = true;
            return;
        }

        // case 5.2: p 的兄弟是红色, 把它转上去
        if (!p.sibling().isBlack) {
            p.parent.isBlack = false;
            p.sibling().isBlack = true;
            if (p == p.parent.left)
                rotateLeft(p.sibling());
//                rotateLeft(p.parent);
            else
                rotateRight(p.sibling());
//                rotateRight(p.parent);
        }

        // case 5.3: p 和 兄弟 和 兄弟的孩子都是黑色,
        if (p.parent.isBlack && p.sibling().isBlack
                && p.sibling().left.isBlack && p.sibling().right.isBlack) {
            p.sibling().isBlack = false;
            delete_case(p.parent);
        }
        // case 5.4: p 是红的, 兄弟和兄弟孩子都是黑色
        else if (!p.parent.isBlack && p.sibling().isBlack
                && p.sibling().left.isBlack && p.sibling().right.isBlack) {
            p.sibling().isBlack = false;
            p.parent.isBlack = true;
        }

        else {
            if (p.sibling().isBlack) {
                // case 5.5:
                if (p == p.parent.left && !p.sibling().left.isBlack
                        && p.sibling().right.isBlack) {
                    p.sibling().isBlack = false;
                    p.sibling().left.isBlack = true;
                    rotateRight(p.sibling().left);
                } else if (p == p.parent.right && p.sibling().left.isBlack
                        && !p.sibling().right.isBlack) {
                    p.sibling().isBlack = false;
                    p.sibling().right.isBlack = true;
                    rotateLeft(p.sibling().right);
                }
            }
            p.sibling().isBlack = p.parent.isBlack;
            p.parent.isBlack = true;
            if (p == p.parent.left) {
                p.sibling().right.isBlack = true;
                rotateLeft(p.sibling());
            } else {
                p.sibling().left.isBlack = true;
                rotateRight(p.sibling());
            }
        }
    }


    private Node findMin(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void doInsert(int newValue) {

        // case 1: 新节点N位于树的根上，没有父节点
        if (root == null) {
            root = new Node();
            root.isBlack = true;
            root.left = root.right = root.parent = null;
            root.value = newValue;
        } else {
            doInsert(root, newValue);
        }
    }


    private void doInsert(Node p, int newValue) {
        // 去往左子树插入
        if (p.value > newValue) {
            if (p.left != null) {
                doInsert(p.left, newValue);
            } else {
                final Node t = new Node();
                t.value = newValue;
                t.left = t.right = null;
                t.parent = p;
                p.left = t;
                doInsert_case(t);
            }
        }
        // 去往右子树插入
        else if (p.value < newValue) {
            if (p.right != null) {
                doInsert(p.right, newValue);
            } else {
                final Node t = new Node();
                t.value = newValue;
                t.left = t.right = null;
                t.parent = p;
                p.right = t;
                doInsert_case(t);
            }
        }
        // 已有相同项
        else {
            // do nothing
        }
    }

    private void doInsert_case(Node n) {

        // case 1: 新节点N位于树的根上，没有父节点
        if (n.parent == null) {
            n.isBlack = true;
            root = n;
        }
        // case 2: 新节点的父节点P是黑
        else if (n.parent.isBlack) {
            // do nothing
        }
        // case 3: 父节点 和 叔父节点 二者都是红色
        else if (!n.parent.isBlack && n.uncle() != null && !n.uncle().isBlack) {
            n.parent.isBlack = true;
            n.uncle().isBlack = true;
            n.grandparent().isBlack = false;
            doInsert_case(n.grandparent());
        }
        // case 4:父节点P是红色而叔父节点U是黑色或缺少
        else if (!n.parent.isBlack &&
                (n.uncle().isBlack) || n.uncle() == null) {

            // `左左`
            if (n.grandparent().left == n.parent && n == n.parent.left) {
                n.parent.isBlack = true;
                n.grandparent().isBlack = false;
                rotateRight(n);
            }
            // `左右`
            else if (n.grandparent().left == n.parent && n == n.parent.right) {
                rotateLeft(n);
                n.isBlack = true;
                n.parent.isBlack = false;
                rotateRight(n);
            }
            // `右右`
            else if (n.grandparent().right == n.parent && n == n.parent.right) {
                n.parent.isBlack = true;
                n.grandparent().isBlack = false;
                rotateLeft(n);
            }
            // `右左`
            else if (n.grandparent().right == n.parent && n == n.parent.left) {
                rotateRight(n);
                n.isBlack = true;
                n.parent.isBlack = false;
                rotateLeft(n);
            }

        }

    }

    private void rotateRight(Node n) {

        Node gp = n.grandparent();
        Node p = n.parent;
        Node c = n.right;

        p.left = c;
        if (c != null) {
            c.parent = p;
        }
        n.right = p;
        p.parent = n;
        if (root == p) {
            root = n;
        }
        n.parent = gp;
        if (gp != null) {
            if (gp.left == p) {
                gp.left = n;
            } else {
                gp.right = n;
            }
        }
    }

    void rotateLeft(Node n) {
        if (n.parent == null) {
            root = n;
            return;
        }
        Node gp = n.grandparent();
        Node p = n.parent;
        Node c = n.left;

        p.right = c;

        if (c != null) {
            c.parent = p;
        }
        n.left = p;
        p.parent = n;

        if (root == p) {
            root = n;
        }
        n.parent = gp;

        if (gp != null) {
            if (gp.left == p) {
                gp.left = n;
            } else {
                gp.right = n;
            }
        }
    }
}

