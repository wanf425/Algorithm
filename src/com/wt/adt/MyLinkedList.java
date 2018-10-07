package com.wt.adt;

public class MyLinkedList<T> {

	private Node<T> beginNode;
	private Node<T> endNode;
	private int size;

	public MyLinkedList() {
		beginNode = new Node<T>(null, null, null);
		endNode = new Node<T>(beginNode, null, null);
		beginNode.next = endNode;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean add(T data) {
		return addBefore(data, endNode);
	}

	public boolean addBefore(T data, Node<T> node) {
		Node<T> cur = new Node<T>(node.pre, data, node);
		node.pre.next = cur;
		node.pre = cur;
		size++;
		
		return true;
	}
	
	public boolean remove(Node<T> node) {
		node.pre.next = node.next;
		node.next.pre = node.pre;
		size--;
		
		return true;
	}

	private class Node<T> {

		T data;
		Node<T> pre;
		Node<T> next;

		public Node(Node<T> pre, T data, Node<T> next) {
			this.pre = pre;
			this.data = data;
			this.next = next;
		}
	}
}
