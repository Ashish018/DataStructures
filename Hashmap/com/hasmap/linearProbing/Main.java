package com.hasmap.linearProbing;

import java.util.*;

class KeyValue<T> {
	T key;
	String name;
	int age;

	public KeyValue() {
	}

	public KeyValue(T key, String name, int age) {
		this.key = key;
		this.name = name;
		this.age = age;
	}
}

class LinearProbing<T> {

	private int currentSize;
	int maxSize;

	KeyValue<T>[] keyvalue;

	LinearProbing() {
	}

	public LinearProbing(int capacity) {
		currentSize = 0;
		maxSize = capacity;
		keyvalue = new KeyValue[maxSize];
	}

	public boolean insert(T key, String name, int age) {
		// Fill your code here
		int i = hash(key), count = 0;
		while (count != maxSize) {
			if (keyvalue[i] == null) {
				keyvalue[i] = new KeyValue(key, name, age);
				return true;
			}
			i = (i + 1) % maxSize;
			count++;
		}
		return false;

	}

	private int hash(T key) {
		return key.hashCode() % maxSize;
	}

	int probe(int index, T key) {
		return 0;
	}

	public KeyValue get(T key) {
		int i = hash(key);
		int count = 0;
		while (count != maxSize) {
			if (keyvalue[i] != null && keyvalue[i].key.equals(key)) {
				System.out.println(
						"Voter ID : " + key + "\nName     : " + keyvalue[i].name + "\nAge      : " + keyvalue[i].age);
				return keyvalue[i];
			}
			i = (i + 1) % maxSize;
			count++;
		}
		System.out.println(key + " is not present in hash table");
		return null;

	}

	public boolean delete(T key) {
		int i = hash(key);
		int count = 0;
		while (count != maxSize) {
			if (keyvalue[i] != null && keyvalue[i].key.equals(key)) {
				System.out.println(key + " deleted from Hash Table");
				keyvalue[i] = null;
				return true;
			}
			i = (i + 1) % maxSize;
			count++;
		}
		System.out.println(key + " is not available in Hash Table");
		return false;
	}

	public KeyValue[] getList() {
		return keyvalue;
	}

}

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Table Size:");
		/** maxSizeake object of LinearProbingHashTable **/
		LinearProbing lpht = new LinearProbing(scan.nextInt());
		System.out.println("Enter the data type of the key to be added in Hash Table");
		System.out.println("1.Integer");
		System.out.println("2.String");
		int nch = scan.nextInt();
		if (nch == 1) {
//           LinearProbing<Integer> lp=new LinearProbing<>();
			KeyValue<Integer> keyObj = new KeyValue<>();
			while (true) {
				System.out.println("1. Insert");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Display");
				System.out.println("5. Exit\nEnter your choice:");
				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					scan.nextLine();
					System.out.println("Enter the key value:");
					int key = Integer.parseInt(scan.nextLine());
					System.out.println("Enter the Name:");
					String name = scan.nextLine();
					System.out.println("Enter the Age:");
					int age = Integer.parseInt(scan.nextLine());
					// Fill your code here
					boolean ins = lpht.insert(key, name, age);
					if (!ins)
						System.out.println("Can't Insert. Hash Table is full!");
					break;
				case 2:
					scan.nextLine();
					System.out.println("Enter the key value:");
					int k1 = scan.nextInt();
					// Fill your code here
					lpht.delete(k1);
					break;
				case 3:
					scan.nextLine();
					System.out.println("Enter the key value:");
					int k = scan.nextInt();
					KeyValue n = lpht.get(k);
					// Fill your code here
					break;
				case 4:
					System.out.printf("%-11s%-14s%-11s%s\n", "ID", "Name", "Age", "Index");
					System.out.println("-----------------------------------------");
					int j = 0;
					for (KeyValue kv : lpht.getList()) {
						if (kv != null)
							System.out.printf("%-11d%-15s%-14d%d\n", kv.key, kv.name, kv.age, j);
						j++;
					}
					System.out.println();
					// Fill your code here
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println("Wrong Entry \n ");
					break;
				}
			}
		} else if (nch == 2) {
			LinearProbing<String> lp = new LinearProbing<>();
			KeyValue<Integer> keyObj = new KeyValue<>();
			while (true) {
				System.out.println("1. Insert");
				System.out.println("2. Delete");
				System.out.println("3. Search");
				System.out.println("4. Display");
				System.out.println("5. exit\nEnter your choice:");

				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					scan.nextLine();
					System.out.println("Enter the key value:");
					String key = scan.nextLine();
					System.out.println("Enter the Name:");
					String name = scan.nextLine();
					System.out.println("Enter the Age:");
					int age = Integer.parseInt(scan.nextLine());
					// Fill your code here
					boolean ins = lpht.insert(key, name, age);
					if (!ins)
						System.out.println("Can't Insert. Hash Table is full!");
					break;
				case 2:
					scan.nextLine();
					System.out.println("Enter the key value:");
					String k1 = scan.nextLine();
					// Fill your code here
					lpht.delete(k1);
					break;
				case 3:
					scan.nextLine();
					System.out.println("Enter the key value:");
					String k = scan.nextLine();
					KeyValue n = lpht.get(k);
					// Fill your code here
					break;
				case 4:

					System.out.printf("%-11s%-14s%-11s%s\n", "ID", "Name", "Age", "Index");
					System.out.println("-----------------------------------------");
					int j = 0;
					// Fill your code here
					for (KeyValue kv : lpht.getList()) {
						if (kv != null)
							System.out.printf("%-11s%-15s%-14d%d\n", kv.key, kv.name, kv.age, j);
						j++;
					}
					System.out.println();
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Wrong Entry \n ");
					break;
				}

			}
		}

	}

}
