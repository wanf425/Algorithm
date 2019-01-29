package com.wt.leetcode;

public class LinkList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // verifyMergeKlists();
        // verifySwapPairs();
        hasLoopV2();
    }


    /**
     * 寻找链表的中间节点
     * 
     * @param node
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        
        return fast == null ? slow : slow.next;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return null;
        }

        ListNode p = head;
        ListNode q = head;

        int i = 0;
        while (i < n) {
            p = p.next;

            if (p == null) {
                head = head.next;
                return head;
            }

            i++;
        }

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;
        return head;
    }

    private static void hasLoopV2() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        LinkList l = new LinkList();
        ListNode node = l.hasLoopV2(node1);
        printLink(node);
    }

    /**
     * 判断是否有环 快慢指针法
     * 
     * @param node
     * @return
     */
    public ListNode hasLoopV2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 判断是否有环 快慢指针法
     * 
     * @param node
     * @return
     */
    public static boolean hasLoopV1(ListNode headNode) {
        if (headNode == null) {
            return false;
        }

        ListNode slow = headNode;
        ListNode fast = headNode.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == null) {
                return false;
            } else if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    private static void verifySwapPairs() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        LinkList l = new LinkList();
        ListNode node = l.swapPairs(node1);
        printLink(node);
    }

    /**
     * 24. Swap Nodes in Pairs
     * 
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode node) {

        if (node == null || node.next == null) {
            return node;
        }

        ListNode temp = node.next;
        node.next = node.next.next;
        temp.next = node;

        temp.next.next = swapPairs(temp.next.next);

        return temp;
    }

    /**
     * 
     * 
     * @param lists
     * @return
     */
    public ListNode mergeKListsV2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return mergeV2(lists, 0, lists.length - 1);
    }

    private ListNode mergeV2(ListNode[] lists, int lo, int hi) {
        if (lo > hi)
            return null;
        if (lo == hi)
            return lists[lo];

        int mid = lo + (hi - lo) / 2;
        ListNode a = mergeV2(lists, lo, mid);
        ListNode b = mergeV2(lists, mid + 1, hi);

        return mergeV2(a, b);
    }

    private ListNode mergeV2(ListNode a, ListNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.val < b.val) {
            a.next = mergeV2(a.next, b);
            return a;
        } else {
            b.next = mergeV2(a, b.next);
            return b;
        }
    }

    private static void verifyMergeKlists() {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(11);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(10);
        node4.next = node5;

        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;

        ListNode[] lists = new ListNode[] { null, node1, null, node4 };
        mergeKLists(lists);
    }

    /**
     * 多个有序链表合并
     * 
     * @param nodeA
     * @param nodeB
     * @return
     */
    private static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int emptyNumber = 0;
        for (ListNode node : lists) {
            if (node == null) {
                emptyNumber++;
            }
        }

        if (emptyNumber >= 1) {
            ListNode[] newLists = new ListNode[lists.length - emptyNumber];
            int j = 0;
            for (ListNode node : lists) {
                if (node != null) {
                    newLists[j] = node;
                    j++;
                }
            }
            lists = newLists;
        }

        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode result = mergeK(lists);
        return result;
    }

    /**
     * 多个有序链表合并，递归
     * 
     * @param nodeA
     * @param nodeB
     * @return
     */
    private static ListNode mergeK(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        }

        int smallVal = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];

            if (smallVal == Integer.MIN_VALUE || smallVal > node.val) {
                smallVal = node.val;
                index = i;
            }
        }

        ListNode smallNode = new ListNode(smallVal);
        ListNode node = lists[index];
        node = node.next;
        lists[index] = node;

        if (node == null) {
            ListNode[] newLists = new ListNode[lists.length - 1];
            int j = 0;

            for (int i = 0; i < lists.length; i++) {
                if (i != index) {
                    newLists[j] = lists[i];
                    j++;
                }
            }
            lists = newLists;
        }
        smallNode.next = mergeK(lists);

        return smallNode;
    }

    /**
     * 打印链表信息
     * 
     * @param node
     */
    public static void printLink(ListNode node) {
        System.out
                .println("current node data:" + node.val + ", next node:" + (node.next != null ? node.next.val : null));

        if (node.next != null) {
            printLink(node.next);
        } else {
            System.out.println("-------------");
        }
    }
}
