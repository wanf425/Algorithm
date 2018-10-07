package com.wt.adt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListStudy {

	public static void main(String[] args) {
		int maxnum = 4000000;
		List list1 = new ArrayList(maxnum);
		for(int i = 0 ;i< maxnum; i++) {
			list1.add(i+1);
		}
		
		
		List list2 = new LinkedList();
		for(int i = 0 ;i< maxnum; i++) {
			list2.add(i+1);
		}
		
		long begintime = System.currentTimeMillis();
		removeEvensVer2(list1);
		long currenttime = System.currentTimeMillis();
		System.out.println("remove by arraylist use " + (currenttime - begintime));
		
	    begintime = System.currentTimeMillis();
		removeEvensVer2(list2);
		currenttime = System.currentTimeMillis();
		System.out.println("remove by linkedlist use " + (currenttime - begintime));
		
//		printList(list1);
//		printList(list2);
	}
	
	public static void printList(List<Integer> list) {
		for(Integer ii : list) {
			System.out.println(ii);
		}
		System.out.println("end");
	}
	
	public static void removeEvensVer1(List<Integer> list) {
		int i = 0;
		while (i < list.size()) {
			if (list.get(i) % 2 == 0) {
				list.remove(i);
			} else {
				i++;
			}
		}
	}
	
	public static void removeEvensVer2(List<Integer> list) {
		ListIterator<Integer> it = list.listIterator();
		
		while(it.hasNext()) {
			if(it.next() % 2 == 0) {
				it.remove();
			}
		}
	}
}
