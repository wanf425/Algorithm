package com.wt.adt;

/**
 * 链表ADT
 * 
 * @author wangtao
 * @param <T>
 */
public class LinkADT<T> {

	/**
	 * 单链表节点
	 * 
	 * @author wangtao
	 * @param <T>
	 */
	private static class SingleNode<T> {
		public SingleNode<T> next;
		public T data;

		public SingleNode(T data) {
			this.data = data;
		}
		
		public T getNextNodeData() {
			return next != null ? next.data : null;
		}
	}

	/**
	 * 单链表反转 version1
	 * 
	 * @param node
	 *            当前节点
	 * @param pre
	 *            前一个节点
	 * @return
	 */
	public static SingleNode reverseV1(SingleNode node, SingleNode pre) {

		if (node == null) {
			return node;
		} else {
			// 反转后的头结点
			SingleNode headNode;

			// next为空，说明是尾节点
			if (node.next == null) {
				// 修改next引用
				node.next = pre;
				// 指定反转后的头节点
				headNode = node;
			} else {
				// 非尾节点，继续递归
				headNode = reverseV1(node.next, node);
				//
				node.next = pre;
			}

			return headNode;
		}
	}

	/**
	 * 单链表反转 version2
	 * 
	 * @param node
	 * @return
	 */
	public static SingleNode reverseV2(SingleNode node) {
		if (node == null || node.next == null) {
			return node;
		} else {
			SingleNode headNode = reverseV2(node.next);
			
			// 重点，将下一个节点的next一引用修改为当前节点
			// 乍一看以为有bug，仔细分析之后发现没问题
			// 如果想不明白，建议跟一次断点
			node.next.next = node; 
			
			node.next = null;
			return headNode;
		}
	}

	/**
	 * 打印链表信息
	 * 
	 * @param node
	 */
	public static void printLink(SingleNode node) {
		System.out.println(
				"current node data:" + node.data + ", next node data:" + node.getNextNodeData());

		if (node.next != null) {
			printLink(node.next);
		} else {
			System.out.println("-------------");
		}
	}
	
	/**
	 * 单链表反转
	 */
	private static void verifySingleNodeReverse() {
		SingleNode<Integer> s1 = new SingleNode<>(1);
		SingleNode<Integer> s2 = new SingleNode<>(2);
		SingleNode<Integer> s3 = new SingleNode<>(3);
		SingleNode<Integer> s4 = new SingleNode<>(4);
		s1.next = s2;
		s2.next = s3;
		s3.next = s4;

		printLink(s1);
		// SingleNode<Integer> firstNode = reverseV1(s1, null);
		SingleNode<Integer> firstNode = reverseV2(s1);
		printLink(firstNode);
	}
	
	public static void main(String[] args) {
		verifySingleNodeReverse();
	}
}
