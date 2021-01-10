package com.linkList;

import java.util.*;

class Node {
	int data;
	Node next;
}

class LinkedList {
	Node head;

	public void addNode(int data) {
		Node n = new Node();
		n.data = data;
		if (head == null)
			head = n;
		else {
			Node n1 = head;
			while (n1.next != null)
				n1 = n1.next;
			n1.next = n;
		}
	}

	public int count() {
		int count1 = 0;
		Node n = head;
		while (n != null) {
			n = n.next;
			count1++;
		}
		return count1;
	}

	public void addafter(int data, int pos) {
		/* Fill your code here */
		if (pos > (count() + 1))
			System.out.println("There are  less than " + pos + " elements in the linked list");
		else {
			Node n = new Node();
			n.data = data;
			Node n1 = head;
			for (int i = 0; i < pos; i++)
				n1 = n1.next;
			n.next = n1.next;
			n1.next = n;
		}
	}

	public void addUniqueNode(int data) {
		/* fill your code here */
		Node n = new Node();

		if (head == null) {
			head = n;
			n.data = data;
		} else {
			Node n1 = head;
			while (n1.next != null) {
				if (n1.data == data)
					return;
				n1 = n1.next;
			}
			if (n1.data == data)
				return;
			n1.next = n;
			n.data = data;
		}
	}

	public void delete(int element) {
		/* Fill your code here */
		Node n1 = head;
		Node n2 = head;
		int pos = 0;
		while (n2 != null) {
			if (n2.data == element) {
				n1.next = n2.next;
				break;
			}
			n1 = n2;
			n2 = n2.next;
		}
	}

	public int search(int element) {
		/* Fill your code here */
		Node n = head;
		while (n != null) {
			if (n.data == element)
				return 1;
			n = n.next;
		}
		return 0;
	}

	public void reverse() {
		/* Fill your code here */

		head = reverse(head);
	}

	Node reverse(Node root1) {
		if (root1.next == null) {
//            head=root1;
			return root1;
		}
		Node temp = reverse(root1.next);
		root1.next.next = root1;
		root1.next = null;
		return temp;

	}

	public void display() {
		Node n = head;
		while (n != null) {
			System.out.print(n.data + " ");
			n = n.next;
		}

	}

	public void printlots(int start, int end) {
		if (start < 0 || end > count())
			System.out.println("Invalid Range");
		else {
			System.out.println("The elements in the range " + start + " to " + end + " are ");
			Node n = head;
			for (int i = 0; i < start - 1; i++)
				n = n.next;
			for (int i = 0; i < end - start + 1; i++) {
				System.out.print(n.data + " ");
				n = n.next;
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ch = "";
		int count = 0;
		LinkedList list = new LinkedList();
		do {
			System.out.println("Enter the value");
			int n = sc.nextInt();
			sc.nextLine();
			list.addNode(n);
			System.out.println("Do you want to add another node? Type Yes/No");
			ch = sc.nextLine();
			count++;
		} while (ch.equals("Yes"));

		System.out.print("The elements in the linked list are");
		list.display();

		System.out.println("\nEnter the range of elements to print");
		int start = sc.nextInt();
		int end = sc.nextInt();
		list.printlots(start, end);

		System.out.println("\nEnter the element to be deleted");
		int element = sc.nextInt();
		System.out.print("The elements in the linked list after deleting the element are ");
		list.delete(element);
		list.display();

		do {
			System.out.println("Enter the position after which you want to add another node");
			int pos = sc.nextInt();
			System.out.println("Enter the value");
			int value = sc.nextInt();
			sc.nextLine();
			list.addafter(value, pos);
			System.out.print("The elements in the linked list are  ");
			list.display();
			System.out.println("Do you want to add another node after a certain position? Type Yes/No");
			ch = sc.nextLine();
		} while (ch.equals("Yes"));

		System.out.print("The elements in the reversed linked list are");
		list.reverse();
		list.display();

	}
}
