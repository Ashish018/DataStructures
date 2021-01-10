package com.hashmap.separateChaining;

import java.util.*;

class SeparateChaining<K extends Comparable<K>, V> {

	private int n;
	private int m;
	private Node<K, V>[] table;

	private class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> next;

		public Node() {

		}

		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node getNext() {
			return next;
		}

		void setNext(Node node) {
			next = node;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}

	public SeparateChaining(int capacity) {
		table = new Node[capacity];
		this.n = capacity;
	}

	public void insert(K key, V value) {

		int i = hash(key);
		Node temp = table[i], prev = table[i];
		boolean flag = true;
		while (temp != null) {
			flag = false;
			prev = temp;
			temp = temp.getNext();
		}
		temp = new Node(key, value, null);
		if (flag)
			table[i] = temp;
		else
			prev.setNext(temp);
	}

	private int hash(K key) {
		String k = key.toString();
		if (key instanceof Integer)
			return (k.charAt(k.length() - 1) - '0') % n;
		int sum = 0;
		for (int i = 0; i < k.length(); i++)
			sum += k.charAt(i);
		return sum % n;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return false;
	}

	public V get(K key) {
		return null;
	}

	public boolean contains(K key) {
		return false;
	}

	public V delete(K key) {
		Node temp = table[hash(key)];
		Node prev = temp;
		boolean flag = true;
		while (temp != null) {
			if (temp.getKey().equals(key)) {
				System.out.println("Data deleted successfully from Hash Table");
				if (flag) {
					table[hash(key)] = temp.getNext();

				} else
					prev.setNext(temp.getNext());
				return (V) temp.getValue();
			}
			flag = false;
			prev = temp;
			temp = temp.getNext();
		}
		System.out.println("Given data is not present in Hash Table");
		return null;
	}

	public void search(K key) {
		Node temp = table[hash(key)];
		while (temp != null) {
			if (temp.getKey().equals(key)) {
				System.out.println("ID : " + temp.getKey() + "\nValue : " + temp.getValue());
				return;
			}
			temp = temp.getNext();
		}
		System.out.println("Search element unavailable in hash table");

	}

	public void display() {
		for (int i = 0; i < table.length; i++) {
			System.out.println();
			System.out.println("Data at index " + i + " in Hash Table:\nID       Value\n--------------");
			Node temp = table[i];
			while (temp != null) {
				System.out.println(temp.getKey() + "       " + temp.getValue());
				temp = temp.getNext();
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the Hash Table:");
		int n = scan.nextInt();
		int choice = 0;
		System.out.println("Enter the combination of data type you want to be added in hash table");
		System.out.println("1. Integer and String\n2. String and Double");
		int n1 = scan.nextInt();
		if (n1 == 1) {
			int val;
			String name;
			SeparateChaining<Integer, String> sc = new SeparateChaining<Integer, String>(n);
			do {
				System.out.println("1. Insertion");
				System.out.println("2. Deletion");
				System.out.println("3. Searching");
				System.out.println("4. Display");
				System.out.println("5. Exit");
				System.out.println("Enter your choice:");
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter the key:");
					val = scan.nextInt();
					scan.nextLine();
					System.out.println("Enter the value:");
					name = scan.nextLine();
					sc.insert(val, name);
					break;
				case 2:
					System.out.println("Enter the key to perform deletion:");
					val = scan.nextInt();
					String del = sc.delete(val);
					break;
				case 3:
					System.out.println("Enter the key to search:");
					val = scan.nextInt();
					sc.search(val);
					break;
				case 4:
					sc.display();
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid option");
					break;
				}

			} while (choice < 5);
		}
		if (n1 == 2) {
			String val;
			double d;
			SeparateChaining<String, Double> sc = new SeparateChaining<String, Double>(n);
			do {
				System.out.println("1. Insertion");
				System.out.println("2. Deletion");
				System.out.println("3. Searching");
				System.out.println("4. Display");
				System.out.println("5. Exit");
				System.out.println("Enter your choice:");
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter the key:");
					scan.nextLine();
					val = scan.nextLine();
					System.out.println("Enter the value");
					d = scan.nextDouble();
					sc.insert(val, d);
					break;
				case 2:
					System.out.println("Enter the key to perform deletion:");
					scan.nextLine();
					val = scan.nextLine();
					Double del = sc.delete(val);
					break;
				case 3:
					System.out.println("Enter the key to search:");
					scan.nextLine();
					val = scan.nextLine();
					sc.search(val);
					break;
				case 4:
					sc.display();
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid option");
					break;
				}

			} while (choice < 5);
		}

	}

}
